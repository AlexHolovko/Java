package lab6;

import java.util.*;

public class task3 {

    static class Visitor {
        String name;
        boolean hasReservation;
        String reservationTime;

        public Visitor(String name, boolean hasReservation, String reservationTime) {
            this.name = name;
            this.hasReservation = hasReservation;
            this.reservationTime = reservationTime;
        }

        @Override
        public String toString() {
            return name + (hasReservation ? " (reservation at " + reservationTime + ")" : "");
        }
    }

    private final int totalTables;
    private int occupiedTables = 0;
    private Queue<Visitor> queue = new LinkedList<>();
    private Map<String, Visitor> reservations = new HashMap<>();

    public task3(int totalTables) {
        this.totalTables = totalTables;
    }

    public void arrive(Visitor visitor) {
        if (visitor.hasReservation) {
            if (occupiedTables < totalTables && reservations.containsKey(visitor.reservationTime)
                    && reservations.get(visitor.reservationTime).equals(visitor)) {
                occupiedTables++;
                System.out.println(visitor + " takes their reserved table.");
                reservations.remove(visitor.reservationTime);
            } else {
                System.out.println(visitor + " arrives and joins the queue with priority.");
                LinkedList<Visitor> temp = new LinkedList<>();
                temp.add(visitor);
                temp.addAll(queue);
                queue = temp;
            }
        } else {
            if (occupiedTables < totalTables) {
                occupiedTables++;
                System.out.println(visitor + " takes a free table.");
            } else {
                queue.add(visitor);
                System.out.println(visitor + " arrives and joins the queue.");
            }
        }
    }

    public void leave() {
        if (occupiedTables == 0) {
            System.out.println("No occupied tables, no one leaves.");
            return;
        }
        occupiedTables--;
        System.out.println("One table has been freed.");

        if (!queue.isEmpty()) {
            Visitor next = queue.poll();
            occupiedTables++;
            System.out.println(next + " takes a free table from the queue.");
        }
    }

    public void reserveTable(String time, Visitor visitor) {
        reservations.put(time, visitor);
        System.out.println(visitor + " reserved a table at " + time);
    }

    public void printStatus() {
        System.out.println("\nCafe status:");
        System.out.println("Total tables: " + totalTables);
        System.out.println("Occupied tables: " + occupiedTables);
        System.out.println("Queue: " + queue);
        System.out.println("Reservations: " + reservations.keySet());
        System.out.println();
    }

    public static void main(String[] args) {
        task3 cafe = new task3(3);

        Visitor v1 = new Visitor("Ivan", false, null);
        Visitor v2 = new Visitor("Maria", true, "19:00");
        Visitor v3 = new Visitor("Peter", false, null);
        Visitor v4 = new Visitor("Olena", false, null);
        Visitor v5 = new Visitor("Sergey", true, "19:00");

        cafe.reserveTable("19:00", v2);

        cafe.arrive(v1);
        cafe.arrive(v3);
        cafe.arrive(v4);
        cafe.arrive(v2);
        cafe.arrive(v5);

        cafe.printStatus();

        cafe.leave();
        cafe.leave();

        cafe.printStatus();
    }
}
