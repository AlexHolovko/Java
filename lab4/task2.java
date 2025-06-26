package lab4;

import java.util.ArrayList;
import java.util.List;

// Интерфейсы
interface IPart {
    String getName();
    boolean isBuilt();
    void build();
}

interface IWorker {
    void work(House house);
}

// Части дома
class Basement implements IPart {
    private boolean built = false;
    public String getName() { return "Basement"; }
    public boolean isBuilt() { return built; }
    public void build() { built = true; System.out.println("Basement built."); }
}

class Walls implements IPart {
    private int builtCount = 0;
    private static final int TOTAL = 4;
    public String getName() { return "Wall"; }
    public boolean isBuilt() { return builtCount == TOTAL; }
    public void build() {
        if (builtCount < TOTAL) {
            builtCount++;
            System.out.println("Wall " + builtCount + " built.");
        }
    }
}

class Door implements IPart {
    private boolean built = false;
    public String getName() { return "Door"; }
    public boolean isBuilt() { return built; }
    public void build() { built = true; System.out.println("Door built."); }
}

class Window implements IPart {
    private int builtCount = 0;
    private static final int TOTAL = 4;
    public String getName() { return "Window"; }
    public boolean isBuilt() { return builtCount == TOTAL; }
    public void build() {
        if (builtCount < TOTAL) {
            builtCount++;
            System.out.println("Window " + builtCount + " built.");
        }
    }
}

class Roof implements IPart {
    private boolean built = false;
    public String getName() { return "Roof"; }
    public boolean isBuilt() { return built; }
    public void build() { built = true; System.out.println("Roof built."); }
}

// Дом
class House {
    Basement basement = new Basement();
    Walls walls = new Walls();
    Door door = new Door();
    Window windows = new Window();
    Roof roof = new Roof();

    public boolean isBuilt() {
        return basement.isBuilt() && walls.isBuilt() && door.isBuilt() && windows.isBuilt() && roof.isBuilt();
    }
}

// Работник
class Worker implements IWorker {
    private String name;
    public Worker(String name) { this.name = name; }

    public void work(House house) {
        if (!house.basement.isBuilt()) house.basement.build();
        else if (!house.walls.isBuilt()) house.walls.build();
        else if (!house.door.isBuilt()) house.door.build();
        else if (!house.windows.isBuilt()) house.windows.build();
        else if (!house.roof.isBuilt()) house.roof.build();
        else System.out.println(name + ": House is already built.");
    }
}

// Бригадир
class TeamLeader implements IWorker {
    private String name;
    public TeamLeader(String name) { this.name = name; }

    public void work(House house) {
        System.out.println(name + " reports:");
        System.out.println("Basement built: " + house.basement.isBuilt());
        System.out.println("Walls built: " + house.walls.isBuilt());
        System.out.println("Door built: " + house.door.isBuilt());
        System.out.println("Windows built: " + house.windows.isBuilt());
        System.out.println("Roof built: " + house.roof.isBuilt());
    }
}

// Команда
class Team {
    private List<IWorker> workers = new ArrayList<>();
    private House house = new House();

    public void addWorker(IWorker worker) {
        workers.add(worker);
    }

    public void buildHouse() {
        while (!house.isBuilt()) {
            for (IWorker w : workers) {
                w.work(house);
                if (house.isBuilt()) break;
            }
        }
        System.out.println("House construction complete!");
    }

    public House getHouse() {
        return house;
    }
}

// Тест
public class task2 {
    public static void main(String[] args) {
        Team team = new Team();
        team.addWorker(new Worker("Worker 1"));
        team.addWorker(new Worker("Worker 2"));
        team.addWorker(new TeamLeader("Foreman"));

        team.buildHouse();

        // Финальный отчёт
        new TeamLeader("Foreman").work(team.getHouse());
    }
}
