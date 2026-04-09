package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * =====================================================
 * MAIN CLASS - UseCase9TrainConsistMgmnt
 * =====================================================
 *
 * Use Case 9: Group Bogies by Type (Collectors.groupingBy)
 *
 * Description:
 * This class groups passenger bogies based on their type
 * using Java Stream API and Collectors.groupingBy().
 *
 * At this stage, the application:
 * - Creates a list of bogies
 * - Converts list into stream
 * - Applies groupingBy collector
 * - Stores result in a Map
 * - Displays grouped bogie structure
 *
 * @author Developer
 * @version 9.0
 */

public class UseCase9TrainConsistMgmnt {

    // Reusing Bogie model from UC8
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
        System.out.println(" UC9 - Group Bogies by Type                  ");
        System.out.println("==============================================\n");

        // Create list of passenger bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 68));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("General", 90));

        // Display all bogies
        System.out.println("All Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Group bogies by type using Collectors.groupingBy()
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // Display grouped bogies
        System.out.println("\nGrouped Bogies by Type:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nType: " + entry.getKey());
            for (Bogie b : entry.getValue()) {
                System.out.println("  " + b);
            }
        }

        System.out.println("\nUC9 grouping completed...");
    }
}