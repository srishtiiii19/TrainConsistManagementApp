package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UseCase12TrainConsistMgmntTest {

    // Helper GoodsBogie class mirroring the main class
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type  = type;
            this.cargo = cargo;
        }
    }

    // Helper method: safety rule using allMatch()
    // Cylindrical bogies must carry only Petroleum
    private boolean isSafetyCompliant(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical")
                        || b.cargo.equals("Petroleum"));
    }

    @Test
    void testSafety_AllBogiesValid() {
        // All cylindrical bogies carry Petroleum → train is SAFE
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Open",        "Coal"));
        bogies.add(new GoodsBogie("Box",         "Grain"));

        boolean result = isSafetyCompliant(bogies);

        assertTrue(result);
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        // Cylindrical bogie carrying Coal → validation FAILS
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Coal"));      // violation
        bogies.add(new GoodsBogie("Open",        "Grain"));

        boolean result = isSafetyCompliant(bogies);

        assertFalse(result);
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        // Non-cylindrical bogies can carry any cargo → train is SAFE
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Open", "Coal"));
        bogies.add(new GoodsBogie("Open", "Grain"));
        bogies.add(new GoodsBogie("Box",  "Steel"));
        bogies.add(new GoodsBogie("Box",  "Coal"));

        boolean result = isSafetyCompliant(bogies);

        assertTrue(result);
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        // At least one cylindrical bogie violates rule → train is UNSAFE
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum")); // valid
        bogies.add(new GoodsBogie("Cylindrical", "Coal"));      // violation
        bogies.add(new GoodsBogie("Open",        "Grain"));

        boolean result = isSafetyCompliant(bogies);

        assertFalse(result);
    }

    @Test
    void testSafety_EmptyBogieList() {
        // No bogies → no violations → allMatch() returns true
        List<GoodsBogie> bogies = new ArrayList<>();

        boolean result = isSafetyCompliant(bogies);

        assertTrue(result);
    }

    @Test
    void testSafety_SingleCylindricalValidCargo() {
        // Single cylindrical bogie with Petroleum → SAFE
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));

        boolean result = isSafetyCompliant(bogies);

        assertTrue(result);
    }

    @Test
    void testSafety_SingleCylindricalInvalidCargo() {
        // Single cylindrical bogie with wrong cargo → UNSAFE
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Grain"));

        boolean result = isSafetyCompliant(bogies);

        assertFalse(result);
    }

    @Test
    void testSafety_OriginalListUnchanged() {
        // Original list must remain unchanged after safety check
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Open",        "Coal"));
        bogies.add(new GoodsBogie("Box",         "Grain"));

        int originalSize     = bogies.size();
        String firstType     = bogies.get(0).type;
        String firstCargo    = bogies.get(0).cargo;

        boolean result = isSafetyCompliant(bogies);

        // Verify list is unchanged
        assertEquals(originalSize, bogies.size());
        assertEquals(firstType,    bogies.get(0).type);
        assertEquals(firstCargo,   bogies.get(0).cargo);
        assertTrue(result);
    }
}