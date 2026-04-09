// Represents a passenger bogie with a name and seating capacity.

public class TrainConsistManagementApp {

    private String name;
    private int capacity;

    // Constructor
    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for capacity
    public int getCapacity() {
        return capacity;
    }

    // Display bogie details
    @Override
    public String toString() {
        return "Bogie [Name: " + name + ", Capacity: " + capacity + " seats]";
    }
}
