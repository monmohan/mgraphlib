package org.adm.graph.core;

import org.adm.graph.core.util.MergeSet;

import java.util.Arrays;

/**
 * Min Spanning Tree Generator, Kruskal's Algorithm.
 * Uses Union Find data structure @see org.adm.graph.core.util.MergeSet
 * to check for connected components
 *
 * @param <E>
 */
public class KruskalSpanningTreeGenerator<E> extends PrimSpanningTreeGenerator<E> {
    public KruskalSpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    public void traverse() {
        generateTree();

    }

    protected void generateTree() {
        Graph.Edge<E>[] edges = g.getEdges();
        Arrays.sort(edges);
        for (Graph.Edge<E> edge : edges) {
            markDiscovered(edge.from);
            markDiscovered(edge.to);
            MergeSet<Graph.Vertex<E>> connComp1 = v2NodeMap.get(edge.from).connComp;

            if (connComp1 == null) {
                connComp1 = new MergeSet<Graph.Vertex<E>>(edge.from);
                v2NodeMap.get(edge.from).connComp = connComp1;
            }

            MergeSet<Graph.Vertex<E>> connComp2 = v2NodeMap.get(edge.to).connComp;

            if (connComp2 == null) {
                connComp2 = new MergeSet<Graph.Vertex<E>>(edge.to);
                v2NodeMap.get(edge.to).connComp = connComp2;
            }
            //if both vertices are not in the same connected component
            if (!connComp1.find().equals(connComp2.find())) {
                connComp1.union(connComp2);
                spanningTree.insertEdge(edge.from.unwrap(), edge.to.unwrap(), edge.edgeWeight);
            }
        }

    }

}
