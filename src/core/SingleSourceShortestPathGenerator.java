package core;

import java.util.LinkedList;

/**
 * Single source shortest path on Weighted Directed Graphs
 * Implements Dijkstra's algorithm
 *
 * @param <E>
 */
public class SingleSourceShortestPathGenerator<E> extends PrimSpanningTreeGenerator<E> {

    private Graph.Vertex<E> startAt;
    private Graph.Vertex<E> endAt;

    public SingleSourceShortestPathGenerator(Graph<E> graph, E startAt) {
        super(graph);
        this.startAt = new Graph.Vertex<E>(startAt);
    }

    @Override
    protected Graph.Vertex<E> getStartVertex() {
        return startAt;
    }


    public LinkedList<Graph.Vertex<E>> getShortestPath(E to) {
        LinkedList<Graph.Vertex<E>> sp = new LinkedList<Graph.Vertex<E>>();
        Graph.Vertex<E> vTo = new Graph.Vertex<E>(to);
        for (Graph.Vertex<E> v : inTree) {
            if (vTo.equals(v)) {
                vTo = v;
                break;
            }
        }

        Node<E> p = v2NodeMap.get(vTo);
        Graph.Vertex<E> parent = p.parent;
        sp.add(vTo);
        sp.add(parent);
        while (parent != null && !parent.equals(startAt)) {
            parent = v2NodeMap.get(parent).parent;
            sp.add(parent);
        }

        return sp;
    }

    @Override
    protected void setDistance(Graph.Vertex<E> v, Graph.Vertex<E> parent) {
        Node<E> n = getOrCreateNode(v);
        if (parent == null) {
            n.distFromSource = getZeroWeight(n);
            n.parent = parent;
            updateHeap(v);
            return;
        }
        Node<E> pN = v2NodeMap.get(parent);

        if (n.distFromSource == null) {
            n.distFromSource = sumDistances(v, pN);
            n.parent = parent;
            updateHeap(v);
        }
        Comparable distViaParent = null;
        if ((distViaParent = sumDistances(v, pN)).compareTo(n.distFromSource) < 0) {
            n.distFromSource = distViaParent;
            n.parent = parent;
            updateHeap(v);
        }

    }

    private void updateHeap(Graph.Vertex<E> v) {
        VertexWDist<E> key = new VertexWDist<E>(v);
        int index = minHeap.getKeyIndex(key);
        minHeap.decreaseKey(key, index);
    }

    private Comparable sumDistances(Graph.Vertex<E> v, Node<E> pN) {
        if (v.edgeWeight instanceof EdgeWeight) {
            return (Comparable) ((EdgeWeight) pN.distFromSource).add((EdgeWeight) v.edgeWeight);
        } else {
            int dTillParent = ((Integer) pN.distFromSource);
            int eW = (Integer) v.edgeWeight;
            return (dTillParent + eW);
        }
    }
}
                              