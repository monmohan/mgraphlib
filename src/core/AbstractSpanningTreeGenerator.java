package core;

/**
 * Abstract class for spanning tree gen
 *
 * @param <E>
 */
public abstract class AbstractSpanningTreeGenerator<E> extends Traversal<E> {
    Graph<E> spanningTree = null;

    protected AbstractSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
        spanningTree = new Graph<E>(eGraph.directed);
    }


    public Graph<E> getSpanningTree() {
        traverse();
        return spanningTree;
    }
}
