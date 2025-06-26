package lab2;

public class task5 {
    public static String removeRuEmails(String text) {
        return text.replaceAll("\\b\\S+@\\S+\\.ru\\b", "");
    }

    public static void main(String[] args) {
        String input = "test@mail.ru example@gmail.com user@domain.ru";
        String output = removeRuEmails(input);
        System.out.println(output);
    }
}
