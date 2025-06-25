package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        try (Scanner file = new Scanner(new File("flight.txt"))) {
            int fuelCapacity = file.nextInt();
            int distanceAB = file.nextInt();
            int distanceBC = file.nextInt();
            int weight = file.nextInt();

            int fuelPerKm;
            if (weight <= 500) fuelPerKm = 1;
            else if (weight <= 1000) fuelPerKm = 4;
            else if (weight <= 1500) fuelPerKm = 7;
            else if (weight <= 2000) fuelPerKm = 9;
            else {
                System.out.println("Cargo is too heavy. The plane cannot take off.");
                return;
            }

            int fuelNeededToAB = distanceAB * fuelPerKm;
            int fuelNeededToBC = distanceBC * fuelPerKm;

            if (fuelCapacity < fuelNeededToAB) {
                System.out.println("Not enough fuel to fly from A to B.");
            } else if (fuelCapacity - fuelNeededToAB < fuelNeededToBC) {
                int refill = fuelNeededToBC - (fuelCapacity - fuelNeededToAB);
                System.out.println("Refuel required at point B: " + refill + " liters.");
            } else {
                System.out.println("Flight possible without refueling.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: flight.txt");
        }
    }
}
