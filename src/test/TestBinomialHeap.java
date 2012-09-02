package test;

import org.adm.graph.core.util.BinomialHeap;
import org.adm.graph.core.util.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 8/7/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBinomialHeap {
    @Test
    public void testCreation() {
        TreeNode<Integer> btn = createTree(5);
        System.out.println("btn = " + btn);
        BinomialHeap<Integer> bhi = new BinomialHeap<Integer>();
        bhi.addTree(btn);
        btn = createTree(10);
        bhi.addTree(btn);
        System.out.println("bhi = " + bhi);


    }

    private TreeNode<Integer> createTree(Integer rootVal) {
        TreeNode<Integer> btn = new TreeNode<Integer>(rootVal);
        TreeNode<Integer> ch = new TreeNode<Integer>(21);
        btn.setChild(ch);
        TreeNode<Integer> sib1 = new TreeNode<Integer>(22);
        ch.setSibling(sib1);
        sib1.setSibling(new TreeNode<Integer>(25));
        TreeNode<Integer> ch2 = new TreeNode<Integer>(31);
        ch.setChild(ch2);
        ch2.setSibling(new TreeNode<Integer>(34));
        return btn;
    }

    @Test
    public void testHeapUnion2() {
        BinomialHeap<Integer> bh = new BinomialHeap<Integer>(10);
        bh.insert(5);
        bh.insert(15);
        bh.insert(8);
        System.out.println("bh = " + bh);
        //make result
        TreeNode<Integer> res = new TreeNode<Integer>(5);
        TreeNode<Integer> ch1 = new TreeNode<Integer>(8);
        TreeNode<Integer> ch1sib = new TreeNode<Integer>(10);
        TreeNode<Integer> ch12 = new TreeNode<Integer>(15);
        res.setChild(ch1);
        ch1.setSibling(ch1sib);
        ch1.setChild(ch12);
        BinomialHeap<Integer> bhres = new BinomialHeap<Integer>();
        bhres.addTree(res);
        assertTrue(bh.equals(bhres));
    }

    @Test
    public void testHeapUnion() {
        BinomialHeap<Integer> bh = new BinomialHeap<Integer>(10);
        bh.insert(5);
        System.out.println("bh = " + bh);
        //make result
        TreeNode<Integer> res = new TreeNode<Integer>(5);
        TreeNode<Integer> ch1 = new TreeNode<Integer>(10);
        res.setChild(ch1);
        BinomialHeap<Integer> bhres = new BinomialHeap<Integer>();
        bhres.addTree(res);
        assertTrue(bh.equals(bhres));


    }

    @Test
    public void testHeapUnion3() {
        BinomialHeap<Integer> bh = new BinomialHeap<Integer>();
        for (int i = 1; i < 8; i++) {
            bh.insert(i);
        }


        //make result

    }

    @Test
    public void testExtractTop() {
        BinomialHeap<Integer> bi = new BinomialHeap<Integer>();
        Integer[] ar = new Integer[]{8, 17, 6, 55, 4, 35, 2};
        for (Integer integer : ar) {
            bi.insert(integer);
        }
        Integer oldKey = new Integer(10);
        bi.insert(oldKey);
        assertTrue(bi.extractTop().equals(2));
        //bi.changeKey(15, oldKey);
        assertTrue(bi.extractTop().equals(4));
        assertTrue(bi.extractTop().equals(6));
        assertTrue(bi.extractTop().equals(8));
        assertTrue(bi.extractTop().equals(10));
        assertTrue(bi.extractTop().equals(17));
        assertTrue(bi.extractTop().equals(35));
        assertTrue(bi.extractTop().equals(55));


    }


}
