package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
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
    public void testSquarePrimesFirstly() {
        IntList lst = IntList.of(7, 15, 16, 12, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49 -> 15 -> 16 -> 12 -> 18", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimesLastly() {
        IntList lst = IntList.of(14, 15, 16, 12, 17);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 12 -> 289", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimesWithNOPrime() {
        IntList lst = IntList.of(14, 15, 16, 12, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 12 -> 18", lst.toString());
        assertFalse(changed);
    }
    @Test
    public void testSquarePrimesWithOneElement() {
        IntList lst = IntList.of(7);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimesEmpty() {
        IntList lst = IntList.of();
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals(null, lst);
        assertFalse(changed);
    }
    @Test
    public void testSquarePrimesRowOfPrimes() {
        IntList lst = IntList.of(7, 11, 17, 19, 23);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49 -> 121 -> 289 -> 361 -> 529", lst.toString());
        assertTrue(changed);
    }
}
