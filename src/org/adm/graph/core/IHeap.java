package org.adm.graph.core;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 7/28/12
 * Time: 10:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IHeap<E> {
    /**
     * @return the top most element on the heap without extracting
     */
    E peek();

    /**
     * @return the top most element and also remove it form Heap
     */
    E extractTop();

    /**
     * Change the value of a key in the heap (increase|decrease key
     * operations in standard Heap)
     *
     * @param newKey new key
     * @param oldKey old key
     */
    void changeKey(E newKey, E oldKey);

    /**
     * Insert an element to this heap
     *
     * @param e object (with derivable key) to insert
     * @return this object to allow chained inserts
     */
    IHeap<E> insert(E e);

}
