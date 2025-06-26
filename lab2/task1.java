package lab2;

public class task1 {
    public static void main(String[] args) {
        String text = "This is a sample sentence. Another sentence here! And a forbiddenword should be hidden.";
        String[] forbiddenWords = {"forbiddenword", "hidden"};

        String[] words = text.split("\\s+");
        System.out.println("Words count: " + words.length);

        String[] sentences = text.split("[.!?]+");
        System.out.println("Sentences count: " + sentences.length);

        System.out.println("Longest line duplicated:");
        System.out.println(text);
        System.out.println(text);

        for (String bad : forbiddenWords) {
            String stars = "*".repeat(bad.length());
            text = text.replaceAll("(?i)\\b" + bad + "\\b", stars);
        }
        System.out.println("Text after forbidden words replaced:");
        System.out.println(text);
    }
}
