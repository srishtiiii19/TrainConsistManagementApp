import java.util.HashMap;
import java.util.Map;

public class TrainConsistUC6 {

    public static void main(String[] args) {

        // Step 1: Create HashMap to store bogie and capacity
        HashMap<String, Integer> bogieCapacityMap = new HashMap<>();

        // Step 2: Insert bogie-capacity mappings
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 54);
        bogieCapacityMap.put("First Class", 24);
        bogieCapacityMap.put("Goods - Rectangular", 100);
        bogieCapacityMap.put("Goods - Cylindrical", 80);

        // Step 3: Display mapping using entrySet()
        System.out.println("=== Train Bogie Capacity Details ===");

        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            String bogieName = entry.getKey();
            Integer capacity = entry.getValue();

            System.out.println("Bogie: " + bogieName + " | Capacity: " + capacity);
        }

        System.out.println("\nProgram continues...");
    }
}