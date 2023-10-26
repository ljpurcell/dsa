package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<Integer> deque1 = new LinkedListDeque<Integer>();

        assertTrue("A newly initialized LLDeque should be empty", deque1.isEmpty());
        deque1.addFirst(23);

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, deque1.size());
        assertFalse("deque1 should now contain 1 item", deque1.isEmpty());

        deque1.addLast(42);
        assertEquals(2, deque1.size());

        deque1.addLast(99);
        assertEquals(3, deque1.size());
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> deque1 = new LinkedListDeque<Integer>();

        // should be empty
        assertTrue("deque1 should be empty upon initialization", deque1.isEmpty());

        deque1.addFirst(10);
        // should not be empty
        assertFalse("deque1 should contain 1 item", deque1.isEmpty());

        deque1.removeFirst();
        // should be empty
        assertTrue("deque1 should be empty after removal", deque1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> deque1 = new LinkedListDeque<Integer>();
        deque1.addFirst(3);

        deque1.removeLast();
        deque1.removeFirst();
        deque1.removeLast();
        deque1.removeFirst();

        int size = deque1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String> deque1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> deque2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> deque3 = new LinkedListDeque<Boolean>();

        deque1.addFirst("string");
        deque2.addFirst(3.14159);
        deque3.addFirst(true);

        String s = deque1.removeFirst();
        double d = deque2.removeFirst();
        boolean b = deque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> deque1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", deque1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", deque1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> deque1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            deque1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) deque1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) deque1.removeLast(), 0.0);
        }
    }

    @Test
    public void testGetMethods() {
        LinkedListDeque<String> deque1 = new LinkedListDeque<String>();
        deque1.addFirst("This is item ONE");
        deque1.addLast("Item two");
        deque1.addLast("Item three");
        deque1.addLast("This is the one you want");

        String resultGet = deque1.get(3);
        String resultGetRecursive = deque1.getRecursive(3);

        String resultGetNoElement = deque1.get(400);
        String resultGetRecursiveNoElement = deque1.getRecursive(400);

        assertEquals("This is the one you want", resultGet);
        assertEquals("This is the one you want", resultGetRecursive);
        assertNull("GET method should have returned null", resultGetNoElement);
        assertNull("GET RECURSIVE method should have returned null", resultGetRecursiveNoElement);
    }

    @Test
    public void testEqualsMethod() {
        LinkedListDeque<String> deque1 = new LinkedListDeque<String>();
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<Integer>();
        LinkedListDeque<String> deque3 = new LinkedListDeque<String>();
        LinkedListDeque<String> deque4 = new LinkedListDeque<String>();

        deque1.addLast("This");
        deque1.addLast("is");
        deque1.addLast("the same");

        deque2.addLast(1);
        deque2.addLast(99);

        deque3.addLast("This");
        deque3.addLast("is");
        deque3.addLast("the same");

        deque4.addLast("Different");
        deque4.addLast("items");
        deque4.addLast("this");
        deque4.addLast("time");

        assertEquals(deque1, deque3);
        assertNotEquals(deque1, deque2);
        assertNotEquals(deque1, deque4);
    }

    @Test
    public void fillUpEmptyAndFillUpAgainTest() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<Integer>();
        LinkedListDeque<Integer> firstEightDeque = new LinkedListDeque<Integer>();
        LinkedListDeque<Integer> revFirstEightDeque = new LinkedListDeque<Integer>();
        LinkedListDeque<Integer> testDeque = new LinkedListDeque<Integer>();
        LinkedListDeque<Integer> revTestDeque = new LinkedListDeque<Integer>();

        assertEquals(emptyDeque, testDeque);

        for (int i = 0; i < 8; i++) {
            testDeque.addLast(i);
            firstEightDeque.addLast(i);
            revFirstEightDeque.addFirst(i);
            revTestDeque.addFirst(i);
        }

        assertEquals(firstEightDeque, testDeque);
        assertEquals(revFirstEightDeque, revTestDeque);

        while (!testDeque.isEmpty()) {
            testDeque.removeLast();
            revTestDeque.removeFirst();
        }

        assertEquals(emptyDeque, testDeque);
        assertEquals(emptyDeque, revTestDeque);

        for (int i = 0; i < 8; i++) {
            testDeque.addFirst(i);
            revTestDeque.addLast(i);
        }

        assertEquals(firstEightDeque, revTestDeque);
        assertEquals(revFirstEightDeque, testDeque);
    }

    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<Integer>();

        for (int i = 0; i < 3; i++) {
            deque.addLast(i);
        }

        assertEquals(3, deque.size());

        assertEquals(Integer.valueOf(2), deque.removeLast());
        assertEquals(Integer.valueOf(1), deque.removeLast());
        assertEquals(Integer.valueOf(0), deque.removeLast());
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<Integer>();

        int N = 5000;
        int sizeOfDeque = 0;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                deque.addLast(randVal);
                sizeOfDeque += 1;
            } else if (operationNumber == 1) {
                // size
                int dequeSize = deque.size();
                assertEquals(sizeOfDeque, dequeSize);
            }
        }
    }

    @Test
    public void isEqualToArrayDequeWithSameElementsTest() {
        ArrayDeque<Boolean> arrayDeque = new ArrayDeque<Boolean>();
        LinkedListDeque<Boolean> linkedListDeque = new LinkedListDeque<Boolean>();

        arrayDeque.addLast(true);
        arrayDeque.addLast(false);
        arrayDeque.addLast(true);

        linkedListDeque.addLast(true);
        linkedListDeque.addLast(false);
        linkedListDeque.addLast(true);

        assertEquals(linkedListDeque, arrayDeque);
    }
}
