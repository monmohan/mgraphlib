package core.util;

/**
 * Simple Binary Heap with extract top, bubble up and bubble down operations
 * Objects implementinng Comparable can be added
 * To be used for impementing Prim's min spanning tree edge weight comparison
 *
 * @param <E>
 */
public class BinaryHeap<E extends Comparable<E>> {
    int capacity = 64;

    E[] heap = (E[]) new Comparable[capacity];
    int lastIdx = 0;

    public BinaryHeap() {
    }

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Insert an item on the heap
     * This can be used to build a heap by repeated insertions
     *
     * @param e item to be added to heap
     * @return this object
     */
    public BinaryHeap<E> insert(E e) {
        ensureCapacity();
        heap[lastIdx] = e;
        bubbleUp(e, lastIdx);
        lastIdx++;
        return this;
    }

    private void ensureCapacity() {
        if (lastIdx == capacity - 1) {
            capacity = 2 * capacity;
            E[] temp = (E[]) new Comparable[capacity];
            System.arraycopy(heap, 0, temp, 0, heap.length);
            heap = temp;
        }

    }

    private void bubbleUp(E e, int currentIdx) {
        int parentIdx = ((currentIdx + 1) / 2) - 1;
        if (parentIdx >= 0) {
            if (heap[parentIdx].compareTo(e) > 0) {
                heap[currentIdx] = heap[parentIdx];
                heap[parentIdx] = e;
            }
            bubbleUp(e, parentIdx);
        }
    }

    public E peek() {
        return heap[0];
    }

    public E extractTop() {
        E top = heap[0];
        if (top != null) {
            //move last to first
            heap[0] = heap[lastIdx - 1];
            lastIdx--;
            heap[lastIdx] = null;
            //now bubbleDown
            bubbleDown(0);
        }
        return top;
    }

    private void bubbleDown(int parentIdx) {
        int child1Idx = 2 * (parentIdx + 1) - 1;
        int child2Idx = child1Idx + 1;
        int minIdx;
        if (heap[child1Idx] == null) return;
        if (heap[child2Idx] == null || (heap[child1Idx].compareTo(heap[child2Idx]) <= 0)) {
            minIdx = child1Idx;
        } else {
            minIdx = child2Idx;
        }
        if (heap[minIdx].compareTo(heap[parentIdx]) < 0) {
            E temp = heap[parentIdx];
            heap[parentIdx] = heap[minIdx];
            heap[minIdx] = temp;
            bubbleDown(minIdx);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastIdx; i++) {
            E e = heap[i];
            sb.append(e).append("\n");

        }
        return sb.toString();
    }
}
