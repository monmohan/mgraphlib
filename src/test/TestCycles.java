package test;

import core.DFSTraversal;
import core.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test Cycle on directed graph
 */
public class TestCycles {
    Graph<MockNode> g = new Graph<MockNode>(true);
    MockNode saas = new MockNode("saas");
    MockNode bahu = new MockNode("bahu");
    MockNode politician = new MockNode("politician");
    MockNode people = new MockNode("people");
    MockNode buero = new MockNode("buerocrats");
    MockNode husband = new MockNode("husband");

    @Before
    public void setUp() {

        g.insertEdge(bahu, saas);
        g.insertEdge(husband, null);
        g.insertEdge(people, politician).insertEdge(people, buero);
    }

    @Test
    public void testTopologicalSort() {
        g.depthFirstSearch();
        Stack<Graph.Vertex<MockNode>> ts = new DFSTraversal<MockNode>(g).topologicalSort();
        Map<MockNode, Integer> oMap = new HashMap<MockNode, Integer>();
        for (int i = 0; i < ts.size(); i++) {
            Graph.Vertex<MockNode> mockNodeVertex = ts.get(i);
            oMap.put(mockNodeVertex.unwrap(), i);
        }
        assertTrue(oMap.get(people) > oMap.get(politician));
        assertTrue(oMap.get(people) > oMap.get(buero));
        assertTrue(oMap.get(bahu) > oMap.get(saas));


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
