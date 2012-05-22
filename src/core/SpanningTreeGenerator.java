package core;

import core.util.BinaryHeap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @param <E>
 */
public class SpanningTreeGenerator<E> extends Traversal<E> {
    Set<Graph.Vertex<E>> inTree = new HashSet<Graph.Vertex<E>>();
    Map<Graph.Vertex<E>, BinaryHeap<Graph.Vertex<E>>> v2EdgeMinHeap =
            new HashMap<Graph.Vertex<E>, BinaryHeap<Graph.Vertex<E>>>();

    public SpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    protected void traverse() {
        inTree.add(g.getVertices().iterator().next());
        generateTree();


    }

    private void generateTree() {
        Graph.Vertex<E> vMin = null;
        for (Graph.Vertex<E> v : inTree) {
            Graph.Vertex<E> vMin2 = getFringeMinimum(v);
            vMin = vMin == null ? vMin2
                    : vMin2 == null ? vMin
                    : vMin.compareTo(vMin2) > 0 ? vMin2
                    : vMin;
            if (!processed(vMin)) {
                inTree.add(vMin);
            }
        }
    }

    public Set<Graph.Vertex<E>> getMinimumSpanningTree() {
        return inTree;
    }

    private Graph.Vertex<E> getFringeMinimum(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Vertex<E>> adjListHeap = v2EdgeMinHeap.get(v);
        Graph.Vertex<E> top = adjListHeap.extractTop();
        while (top != null || processed(top)) {
            top = adjListHeap.extractTop();
        }
        return top;
    }


}
