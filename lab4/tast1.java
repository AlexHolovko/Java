package lab4;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Equipment implements Serializable, Comparable<Equipment> {
    private String name;
    private String issuedTo;
    private int quantity;
    private boolean isReturned;
    private LocalDate issueDate;

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

    public String getName() { return name; }
    public boolean isReturned() { return isReturned; }

    @Override
    public int compareTo(Equipment other) {
        int cmp = this.name.compareToIgnoreCase(other.name);
        if (cmp != 0) return cmp;
        return Integer.compare(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Issued To: %s | Quantity: %d | %s | Issue Date: %s",
                name, issuedTo, quantity,
                isReturned ? "Returned" : "Issued",
                issueDate != null ? issueDate : "-");
    }

    public static void saveToFile(List<Equipment> equipments, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(equipments);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Equipment> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Equipment>) ois.readObject();
        }
    }
}

public class tast1 {
    public static void main(String[] args) throws Exception {
        List<Equipment> equipments = new ArrayList<>();
        equipments.add(new Equipment("Microscope", "Ivanenko I.I.", 2, false, LocalDate.of(2025,6,1)));
        equipments.add(new Equipment("Flask", 10));
        equipments.add(new Equipment("Thermometer", "Petrenko O.O.", 3, false, LocalDate.of(2025,6,2)));

        Equipment.saveToFile(equipments, "equipment.dat");

        List<Equipment> loadedEquipments = Equipment.loadFromFile("equipment.dat");

        Collections.sort(loadedEquipments);

        loadedEquipments.forEach(System.out::println);
    }
}
