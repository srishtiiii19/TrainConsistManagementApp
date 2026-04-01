import java.util.HashSet;

public class TrainConsistManagementApp {
    public static void main(String[] args) {

        // Create HashSet for bogie IDs
        HashSet<String> bogieIDs = new HashSet<>();

        // Add bogie IDs (including duplicates intentionally)
        bogieIDs.add("B101");
        bogieIDs.add("B102");
        bogieIDs.add("B103");
        bogieIDs.add("B101"); // duplicate
        bogieIDs.add("B102"); // duplicate
        bogieIDs.add("B104");

        // Display final set (duplicates automatically removed)
        System.out.println("Unique Bogie IDs:");
        System.out.println(bogieIDs);
    }
}