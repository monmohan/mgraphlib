package core;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 27/5/12
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
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
}
