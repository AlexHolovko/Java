package lab2;

public class task3 {
    public static boolean isStrongPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSymbol = password.matches(".*[!*_.].*");

        return hasUpper && hasLower && hasDigit && hasSymbol;
    }

    public static void main(String[] args) {
        String test = "Abc123!*";
        System.out.println("Is strong? " + isStrongPassword(test));
    }
}
