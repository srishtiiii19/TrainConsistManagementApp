package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseCase11TrainConsistMgmntTest {

    // Regex patterns mirroring the main class
    static final String TRAIN_ID_PATTERN   = "TRN-\\d{4}";
    static final String CARGO_CODE_PATTERN = "PET-[A-Z]{2}";

    // Helper method to validate Train ID
    private boolean validateTrainID(String trainID) {
        Pattern pattern = Pattern.compile(TRAIN_ID_PATTERN);
        Matcher matcher = pattern.matcher(trainID);
        return matcher.matches();
    }

    // Helper method to validate Cargo Code
    private boolean validateCargoCode(String cargoCode) {
        Pattern pattern = Pattern.compile(CARGO_CODE_PATTERN);
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    @Test
    void testRegex_ValidTrainID() {
        // TRN-1234 should be accepted as valid
        assertTrue(validateTrainID("TRN-1234"));
        assertTrue(validateTrainID("TRN-0000"));
        assertTrue(validateTrainID("TRN-9999"));
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        // Incorrectly formatted Train IDs should be rejected
        assertFalse(validateTrainID("TRAIN12"));
        assertFalse(validateTrainID("TRN12A"));
        assertFalse(validateTrainID("1234-TRN"));
        assertFalse(validateTrainID("TRN_1234"));
        assertFalse(validateTrainID("trn-1234")); // lowercase
    }

    @Test
    void testRegex_ValidCargoCode() {
        // PET-AB should be accepted as valid
        assertTrue(validateCargoCode("PET-AB"));
        assertTrue(validateCargoCode("PET-ZZ"));
        assertTrue(validateCargoCode("PET-AA"));
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        // Incorrectly formatted Cargo Codes should be rejected
        assertFalse(validateCargoCode("PET-ab")); // lowercase
        assertFalse(validateCargoCode("PET123"));  // no hyphen
        assertFalse(validateCargoCode("AB-PET"));  // wrong order
        assertFalse(validateCargoCode("pet-AB"));  // lowercase prefix
        assertFalse(validateCargoCode("PET-1A"));  // digit in suffix
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        // Train ID must have exactly 4 digits after TRN-
        assertFalse(validateTrainID("TRN-123"));   // 3 digits
        assertFalse(validateTrainID("TRN-12345")); // 5 digits
        assertFalse(validateTrainID("TRN-12"));    // 2 digits
        assertFalse(validateTrainID("TRN-1"));     // 1 digit
        assertTrue(validateTrainID("TRN-1234"));   // exactly 4 digits
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        // Cargo Code must have exactly 2 uppercase letters after PET-
        assertFalse(validateCargoCode("PET-ab")); // lowercase
        assertFalse(validateCargoCode("PET-Ab")); // mixed case
        assertFalse(validateCargoCode("PET-aB")); // mixed case
        assertTrue(validateCargoCode("PET-AB"));  // valid uppercase
        assertTrue(validateCargoCode("PET-XY"));  // valid uppercase
    }

    @Test
    void testRegex_EmptyInputHandling() {
        // Empty strings should return invalid for both validations
        assertFalse(validateTrainID(""));
        assertFalse(validateCargoCode(""));
    }

    @Test
    void testRegex_ExactPatternMatch() {
        // Extra characters beyond pattern should be rejected
        assertFalse(validateTrainID("TRN-1234X"));   // extra char at end
        assertFalse(validateTrainID("XTRN-1234"));   // extra char at start
        assertFalse(validateTrainID(" TRN-1234"));   // leading space
        assertFalse(validateTrainID("TRN-1234 "));   // trailing space

        assertFalse(validateCargoCode("PET-ABC"));   // 3 letters instead of 2
        assertFalse(validateCargoCode("PET-A"));     // 1 letter instead of 2
        assertFalse(validateCargoCode("XPET-AB"));   // extra char at start
        assertFalse(validateCargoCode("PET-ABX"));   // extra char at end
    }
}