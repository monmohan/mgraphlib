package org.adm.graph.core;

import org.adm.graph.core.util.BinaryHeap;

import java.util.HashSet;
import java.util.Set;

/**
 * Min Spanning Tree Generation, Prim's implementation,
 * uses Binary Heap to decide the fringe minimum value vertex
 *
 * @param <E>
 */
public class PrimSpanningTreeGenerator<E> extends AbstractSpanningTreeGenerator<E> {
    Set<Graph.Vertex<E>> inTree = new HashSet<Graph.Vertex<E>>();

    BinaryHeap<VertexWDist<E>> minHeap = new BinaryHeap<VertexWDist<E>>(256);

    public PrimSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
        //initialize Min Heap
        for (Graph.Vertex<E> eVertex : g.getVertices()) {
            getOrCreateNode(eVertex);
            VertexWDist<E> x = new VertexWDist<E>(eVertex);
            minHeap.insert(x);
        }

    }

    @Override
    public void traverse() {
        Graph.Vertex<E> start = getStartVertex();
        traverse(start);
    }

    @Override
    public void traverse(Graph.Vertex<E> start) {
        setDistance(start, null);
        generateTree(start);
        buildSpanningTree();
    }

    protected Graph.Vertex<E> getStartVertex() {
        //choose an arbitrary source
        return g.getVertices().iterator().next();
    }

    protected Node<E> getOrCreateNode(Graph.Vertex<E> eVertex) {
        Node<E> node = v2NodeMap.get(eVertex);
        if (node == null) {
            node = new Node<E>(eVertex, false);
            v2NodeMap.put(eVertex, node);
        }
        return node;
    }

    /**
     * Represent the Min Spanning Tree as a sub graph of the original Graph
     */
    protected void buildSpanningTree() {
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

    protected Graph.Vertex<E> getMinimum() {
        VertexWDist<E> vertexWDist = minHeap.extractTop();
        return vertexWDist != null ? vertexWDist.wrapped : null;
    }

    protected void updateDistances(Graph.Vertex<E> start) {
        for (Graph.Vertex<E> tV : g.getAdjList(start)) {
            setDistance(tV, start);

        }

    }

    protected void setDistance(Graph.Vertex<E> v, Graph.Vertex<E> parent) {
        if (processed(v)) {
            return;
        }
        Node<E> n = v2NodeMap.get(v);
        if (parent == null) {
            n.distFromSource = getZeroWeight(n);
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

    protected Comparable getZeroWeight(Node<E> node) {
        if (node.distFromSource instanceof EdgeWeight) {
            return (Comparable) ((EdgeWeight) node.distFromSource).getZeroWeightI();
        } else {
            return 0;//TODO Proper implementation for all Comparable types
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
                  