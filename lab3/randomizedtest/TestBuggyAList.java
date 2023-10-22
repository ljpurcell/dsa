package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            correct.addLast(i);
            buggy.addLast(i);
        }

        assertEquals(correct.size(), buggy.size());

        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correctSize = correct.size();
                int buggySize = buggy.size();
                System.out.println("Correct size: " + correctSize + ". Buggy size: " + buggySize);
                assertEquals(correctSize, buggySize);
            } else if (correct.size() > 0) {
                if (operationNumber == 2) {
                    // getLast
                    int correctLast = correct.getLast();
                    int buggyLast = buggy.getLast();
                    System.out.println("Correct last: " + correctLast + ". Buggy last: " + buggyLast);
                    assertEquals(correctLast, buggyLast);
                } else if (operationNumber == 3) {
                    // removeLast
                    int correctLast = correct.removeLast();
                    int buggyLast = buggy.removeLast();
                    System.out.println("Correct removeLast: " + correctLast + ". Buggy removeLast: " + buggyLast);
                    assertEquals(correctLast, buggyLast);
                }
            }
        }
    }
}
