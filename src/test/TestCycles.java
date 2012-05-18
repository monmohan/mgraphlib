package test;

import core.DFSTraversal;
import core.Graph;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 24/4/12
 * Time: 7:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestCycles {
    public static void main(String[] args) {
        testCycleDetection();
        MockNode saas=new MockNode("saas");
        MockNode bahu=new MockNode("bahu");
        MockNode politician =new MockNode("politician");
        MockNode janta =new MockNode("janta");
        MockNode buero =new MockNode("buerocrats");
        MockNode husband=new MockNode("husband");
        Graph<MockNode> g=new Graph<MockNode>();
        g.setDirected(true);
        g.insertEdge(bahu,saas);
        g.insertEdge(husband,null);
        g.insertEdge(janta,politician).insertEdge(janta,buero);
        g.depthFirstSearch();
        Stack<Graph.Vertex<MockNode>> ts=new DFSTraversal<MockNode>(g).topologicalSort();
        for (Graph.Vertex<MockNode> t : ts) {
            System.out.println(t);
        }


    }

    private static void testCycleDetection() {
        MockNode root=new MockNode("root");
        MockNode s1=new MockNode("Number=1,Level=1");
        MockNode s2=new MockNode("Number=2,Level=1");
        MockNode s3=new MockNode("Number=1,:Level=2");
        Graph<MockNode> g=new Graph<MockNode>();
        g.setDirected(true);
        g.insertEdge(root,s1).insertEdge(root,s2).insertEdge(s1,s3).insertEdge(s2,s3);
        boolean hasCycles=g.hasCycles();
        System.out.println("hasCycles = " + hasCycles);
        //now add s3 back to root
        g.insertEdge(s3,root);
        hasCycles=g.hasCycles();
        System.out.println("hasCycles = " + hasCycles);


    }


}
