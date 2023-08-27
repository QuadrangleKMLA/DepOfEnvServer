package com.quadrangle.depOfEnv.controller;

import com.quadrangle.depOfEnv.Functions;
import com.quadrangle.depOfEnv.entity.management.*;
import com.quadrangle.depOfEnv.exception.exception.ResourceNotFoundException;
import com.quadrangle.depOfEnv.repository.management.BiweeklyRepository;
import com.quadrangle.depOfEnv.repository.management.DailyRepository;
import com.quadrangle.depOfEnv.repository.management.FinalRepository;
import com.quadrangle.depOfEnv.repository.management.WeeklyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
    private final DailyRepository dailyRepository;
    private final WeeklyRepository weeklyRepository;
    private final BiweeklyRepository biweeklyRepository;
    private final FinalRepository finalRepository;

    @Autowired
    public ManagementController(DailyRepository dailyRepository, WeeklyRepository weeklyRepository, BiweeklyRepository biweeklyRepository, FinalRepository finalRepository) {
        this.dailyRepository = dailyRepository;
        this.weeklyRepository = weeklyRepository;
        this.biweeklyRepository = biweeklyRepository;
        this.finalRepository = finalRepository;
    }

    @GetMapping("/daily")
    public ResponseEntity<?> getDailyList() {
        return new ResponseEntity<>(dailyRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/weekly")
    public ResponseEntity<?> getWeeklyList() {
        return new ResponseEntity<>(weeklyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/biweekly")
    public ResponseEntity<?> getBiweeklyList() {
        return new ResponseEntity<>(biweeklyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/final")
    public ResponseEntity<?> getFinalList() {
        return new ResponseEntity<>(finalRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/daily/{roomNumber}/{trashType}")
    public ResponseEntity<?> updateDaily(@PathVariable(value = "roomNumber") Integer roomNumber, @PathVariable(value = "trashType") String trashType) {
        String roomID = "ROOM_NUMBER_" + roomNumber;

        Daily daily = dailyRepository.findByRoomNumber(Enum.valueOf(ERoomNumber.class, roomID)).orElseThrow(() -> new ResourceNotFoundException("Room with number: " + roomNumber + " not found."));

        switch (trashType) {
            case "recycling" -> daily.setRecycling(true);
            case "box" -> daily.setBox(true);
            case "waste" -> daily.setWaste(true);
            default -> throw new ResourceNotFoundException("Trash type of name: " + trashType + " not found.");
        }

        return new ResponseEntity<>(dailyRepository.save(daily), HttpStatus.OK);
    }

    @PutMapping("/evaluate")
    public ResponseEntity<?> evaluateRecords(@RequestBody Map<String, Boolean> evaluationList) throws ParseException {
        ERoomNumber[] roomNumbers = ERoomNumber.values();
        Map<String, ArrayList<String>> result = new HashMap<>();
        result.put("Daily Passed", new ArrayList<>());
        result.put("Weekly Passed", new ArrayList<>());
        result.put("Point Recommended", new ArrayList<>());

        for (ERoomNumber roomNumber : roomNumbers) {
            Daily daily = dailyRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room with number: " + roomNumber.toString() + " not found."));
            Weekly weekly = weeklyRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room with number: " + roomNumber.toString() + " not found."));
            Biweekly biweekly = biweeklyRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room with number: " + roomNumber.toString() + " not found."));
            Final finalRecord = finalRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room with number: " + roomNumber.toString() + " not found."));

            if (daily.getWaste() && daily.getBox() && daily.getRecycling() && evaluationList.get("Daily To Weekly")) {
                weekly.insertDate(Functions.getTimeStamp());
                daily.resetStatus();

                dailyRepository.save(daily);
                weekly = weeklyRepository.save(weekly);

                ArrayList<String> rooms = result.get("Daily Passed");
                rooms.add(roomNumber.toString());

                result.replace("Daily Passed", rooms);
            } else if (evaluationList.get("Daily To Weekly")) {
                daily.resetStatus();
                dailyRepository.save(daily);
            }

            if (weekly.getDates().size() == 2 && evaluationList.get("Weekly to Biweekly")) {
                List<Date> dates = weekly.getDates();
                Map<String, Date> dateMap = new HashMap<>();

                dateMap.put("Day1", dates.remove(0));
                dateMap.put("Day2", dates.remove(0));

                biweekly.insertWeeklyRecord(dateMap);
                weekly.resetDates();

                weeklyRepository.save(weekly);
                biweekly = biweeklyRepository.save(biweekly);

                ArrayList<String> rooms = result.get("Weekly Passed");
                rooms.add(roomNumber.toString());

                result.replace("Weekly Passed", rooms);
            } else if (evaluationList.get("Weekly To Biweekly")) {
                weekly.resetDates();
                weeklyRepository.save(weekly);
            }

            if (!biweekly.getDates().containsValue(null) && !biweekly.getDates().isEmpty() && evaluationList.get("Biweekly To Final")) {
                finalRecord.setQualified(true);
                biweekly.resetDates();

                biweeklyRepository.save(biweekly);
                finalRepository.save(finalRecord);

                ArrayList<String> rooms = result.get("Point Recommended");
                rooms.add(roomNumber.toString());

                result.replace("Point Recommended", rooms);
            } else if (evaluationList.get("Biweekly To Final")) {
                biweekly.resetDates();
                biweeklyRepository.save(biweekly);
            }

            if (evaluationList.get("Final Reset?")) {
                finalRecord.resetQualification();
                finalRepository.save(finalRecord);
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
