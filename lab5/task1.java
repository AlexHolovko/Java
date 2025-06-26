package lab5;

import java.io.*;
import java.util.*;

public class task1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to first file: ");
        String file1 = scanner.nextLine();
        System.out.print("Enter path to second file: ");
        String file2 = scanner.nextLine();

        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2))) {

            String line1, line2;
            int lineNum = 1;
            boolean mismatchFound = false;

            while (true) {
                line1 = br1.readLine();
                line2 = br2.readLine();

                if (line1 == null && line2 == null) break;

                if (!Objects.equals(line1, line2)) {
                    mismatchFound = true;
                    System.out.println("Line " + lineNum + " differs:");
                    System.out.println("File1: " + (line1 == null ? "(no line)" : line1));
                    System.out.println("File2: " + (line2 == null ? "(no line)" : line2));
                }
                lineNum++;
            }

            if (!mismatchFound) {
                System.out.println("Files match line by line.");
            }
        }
        scanner.close();
    }
}

