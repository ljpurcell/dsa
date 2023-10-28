package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", deque.isEmpty());
        deque.addFirst(23);

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, deque.size());
        assertFalse("deque should now contain 1 item", deque.isEmpty());

        deque.addLast(42);
        assertEquals(2, deque.size());

        deque.addLast(99);
        assertEquals(3, deque.size());
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        // should be empty
        assertTrue("deque should be empty upon initialization", deque.isEmpty());

        deque.addFirst(10);
        // should not be empty
        assertFalse("deque should contain 1 item", deque.isEmpty());

        deque.removeFirst();
        // should be empty
        assertTrue("deque should be empty after removal", deque.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.addFirst(3);

        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();

        int size = deque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> deque1 = new ArrayDeque<String>();
        ArrayDeque<Double> deque2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> deque3 = new ArrayDeque<Boolean>();

        deque1.addFirst("string");
        deque2.addFirst(3.14159);
        deque3.addFirst(true);

        String s = deque1.removeFirst();
        double d = deque2.removeFirst();
        boolean b = deque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", deque.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", deque.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            deque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) deque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) deque.removeLast(), 0.0);
        }
    }

    @Test
    public void testGetMethods() {
        ArrayDeque<String> deque = new ArrayDeque<String>();
        deque.addLast("This is item ONE");
        deque.addLast("Item two");
        deque.addLast("Item three");
        deque.addLast("This is the first one you want");

        String resultGet = deque.get(3);

        deque.addLast("This is the second one you want");

        String resultGetAfterWrap = deque.get(4);
        String resultGetNoElement = deque.get(400);

        deque.removeLast();
        String resultResultGetAfterWrapRemoval = deque.get(3);

        assertEquals("This is the first one you want", resultGet);
        assertEquals("This is the second one you want", resultGetAfterWrap);
        assertEquals("This is the first one you want", resultResultGetAfterWrapRemoval);
        assertNull("GET method should have returned null", resultGetNoElement);
    }

    @Test
    public void testEqualsMethod() {
        ArrayDeque<String> deque1 = new ArrayDeque<String>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<Integer>();
        ArrayDeque<String> deque3 = new ArrayDeque<String>();
        ArrayDeque<String> deque4 = new ArrayDeque<String>();

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
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> firstEightDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> revFirstEightDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> testDeque = new ArrayDeque<Integer>();
        ArrayDeque<Integer> revTestDeque = new ArrayDeque<Integer>();

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
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

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
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        int N = 50000;
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
            } else if (operationNumber == 2) {
                assertEquals(deque.get(deque.size() - 1), deque.removeLast());
                sizeOfDeque = sizeOfDeque >= 1 ? sizeOfDeque - 1 : sizeOfDeque;
            }
        }
    }

    @Test
    public void isEqualToLinkedListDequeWithSameElementsTest() {
        ArrayDeque<Boolean> arrayDeque = new ArrayDeque<Boolean>();
        LinkedListDeque<Boolean> linkedListDeque = new LinkedListDeque<Boolean>();

        arrayDeque.addLast(true);
        arrayDeque.addLast(false);
        arrayDeque.addLast(true);

        linkedListDeque.addLast(true);
        linkedListDeque.addLast(false);
        linkedListDeque.addLast(true);

        assertEquals(arrayDeque, linkedListDeque);
    }
}
