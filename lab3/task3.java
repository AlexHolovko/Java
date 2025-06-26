package lab3;

// Task 3 - English version

import java.util.ArrayList;
import java.util.List;

// Base class Animal
abstract class Animal {
    protected String name;
    protected int age;
    protected double foodRequirementPerDay;
    protected boolean isPredator;

    public Animal(String name, int age, double foodRequirementPerDay, boolean isPredator) {
        this.name = name;
        this.age = age;
        this.foodRequirementPerDay = foodRequirementPerDay;
        this.isPredator = isPredator;
    }

    public abstract String makeSound();

    public String getName() {
        return name;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public double getFoodRequirementPerDay() {
        return foodRequirementPerDay;
    }

    public String getInfo() {
        return String.format("%s (Age: %d, Predator: %s, Food/day: %.2f kg, Sound: %s)",
                name, age, isPredator ? "Yes" : "No", foodRequirementPerDay, makeSound());
    }
}

class Tiger extends Animal {
    public Tiger(String name, int age) {
        super(name, age, 10.0, true);
    }

    @Override
    public String makeSound() {
        return "Roar";
    }
}

class Rabbit extends Animal {
    public Rabbit(String name, int age) {
        super(name, age, 0.5, false);
    }

    @Override
    public String makeSound() {
        return "Squeak";
    }
}

class Wolf extends Animal {
    public Wolf(String name, int age) {
        super(name, age, 8.0, true);
    }

    @Override
    public String makeSound() {
        return "Howl";
    }
}

class Kangaroo extends Animal {
    public Kangaroo(String name, int age) {
        super(name, age, 4.0, false);
    }

    @Override
    public String makeSound() {
        return "Chortle";
    }
}

public class task3 {
    public static void main(String[] args) {
        List<Animal> zoo = new ArrayList<>();

        zoo.add(new Tiger("Sheru", 5));
        zoo.add(new Rabbit("Bunny", 2));
        zoo.add(new Wolf("Akela", 6));
        zoo.add(new Kangaroo("Jack", 4));
        zoo.add(new Rabbit("Snowball", 1));
        zoo.add(new Tiger("Rajah", 7));

        System.out.println("Zoo Animals:");
        zoo.forEach(animal -> System.out.println(animal.getInfo()));

        long predatorsCount = zoo.stream().filter(Animal::isPredator).count();
        System.out.println("\nNumber of predators: " + predatorsCount);

        double totalFood = zoo.stream()
                .mapToDouble(Animal::getFoodRequirementPerDay)
                .sum();
        System.out.printf("Total daily food required: %.2f kg\n", totalFood);
    }
}
