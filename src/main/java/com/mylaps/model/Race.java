package com.mylaps.model;

import java.time.LocalTime;

public class Race {

    private int kartNo;
    private LocalTime passingTime;

    public Race() {
    }

    public Race(int kartNo, LocalTime passingTime) {
        this.kartNo = kartNo;
        this.passingTime = passingTime;
    }

    public int getKartNo() {
        return kartNo;
    }

    public void setKartNo(int kartNo) {
        this.kartNo = kartNo;
    }

    public LocalTime getPassingTime() {
        return passingTime;
    }

    public void setPassingTime(LocalTime passingTime) {
        this.passingTime = passingTime;
    }

    @Override
    public String toString() {
        return "Race{" +
                "kartNo=" + kartNo +
                ", passingTime=" + passingTime +
                '}';
    }
}
