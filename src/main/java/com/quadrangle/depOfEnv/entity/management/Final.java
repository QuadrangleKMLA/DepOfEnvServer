package com.quadrangle.depOfEnv.entity.management;

import jakarta.persistence.*;

@Entity
@Table(name = "final", uniqueConstraints = {@UniqueConstraint(name = "roomNumber", columnNames = {"room_number"})})
public class Final {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_number")
    private ERoomNumber roomNumber;

    @Column(name = "qualified")
    private Boolean qualified = false;

    public Final() {}

    public Final(ERoomNumber roomNumber) {
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

    public Boolean getQualified() {
        return qualified;
    }

    public void setQualified(Boolean qualified) {
        this.qualified = qualified;
    }

    public void resetQualification() {
        this.qualified = false;
    }
}
