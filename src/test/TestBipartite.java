package test;

import core.Graph;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 26/5/12
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBipartite {
    Graph<MockNode> g = new Graph<MockNode>(true);
    MockNode a = new MockNode("a");
    MockNode a11 = new MockNode("a.L1.1");
    MockNode a12 = new MockNode("a.L1.2");
    MockNode a21 = new MockNode("a.L2.1");


    @Test
    public void testBipartite() {
        g.insertEdge(a, a11).insertEdge(a, a12)
                .insertEdge(a11, a12);

        assertFalse(g.isBipartite());
        g = new Graph<MockNode>(true);
        g.insertEdge(a, a11).insertEdge(a, a12)
                .insertEdge(a11, a21).insertEdge(a12, a21);
        assertTrue(g.isBipartite());
    }

}
