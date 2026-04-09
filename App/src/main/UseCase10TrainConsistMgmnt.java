package main;

import java.util.ArrayList;
import java.util.List;

/**
 * =====================================================
 * MAIN CLASS - UseCase10TrainConsistMgmnt
 * =====================================================
 *
 * Use Case 10: Count Total Seats in Train (reduce)
 *
 * Description:
 * This class aggregates seating capacities of all bogies
 * into a single total value using Java Stream reduce().
 *
 * At this stage, the application:
 * - Creates a list of bogies
 * - Converts list into stream
 * - Extracts capacity using map()
 * - Aggregates total using reduce()
 * - Displays total seating capacity
 *
 * @author Developer
 * @version 10.0
 */

public class UseCase10TrainConsistMgmnt {

    // Reusing Bogie model from UC9
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " -> " + capacity;
        }
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UC10 - Count Total Seats in Train (reduce)  ");
        System.out.println("==============================================\n");

        // Create list of passenger bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        // Display all bogies
        System.out.println("All Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Aggregate total seating capacity using map() and reduce()
        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // Display total seating capacity
        System.out.println("\nTotal Seating Capacity: " + totalSeats);

        System.out.println("\nUC10 aggregation completed...");
    }
}