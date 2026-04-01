import java.util.LinkedHashSet;

public class TrainConsistManagementApp {
    public static void main(String[] args) {

        // Create LinkedHashSet for train formation
        LinkedHashSet<String> train = new LinkedHashSet<>();

        // Add bogies
        train.add("Engine");
        train.add("Sleeper");
        train.add("Cargo");
        train.add("Guard");


        // Attempt to add duplicate bogie
        train.add("Sleeper"); // duplicate, will be ignored

        // Display final formation
        System.out.println("Final Train Formation (No duplicates, Insertion Order Preserved):");
        System.out.println(train);
    }
}