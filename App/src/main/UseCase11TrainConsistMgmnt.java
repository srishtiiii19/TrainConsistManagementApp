package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * =====================================================
 * MAIN CLASS - UseCase11TrainConsistMgmnt
 * =====================================================
 *
 * Use Case 11: Validate Train ID & Cargo Codes (Regex)
 *
 * Description:
 * This class validates Train ID and Cargo Code formats
 * using Regular Expressions with Pattern and Matcher.
 *
 * At this stage, the application:
 * - Defines regex patterns for Train ID and Cargo Code
 * - Compiles patterns using Pattern class
 * - Creates Matcher objects for input validation
 * - Displays whether input is valid or invalid
 *
 * Train ID Format  : TRN-1234  (TRN- followed by 4 digits)
 * Cargo Code Format: PET-AB    (PET- followed by 2 uppercase letters)
 *
 * @author Developer
 * @version 11.0
 */

public class UseCase11TrainConsistMgmnt {

    // Regex patterns
    static final String TRAIN_ID_PATTERN  = "TRN-\\d{4}";
    static final String CARGO_CODE_PATTERN = "PET-[A-Z]{2}";

    // Validate Train ID
    public static boolean validateTrainID(String trainID) {
        Pattern pattern = Pattern.compile(TRAIN_ID_PATTERN);
        Matcher matcher = pattern.matcher(trainID);
        return matcher.matches();
    }

    // Validate Cargo Code
    public static boolean validateCargoCode(String cargoCode) {
        Pattern pattern = Pattern.compile(CARGO_CODE_PATTERN);
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UC11 - Validate Train ID & Cargo Codes      ");
        System.out.println("==============================================\n");

        // Test Train ID validation
        String[] trainIDs = {"TRN-1234", "TRAIN12", "TRN12A", "1234-TRN", "TRN-123", "TRN-12345"};

        System.out.println("Train ID Validation:");
        for (String id : trainIDs) {
            boolean isValid = validateTrainID(id);
            System.out.println("  " + id + " -> " + (isValid ? "Valid" : "Invalid"));
        }

        // Test Cargo Code validation
        String[] cargoCodes = {"PET-AB", "PET-ab", "PET123", "AB-PET", "PET-A", "PET-ABC"};

        System.out.println("\nCargo Code Validation:");
        for (String code : cargoCodes) {
            boolean isValid = validateCargoCode(code);
            System.out.println("  " + code + " -> " + (isValid ? "Valid" : "Invalid"));
        }

        System.out.println("\nUC11 validation completed...");
    }
}