package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UseCase8TrainConsistMgmtTest {

    // Helper Bogie class mirroring the main class
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Helper method to filter bogies by capacity threshold
    private List<Bogie> filterBogies(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("General", 90));

        List<Bogie> result = filterBogies(bogies, 70);

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(b -> b.name.equals("Sleeper")));
        assertTrue(result.stream().anyMatch(b -> b.name.equals("General")));
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 56));

        List<Bogie> result = filterBogies(bogies, 70);

        assertEquals(0, result.size());
        assertFalse(result.stream().anyMatch(b -> b.name.equals("Sleeper")));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        List<Bogie> result = filterBogies(bogies, 70);

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("General", 90));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Express", 85));

        List<Bogie> result = filterBogies(bogies, 70);

        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(b -> b.name.equals("Sleeper")));
        assertTrue(result.stream().anyMatch(b -> b.name.equals("General")));
        assertTrue(result.stream().anyMatch(b -> b.name.equals("Express")));
    }

    @Test
    void testFilter_NoBogiesMatching() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        List<Bogie> result = filterBogies(bogies, 70);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("General", 90));
        bogies.add(new Bogie("Express", 85));

        List<Bogie> result = filterBogies(bogies, 70);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(bogies.size(), result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        List<Bogie> result = filterBogies(bogies, 70);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        int originalSize = bogies.size();
        String firstName = bogies.get(0).name;
        int firstCapacity = bogies.get(0).capacity;

        List<Bogie> result = filterBogies(bogies, 60);

        // Verify size unchanged
        assertEquals(originalSize, bogies.size());
        assertEquals(4, bogies.size());

        // Verify contents unchanged
        assertEquals(firstName, bogies.get(0).name);
        assertEquals(firstCapacity, bogies.get(0).capacity);

        // Verify result is a separate list
        assertNotSame(bogies, result);
    }
}