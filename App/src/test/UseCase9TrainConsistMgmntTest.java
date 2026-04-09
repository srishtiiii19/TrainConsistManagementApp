package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UseCase9TrainConsistMgmntTest {

    // Helper Bogie class mirroring the main class
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Helper method to group bogies by type
    private Map<String, List<Bogie>> groupBogies(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
    }

    @Test
    void testGrouping_BogiesGroupedByType() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Sleeper", 68));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Sleeper group should exist and contain 2 bogies
        assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());

        // AC Chair group should exist and contain 1 bogie
        assertTrue(result.containsKey("AC Chair"));
        assertEquals(1, result.get("AC Chair").size());
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 68));
        bogies.add(new Bogie("Sleeper", 80));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // All three Sleeper bogies should be in the same group
        assertTrue(result.containsKey("Sleeper"));
        assertEquals(3, result.get("Sleeper").size());
        assertEquals(1, result.size()); // Only one key in map
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Each bogie type should be a separate key
        assertEquals(4, result.size());
        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
        assertTrue(result.containsKey("General"));
    }

    @Test
    void testGrouping_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Empty list should produce empty map without errors
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 65));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Only one key should exist in the map
        assertEquals(1, result.size());
        assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Verify all expected keys exist
        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
        assertEquals(3, result.size());
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 68));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Validate group sizes match expected counts
        assertEquals(2, result.get("Sleeper").size());
        assertEquals(2, result.get("AC Chair").size());
        assertEquals(1, result.get("First Class").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        int originalSize = bogies.size();
        String firstName = bogies.get(0).name;
        int firstCapacity = bogies.get(0).capacity;

        Map<String, List<Bogie>> result = groupBogies(bogies);

        // Original list must remain unchanged
        assertEquals(originalSize, bogies.size());
        assertEquals(4, bogies.size());
        assertEquals(firstName, bogies.get(0).name);
        assertEquals(firstCapacity, bogies.get(0).capacity);

        // Result must be a separate structure
        assertNotNull(result);
        assertNotSame(bogies, result);
    }
}