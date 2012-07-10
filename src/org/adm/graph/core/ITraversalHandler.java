package org.adm.graph.core;

/**
 * Interface for graph traversal
 *
 * @param <E>
 */
public interface ITraversalHandler<E> {
    /**
     * Traverse a Graph -
     * In case of BFSTraversal, this will ensure that
     * all connected components and all edges are traversed
     */
    void traverse();

    /**
     * Traverse a Graph starting at the given Vertex
     *
     * @param startingAt start vertex for traversal
     */
    void traverse(Graph.Vertex<E> startingAt);

    boolean processed(Graph.Vertex<E> v);

    void visitEdge(Graph.Vertex<E> v, Graph.Vertex<E> next);

    void markProcessed(Graph.Vertex<E> v);

    boolean discovered(Graph.Vertex<E> v);

    void markDiscovered(Graph.Vertex<E> v);

    void visitVertex(Graph.Vertex<E> v);

    void setParent(Graph.Vertex<E> v, Graph.Vertex<E> p);
}
