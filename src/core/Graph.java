package core;


import java.util.*;

/**
 * Represents the Graph object.
 * Uses Adjacency List. Internally a Graph is represented by a HashMap
 * Key=Vertex (say V)
 * Value= Linked List of all Vertex where there is an edge between V and this Vertex
 * A Vertex is a wrapper around Graph of the objects being built (E)
 * Supports both directed and undirected graphs
 *
 * @param <E>
 */

public class Graph<E> {

    protected Map<Vertex<E>, LinkedList<Vertex<E>>> graphAdjMap = new HashMap<Vertex<E>, LinkedList<Vertex<E>>>();
    boolean directed = false;

    public Graph() {
    }

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public Graph<E> insertEdge(E e1, E e2, Comparable edgeWeight) {
        Vertex<E> v1 = new Vertex<E>(e1);
        Vertex<E> v2 = new Vertex<E>(e2);
        v2.edgeWeight = edgeWeight;
        insert(v1, v2);
        if (!directed && e2 != null) {
            v1.edgeWeight = edgeWeight;
            v1.dup = true;
            insert(v2, v1);
        }
        return this;
    }

    public Graph<E> insertEdge(E e1, E e2) {
        return insertEdge(e1, e2, null);
    }

    private void insert(Vertex<E> v1, Vertex<E> v2) {
        LinkedList<Vertex<E>> adjList = graphAdjMap.get(v1);
        if (adjList == null) {
            adjList = new LinkedList<Vertex<E>>();
        }
        if (v2.e != null && !adjList.contains(v2)) {
            adjList.add(v2);
            ++v1.degree;
        }
        graphAdjMap.put(v1, adjList);

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Vertex<E>, LinkedList<Vertex<E>>> adjLists : graphAdjMap.entrySet()) {
            sb.append("Adjacency list for ").append(adjLists.getKey()).append("\n");
            for (Vertex<E> v : adjLists.getValue()) {
                sb.append(v).append(",");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void breadthFirstSearch() {
        new BFSTraversal<E>(this).traverse();
    }


    public void depthFirstSearch() {
        new DFSTraversal<E>(this).traverse();
    }

    public boolean hasCycles() {
        DFSTraversal<E> tr = new DFSTraversal<E>(this);
        tr.traverse();
        return tr.hasCycles;
    }

    public boolean isBipartite() {
        TwoColoringTraversal<E> b = new TwoColoringTraversal<E>(this);
        b.traverse();
        return b.isBipartite;
    }


    public LinkedList<Vertex<E>> findShortestPath(E e1, E e2) {
        BFSTraversal<E> bfs = new BFSTraversal<E>(this);
        Vertex<E> v1 = new Vertex<E>(e1);
        bfs.traverse(v1);
        return bfs.findShortestPath(v1, new Vertex<E>(e2));

    }


    public LinkedList<Vertex<E>> getAdjList(Vertex<E> v) {
        LinkedList<Vertex<E>> adjList = graphAdjMap.get(v);
        return (adjList == null) ? new LinkedList<Vertex<E>>() : adjList;
    }

    public Edge<E>[] getIncidentEdges(Vertex<E> v) {
        Edge<E> e = null;
        LinkedList<Graph.Vertex<E>> adjL = getAdjList(v);
        Edge<E>[] incEdges = new Edge[adjL.size()];
        int i = 0;
        for (Vertex<E> to : adjL) {
            e = new Edge<E>();
            e.from = v;
            e.to = to;
            e.edgeWeight = to.edgeWeight;
            incEdges[i++] = e;
        }
        return incEdges;


    }


    public boolean isDirected() {
        return directed;
    }


    public Set<Vertex<E>> getVertices() {
        return graphAdjMap.keySet();
    }

    /**
     * Return all edges in the graph
     *
     * @return
     */
    Edge<E>[] getEdges() {
        List<Edge<E>> edges = new ArrayList<Edge<E>>();

        for (Map.Entry<Vertex<E>, LinkedList<Vertex<E>>> v2Edges : graphAdjMap.entrySet()) {
            Vertex<E> v = v2Edges.getKey();
            Edge<E> e = null;
            for (Vertex<E> eVertex : v2Edges.getValue()) {
                if (!eVertex.dup) {
                    e = new Edge<E>();
                    e.from = v;
                    e.to = eVertex;
                    e.edgeWeight = eVertex.edgeWeight;
                    edges.add(e);
                }
            }

        }
        return (Edge<E>[]) edges.toArray(new Edge[edges.size()]);

    }

    /**
     * An Edge class, holds from and to Vertex and the edge weight
     *
     * @param <E>
     */
    public static class Edge<E> implements Comparable<Edge<E>> {
        Vertex<E> from;
        Vertex<E> to;
        Comparable edgeWeight;

        @Override
        public int compareTo(Edge<E> o) {
            return this.edgeWeight.compareTo(o.edgeWeight);
        }
    }

    public static class Vertex<E> implements Comparable<Vertex<E>> {
        E e = null;
        int degree = 0;
        Comparable edgeWeight = null;
        boolean dup = false;//tracks repeated edges in undirected graphs when list of edges is retrieved


        public Vertex(E e) {
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            if (e != null ? !e.equals(vertex.e) : vertex.e != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return e != null ? e.hashCode() : 0;
        }

        @Override
        public String toString() {
            return e != null ? e.toString() : "";
        }

        @Override
        public int compareTo(Vertex<E> o) {
            return this.edgeWeight.compareTo(o.edgeWeight);
        }

        public E unwrap() {
            return e;
        }


    }


}
