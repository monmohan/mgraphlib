package org.adm.graph.core.util;

import org.adm.graph.core.IHeap;

import java.util.Stack;


/**
 * Represents a Binomial Heap as linked list of Binomial Trees
 * Each Tree is head ordered. Also, a proper Heap union ensures
 * that B(k) appears in a n-node Heap if and only if k+1-th bit of
 * binary representation of n is 1. This ensures O(lgn) number of trees.
 *
 * @param <E>
 * @author monmohan singh
 */
public class BinomialHeap<E extends Comparable<E>> implements IHeap<E> {
    TreeNode<E> head;

    public BinomialHeap() {
    }

    public BinomialHeap(E e) {
        head = new TreeNode<E>(e);
    }

    public BinomialHeap<E> insert(E e) {
        BinomialHeap<E> h = new BinomialHeap<E>(e);
        this.union(h);
        return this;
    }

    @Override
    public E peek() {
        TreeNode<E> current = head; //first node in the list
        E top = current.e;
        E next = null;
        while (current != null) {
            next = current.sibling.e;
            top = top.compareTo(next) < 0 ? next : top;
            current = current.sibling;
        }
        return top;

    }

    int getNumTrees() {
        int i = 0;
        TreeNode<E> current = head;
        while (current != null) {
            i++;
            current = current.sibling;
        }
        return i; //TODO cache
    }

    public TreeNode<E> getHeadTree() {
        return head;
    }

    public void addTree(TreeNode<E> headOfTree) {
        if (head == null) {
            head = headOfTree;
        } else {
            //append to the end
            TreeNode<E> cur = head;
            while (cur.sibling != null) {
                cur = cur.sibling;
            }
            cur.sibling = headOfTree;
        }
    }

    public BinomialHeap<E> union(BinomialHeap<E> h2) {
        merge(h2);
        TreeNode<E> current = head;
        TreeNode<E> previous = null;
        TreeNode<E> next = null;
        TreeNode<E> sibNext = null;
        while ((next = current.sibling) != null) {
            sibNext = next.sibling;
            if (isDegreeEqual(current, next)) {
                if (isDegreeEqual(next, sibNext)) {
                    previous = current;
                    current = current.sibling;

                } else {
                    //append one tree to another
                    if (current.e.compareTo(next.e) >= 0) {
                        current.sibling = next.child;
                        next.child = current;
                        if (previous != null) previous.sibling = next;
                        if (current == head) head = next;//move the head pointer for this heap
                        current = next;
                    } else {
                        TreeNode<E> temp = current.child;
                        current.child = next;
                        next.sibling = temp;
                        current.sibling = sibNext;
                    }

                }
            } else {
                previous = current;
                current = current.sibling;
            }
        }
        return this;
    }

    private boolean isDegreeEqual(TreeNode<E> current, TreeNode<E> previous) {
        return !(current == null || previous == null) && current.getDegree() == previous.getDegree();
    }

    /**
     * Merge the given heap to thi and sort root nodes by degree
     *
     * @param h2
     * @return
     */
    private BinomialHeap<E> merge(BinomialHeap<E> h2) {
        BinomialHeap<E> result = new BinomialHeap<E>();
        TreeNode<E> p1 = head;
        TreeNode<E> temp = null;
        TreeNode<E> p2 = h2.head;
        while (p1 != null && p2 != null) {
            if (p1.getDegree() <= p2.getDegree()) {
                temp = p1.sibling;
                p1.sibling = null;
                result.addTree(p1);
                p1 = temp;
            } else {
                temp = p2.sibling;
                p2.sibling = null;
                result.addTree(p2);
                p2 = temp;
            }
        }
        TreeNode<E> rest = p1 == null ?
                p2 == null ? null : p2
                : p1;
        if (rest != null) {
            result.addTree(rest);
        }
        this.head = result.head;
        return this;
    }

    @Override
    public E extractTop() {
        TreeNode<E> current = head; //first node in the list        
        TreeNode<E> prev = null;
        TreeNode<E> top = current;
        TreeNode<E> topprev = prev;
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        while (current != null) {
            if (top.e.compareTo(current.e) > 0) {
                top = current;
                topprev = prev;
            }
            prev = current;
            current = current.sibling;

        }
        if (topprev == null) {
            //change head
            head = head.sibling;
        } else {
            //remove link
            topprev.sibling = top.sibling;

        }
        top.sibling = null;
        E result = top.e;
        //create new Binomial Heap
        //take all children of the parent and reverse pointers
        Stack<TreeNode<E>> rev = new Stack<TreeNode<E>>();
        current = top.child;

        if (current != null) {
            while (current != null) {
                rev.push(current);
                current = current.sibling;
            }
            current = rev.isEmpty() ? null : rev.pop();
            TreeNode<E> root = current;
            TreeNode<E> temp = null;
            while (!rev.isEmpty()) {
                temp = rev.pop();
                temp.sibling = null;
                current.sibling = temp;
                current = current.sibling;
            }
            current.sibling = null;

            BinomialHeap<E> nH = new BinomialHeap<E>();
            nH.addTree(root);
            this.union(nH);
        }
        return result;


    }

    private boolean isEmpty() {
        return head == null;
    }

    @Override
    public void changeKey(E newKey, E oldKey) {
        throw new UnsupportedOperationException(" TODO");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        TreeNode<E> cTree = head;
        int i = 0;
        while (cTree != null) {
            sb.append("Tree Num ").append(++i).append("\n");
            sb.append(cTree);
            cTree = cTree.sibling;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinomialHeap<E> that = (BinomialHeap<E>) o;
        if (this.getNumTrees() != that.getNumTrees()) return false;
        TreeNode<E> c1 = head;
        TreeNode<E> c2 = that.head;
        boolean result = true;
        while (c1 != null) {
            result = result && (c1.equals(c2));
            c1 = c1.sibling;
            c2 = c2.sibling;
        }
        return result;

    }

    @Override
    public int hashCode() {
        if (head == null) return 0;
        int hc = 31;
        TreeNode<E> current = head;
        while (current != null) {
            hc = hc + 19 * current.hashCode();
            current = current.sibling;
        }
        return hc;

    }
}
