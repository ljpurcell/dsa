package deque;

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
        assertFalse("lld1 should now contain 1 item", deque.isEmpty());

        deque.addLast(42);
        assertEquals(2, deque.size());

        deque.addLast(99);
        assertEquals(3, deque.size());
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
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
        ArrayDeque<String> deque = new ArrayDeque<>();
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
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        ArrayDeque<String> deque3 = new ArrayDeque<>();
        ArrayDeque<String> deque4 = new ArrayDeque<>();

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
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        ArrayDeque<Integer> firstEightDeque = new ArrayDeque<>();
        ArrayDeque<Integer> revFirstEightDeque = new ArrayDeque<>();
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        ArrayDeque<Integer> revTestDeque = new ArrayDeque<>();

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
}
