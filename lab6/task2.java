package lab6;

import java.util.*;
import java.util.stream.Collectors;

public class task2 {

    static class WordEntry {
        private String word;
        private List<String> translations;
        private int accessCount;

        public WordEntry(String word, List<String> translations) {
            this.word = word;
            this.translations = new ArrayList<>(translations);
            this.accessCount = 0;
        }

        public String getWord() {
            return word;
        }

        public List<String> getTranslations() {
            accessCount++;
            return translations;
        }

        public void setTranslations(List<String> translations) {
            this.translations = new ArrayList<>(translations);
        }

        public void addTranslation(String translation) {
            translations.add(translation);
        }

        public void removeTranslation(String translation) {
            translations.remove(translation);
        }

        public int getAccessCount() {
            return accessCount;
        }

        @Override
        public String toString() {
            return "Word: " + word + ", Translations: " + translations + ", Accesses: " + accessCount;
        }
    }

    private Map<String, WordEntry> dictionary = new HashMap<>();

    private void initSampleData() {
        dictionary.put("hello", new WordEntry("hello", Arrays.asList("hola", "salut")));
        dictionary.put("world", new WordEntry("world", Arrays.asList("mundo", "monde")));
        dictionary.put("cat", new WordEntry("cat", Arrays.asList("gato", "chat")));
        dictionary.put("dog", new WordEntry("dog", Arrays.asList("perro", "chien")));
        dictionary.put("house", new WordEntry("house", Arrays.asList("casa", "maison")));
    }

    private void displayTranslations(String word) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            System.out.println("Translations for '" + word + "': " + entry.getTranslations());
        } else {
            System.out.println("Word '" + word + "' not found in dictionary.");
        }
    }

    private void addOrReplaceTranslations(String word, List<String> translations) {
        word = word.toLowerCase();
        if (dictionary.containsKey(word)) {
            dictionary.get(word).setTranslations(translations);
            System.out.println("Translations replaced for word '" + word + "'.");
        } else {
            dictionary.put(word, new WordEntry(word, translations));
            System.out.println("Word '" + word + "' added with translations.");
        }
    }

    private void addTranslation(String word, String translation) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.addTranslation(translation);
            System.out.println("Translation '" + translation + "' added to word '" + word + "'.");
        } else {
            System.out.println("Word '" + word + "' not found.");
        }
    }

    private void removeTranslation(String word, String translation) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            if (entry.getTranslations().contains(translation)) {
                entry.removeTranslation(translation);
                System.out.println("Translation '" + translation + "' removed from word '" + word + "'.");
            } else {
                System.out.println("Translation '" + translation + "' not found for word '" + word + "'.");
            }
        } else {
            System.out.println("Word '" + word + "' not found.");
        }
    }

    private void addWord(String word, List<String> translations) {
        word = word.toLowerCase();
        if (!dictionary.containsKey(word)) {
            dictionary.put(word, new WordEntry(word, translations));
            System.out.println("Word '" + word + "' added.");
        } else {
            System.out.println("Word '" + word + "' already exists.");
        }
    }

    private void removeWord(String word) {
        if (dictionary.remove(word.toLowerCase()) != null) {
            System.out.println("Word '" + word + "' removed.");
        } else {
            System.out.println("Word '" + word + "' not found.");
        }
    }

    private void showTopPopularWords() {
        System.out.println("Top 10 popular words:");
        dictionary.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount).reversed())
                .limit(10)
                .forEach(w -> System.out.println(w.getWord() + " (accesses: " + w.getAccessCount() + ")"));
    }

    private void showTopUnpopularWords() {
        System.out.println("Top 10 unpopular words:");
        dictionary.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount))
                .limit(10)
                .forEach(w -> System.out.println(w.getWord() + " (accesses: " + w.getAccessCount() + ")"));
    }

    private void showMenu() {
        System.out.println("\nDictionary Menu:");
        System.out.println("1. Display translations");
        System.out.println("2. Add or replace translations");
        System.out.println("3. Add translation to word");
        System.out.println("4. Remove translation from word");
        System.out.println("5. Add new word");
        System.out.println("6. Remove word");
        System.out.println("7. Show top 10 popular words");
        System.out.println("8. Show top 10 unpopular words");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    public void run() {
        initSampleData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter word to display translations: ");
                    String wordToShow = sc.nextLine();
                    displayTranslations(wordToShow);
                    break;
                case "2":
                    System.out.print("Enter word to add/replace translations: ");
                    String wordToAddOrReplace = sc.nextLine();
                    System.out.print("Enter translations separated by commas: ");
                    String[] translationsArr = sc.nextLine().split(",");
                    List<String> translationsList = Arrays.stream(translationsArr)
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    addOrReplaceTranslations(wordToAddOrReplace, translationsList);
                    break;
                case "3":
                    System.out.print("Enter word to add translation: ");
                    String wordToAddTrans = sc.nextLine();
                    System.out.print("Enter translation to add: ");
                    String transToAdd = sc.nextLine();
                    addTranslation(wordToAddTrans, transToAdd);
                    break;
                case "4":
                    System.out.print("Enter word to remove translation: ");
                    String wordToRemoveTrans = sc.nextLine();
                    System.out.print("Enter translation to remove: ");
                    String transToRemove = sc.nextLine();
                    removeTranslation(wordToRemoveTrans, transToRemove);
                    break;
                case "5":
                    System.out.print("Enter new word: ");
                    String newWord = sc.nextLine();
                    System.out.print("Enter translations separated by commas: ");
                    String[] newTransArr = sc.nextLine().split(",");
                    List<String> newTransList = Arrays.stream(newTransArr)
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    addWord(newWord, newTransList);
                    break;
                case "6":
                    System.out.print("Enter word to remove: ");
                    String wordToRemove = sc.nextLine();
                    removeWord(wordToRemove);
                    break;
                case "7":
                    showTopPopularWords();
                    break;
                case "8":
                    showTopUnpopularWords();
                    break;
                case "9":
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void main(String[] args) {
        new task2().run();
    }
}

