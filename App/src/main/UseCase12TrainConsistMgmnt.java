package main;

import java.util.ArrayList;
import java.util.List;

/**
 * =====================================================
 * MAIN CLASS - UseCase12TrainConsistMgmnt
 * =====================================================
 *
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * Description:
 * This class validates safety compliance of goods bogies
 * using Stream allMatch() and conditional lambda logic.
 *
 * At this stage, the application:
 * - Creates a list of goods bogies with type and cargo
 * - Converts list into stream
 * - Applies allMatch() with safety rule
 * - Cylindrical bogies must carry only Petroleum
 * - Displays whether train is safety compliant
 *
 * @author Developer
 * @version 12.0
 */

public class UseCase12TrainConsistMgmnt {

    // Goods Bogie model with type and cargo fields
    static class GoodsBogie {
        String type;   // e.g. Cylindrical, Open, Box
        String cargo;  // e.g. Petroleum, Coal, Grain

        GoodsBogie(String type, String cargo) {
            this.type  = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " [" + cargo + "]";
        }
    }

    // Safety rule: Cylindrical bogies must carry only Petroleum
    public static boolean isSafetyCompliant(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical")
                        || b.cargo.equals("Petroleum"));
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods    ");
        System.out.println("==============================================\n");

        // Create list of goods bogies
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Open",        "Coal"));
        bogies.add(new GoodsBogie("Box",         "Grain"));
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));

        // Display all bogies
        System.out.println("Goods Bogies:");
        for (GoodsBogie b : bogies) {
            System.out.println("  " + b);
        }

        // Perform safety compliance check using allMatch()
        boolean isSafe = isSafetyCompliant(bogies);

        // Display result
        System.out.println("\nSafety Compliance Check:");
        System.out.println("  Train is " + (isSafe ? "SAFE ✔" : "UNSAFE ✘"));

        // Test with a violation
        System.out.println("\n--- Testing with unsafe bogie ---");
        List<GoodsBogie> unsafeBogies = new ArrayList<>();
        unsafeBogies.add(new GoodsBogie("Cylindrical", "Coal"));      // violation
        unsafeBogies.add(new GoodsBogie("Open",        "Grain"));

        System.out.println("Goods Bogies:");
        for (GoodsBogie b : unsafeBogies) {
            System.out.println("  " + b);
        }

        boolean isUnsafe = isSafetyCompliant(unsafeBogies);
        System.out.println("\nSafety Compliance Check:");
        System.out.println("  Train is " + (isUnsafe ? "SAFE ✔" : "UNSAFE ✘"));

        System.out.println("\nUC12 safety check completed...");
    }
}