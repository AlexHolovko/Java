package lab6;

import java.util.*;
import java.util.stream.Collectors;

class TV {
    private String model;
    private int year;
    private double price;
    private double diagonal;
    private String manufacturer;
    private String country;

    public TV(String model, int year, double price, double diagonal, String manufacturer, String country) {
        this.model = model;
        this.year = year;
        this.price = price;
        this.diagonal = diagonal;
        this.manufacturer = manufacturer;
        this.country = country;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.format("Model: %s, Year: %d, Price: %.2f, Diagonal: %.1f\", Manufacturer: %s, Country: %s",
                model, year, price, diagonal, manufacturer, country);
    }
}

public class task4 {
    private List<TV> tvs;

    public task4(List<TV> tvs) {
        this.tvs = tvs;
    }

    public void showAll() {
        System.out.println("All TVs:");
        tvs.forEach(System.out::println);
        System.out.println();
    }

    public void showByDiagonal(double diagonal) {
        System.out.println("TVs with diagonal " + diagonal + ":");
        tvs.stream()
                .filter(tv -> tv.getDiagonal() == diagonal)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showByManufacturer(String manufacturer) {
        System.out.println("TVs by manufacturer '" + manufacturer + "':");
        tvs.stream()
                .filter(tv -> tv.getManufacturer().equalsIgnoreCase(manufacturer))
                .forEach(System.out::println);
        System.out.println();
    }

    public void showByYearAndDiagonalAndPrice(int year, double maxDiagonal, double minPrice) {
        System.out.printf("TVs from year %d with diagonal ≤ %.1f and price ≥ %.2f:%n", year, maxDiagonal, minPrice);
        tvs.stream()
                .filter(tv -> tv.getYear() == year)
                .filter(tv -> tv.getDiagonal() <= maxDiagonal)
                .filter(tv -> tv.getPrice() >= minPrice)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showByPriceGreaterThan(double price) {
        System.out.println("TVs with price greater than " + price + ":");
        tvs.stream()
                .filter(tv -> tv.getPrice() > price)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showSortedByPriceAsc() {
        System.out.println("TVs sorted by price ascending:");
        tvs.stream()
                .sorted(Comparator.comparingDouble(TV::getPrice))
                .forEach(System.out::println);
        System.out.println();
    }

    public void showSortedByDiagonalDesc() {
        System.out.println("TVs sorted by diagonal descending:");
        tvs.stream()
                .sorted(Comparator.comparingDouble(TV::getDiagonal).reversed())
                .forEach(System.out::println);
        System.out.println();
    }

    public void showGroupedByCountry() {
        System.out.println("TVs grouped by country:");
        Map<String, List<TV>> grouped = tvs.stream()
                .collect(Collectors.groupingBy(TV::getCountry));
        grouped.forEach((country, list) -> {
            System.out.println(country + ":");
            list.forEach(tv -> System.out.println("  " + tv));
        });
        System.out.println();
    }

    public void showTop5MostExpensive() {
        System.out.println("Top 5 most expensive TVs:");
        tvs.stream()
                .sorted(Comparator.comparingDouble(TV::getPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showTop3SmallestDiagonal() {
        System.out.println("Top 3 TVs with smallest diagonal:");
        tvs.stream()
                .sorted(Comparator.comparingDouble(TV::getDiagonal))
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
    }

    public void showLastMostExpensiveWithDiagonal40() {
        System.out.println("Last most expensive TV with 40\" diagonal:");
        List<TV> filteredSorted = tvs.stream()
                .filter(t -> t.getDiagonal() == 40)
                .sorted(Comparator.comparingDouble(TV::getPrice).reversed())
                .collect(Collectors.toList());

        if (filteredSorted.isEmpty()) {
            System.out.println("No TV found.");
        } else {
            TV lastMostExpensive = filteredSorted.get(filteredSorted.size() - 1);
            System.out.println(lastMostExpensive);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<TV> tvList = Arrays.asList(
                new TV("ModelA", 2023, 1200.0, 40.0, "Samsung", "South Korea"),
                new TV("ModelB", 2022, 800.0, 32.0, "LG", "South Korea"),
                new TV("ModelC", 2023, 1500.0, 55.0, "Sony", "Japan"),
                new TV("ModelD", 2021, 300.0, 24.0, "Philips", "Netherlands"),
                new TV("ModelE", 2023, 2000.0, 40.0, "Samsung", "South Korea"),
                new TV("ModelF", 2023, 600.0, 28.0, "LG", "South Korea"),
                new TV("ModelG", 2022, 400.0, 20.0, "Sony", "Japan"),
                new TV("ModelH", 2023, 500.0, 30.0, "Philips", "Netherlands"),
                new TV("ModelI", 2020, 2500.0, 65.0, "Samsung", "South Korea"),
                new TV("ModelJ", 2023, 700.0, 40.0, "Sony", "Japan")
        );

        task4 store = new task4(tvList);

        store.showAll();
        store.showByDiagonal(40.0);
        store.showByManufacturer("Samsung");
        store.showByYearAndDiagonalAndPrice(2023, 30, 500);
        store.showByPriceGreaterThan(1000);
        store.showSortedByPriceAsc();
        store.showSortedByDiagonalDesc();
        store.showGroupedByCountry();
        store.showTop5MostExpensive();
        store.showTop3SmallestDiagonal();
        store.showLastMostExpensiveWithDiagonal40();
    }
}
