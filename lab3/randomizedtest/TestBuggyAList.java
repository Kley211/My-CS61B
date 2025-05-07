package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import javax.print.DocFlavor;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void ThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> wrong = new BuggyAList<>();

        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);

        wrong.addLast(4);
        wrong.addLast(5);
        wrong.addLast(6);

        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());
    }

    @Test
    public void randomizedTest() {

            AListNoResizing<Integer> correct = new AListNoResizing<>();
            BuggyAList<Integer> broken = new BuggyAList<>();

            int N = 5000;
            for (int i = 0; i < N; i += 1) {
                int operationNumber = StdRandom.uniform(0, 4);
                if (operationNumber == 0) {
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    correct.addLast(randVal);
                    broken.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
                } else if (operationNumber == 1) {
                    // size
                    assertEquals(correct.size(), broken.size());
//                System.out.println("size: " + size);
                } else if (operationNumber == 2 && correct.size() > 0 && broken.size() > 0) {
                    // getLast
                    assertEquals(correct.getLast(), broken.getLast());
//                System.out.println("getLast(): " + last);
                } else if (operationNumber == 3 && correct.size() > 0 && broken.size() > 0) {
                    // removeLast
                    assertEquals(correct.removeLast(), broken.removeLast());
//                System.out.println("removeLast(): " + last);
                }
            }
        }
}

