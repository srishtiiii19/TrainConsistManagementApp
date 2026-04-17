package main;
public class UseCase20TrainConsistMgmt {

    // Search with validation (Linear Search + fail-fast)
    public static boolean searchBogie(String[] bogieIds, String key) {

        // Fail-fast validation
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available for search");
        }

        for (String id : bogieIds) {
            if (id.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        String[] bogieIds = {"BG101","BG205","BG309"};
        String searchKey = "BG205";

        try {
            boolean found = searchBogie(bogieIds, searchKey);

            if (found) {
                System.out.println("Bogie found: " + searchKey);
            } else {
                System.out.println("Bogie not found: " + searchKey);
            }

        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}