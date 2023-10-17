package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSinglePrimeElement() {
        IntList lst = IntList.of(7);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49", lst.toString());
        assertTrue(changed);
    }

      @Test
    public void testSquarePrimesSingleCompositeElement() {
        IntList lst = IntList.of(8);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesFirstElementIsPrime() {
        IntList lst = IntList.of(13, 8, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("169 -> 8 -> 4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesLastElementIsPrime() {
        IntList lst = IntList.of(8, 9, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 9 -> 169", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesFirstElementIsComposite() {
        IntList lst = IntList.of(9, 8, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 8 -> 4", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesLastElementIsComposite() {
        IntList lst = IntList.of(8, 8, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 8 -> 12", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesTestWithPrimeOverTwenty() {
        IntList lst = IntList.of(8, 8, 23, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 8 -> 529 -> 12", lst.toString());
        assertTrue(changed);
    }

    @Test // TODO run debugger over this test
    public void testSquarePrimesTestWithLongList() {
        IntList lst = IntList.of(8, 8, 23, 12, 13, 10, 7, 31, 41);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("8 -> 8 -> 529 -> 12 -> 169 -> 10 -> 49 -> 961 -> 1681", lst.toString());
        assertTrue(changed);
    }
}
