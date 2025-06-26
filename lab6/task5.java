package lab6;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

enum AccreditationLevel {
    GENERAL_EDUCATION,
    GYMNASIUM,
    LYCEUM;

    public static AccreditationLevel fromString(String s) {
        switch (s.toLowerCase()) {
            case "загальноосвітня":
            case "general_education":
                return GENERAL_EDUCATION;
            case "гімназія":
            case "gymnasium":
                return GYMNASIUM;
            case "ліцей":
            case "lyceum":
                return LYCEUM;
            default:
                return GENERAL_EDUCATION;
        }
    }
}

abstract class Building {
    protected String address;

    public Building(String address) {
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public abstract void parseAndSetFields(String input);

    public abstract void printInfo();
}

class ResidentialBuilding extends Building {
    private int residentsCount;

    public ResidentialBuilding(String address, int residentsCount) {
        super(address);
        this.residentsCount = residentsCount;
    }

    public ResidentialBuilding() {
        super("");
    }

    public void setResidentsCount(int count) {
        this.residentsCount = count;
    }

    public int getResidentsCount() {
        return residentsCount;
    }

    @Override
    public void parseAndSetFields(String input) {
        Map<String, String> map = Utils.parseInput(input);
        if (map.containsKey("address")) setAddress(map.get("address"));
        if (map.containsKey("residents")) residentsCount = Integer.parseInt(map.get("residents"));
    }

    @Override
    public void printInfo() {
        System.out.printf("Residential Building at %s, Residents: %d%n", address, residentsCount);
    }
}

class Store extends Building {
    private int departmentsCount;

    public Store(String address, int departmentsCount) {
        super(address);
        this.departmentsCount = departmentsCount;
    }

    public Store() {
        super("");
    }

    public void setDepartmentsCount(int count) {
        this.departmentsCount = count;
    }

    public int getDepartmentsCount() {
        return departmentsCount;
    }

    @Override
    public void parseAndSetFields(String input) {
        Map<String, String> map = Utils.parseInput(input);
        if (map.containsKey("address")) setAddress(map.get("address"));
        if (map.containsKey("departments")) departmentsCount = Integer.parseInt(map.get("departments"));
    }

    @Override
    public void printInfo() {
        System.out.printf("Store at %s, Departments: %d%n", address, departmentsCount);
    }
}

class Hospital extends Building {
    private int bedsCount;

    public Hospital(String address, int bedsCount) {
        super(address);
        this.bedsCount = bedsCount;
    }

    public Hospital() {
        super("");
    }

    public void setBedsCount(int count) {
        this.bedsCount = count;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    @Override
    public void parseAndSetFields(String input) {
        Map<String, String> map = Utils.parseInput(input);
        if (map.containsKey("address")) setAddress(map.get("address"));
        if (map.containsKey("beds")) bedsCount = Integer.parseInt(map.get("beds"));
    }

    @Override
    public void printInfo() {
        System.out.printf("Hospital at %s, Beds: %d%n", address, bedsCount);
    }
}

class School extends Building {
    private AccreditationLevel accreditationLevel;
    private int studentsCount;

    public School(String address, AccreditationLevel level, int studentsCount) {
        super(address);
        this.accreditationLevel = level;
        this.studentsCount = studentsCount;
    }

    public School() {
        super("");
        this.accreditationLevel = AccreditationLevel.GENERAL_EDUCATION;
        this.studentsCount = 0;
    }

    public void setAccreditationLevel(AccreditationLevel level) {
        this.accreditationLevel = level;
    }

    public void setStudentsCount(int count) {
        this.studentsCount = count;
    }

    public AccreditationLevel getAccreditationLevel() {
        return accreditationLevel;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    @Override
    public void parseAndSetFields(String input) {
        Map<String, String> map = Utils.parseInput(input);
        if (map.containsKey("address")) setAddress(map.get("address"));
        if (map.containsKey("accreditation")) accreditationLevel = AccreditationLevel.fromString(map.get("accreditation"));
        if (map.containsKey("students")) studentsCount = Integer.parseInt(map.get("students"));
    }

    @Override
    public void printInfo() {
        System.out.printf("School at %s, Accreditation: %s, Students: %d%n", address, accreditationLevel, studentsCount);
    }
}

class Street {
    private String name;
    private List<Building> buildings;

    public Street(String name) {
        this.name = name;
        this.buildings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBuilding(Building b) {
        buildings.add(b);
    }

    public void removeBuilding(Building b) {
        buildings.remove(b);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void printStreetInfo() {
        System.out.println("Street: " + name);
        for (Building b : buildings) {
            b.printInfo();
        }
    }

    public List<Store> findStoresNearResidential(String residentialAddress, int radius, int departmentCount) {
        List<Store> result = new ArrayList<>();
        int idx = -1;
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (b instanceof ResidentialBuilding && b.getAddress().equalsIgnoreCase(residentialAddress)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return result;
        int start = Math.max(0, idx - radius);
        int end = Math.min(buildings.size() - 1, idx + radius);
        for (int i = start; i <= end; i++) {
            Building b = buildings.get(i);
            if (b instanceof Store) {
                Store s = (Store) b;
                if (s.getDepartmentsCount() == departmentCount) {
                    result.add(s);
                }
            }
        }
        return result;
    }
}

class Utils {
    public static Map<String, String> parseInput(String input) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = input.split(";");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].trim().toLowerCase(), kv[1].trim());
            }
        }
        return map;
    }
}

class Menu {
    private Street street;
    private Scanner scanner;

    public Menu(Street street) {
        this.street = street;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Show street info");
            System.out.println("2. Add building");
            System.out.println("3. Remove building");
            System.out.println("4. Find stores near residential building");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    street.printStreetInfo();
                    break;
                case "2":
                    addBuildingMenu();
                    break;
                case "3":
                    removeBuildingMenu();
                    break;
                case "4":
                    findStoresMenu();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addBuildingMenu() {
        System.out.print("Choose building type (residential/store/hospital/school): ");
        String type = scanner.nextLine().toLowerCase();
        Building b = null;
        System.out.println("Enter fields as key=value separated by ';' (e.g. address=123; residents=50):");
        String input = scanner.nextLine();
        switch (type) {
            case "residential":
                b = new ResidentialBuilding();
                break;
            case "store":
                b = new Store();
                break;
            case "hospital":
                b = new Hospital();
                break;
            case "school":
                b = new School();
                break;
            default:
                System.out.println("Unknown building type");
                return;
        }
        b.parseAndSetFields(input);
        street.addBuilding(b);
        System.out.println("Building added");
    }

    private void removeBuildingMenu() {
        System.out.print("Enter address of building to remove: ");
        String addr = scanner.nextLine();
        Building toRemove = null;
        for (Building b : street.getBuildings()) {
            if (b.getAddress().equalsIgnoreCase(addr)) {
                toRemove = b;
                break;
            }
        }
        if (toRemove != null) {
            street.removeBuilding(toRemove);
            System.out.println("Building removed");
        } else {
            System.out.println("Building not found");
        }
    }

    private void findStoresMenu() {
        System.out.print("Enter residential building address: ");
        String resAddr = scanner.nextLine();
        System.out.print("Enter radius (number of buildings to search around): ");
        int radius = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter required departments count in store: ");
        int depCount = Integer.parseInt(scanner.nextLine());
        List<Store> foundStores = street.findStoresNearResidential(resAddr, radius, depCount);
        if (foundStores.isEmpty()) {
            System.out.println("No stores found with specified criteria.");
        } else {
            System.out.println("Found stores:");
            foundStores.forEach(Store::printInfo);
        }
    }
}

public class task5 {
    public static void main(String[] args) {
        Street street = new Street("Main Street");
        street.addBuilding(new ResidentialBuilding("1A", 50));
        street.addBuilding(new Store("2B", 1));
        street.addBuilding(new Store("3C", 5));
        street.addBuilding(new Hospital("4D", 100));
        street.addBuilding(new School("5E", AccreditationLevel.GYMNASIUM, ThreadLocalRandom.current().nextInt(200, 500)));

        Menu menu = new Menu(street);
        menu.start();
    }
}
