package org.adm.graph.core.util;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a Tree node.
 * A Tree Node has pointers to left child node, a sibling node, satellite data (E)
 * and parent node
 *
 * @param <E>
 * @author monmohan singh
 */
public class TreeNode<E> {
    public TreeNode(E e) {
        this.e = e;
    }

    TreeNode<E> parent;
    TreeNode<E> child;//left most child
    TreeNode<E> sibling;//right sibling
    E e;//data

    public int getDegree() {//TODO cache it
        int degree = 0;
        TreeNode<E> nodes = child;
        while (nodes != null) {
            degree++;
            nodes = nodes.sibling;
        }
        return degree;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(e);
        sb.append("\n");
        ArrayList<TreeNode<E>> children = new ArrayList<TreeNode<E>>();
        TreeNode<E> c = child;
        if (c == null) sb.append("-no-child-");
        while (c != null) {
            sb.append(c.e).append(",");
            children.add(c);
            c = c.sibling;
        }
        sb.append("\n");
        for (TreeNode<E> bn : children) {
            sb.append(bn.toString());
        }

        return sb.toString();
    }

    public TreeNode<E> getParent() {
        return parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public TreeNode<E> getChild() {
        return child;
    }

    public void setChild(TreeNode<E> child) {
        this.child = child;
    }

    public TreeNode<E> getSibling() {
        return sibling;
    }

    public void setSibling(TreeNode<E> sibling) {
        this.sibling = sibling;
    }

    public E getObject() {
        return e;
    }

    LinkedList<TreeNode<E>> getChildren() {
        LinkedList<TreeNode<E>> btn = new LinkedList<TreeNode<E>>();
        TreeNode<E> ch = child;
        while (ch != null) {
            btn.add(ch);
            ch = ch.sibling;
        }
        return btn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode that = (TreeNode) o;

        boolean result = (e != null ? e.equals(that.e) : that.e != null);
        result = result && (getDegree() == that.getDegree());
        if (result) {
            TreeNode<E> ch = child;
            TreeNode<E> ch2 = that.child;
            while (ch != null) {
                result = result && ch.equals(ch2);
                ch = ch.sibling;
                ch2 = ch2.sibling;
            }
        }
        return result;

    }

    @Override
    public int hashCode() {
        if (e == null) return 0;
        int hc = 31;
        TreeNode<E> current = child;
        while (current != null) {
            hc = hc + 19 * current.hashCode();
            current = current.sibling;
        }
        return hc;
    }
}
