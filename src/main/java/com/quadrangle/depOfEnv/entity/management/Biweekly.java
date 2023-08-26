package com.quadrangle.depOfEnv.entity.management;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "biweekly", uniqueConstraints = {@UniqueConstraint(name = "roomNumber", columnNames = {"room_number"})})
public class Biweekly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_number")
    private ERoomNumber roomNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Date> dates = new HashMap<>();

    private static boolean flag = false;

    public Biweekly() {}

    public Biweekly(ERoomNumber roomNumber) {
        this.roomNumber = roomNumber;

        dates.put("Week1-Day1", null);
        dates.put("Week1-Day2", null);
        dates.put("Week2-Day1", null);
        dates.put("Week2-Day2", null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ERoomNumber getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(ERoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Map<String, Date> getDates() {
        return dates;
    }

    public void insertWeeklyRecord(Map<String, Date> dateMap) {
        if (!flag) {
            dates.replace("Week1-Day1", dateMap.get("Day1"));
            dates.replace("Week1-Day2", dateMap.get("Day2"));

        } else {
            dates.replace("Week2-Day1", dateMap.get("Day1"));
            dates.replace("Week2-Day2", dateMap.get("Day2"));

        }
    }

    public void resetDates() {
        if (!flag) {
            dates.replace("Week1-Day1", null);
            dates.replace("Week1-Day2", null);

        } else {
            dates.replace("Week2-Day1", null);
            dates.replace("Week2-Day2", null);

        }
        flag = !flag;
    }
}
