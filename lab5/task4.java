package lab5;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter source directory path: ");
            String sourceDir = scanner.nextLine();

            System.out.print("Enter destination directory path: ");
            String destDir = scanner.nextLine();

            copyDirectory(sourceDir, destDir);

            System.out.println("Copy completed.");
        } catch (IOException e) {
            System.out.println("Error during copying: " + e.getMessage());
        }
    }

    public static void copyDirectory(String sourcePath, String destPath) throws IOException {
        Path sourceDir = Paths.get(sourcePath);
        Path destDir = Paths.get(destPath);

        if (!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            throw new IOException("Source directory does not exist or is not a directory.");
        }

        if (!Files.exists(destDir)) {
            Files.createDirectories(destDir);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir)) {
            for (Path file: stream) {
                if (Files.isRegularFile(file)) {
                    Path destFile = destDir.resolve(file.getFileName());
                    Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied: " + file.getFileName());
                }
            }
        }
    }
}
