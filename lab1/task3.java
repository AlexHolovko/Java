package lab1;

import java.util.*;

public class task3 {
    static Map<String, Integer> menu = new HashMap<>();
    static {
        menu.put("Coffee", 30);
        menu.put("Tea", 20);
        menu.put("Cake", 40);
        menu.put("Croissant", 50);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int companyCount = 1;

        while (true) {
            System.out.print("How many people in company #" + companyCount + "? (0 to exit): ");
            int n = scanner.nextInt();
            if (n == 0) break;

            int total = 0;
            for (int i = 1; i <= n; i++) {
                System.out.println("Menu for customer #" + i + ":");
                menu.forEach((k, v) -> System.out.println(k + " - " + v + " UAH"));
                scanner.nextLine(); 

                while (true) {
                    System.out.print("Your choice (or 'no' to finish): ");
                    String item = scanner.nextLine();
                    if (item.equalsIgnoreCase("no")) break;
                    total += menu.getOrDefault(item, 0);
                }
            }

            System.out.println("Total amount for company #" + companyCount + ": " + total + " UAH\n");
            companyCount++;
        }
        scanner.close();
    }
}
