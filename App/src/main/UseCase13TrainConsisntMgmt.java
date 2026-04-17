package main;

import java.util.*;
import java.util.stream.Collectors;

class Bogie {
    private String type;
    private int capacity;

    public Bogie(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return type + " (" + capacity + ")";
    }
}

public class UseCase13TrainConsistMgmt {

    public static List<Bogie> filterWithLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                result.add(b);
            }
        }
        return result;
    }

    public static List<Bogie> filterWithStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 50),
                new Bogie("First Class", 65),
                new Bogie("Goods", 40),
                new Bogie("Cylindrical", 80)
        );

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = filterWithLoop(bogies);
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        List<Bogie> streamResult = filterWithStream(bogies);
        long endStream = System.nanoTime();

        System.out.println("Loop Result: " + loopResult);
        System.out.println("Stream Result: " + streamResult);

        System.out.println("Loop Time: " + (endLoop - startLoop) + " ns");
        System.out.println("Stream Time: " + (endStream - startStream) + " ns");
    }
}

    // Stream-based filtering
    public static List<Bogie> filterWithStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();

        // Sample dataset
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 65));
        bogies.add(new Bogie("Goods Rectangular", 40));
        bogies.add(new Bogie("Goods Cylindrical", 80));

        // LOOP PERFORMANCE
        long startLoop = System.nanoTime();
        List<Bogie> loopResult = filterWithLoop(bogies);
        long endLoop = System.nanoTime();

        long loopTime = endLoop - startLoop;

        // STREAM PERFORMANCE
        long startStream = System.nanoTime();
        List<Bogie> streamResult = filterWithStream(bogies);
        long endStream = System.nanoTime();

        long streamTime = endStream - startStream;

        // Output
        System.out.println("Loop Result: " + loopResult);
        System.out.println("Stream Result: " + streamResult);

        System.out.println("Loop Execution Time: " + loopTime + " ns");
        System.out.println("Stream Execution Time: " + streamTime + " ns");
    }
}
