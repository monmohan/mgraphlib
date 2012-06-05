package core;

/**
 * Abstract class for spanning tree gen
 *
 * @param <E>
 */
public abstract class AbstractSpanningTreeGenerator<E> extends Traversal<E> {
    Graph<E> spanningTree = new Graph<E>();

    protected AbstractSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }


    public Graph<E> getSpanningTree() {
        traverse();
        return spanningTree;
    }
}
