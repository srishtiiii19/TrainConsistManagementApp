package main;
import java.util.Arrays;

public class UseCase17TrainConsistMgmt {

    public static void sortBogieNames(String[] bogies) {
        Arrays.sort(bogies);
    }

    public static void main(String[] args) {

        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        System.out.println("Before Sorting: " + Arrays.toString(bogieNames));

        sortBogieNames(bogieNames);

        System.out.println("After Sorting: " + Arrays.toString(bogieNames));
    }
}