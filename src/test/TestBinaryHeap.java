package test;

import org.adm.graph.core.util.BinaryHeap;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test Binary heap extraction
 */
public class TestBinaryHeap {
    @Test
    public void testExtract() {
        System.out.println("Testing Min Heap..");
        Integer[] ar = new Integer[]{8, 17, 6, 55, 4, 35, 2};
        BinaryHeap<Integer> bi = new BinaryHeap<Integer>();

        for (Integer integer : ar) {
            bi.insert(integer);
        }
        Integer oldKey = new Integer(10);
        bi.insert(oldKey);
        assertTrue(bi.extractTop().equals(2));
        bi.changeKey(15, oldKey);
        assertTrue(bi.extractTop().equals(4));
        assertTrue(bi.extractTop().equals(6));
        assertTrue(bi.extractTop().equals(8));
        assertTrue(bi.extractTop().equals(15));
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

    @Test
    public void testMaxHeap() {
        System.out.println("Testing Max Heap..");
        Element[] ar = new Element[]{new Element(10), new Element(20), new Element(30), new Element(40), new Element(50), new Element(60)};
        BinaryHeap<Element> bh = new BinaryHeap<Element>();
        for (Element element : ar) {
            bh.insert(element);
        }
        assertTrue(bh.extractTop().i.equals(60));
        assertTrue(bh.extractTop().i.equals(50));
        bh.insert(new Element(5));
        assertTrue(bh.extractTop().i.equals(40));
        assertTrue(bh.extractTop().i.equals(30));
        assertTrue(bh.extractTop().i.equals(20));
        assertTrue(bh.extractTop().i.equals(10));
        assertTrue(bh.extractTop().i.equals(5));

    }

    static class Element implements Comparable<Element> {
        Integer i;

        Element(Integer i) {
            this.i = i;
        }

        @Override
        public int compareTo(Element o) {
            return -1 * (this.i.compareTo(o.i));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            if (i != null ? !i.equals(element.i) : element.i != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i != null ? i.hashCode() : 0;
        }

        @Override
        public String toString() {
            return i.toString();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

}
