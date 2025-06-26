package org.autobase;

import org.autobase.model.*;
import org.autobase.service.Dispatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        List<Driver> drivers = Arrays.asList(
                new Driver("Ivan", 6),
                new Driver("Olena", 3),
                new Driver("Stepan", 1)
        );

        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("Volvo", 1500),
                new Vehicle("MAN", 1000),
                new Vehicle("Sprinter", 600)
        );

        Dispatcher dispatcher = new Dispatcher(drivers, vehicles);

        List<CargoRequest> requests = Arrays.asList(
                new CargoRequest("Kyiv", 800, "general", 100),
                new CargoRequest("Lviv", 1200, "hazardous", 200),
                new CargoRequest("Odesa", 500, "fragile", 150)
        );

        for (CargoRequest request : requests) {
            Optional<Trip> trip = dispatcher.assignTrip(request);
            if (trip.isPresent()) {
                Trip t = trip.get();
                System.out.println("Заявка оброблена:");
                System.out.println("  Водій: " + t.getDriver().getName());
                System.out.println("  Напрямок: " + t.getRequest().getDestination());
                System.out.println("  Автомобіль: " + t.getVehicle().getModel());
                System.out.println("  Оплата: " + t.getPayment());
                System.out.println("  Поломка: " + (t.isVehicleBroken() ? "так" : "ні"));
            } else {
                System.out.println("Не вдалося призначити рейс для: " + request.getDestination());
            }
            System.out.println("----------");
        }

        System.out.println("\nСтатистика:");
        Map<String, Long> deliveriesByDriver = dispatcher.countDeliveriesByDriver();
        for (String driver : deliveriesByDriver.keySet()) {
            System.out.println("  Водій " + driver + " виконав: " + deliveriesByDriver.get(driver) + " рейс(ів)");
        }

        Map<String, Long> deliveriesByCity = dispatcher.countDeliveriesPerDestination();
        for (String city : deliveriesByCity.keySet()) {
            System.out.println("  До міста " + city + " доставлено: " + deliveriesByCity.get(city) + " вантаж(ів)");
        }

        List<Driver> topEarners = dispatcher.getTopEarners();
        System.out.println("\nВодії з найбільшим заробітком:");
        for (Driver d : topEarners) {
            System.out.println("  " + d.getName() + ": " + d.getTotalEarnings() + " грн");
        }

        System.out.println("\nДеталі подорожей збережено в: src/main/resources/log.txt");
    }
}