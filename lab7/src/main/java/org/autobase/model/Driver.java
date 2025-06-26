package org.autobase.model;

import lombok.Data;

@Data
public class Driver {
    private String name;
    private int experienceYears;
    private double totalEarnings;
    private boolean available;
    private int totalTrips;

    public Driver(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.available = true;
        this.totalEarnings = 0;
        this.totalTrips = 0;
    }

    public void addEarnings(double amount) {
        this.totalEarnings += amount;
        this.totalTrips++;
    }

    public void markBusy() {
        this.available = false;
    }

    public void markAvailable() {
        this.available = true;
    }
}
