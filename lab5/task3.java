package lab5;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Employee(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public String toString() {
        return String.format("Employee: %s %s, DOB: %s, Age: %d", firstName, lastName, birthDate, getAge());
    }
}

public class task3 {
    private List<Employee> employees = new ArrayList<>();
    private boolean dataChanged = false;
    private String filename;

    public task3(String filename) {
        this.filename = filename;
        loadEmployees();
    }

    @SuppressWarnings("unchecked")
    private void loadEmployees() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found, starting with empty employee list.");
            employees = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            employees = (List<Employee>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Failed to load employees: " + e.getMessage());
            employees = new ArrayList<>();
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
            dataChanged = false;
            System.out.println("Employees saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save employees: " + e.getMessage());
        }
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        dataChanged = true;
    }

    public void removeEmployeeByLastName(String lastName) {
        boolean removed = employees.removeIf(e -> e.getLastName().equalsIgnoreCase(lastName));
        if (removed) {
            System.out.println("Employee(s) with last name '" + lastName + "' removed.");
            dataChanged = true;
        } else {
            System.out.println("No employee found with last name '" + lastName + "'.");
        }
    }

    public List<Employee> searchByLastName(String lastName) {
        return employees.stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public void displayAll() {
        employees.forEach(System.out::println);
    }

    public void displaySortedByAge() {
        employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .forEach(System.out::println);
    }

    public void displayByLastNameInitial(char initial) {
        employees.stream()
                .filter(e -> e.getLastName().toLowerCase().startsWith(String.valueOf(initial).toLowerCase()))
                .forEach(System.out::println);
    }

    public void editEmployee(String lastName, Scanner scanner) {
        List<Employee> list = searchByLastName(lastName);
        if (list.isEmpty()) {
            System.out.println("No employee found with last name: " + lastName);
            return;
        }
        Employee emp = list.get(0); // редагуємо першого знайденого
        System.out.println("Editing employee: " + emp);

        System.out.print("Enter new first name (leave blank to skip): ");
        String newFirstName = scanner.nextLine();
        System.out.print("Enter new last name (leave blank to skip): ");
        String newLastName = scanner.nextLine();
        System.out.print("Enter new birth date (YYYY-MM-DD, leave blank to skip): ");
        String newDob = scanner.nextLine();

        // Оскільки поля приватні і без сеттерів, можна створити новий об’єкт та замінити
        String fName = newFirstName.isBlank() ? emp.toString().split(" ")[1] : newFirstName;
        String lName = newLastName.isBlank() ? emp.getLastName() : newLastName;
        LocalDate dob = emp.getBirthDate();
        if (!newDob.isBlank()) {
            try {
                dob = LocalDate.parse(newDob);
            } catch (Exception e) {
                System.out.println("Invalid date format. Keeping old date.");
            }
        }

        Employee updatedEmp = new Employee(fName, lName, dob);
        employees.remove(emp);
        employees.add(updatedEmp);
        dataChanged = true;
        System.out.println("Employee updated.");
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nChoose action:");
                System.out.println("1. Add employee");
                System.out.println("2. Edit employee");
                System.out.println("3. Remove employee");
                System.out.println("4. Search employee by last name");
                System.out.println("5. Display all employees");
                System.out.println("6. Display employees sorted by age");
                System.out.println("7. Display employees by last name initial");
                System.out.println("8. Save to file");
                System.out.println("9. Exit");
                System.out.print("Your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.print("First name: ");
                        String fn = scanner.nextLine();
                        System.out.print("Last name: ");
                        String ln = scanner.nextLine();
                        System.out.print("Birth date (YYYY-MM-DD): ");
                        String bd = scanner.nextLine();
                        try {
                            LocalDate date = LocalDate.parse(bd);
                            addEmployee(new Employee(fn, ln, date));
                            System.out.println("Employee added.");
                        } catch (Exception e) {
                            System.out.println("Invalid date format.");
                        }
                    }
                    case "2" -> {
                        System.out.print("Enter last name of employee to edit: ");
                        String ln = scanner.nextLine();
                        editEmployee(ln, scanner);
                    }
                    case "3" -> {
                        System.out.print("Enter last name of employee to remove: ");
                        String ln = scanner.nextLine();
                        removeEmployeeByLastName(ln);
                    }
                    case "4" -> {
                        System.out.print("Enter last name to search: ");
                        String ln = scanner.nextLine();
                        List<Employee> found = searchByLastName(ln);
                        if (found.isEmpty()) {
                            System.out.println("No employees found.");
                        } else {
                            found.forEach(System.out::println);
                        }
                    }
                    case "5" -> displayAll();
                    case "6" -> displaySortedByAge();
                    case "7" -> {
                        System.out.print("Enter initial letter: ");
                        String letter = scanner.nextLine();
                        if (!letter.isEmpty())
                            displayByLastNameInitial(letter.charAt(0));
                    }
                    case "8" -> saveEmployees();
                    case "9" -> {
                        if (dataChanged) {
                            System.out.println("Saving data before exit...");
                            saveEmployees();
                        }
                        exit = true;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the filename for employee data storage:");
        try (Scanner sc = new Scanner(System.in)) {
            String file = sc.nextLine();
            task3 system = new task3(file);
            system.run();
        }
    }
}
