package test;

import core.util.BinaryHeap;

/**
 * Test Binary heap extraction
 */
public class TestBinaryHeap {
    public static void main(String[] args) {
        Integer[] ar = new Integer[]{8, 7, 6, 5, 4, 3, 2, 1};
        BinaryHeap<Integer> bi = new BinaryHeap<Integer>();

        for (Integer integer : ar) {
            bi.insert(integer);
        }
        System.out.println("bi = " + bi);
        testExtract(bi, "one");
        testExtract(bi, "two");
        testExtract(bi, "three");

    }

    private static void testExtract(BinaryHeap<Integer> bi, String message) {
        bi.extractTop();
        System.out.println("------------" + message + "----------");
        System.out.println("bi = " + bi);
    }
}
