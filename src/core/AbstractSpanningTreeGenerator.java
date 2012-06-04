package core;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 4/6/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
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
