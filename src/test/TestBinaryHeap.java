package test;

import core.util.BinaryHeap;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test Binary heap extraction
 */
public class TestBinaryHeap {
    @Test
    public void testExtract() {
        Integer[] ar = new Integer[]{8, 17, 6, 55, 4, 35, 2};
        BinaryHeap<Integer> bi = new BinaryHeap<Integer>();

        for (Integer integer : ar) {
            bi.insert(integer);
        }
        Integer oldKey = new Integer(10);
        bi.insert(oldKey);
        assertTrue(bi.extractTop().equals(2));
        bi.decreaseKey(5, bi.getKeyIndex(oldKey));
        assertTrue(bi.extractTop().equals(4));
        assertTrue(bi.extractTop().equals(5));
        assertTrue(bi.extractTop().equals(6));
        assertTrue(bi.extractTop().equals(8));
        assertTrue(bi.extractTop().equals(17));
        assertTrue(bi.extractTop().equals(35));
        assertTrue(bi.extractTop().equals(55));


    }

    @Test
    public void allNull() {
        Integer[] ar = new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        BinaryHeap<Integer> bi = new BinaryHeap<Integer>();
        for (Integer integer : ar) {
            bi.insert(integer);
        }

    }


}
