package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UseCase10TrainConsistMgmntTest {

    // Helper Bogie class mirroring the main class
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Helper method to calculate total seats using map() and reduce()
    private int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    @Test
    void testReduce_TotalSeatCalculation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        int result = calculateTotalSeats(bogies);

        // 72 + 56 + 24 + 90 = 242
        assertEquals(242, result);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 68));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        int result = calculateTotalSeats(bogies);

        // 72 + 68 + 56 + 60 + 24 = 280
        assertEquals(280, result);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));

        int result = calculateTotalSeats(bogies);

        // Single bogie: total should equal its own capacity
        assertEquals(72, result);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        int result = calculateTotalSeats(bogies);

        // Empty list: identity value (0) should be returned
        assertEquals(0, result);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 100));
        bogies.add(new Bogie("AC Chair", 200));

        int result = calculateTotalSeats(bogies);

        // Verify map() correctly extracts and sums capacities
        assertEquals(300, result);
        assertEquals(bogies.get(0).capacity + bogies.get(1).capacity, result);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));
        bogies.add(new Bogie("Express", 110));

        int result = calculateTotalSeats(bogies);

        // Manually compute expected total
        int expected = bogies.stream()
                .mapToInt(b -> b.capacity)
                .sum();

        // All bogies must be included
        assertEquals(expected, result);
        assertEquals(352, result); // 72+56+24+90+110 = 352
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        int originalSize = bogies.size();
        String firstName = bogies.get(0).name;
        int firstCapacity = bogies.get(0).capacity;

        int result = calculateTotalSeats(bogies);

        // Original list must remain unchanged after aggregation
        assertEquals(originalSize, bogies.size());
        assertEquals(4, bogies.size());
        assertEquals(firstName, bogies.get(0).name);
        assertEquals(firstCapacity, bogies.get(0).capacity);

        // Result must be a valid total
        assertEquals(242, result);
    }
}