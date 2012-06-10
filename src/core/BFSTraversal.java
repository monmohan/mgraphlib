package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class encapsulating Breadth First Traversal
 * Implements method to find shortest path between two nodes in linear time
 *
 * @param <E>
 */
public class BFSTraversal<E> extends Traversal<E> {
    Queue<Graph.Vertex<E>> bfs = new LinkedList<Graph.Vertex<E>>();

    public BFSTraversal(Graph<E> graph) {
        super(graph);
    }


    public void traverse() {
        v2NodeMap = new HashMap<Graph.Vertex<E>, Node<E>>();
        for (Graph.Vertex<E> v : g.getVertices()) {
            traverse(v);
        }

    }

    public void traverse(Graph.Vertex<E> initial) {
        Graph.Vertex<E> v = initial;
        markDiscovered(v);
        //no need to set parent
        bfs.offer(v);
        while (!bfs.isEmpty()) {
            v = bfs.poll();
            if (!processed(v)) {
                process(v);
                markProcessed(v);
            }
        }
    }

    private void process(Graph.Vertex<E> v) {
        visitVertex(v);
        LinkedList<Graph.Vertex<E>> edges = g.getAdjList(v);
        for (Graph.Vertex<E> next : edges) {
            if (!processed(next)/*visit each edge once*/) {
                visitEdge(v, next);
            }
            if (!discovered(next)) {
                markDiscovered(next);
                setParent(next, v);
                bfs.offer(next);
            }
        }

    }

    LinkedList<Graph.Vertex<E>> findShortestPath(Graph.Vertex<E> v1, Graph.Vertex<E> v2) {
        LinkedList<Graph.Vertex<E>> sp = new LinkedList<Graph.Vertex<E>>();
        Node<E> p = v2NodeMap.get(v2);
        Graph.Vertex<E> parent = p.parent;
        sp.add(v2);
        sp.add(parent);
        while (parent != null && !parent.equals(v1)) {
            parent = v2NodeMap.get(parent).parent;
            sp.add(parent);
        }

        return sp;
    }


}


