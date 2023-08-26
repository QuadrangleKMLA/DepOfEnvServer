package com.quadrangle.depOfEnv.entity.management;

import jakarta.persistence.*;

@Entity
@Table(name = "daily", uniqueConstraints = {@UniqueConstraint(name = "roomNumber", columnNames = {"room_number"})})
public class Daily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_number")
    private ERoomNumber roomNumber;

    @Column(name = "recycling")
    private Boolean recycling;

    @Column(name = "box")
    private Boolean box;

    @Column(name = "waste")
    private Boolean waste;

    public Daily() {}

    public Daily(ERoomNumber roomNumber) {
        this.roomNumber = roomNumber;
        this.recycling = false;
        this.box = false;
        this.waste = false;
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

    public Boolean getRecycling() {
        return recycling;
    }

    public void setRecycling(Boolean recycling) {
        this.recycling = recycling;
    }

    public Boolean getBox() {
        return box;
    }

    public void setBox(Boolean box) {
        this.box = box;
    }

    public Boolean getWaste() {
        return waste;
    }

    public void setWaste(Boolean waste) {
        this.waste = waste;
    }

    public void resetStatus() {
        this.recycling = false;
        this.box = false;
        this.waste = false;
    }
}
