package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for implementing Traversals on the Graph
 *
 * @param <E>
 */
public abstract class Traversal<E> {
    protected Graph<E> g;
    Map<Graph.Vertex<E>, Node<E>> v2NodeMap = new HashMap<Graph.Vertex<E>, Node<E>>();

    protected Traversal() {
    }

    public Traversal(Graph<E> graph) {
        this.g = graph;
    }

    abstract protected void traverse();

    protected void traverse(Graph.Vertex<E> startingAt) {

    }

    protected boolean processed(Graph.Vertex<E> v) {
        Node n = v2NodeMap.get(v);
        return n != null && n.isProcessed;
    }

    protected void visitEdge(Graph.Vertex<E> v, Graph.Vertex<E> next) {
        System.out.println(" Edge = From " + v + " to " + next);
    }

    protected void markProcessed(Graph.Vertex<E> v) {
        Node n = v2NodeMap.get(v);
        n.isProcessed = true;

        System.out.println(v + " is processed");
    }

    protected boolean discovered(Graph.Vertex<E> v) {
        Node n = v2NodeMap.get(v);
        return n != null && (n.isDiscovered || n.isProcessed);
    }

    protected void markDiscovered(Graph.Vertex<E> v) {
        // visitVertex(v);
        v2NodeMap.put(v, new Node<E>(v, true));
    }

    protected void visitVertex(Graph.Vertex<E> v) {
        System.out.println("Visiting vertex = " + v);
    }

    protected static class Node<E> {
        Graph.Vertex<E> vertex;
        boolean isDiscovered = false;
        boolean isProcessed = false;
        Graph.Vertex<E> parent;

        Node(Graph.Vertex<E> vertex, boolean discovered) {
            this.vertex = vertex;
            isDiscovered = discovered;
        }

    }
}
