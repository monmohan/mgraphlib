package test;

import core.Graph;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 8/4/12
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTraversals {
    public static void main(String[] args) {
        Graph<MockNode> friends=new Graph<MockNode>();
        Map<MockNode, MockNode[]> fr=new HashMap<MockNode, MockNode[]>();
        MockNode arjun=new MockNode("arjun");
        MockNode ginnu=new MockNode("ginnu");
        MockNode shitij=new MockNode("shitij");
        MockNode nishit=new MockNode("nishit");
        MockNode shloka=new MockNode("shloka");
        MockNode[] fGinnu=new MockNode[]{new MockNode("pranav"),new MockNode("parth"),
                arjun,shloka};
        for (MockNode person : fGinnu) {
            friends.insertEdge(ginnu, person);
        }
        
        MockNode[] fArjun=new MockNode[]{ginnu,shitij,nishit};
        for (MockNode person : fArjun) {
            friends.insertEdge(arjun, person);
        }
                
        MockNode[] fShitij=new MockNode[]{nishit,shloka};
        for (int i = 0; i < fShitij.length; i++) {
            MockNode person = fShitij[i];
            friends.insertEdge(shitij,person);
        }
        System.out.println("friendship graph = " + friends);
        doBFS(friends);
        doDFS(friends);

        testShortestPath(friends, ginnu, shitij);
    }

    private static void testShortestPath(Graph<MockNode> friends, MockNode ginnu, MockNode shitij) {
        LinkedList<Graph.Vertex<MockNode>> sp=friends.findShortestPath(ginnu, shitij);
        for (Graph.Vertex<MockNode> next : sp) {
            System.out.println(next);
        }
    }

    private static void doBFS(Graph<MockNode> friends) {

        System.out.println(" BFS Test 1 >> start");
        friends.breadthFirstSearch();
        System.out.println(" BFS Test 1 >> done");
    }

    private static void doDFS(Graph<MockNode> friends) {
        System.out.println("===============================");
        System.out.println(" DFS Test 1 >> start");
        friends.depthFirstSearch();
        System.out.println(" DFS Test 1 >> done");
    }

}
