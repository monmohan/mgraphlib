package test;

import core.AbstractSpanningTreeGenerator;
import core.Graph;
import core.KruskalSpanningTreeGenerator;
import core.PrimSpanningTreeGenerator;
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
    public void testPrim() {

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
