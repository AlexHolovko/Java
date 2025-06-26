package lab5;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class task2 {

    public static Set<String> loadForbiddenWords(String filename) throws IOException {
        return new HashSet<>(Files.readAllLines(Paths.get(filename))
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet()));
    }

    public static Map<String, Integer> countForbiddenWords(String text, Set<String> forbiddenWords) {
        Map<String, Integer> counts = new HashMap<>();
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            if (forbiddenWords.contains(word)) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }
        return counts;
    }

    public static void replaceForbiddenWordsInFile(Path file, Set<String> forbiddenWords) throws IOException {
        String content = Files.readString(file);
        for (String forbidden : forbiddenWords) {
            content = content.replaceAll("(?i)\\b" + forbidden + "\\b", "*".repeat(forbidden.length()));
        }
        Files.writeString(file, content);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter path to forbidden words file: ");
        String forbiddenWordsFile = scanner.nextLine();
        Set<String> forbiddenWords = loadForbiddenWords(forbiddenWordsFile);

        System.out.print("Enter directory path with text files: ");
        Path dir = Paths.get(scanner.nextLine());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                String content = Files.readString(file);
                Map<String, Integer> foundWords = countForbiddenWords(content, forbiddenWords);
                if (!foundWords.isEmpty()) {
                    System.out.println("File: " + file.getFileName());
                    foundWords.forEach((word, count) -> System.out.println("  Word: '" + word + "' Count: " + count));

                    System.out.print("Replace forbidden words in this file? (y/n): ");
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("y")) {
                        replaceForbiddenWordsInFile(file, forbiddenWords);
                        System.out.println("File updated.");
                    }
                }
            }
        }
        scanner.close();
    }
}
