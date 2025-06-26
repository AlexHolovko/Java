package org.autobase.model;

import lombok.Data;

@Data
public class Trip {
    private Driver driver;
    private Vehicle vehicle;
    private CargoRequest request;
    private boolean completed;
    private boolean vehicleBroken;
    private double payment;

    public Trip(Driver driver, Vehicle vehicle, CargoRequest request) {
        this.driver = driver;
        this.vehicle = vehicle;
        this.request = request;
        this.completed = false;
        this.vehicleBroken = false;
    }

    public void completeTrip(boolean vehicleBrokeDown) {
        this.vehicleBroken = vehicleBrokeDown;
        this.completed = true;
        this.payment = calculatePayment();
        driver.addEarnings(payment);
        driver.markAvailable();
        if (vehicleBrokeDown) {
            vehicle.markBroken();
        } else {
            vehicle.markAvailable();
        }
    }

    private double calculatePayment() {
        return request.getRoadLengthKm() * 2.5 + request.getCargoWeightKg() * 0.5;
    }
}
