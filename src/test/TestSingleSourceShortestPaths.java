package test;

import core.AbstractSpanningTreeGenerator;
import core.Graph;
import core.SingleSourceShortestPathGenerator;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

/**
 * Test Djik
 */
public class TestSingleSourceShortestPaths {

    @Test
    public void testDefaults() {
        //test rudimentary
        Graph<String> g = new Graph<String>(true);
        g.insertEdge("Root", "child1", 100);
        g.insertEdge("Root", "child2", 100);
        AbstractSpanningTreeGenerator<String> stg = new SingleSourceShortestPathGenerator<String>(g, "Root");
        Graph<String> st = stg.getSpanningTree();
        assertTrue(st.getVertices().size() == 3);
        for (Graph.Vertex<String> v : st.getVertices()) {

            if (v.unwrap().equals("Root")) {
                assertTrue(st.getAdjList(v).size() == 2);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child1")));
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child2")));
            }
            if (v.unwrap().equals("child1")) {
                assertTrue(st.getAdjList(v).size() == 0);
            }
            if (v.unwrap().equals("child2")) {
                assertTrue(st.getAdjList(v).size() == 0);
            }
        }
        ;
    }

    @Test
    public void TestShortestPath() {
        Graph<String> g = new Graph<String>(true);
        g.insertEdge("A", "B", 10);
        g.insertEdge("B", "C", 15);
        g.insertEdge("C", "D", 20);
        g.insertEdge("A", "D", 40);

        SingleSourceShortestPathGenerator<String> sg = new SingleSourceShortestPathGenerator<String>(g, "A");
        sg.traverse();
        LinkedList<Graph.Vertex<String>> sp = sg.getShortestPath("D");
        assertTrue(sp.get(0).unwrap().equals("D"));
        assertTrue(sp.get(1).unwrap().equals("A"));

        //Now create some other path
        g.insertEdge("B", "D", 11);
        sg = new SingleSourceShortestPathGenerator<String>(g, "A");
        sg.traverse();
        sp = sg.getShortestPath("D");
        assertTrue(sp.get(0).unwrap().equals("D"));
        assertTrue(sp.get(1).unwrap().equals("B"));
        assertTrue(sp.get(2).unwrap().equals("A"));


    }
}
