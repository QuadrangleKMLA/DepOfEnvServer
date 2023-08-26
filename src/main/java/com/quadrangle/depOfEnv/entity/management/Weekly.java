package com.quadrangle.depOfEnv.entity.management;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "weekly", uniqueConstraints = {@UniqueConstraint(name = "roomNumber", columnNames = {"room_number"})})
public class Weekly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_number")
    private ERoomNumber roomNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Date> dates = new ArrayList<>(2);

    public Weekly() {}

    public Weekly(ERoomNumber roomNumber) {
        this.roomNumber = roomNumber;
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

    public List<Date> getDates() {
        return this.dates;
    }

    public void insertDate(Date date) {
        if (!(dates.size() == 2)) {
            dates.add(date);
        }
    }

    public void resetDates() {
        dates.clear();
    }
}
