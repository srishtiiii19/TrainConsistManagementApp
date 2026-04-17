package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UseCase13TrainConsistMgmtTest {

    private List<Bogie> getSampleBogies() {
        return Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 50),
                new Bogie("First Class", 65),
                new Bogie("Goods", 40),
                new Bogie("Cylindrical", 80)
        );
    }

    @Test
    void testLoopFilteringLogic() {
        List<Bogie> result = UseCase13TrainConsistMgmt.filterWithLoop(getSampleBogies());
        for (Bogie b : result) {
            assertTrue(b.getCapacity() > 60);
        }
    }

    @Test
    void testStreamFilteringLogic() {
        List<Bogie> result = UseCase13TrainConsistMgmt.filterWithStream(getSampleBogies());
        for (Bogie b : result) {
            assertTrue(b.getCapacity() > 60);
        }
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> bogies = getSampleBogies();

        List<Bogie> loopResult = UseCase13TrainConsistMgmt.filterWithLoop(bogies);
        List<Bogie> streamResult = UseCase13TrainConsistMgmt.filterWithStream(bogies);

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long start = System.nanoTime();
        UseCase13TrainConsistMgmt.filterWithLoop(getSampleBogies());
        long end = System.nanoTime();

        assertTrue((end - start) > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Test", i));
        }

        List<Bogie> result = UseCase13TrainConsistMgmt.filterWithStream(bogies);

        for (Bogie b : result) {
            assertTrue(b.getCapacity() > 60);
        }
    }
}