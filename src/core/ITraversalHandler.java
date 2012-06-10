package core;

/**
 * Interface for graph traversal
 *
 * @param <E>
 */
public interface ITraversalHandler<E> {
    void traverse();

    void traverse(Graph.Vertex<E> startingAt);

    boolean processed(Graph.Vertex<E> v);

    void visitEdge(Graph.Vertex<E> v, Graph.Vertex<E> next);

    void markProcessed(Graph.Vertex<E> v);

    boolean discovered(Graph.Vertex<E> v);

    void markDiscovered(Graph.Vertex<E> v);

    void visitVertex(Graph.Vertex<E> v);

    void setParent(Graph.Vertex<E> v, Graph.Vertex<E> p);
}
