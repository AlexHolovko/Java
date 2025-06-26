package lab4;

import java.time.LocalDate;
import java.util.*;

interface CatalogItem {
    String getTitle();
    void displayInfo();
    String getAuthor();
}

class Book implements CatalogItem {
    private String author;
    private String title;
    private String genre;
    private int pages;

    public Book(String author, String title, String genre, int pages) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public void displayInfo() {
        System.out.println("Book: " + title + ", Author: " + author + ", Genre: " + genre + ", Pages: " + pages);
    }
}

class Newspaper implements CatalogItem {
    private String title;
    private LocalDate date;
    private List<String> headlines;

    public Newspaper(String title, LocalDate date, List<String> headlines) {
        this.title = title;
        this.date = date;
        this.headlines = headlines;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return ""; }

    public void displayInfo() {
        System.out.println("Newspaper: " + title + ", Date: " + date);
        System.out.println("Headlines:");
        for (String h : headlines) System.out.println(" - " + h);
    }
}

class Almanac implements CatalogItem {
    private String title;
    private List<Book> works;

    public Almanac(String title, List<Book> works) {
        this.title = title;
        this.works = works;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return ""; }

    public void displayInfo() {
        System.out.println("Almanac: " + title);
        System.out.println("Works:");
        for (Book b : works) b.displayInfo();
    }
}

class LibraryCatalog {
    private List<CatalogItem> items = new ArrayList<>();

    public void addItem(CatalogItem item) {
        items.add(item);
    }

    public void removeByTitle(String title) {
        items.removeIf(i -> i.getTitle().equalsIgnoreCase(title));
    }

    public void displayAll() {
        items.forEach(CatalogItem::displayInfo);
    }

    public void searchByTitle(String title) {
        items.stream()
            .filter(i -> i.getTitle().toLowerCase().contains(title.toLowerCase()))
            .forEach(CatalogItem::displayInfo);
    }

    public void searchByAuthor(String author) {
        items.stream()
            .filter(i -> i.getAuthor() != null && i.getAuthor().toLowerCase().contains(author.toLowerCase()))
            .forEach(CatalogItem::displayInfo);
    }
}

// Тест
public class task3 {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();

        Book b1 = new Book("J.K. Rowling", "Harry Potter", "Fantasy", 500);
        Book b2 = new Book("George Orwell", "1984", "Dystopian", 328);
        Newspaper n1 = new Newspaper("Daily News", LocalDate.of(2025,6,1), List.of("Headline1", "Headline2"));
        Almanac a1 = new Almanac("Classic Works", List.of(b1, b2));

        catalog.addItem(b1);
        catalog.addItem(b2);
        catalog.addItem(n1);
        catalog.addItem(a1);

        System.out.println("Display all catalog:");
        catalog.displayAll();

        System.out.println("\nSearch by title 'Harry':");
        catalog.searchByTitle("Harry");

        System.out.println("\nSearch by author 'Orwell':");
        catalog.searchByAuthor("Orwell");

        System.out.println("\nRemoving '1984' from catalog...");
        catalog.removeByTitle("1984");

        System.out.println("\nDisplay all catalog after removal:");
        catalog.displayAll();
    }
}
