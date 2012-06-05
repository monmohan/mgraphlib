package core;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A Two coloring traversal, test to check if graph is bipartite .
 * Colors one vertex RED and all adjacent vertex Black
 *
 * @param <E>
 */
public class TwoColoringTraversal<E> extends BFSTraversal<E> {
    boolean isBipartite = true;

    public TwoColoringTraversal(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    public void traverse() {
        v2NodeMap = new HashMap<Graph.Vertex<E>, Node<E>>();
        Iterator<Graph.Vertex<E>> iterator = g.getVertices().iterator();
        if (iterator.hasNext()) {
            Graph.Vertex<E> v = iterator.next();
            //color first vertex red
            Node<E> nx = new Node<E>(v, false);
            nx.setColor(Node.Color.RED);
            v2NodeMap.put(v, nx);

        }
        iterator = g.getVertices().iterator();
        while (iterator.hasNext()) {
            Graph.Vertex<E> v = iterator.next();
            traverse(v);
        }
    }

    @Override
    public void visitEdge(Graph.Vertex<E> v, Graph.Vertex<E> next) {
        super.visitEdge(v, next);
        Node.Color color = v2NodeMap.get(v).getComplement();
        Node<E> nx = v2NodeMap.get(next);
        nx = nx == null ? new Node<E>(next, false) : nx;
        v2NodeMap.put(next, nx);
        isBipartite = nx.setColor(color);
    }


}
