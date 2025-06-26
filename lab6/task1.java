package lab6;

import java.util.*;


public class task1 {
    private Map<String, String> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- User Authorization System ---");
            System.out.println("1. Add new user");
            System.out.println("2. Remove existing user");
            System.out.println("3. Check if user exists");
            System.out.println("4. Change user login");
            System.out.println("5. Change user password");
            System.out.println("6. Show all users");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addUser();
                case "2" -> removeUser();
                case "3" -> checkUser();
                case "4" -> changeLogin();
                case "5" -> changePassword();
                case "6" -> showUsers();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addUser() {
        System.out.print("Enter new login: ");
        String login = scanner.nextLine().trim();
        if (users.containsKey(login)) {
            System.out.println("User with this login already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(login, password);
        System.out.println("User added successfully.");
    }

    private void removeUser() {
        System.out.print("Enter login to remove: ");
        String login = scanner.nextLine().trim();
        if (users.remove(login) != null) {
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void checkUser() {
        System.out.print("Enter login to check: ");
        String login = scanner.nextLine().trim();
        if (users.containsKey(login)) {
            System.out.println("User exists.");
        } else {
            System.out.println("User does not exist.");
        }
    }

    private void changeLogin() {
        System.out.print("Enter current login: ");
        String oldLogin = scanner.nextLine().trim();
        if (!users.containsKey(oldLogin)) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Enter new login: ");
        String newLogin = scanner.nextLine().trim();
        if (users.containsKey(newLogin)) {
            System.out.println("User with new login already exists.");
            return;
        }
        String password = users.remove(oldLogin);
        users.put(newLogin, password);
        System.out.println("Login changed successfully.");
    }

    private void changePassword() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine().trim();
        if (!users.containsKey(login)) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        users.put(login, newPassword);
        System.out.println("Password changed successfully.");
    }

    private void showUsers() {
        System.out.println("Current users:");
        users.keySet().stream()
            .sorted()
            .forEach(login -> System.out.println("- " + login));
    }

    public static void main(String[] args) {
        task1 task = new task1();
        task.menu();
    }
}
