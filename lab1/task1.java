package lab1;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int a = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int b = scanner.nextInt();

        int start = Math.min(a, b);
        int end = Math.max(a, b);

        System.out.println("Odd numbers in the range:");
        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        scanner.close();
    }
}
