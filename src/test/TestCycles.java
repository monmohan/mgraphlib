package test;

import core.DFSTraversal;
import core.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Test Cycle on directed graph
 */
public class TestCycles {
    Graph<MockNode> g = new Graph<MockNode>(true);
    MockNode saas = new MockNode("saas");
    MockNode bahu = new MockNode("bahu");
    MockNode politician = new MockNode("politician");
    MockNode janta = new MockNode("janta");
    MockNode buero = new MockNode("buerocrats");
    MockNode husband = new MockNode("husband");

    @Before
    public void setUp() {

        g.insertEdge(bahu, saas);
        g.insertEdge(husband, null);
        g.insertEdge(janta, politician).insertEdge(janta, buero);
    }

    @Test
    public void testTopologicalSort() {
        g.depthFirstSearch();
        Stack<Graph.Vertex<MockNode>> ts = new DFSTraversal<MockNode>(g).topologicalSort();
        assertEquals(ts.pop().unwrap(), husband);
        assertEquals(ts.pop().unwrap(), saas);
        assertEquals(ts.pop().unwrap(), bahu);
        assertEquals(ts.pop().unwrap(), politician);
        assertEquals(ts.pop().unwrap(), buero);
        assertEquals(ts.pop().unwrap(), janta);
    }


    @Test
    public void testCycleDetection() {
        MockNode root = new MockNode("root");
        MockNode s1 = new MockNode("Number=1,Level=1");
        MockNode s2 = new MockNode("Number=2,Level=1");
        MockNode s3 = new MockNode("Number=1,:Level=2");
        Graph<MockNode> g = new Graph<MockNode>(true);
        g.insertEdge(root, s1).insertEdge(root, s2).insertEdge(s1, s3).insertEdge(s2, s3);
        boolean hasCycles = g.hasCycles();
        assertFalse(hasCycles);
        //now add s3 back to root
        g.insertEdge(s3, root);
        hasCycles = g.hasCycles();
        assertTrue(hasCycles);


    }


}
