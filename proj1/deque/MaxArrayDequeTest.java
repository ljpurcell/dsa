package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class MaxArrayDequeTest {

    @Test
    public void isEmptyTest() {

        Comparator<Integer> ic = MaxArrayDequeComparators.getIntegerComparator();

        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(ic);

        assertTrue(deque.isEmpty());

        deque.addFirst(1);

        assertFalse(deque.isEmpty());

        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();

        assertTrue(deque.isEmpty());
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void sizeTest() {

        Comparator<Integer> ic = MaxArrayDequeComparators.getIntegerComparator();

        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(ic);

        assertTrue("A newly initialized MaxArrayDeque should be empty", deque.isEmpty());
        deque.addFirst(23);

        assertEquals(1, deque.size());
        assertFalse("deque should now contain 1 item", deque.isEmpty());

        deque.addLast(42);
        assertEquals(2, deque.size());

        deque.addLast(99);
        assertEquals(3, deque.size());
    }

    @Test
    /** Checks the MaxArrayDeque returns the maximum value correctly */
    public void getMaxTestInteger() {

        Comparator<Integer> ic = MaxArrayDequeComparators.getIntegerComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(ic);

        deque.addFirst(1);
        deque.addFirst(119);
        deque.addFirst(2);
        deque.addFirst(17);

        int max = deque.max();
        assertEquals(119, max);
    }

    @Test
    /** Checks the MaxArrayDeque returns the maximum value correctly */
    public void getMaxTestString() {

        Comparator<String> stringComparator = MaxArrayDequeComparators.getStringComparator();
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(stringComparator);

        deque.addFirst("a");
        deque.addFirst("carnival");
        deque.addFirst("zebra");

        String max = deque.max();
        assertEquals("zebra", max);
    }


    @Test
    /** Checks the MaxArrayDeque returns the maximum value correctly */
    public void getMaxTestIfDequeEmpty() {

        Comparator<Integer> ic = MaxArrayDequeComparators.getIntegerComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(ic);

        assertNull(deque.max());
    }

    @Test
    /** Checks the MaxArrayDeque returns the maximum value correctly */
    public void getMaxTestIfDequeEmptyThenFilled() {

        Comparator<Integer> ic = MaxArrayDequeComparators.getIntegerComparator();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(ic);

        assertNull(deque.max());

        deque.addFirst(1);
        deque.addFirst(15);
        deque.addLast(1);
        deque.addLast(1199);

        int max = deque.max();
        assertEquals(1199, max);
    }
}
