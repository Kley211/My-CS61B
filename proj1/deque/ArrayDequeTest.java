package deque;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest()
    {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        assertTrue("The new ArratList must be empty", lld1.isEmpty());

        lld1.addFirst("K");
        assertEquals(1,lld1.size());

        lld1.addLast("ley");
        assertEquals(2,lld1.size());

        lld1.addLast("yes");
        assertEquals(3,lld1.size());

        String item = lld1.removeLast();
        assertEquals(2,lld1.size());
        System.out.println(item);

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    public void addRemoveTest() {



        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
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
    public void expandDeque()
    {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(4);
        lld1.addLast(3);
        lld1.addLast(2);
        lld1.addLast(1);

        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(0);

        lld1.addFirst(8);
        lld1.addFirst(7);
        lld1.addLast(0);


    }
}
