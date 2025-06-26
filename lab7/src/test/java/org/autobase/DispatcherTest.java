package org.autobase;

import org.autobase.model.*;
import org.autobase.service.Dispatcher;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DispatcherTest {

    @Test
    public void testSuccessfulAssignment() {
        Driver driver = new Driver("Alex", 5);
        Vehicle vehicle = new Vehicle("Volvo", 1000);
        CargoRequest request = new CargoRequest("Kyiv", 800, "general", 100);

        Dispatcher dispatcher = new Dispatcher(Arrays.asList(driver), Arrays.asList(vehicle));
        Optional<Trip> tripOptional = dispatcher.assignTrip(request);

        assertTrue(tripOptional.isPresent());

        Trip trip = tripOptional.get();
        assertTrue(trip.isCompleted());
        assertEquals("Kyiv", trip.getRequest().getDestination());
    }

    @Test
    public void testDriverUnavailable() {
        Driver driver = new Driver("Olga", 1);
        driver.markBusy(); // Водій зайнятий
        Vehicle vehicle = new Vehicle("MAN", 2000);
        CargoRequest request = new CargoRequest("Lviv", 500, "general", 50);

        Dispatcher dispatcher = new Dispatcher(Arrays.asList(driver), Arrays.asList(vehicle));
        Optional<Trip> trip = dispatcher.assignTrip(request);

        assertFalse(trip.isPresent());
    }

    @Test
    public void testVehicleTooSmall() {
        Driver driver = new Driver("Ivan", 4);
        Vehicle vehicle = new Vehicle("SmallTruck", 300); // Занадто маленьке авто
        CargoRequest request = new CargoRequest("Odesa", 500, "fragile", 200);

        Dispatcher dispatcher = new Dispatcher(Arrays.asList(driver), Arrays.asList(vehicle));
        Optional<Trip> trip = dispatcher.assignTrip(request);

        assertFalse(trip.isPresent());
    }
}
