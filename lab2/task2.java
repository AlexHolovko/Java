package lab2;

public class task2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("HelloWorld");

        String sub = sb.substring(0, 5);
        System.out.println("Substring: " + sub);

        sb.append("!!!");
        System.out.println("After append: " + sb);

        sb.insert(5, " Beautiful");
        System.out.println("After insert: " + sb);

        int start = sb.indexOf("World");
        if (start != -1) {
            sb.delete(start, start + "World".length());
        }
        System.out.println("After delete: " + sb);

        int pos = sb.indexOf("Beautiful");
        if (pos != -1) {
            sb.delete(pos, pos + "Beautiful".length());
            sb.insert(pos, "Amazing");
        }
        System.out.println("After replace: " + sb);
    }
}
