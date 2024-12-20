package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing a = new AListNoResizing();
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        BuggyAList b = new BuggyAList();
        b.addLast(4);
        b.addLast(5);
        b.addLast(6);
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> A = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                A.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeA = A.size();
                assertEquals(sizeL, sizeA);
            } else if (operationNumber == 2) {
                if (L.size() != 0 && A.size() != 0) {
                    int lastItemL = L.getLast();
                    int lastItemA = A.getLast();
                    assertEquals(lastItemL, lastItemA);
                }
            } else if (operationNumber == 3) {
                if (L.size() != 0 && A.size() != 0) {
                    int removedItemL = L.removeLast();
                    int removedItemA = A.removeLast();
                    assertEquals(removedItemL, removedItemA);
                }
            }
        }
    }
}
