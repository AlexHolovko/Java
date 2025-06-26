package org.autobase.service;

import org.autobase.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Dispatcher {
    private final List<Driver> drivers;
    private final List<Vehicle> vehicles;
    private final List<Trip> tripLog = new ArrayList<>();

    public Dispatcher(List<Driver> drivers, List<Vehicle> vehicles) {
        this.drivers = drivers;
        this.vehicles = vehicles;
    }

    public Optional<Trip> assignTrip(CargoRequest request) {
        Optional<Driver> availableDriver = drivers.stream()
                .filter(Driver::isAvailable)
                .filter(d -> d.getExperienceYears() >= requiredExperience(request))
                .findFirst();

        Optional<Vehicle> suitableVehicle = vehicles.stream()
                .filter(Vehicle::isAvailable)
                .filter(v -> v.getMaxLoadKg() >= request.getCargoWeightKg())
                .min(Comparator.comparingDouble(Vehicle::getMaxLoadKg));

        if (availableDriver.isPresent() && suitableVehicle.isPresent()) {
            Driver driver = availableDriver.get();
            Vehicle vehicle = suitableVehicle.get();

            driver.markBusy();
            vehicle.setAvailable(false);

            Trip trip = new Trip(driver, vehicle, request);

            boolean brokeDown = new Random().nextDouble() < 0.2;
            trip.completeTrip(brokeDown);

            logTrip(trip);
            tripLog.add(trip);
            return Optional.of(trip);
        }
        return Optional.empty();
    }

    private int requiredExperience(CargoRequest req) {
        int requiredExperience;
        switch (req.getCargoType().toLowerCase()) {
            case "hazardous":
                requiredExperience = 5;
                break;
            case "fragile":
                requiredExperience = 3;
                break;
            default:
                requiredExperience = 1;
        }
        return requiredExperience;
    }

    private void logTrip(Trip trip) {
        try (FileWriter writer = new FileWriter("src/main/resources/log.txt", true)) {
            writer.write("Trip completed:\n");
            writer.write("Driver: " + trip.getDriver().getName() + "\n");
            writer.write("Destination: " + trip.getRequest().getDestination() + "\n");
            writer.write("Vehicle: " + trip.getVehicle().getModel() + "\n");
            writer.write("Cargo: " + trip.getRequest().getCargoType() + ", " +
                    trip.getRequest().getCargoWeightKg() + "kg\n");
            writer.write("Broken: " + trip.isVehicleBroken() + "\n");
            writer.write("Payment: " + trip.getPayment() + "\n");
            writer.write("----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> getTopEarners() {
        return drivers.stream()
                .sorted(Comparator.comparingDouble(Driver::getTotalEarnings).reversed())
                .collect(Collectors.toList());
    }

    public Map<String, Long> countDeliveriesPerDestination() {
        return tripLog.stream()
                .map(t -> t.getRequest().getDestination())
                .collect(Collectors.groupingBy(dest -> dest, Collectors.counting()));
    }

    public Map<String, Long> countDeliveriesByDriver() {
        return tripLog.stream()
                .map(t -> t.getDriver().getName())
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }
}
