package core;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 27/5/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
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
    protected void visitEdge(Graph.Vertex<E> v, Graph.Vertex<E> next) {
        super.visitEdge(v, next);
        Node.Color color = v2NodeMap.get(v).getComplement();
        Node<E> nx = v2NodeMap.get(next);
        nx = nx == null ? new Node<E>(next, false) : nx;
        v2NodeMap.put(next, nx);
        isBipartite = nx.setColor(color);
    }


}
