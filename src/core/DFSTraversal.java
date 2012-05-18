package core;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 21/4/12
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class DFSTraversal<E> extends Traversal<E> {
    Graph<E> g = null;
    Stack<Graph.Vertex<E>> dfs = new Stack<Graph.Vertex<E>>();
    protected boolean hasCycles=false;


    public DFSTraversal(Graph<E> g) {
        this.g = g;
    }

    public void traverse() {
        v2NodeMap = new HashMap<Graph.Vertex<E>, Node<E>>();
        for (Graph.Vertex<E> v : g.getVertices()) {
            process(v);
        }



    }

    private void process(Graph.Vertex<E> v) {
       if(discovered(v) && !processed(v)){
           hasCycles=true;
       }
        if(discovered(v)){
            return;
        }
        markDiscovered(v);
        visitVertex(v);
        LinkedList<Graph.Vertex<E>> edges = g.getAdjList(v);
        for (Graph.Vertex<E> next : edges) {
            visitEdge(v, next);
            process(next);
        }
        markProcessed(v);
    }

    @Override
    protected void markProcessed(Graph.Vertex<E> v) {
        super.markProcessed(v);    //To change body of overridden methods use File | Settings | File Templates.
        dfs.push(v);
    }

    public Stack<Graph.Vertex<E>> topologicalSort(){
        traverse();
       return hasCycles?null:dfs;
    }
}
