package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Equipment {
    private String name;
    private String issuedTo;
    private int quantity;
    private boolean isReturned;
    private LocalDate issueDate;

    // Constructors
    public Equipment(String name, String issuedTo, int quantity, boolean isReturned, LocalDate issueDate) {
        this.name = name;
        this.issuedTo = issuedTo;
        this.quantity = quantity;
        this.isReturned = isReturned;
        this.issueDate = issueDate;
    }

    public Equipment(String name, int quantity) {
        this(name, "", quantity, true, null);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    // String representation
    @Override
    public String toString() {
        return String.format("Name: %s | Issued To: %s | Quantity: %d | %s | Issue Date: %s",
                name, issuedTo, quantity,
                isReturned ? "Returned" : "Issued",
                (issueDate != null ? issueDate : "-")
        );
    }

    // Check availability
    public boolean isAvailable() {
        return isReturned;
    }

    // Search by name
    public boolean hasName(String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase());
    }
}

public class task1 {
    public static void main(String[] args) {
        List<Equipment> equipments = new ArrayList<>();

        equipments.add(new Equipment("Microscope", "Ivanenko I.I.", 2, false, LocalDate.of(2025, 6, 1)));
        equipments.add(new Equipment("Flask", 10));
        equipments.add(new Equipment("Thermometer", "Petrenko O.O.", 3, false, LocalDate.of(2025, 6, 2)));
        equipments.add(new Equipment("Stand", 5));
        equipments.add(new Equipment("Test Tube", 50));
        equipments.add(new Equipment("Pipette", "Syderenko L.L.", 10, false, LocalDate.of(2025, 6, 3)));

        // a) Display all equipment
        System.out.println("All equipment:");
        equipments.forEach(System.out::println);

        // b) Display available equipment
        System.out.println("\nAvailable equipment:");
        equipments.stream().filter(Equipment::isAvailable).forEach(System.out::println);

        // b) Display issued equipment
        System.out.println("\nIssued equipment:");
        equipments.stream().filter(e -> !e.isAvailable()).forEach(System.out::println);

        // c) Search by name
        String keyword = "microscope";
        System.out.println("\nSearch by name: '" + keyword + "'");
        List<Equipment> found = equipments.stream()
                .filter(e -> e.hasName(keyword))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            System.out.println("Nothing found.");
        } else {
            found.forEach(System.out::println);
        }
    }
}