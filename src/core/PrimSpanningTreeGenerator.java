package core;

import core.util.BinaryHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Min Spanning Tree Generation, Prim's implementation,
 * uses Binary Heap to decide the fringe minimum value vertex
 *
 * @param <E>
 */
public class PrimSpanningTreeGenerator<E> extends AbstractSpanningTreeGenerator<E> {
    List<Graph.Vertex<E>> inTree = new ArrayList<Graph.Vertex<E>>();

    BinaryHeap<VertexWDist<E>> minHeap = new BinaryHeap<VertexWDist<E>>(256);

    public PrimSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    public void traverse() {
        for (Graph.Vertex<E> eVertex : g.getVertices()) {
            Node<E> node = new Node<E>(eVertex, false);
            v2NodeMap.put(eVertex, node);
            VertexWDist<E> x = new VertexWDist<E>(eVertex);
            minHeap.insert(x);
        }
        //choose an arbitrary source
        Graph.Vertex<E> start = g.getVertices().iterator().next();
        setDistance(start, null);
        generateTree(start);
        createMinSpanningSubGraph();


    }

    /**
     * Represent the Min Spanning Tree as a sub graph of the original Graph
     */
    private void createMinSpanningSubGraph() {
        for (Graph.Vertex<E> vertex : inTree) {
            Graph.Vertex<E> p = parent(vertex);
            if (p != null) {
                spanningTree.insertEdge(p.unwrap(), vertex.unwrap(), vertex.edgeWeight);
            }

        }

    }

    protected void generateTree(Graph.Vertex<E> start) {
        if ((start == null) || (inTree.size() == g.getVertices().size())) {
            return;
        }
        if (!processed(start)) {
            inTree.add(start);
            updateDistances(start);
            markProcessed(start);
        }
        generateTree(getMinimum());


    }

    private Graph.Vertex<E> getMinimum() {
        VertexWDist<E> vertexWDist = minHeap.extractTop();
        return vertexWDist != null ? vertexWDist.wrapped : null;
    }

    private void updateDistances(Graph.Vertex<E> start) {
        for (Graph.Vertex<E> tV : g.getAdjList(start)) {
            setDistance(tV, start);

        }

    }

    private void setDistance(Graph.Vertex<E> v, Graph.Vertex<E> parent) {
        if (processed(v)) {
            return;
        }
        Node<E> n = v2NodeMap.get(v);
        if (parent == null) {
            n.distFromSource = 0;
            n.parent = parent;
        }
        if ((n.distFromSource == null) || (n.distFromSource.compareTo(v.edgeWeight) > 0)) {
            n.distFromSource = v.edgeWeight;
            n.parent = parent;
            VertexWDist<E> key = new VertexWDist<E>(v);
            int index = minHeap.getKeyIndex(key);
            minHeap.decreaseKey(key, index);
        }

    }


    class VertexWDist<E> implements Comparable<VertexWDist<E>> {
        Graph.Vertex<E> wrapped = null;

        VertexWDist(Graph.Vertex<E> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public int compareTo(VertexWDist<E> o) {
            Node nThis = v2NodeMap.get(this.wrapped);
            Node nOther = v2NodeMap.get(o.wrapped);
            if (nOther.distFromSource == null) {
                return -1;
            } else if (nThis.distFromSource == null) {
                return 1;
            } else {
                return nThis.distFromSource.compareTo(nOther.distFromSource);

            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            VertexWDist that = (VertexWDist) o;

            if (!wrapped.equals(that.wrapped)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return wrapped.hashCode();
        }
    }

}                                                  
                  