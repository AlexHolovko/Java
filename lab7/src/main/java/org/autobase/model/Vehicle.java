package org.autobase.model;

import lombok.Data;

@Data
public class Vehicle {
    private String model;
    private double maxLoadKg;
    private boolean broken;
    private boolean available;

    public Vehicle(String model, double maxLoadKg) {
        this.model = model;
        this.maxLoadKg = maxLoadKg;
        this.broken = false;
        this.available = true;
    }

    public void markBroken() {
        this.broken = true;
        this.available = false;
    }

    public void markAvailable() {
        this.broken = false;
        this.available = true;
    }
}
