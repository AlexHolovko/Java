package lab1;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = Integer.MAX_VALUE;
        while (true) {
            System.out.print("Enter a number: ");
            int num = scanner.nextInt();
            System.out.println("You entered: " + num);

            if (num == 0 || num <= 9) {
                System.out.println("Exit condition met.");
                break;
            }
            if (num > 0 && num < min) min = num;
        }
        if (min == Integer.MAX_VALUE) 
            System.out.println("No positive numbers were entered.");
        else 
            System.out.println("The minimum positive number is: " + min);

        scanner.close();
    }
}
