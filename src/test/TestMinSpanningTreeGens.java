package test;

import org.adm.graph.core.AbstractSpanningTreeGenerator;
import org.adm.graph.core.Graph;
import org.adm.graph.core.KruskalSpanningTreeGenerator;
import org.adm.graph.core.PrimSpanningTreeGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 3/6/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMinSpanningTreeGens {
    Graph<MockNode> g = new Graph<MockNode>();
    MockNode leftTop = new MockNode("LeftTop");
    MockNode leftBottom = new MockNode("LeftBottom");
    MockNode rightTop = new MockNode("rightTop");
    MockNode rightBottom = new MockNode("RightBottom");

    @Before
    public void setup() {
        g.insertEdge(leftTop, leftBottom, 10);
        g.insertEdge(leftTop, rightTop, 15);
        g.insertEdge(leftBottom, rightTop, 30);
        g.insertEdge(leftBottom, rightBottom, 50);
        g.insertEdge(rightBottom, rightTop, 60);
    }

    @After
    public void tearDown() {
        g = new Graph<MockNode>();
    }

    @Test
    public void testKruskal() {
        AbstractSpanningTreeGenerator<MockNode> stg = new KruskalSpanningTreeGenerator<MockNode>(g);
        Graph<MockNode> tr = stg.getSpanningTree();
        assertFalse(tr.getVertices().isEmpty());
        for (Graph.Vertex<MockNode> mockNodeVertex : tr.getAdjList(new Graph.Vertex<MockNode>(leftBottom))) {
            //there should not be any edge between left Bottom and right top
            assertFalse(mockNodeVertex.unwrap().equals(rightTop));
            assertTrue(mockNodeVertex.unwrap().equals(leftTop) || mockNodeVertex.unwrap().equals(rightBottom));

        }


    }

    @Test
    public void testPrim1() {
        //test rudimentary
        Graph<String> g = new Graph<String>();
        g.insertEdge("Root", "child1", 100);
        g.insertEdge("Root", "child2", 100);
        AbstractSpanningTreeGenerator<String> stg = new PrimSpanningTreeGenerator<String>(g);
        Graph<String> st = stg.getSpanningTree();
        assertTrue(st.getVertices().size() == 3);
        for (Graph.Vertex<String> v : st.getVertices()) {

            if (v.unwrap().equals("Root")) {
                assertTrue(st.getAdjList(v).size() == 2);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child1")));
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child2")));
            }
            if (v.unwrap().equals("child1")) {
                assertTrue(st.getAdjList(v).size() == 1);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("Root")));
            }
            if (v.unwrap().equals("child2")) {
                assertTrue(st.getAdjList(v).size() == 1);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("Root")));
            }

        }
        ;
    }

    @Test
    public void testPrim2() {
        //test rudimentary
        Graph<String> g = new Graph<String>();
        g.insertEdge("Root", "child1", 10);
        g.insertEdge("Root", "child2", 11);
        g.insertEdge("child1", "child11", 20);
        g.insertEdge("child2", "child11", 25);

        AbstractSpanningTreeGenerator<String> stg = new PrimSpanningTreeGenerator<String>(g);
        Graph<String> st = stg.getSpanningTree();
        assertTrue(st.getVertices().size() == 4);
        for (Graph.Vertex<String> v : st.getVertices()) {

            if (v.unwrap().equals("Root")) {
                assertTrue(st.getAdjList(v).size() == 2);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child1")));
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child2")));
            }
            if (v.unwrap().equals("child1")) {
                assertTrue(st.getAdjList(v).size() == 2);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("Root")));
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child11")));
            }
            if (v.unwrap().equals("child2")) {
                assertTrue(st.getAdjList(v).size() == 1);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("Root")));
            }
            if (v.unwrap().equals("child11")) {
                assertTrue(st.getAdjList(v).size() == 1);
                assertTrue(st.getAdjList(v).contains(new Graph.Vertex<String>("child1")));
            }

        }
        ;
    }

    @Test
    public void testPrim3() {

        AbstractSpanningTreeGenerator<MockNode> stg = new PrimSpanningTreeGenerator<MockNode>(g);
        Graph<MockNode> tr = stg.getSpanningTree();
        assertFalse(tr.getVertices().isEmpty());
        for (Graph.Vertex<MockNode> mockNodeVertex : tr.getAdjList(new Graph.Vertex<MockNode>(leftBottom))) {
            //there should not be any edge between left Bottom and right top
            assertFalse(mockNodeVertex.unwrap().equals(rightTop));
            assertTrue(mockNodeVertex.unwrap().equals(leftTop) || mockNodeVertex.unwrap().equals(rightBottom));

        }


    }
}
