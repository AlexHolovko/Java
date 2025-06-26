package lab2;

import java.util.regex.Pattern;

public class task4 {
    public static void main(String[] args) {
        String regex = "^[A-Z][a-zA-Z]{1,8}tion[,.!:;]$";
        Pattern pattern = Pattern.compile(regex);

        String[] words = {"Situation:", "Motivation,", "Action!", "Obligation.", "Incorrect", "action."};

        for (String w : words) {
            if (pattern.matcher(w).matches()) {
                System.out.println(w + " matches pattern.");
            } else {
                System.out.println(w + " does NOT match pattern.");
            }
        }
    }
}
