

package lab3;

import java.time.LocalDate;
import java.util.Arrays;

// Enum for magazine frequency
enum Frequency {
    WEEKLY, MONTHLY, YEARLY
}

// Person class
class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName() + ", Born: " + birthDate;
    }
}

// Article class
class Article {
    private Person author;
    private String title;
    private double rating;

    public Article(Person author, String title, double rating) {
        this.author = author;
        this.title = title;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author.getFullName() + ", Rating: " + rating;
    }
}

// Magazine class
class Magazine {
    private String name;
    private Frequency frequency;
    private LocalDate releaseDate;
    private int circulation;
    private Article[] articles;

    public Magazine(String name, Frequency frequency, LocalDate releaseDate, int circulation, Article[] articles) {
        this.name = name;
        this.frequency = frequency;
        this.releaseDate = releaseDate;
        this.circulation = circulation;
        this.articles = articles;
    }

    public void displayInfo() {
        System.out.println("Magazine: " + name);
        System.out.println("Frequency: " + frequency);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Circulation: " + circulation);
        System.out.println("Articles:");
        Arrays.stream(articles).forEach(System.out::println);
    }
}

// Main class for testing
public class task2 {
    public static void main(String[] args) {
        Person author1 = new Person("Alice", "Johnson", LocalDate.of(1990, 4, 12));
        Person author2 = new Person("Bob", "Smith", LocalDate.of(1985, 9, 30));

        Article art1 = new Article(author1, "Understanding Java", 4.8);
        Article art2 = new Article(author2, "Intro to AI", 4.5);

        Article[] articleList = {art1, art2};

        Magazine magazine = new Magazine("Tech World", Frequency.MONTHLY, LocalDate.of(2025, 6, 1), 10000, articleList);

        magazine.displayInfo();
    }
}
