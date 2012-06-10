package core;

import core.util.BinaryHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Min Spanning Tree Generation, Prim's implementation,
 * uses Binary Heap to decide the fringe minimum value vertex
 *
 * @param <E>
 */
public class PrimSpanningTreeGenerator<E> extends AbstractSpanningTreeGenerator<E> {
    List<Graph.Vertex<E>> inTree = new ArrayList<Graph.Vertex<E>>();
    Map<Graph.Vertex<E>, BinaryHeap<Graph.Edge<E>>> v2EdgeMinHeap =
            new HashMap<Graph.Vertex<E>, BinaryHeap<Graph.Edge<E>>>();

    public PrimSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    public void traverse() {
        inTree.add(g.getVertices().iterator().next());
        generateTree();


    }

    protected void generateTree() {
        Graph.Edge<E> edgeMin = null;
        if (inTree.size() == g.getVertices().size()) return;
        for (Graph.Vertex<E> v : inTree) {
            markDiscovered(v);
            Graph.Edge<E> edgeMin2 = getFringeMinimum(v, true);
            edgeMin = edgeMin == null ? edgeMin2
                    : edgeMin2 == null ? edgeMin
                    : edgeMin.compareTo(edgeMin2) > 0 ? edgeMin2
                    : edgeMin;
        }

        if (edgeMin == null) {
            return;
        }
        updateTree(edgeMin);
        generateTree();
    }

    private void updateTree(Graph.Edge<E> edgeMin) {
        getFringeMinimum(edgeMin.from, false);
        spanningTree.insertEdge(edgeMin.from.unwrap(), edgeMin.to.unwrap(), edgeMin.edgeWeight);
        markDiscovered(edgeMin.to);
        inTree.add(edgeMin.to);
    }


    private Graph.Edge<E> getFringeMinimum(Graph.Vertex<E> v, boolean peekOnly) {
        BinaryHeap<Graph.Edge<E>> adjListHeap = getMinHeap(v);
        Graph.Edge<E> top = peekOnly ?
                adjListHeap.peek()
                : adjListHeap.extractTop();
        while (top != null && discovered(top.to)) {
            top = adjListHeap.extractTop(); //throw it away
            if (peekOnly) {
                top = adjListHeap.peek();
            }
        }
        return top;
    }

    private BinaryHeap<Graph.Edge<E>> getMinHeap(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Edge<E>> h = v2EdgeMinHeap.get(v);
        if (h == null) {
            h = buildHeap(v);
            v2EdgeMinHeap.put(v, h);
        }
        return h;
    }

    private BinaryHeap<Graph.Edge<E>> buildHeap(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Edge<E>> h = new BinaryHeap<Graph.Edge<E>>();
        for (Graph.Edge<E> edge : g.getIncidentEdges(v)) {
            h.insert(edge);
        }
        return h;
    }

}
                  