package main;
import java.util.Arrays;

public class UseCase16TrainConsistMgmt {

    // Bubble Sort method
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // optimization: stop if already sorted
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {

        int[] capacities = {72, 56, 24, 70, 60};

        System.out.println("Before Sorting: " + Arrays.toString(capacities));

        bubbleSort(capacities);

        System.out.println("After Sorting: " + Arrays.toString(capacities));
    }
}