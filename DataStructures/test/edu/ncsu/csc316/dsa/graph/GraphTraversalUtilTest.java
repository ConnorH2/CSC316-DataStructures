package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Connor Hekking
 *
 */
public class GraphTraversalUtilTest {

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	new GraphTraversalUtil();
    	
    	Graph<String, Integer> undirectedGraph = new AdjacencyListGraph<String, Integer>();
    	
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Map<Vertex<String>, Edge<Integer>> m = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
        
        Iterator<Edge<Integer>> it1 = m.values().iterator();
        
        assertEquals(e10, it1.next());
        assertEquals(e8, it1.next());
        assertEquals(e5, it1.next());
        assertEquals(e1, it1.next());
        assertFalse(it1.hasNext());
        
        Iterator<Vertex<String>> it2 = m.iterator();
        assertEquals(v5, it2.next());
        assertEquals(v4, it2.next());
        assertEquals(v3, it2.next());
        assertEquals(v2, it2.next());
        assertFalse(it2.hasNext());
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e10);
        undirectedGraph.removeEdge(e7);
        undirectedGraph.removeEdge(e1);
        
        m = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
        
		Iterator<Edge<Integer>> it3 = m.values().iterator();
        
        assertEquals(e9, it3.next());
        assertEquals(e6, it3.next());
        assertEquals(e5, it3.next());
        assertEquals(e2, it3.next());
        assertFalse(it3.hasNext());
        
        Iterator<Vertex<String>> it4 = m.iterator();
        assertEquals(v5, it4.next());
        assertEquals(v4, it4.next());
        assertEquals(v2, it4.next());
        assertEquals(v3, it4.next());
        assertFalse(it4.hasNext());
        
        // Test with directed graph
        
        Graph<String, Integer> directedGraph = new AdjacencyListGraph<String, Integer>(true);
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        directedGraph.removeEdge(e1);
        directedGraph.removeEdge(e10);
        directedGraph.removeEdge(e9);
        directedGraph.removeEdge(e3);
        m = GraphTraversalUtil.depthFirstSearch(directedGraph, v1);
        
        Iterator<Edge<Integer>> it5 = m.values().iterator();
        
        assertEquals(e11, it5.next());
        assertEquals(e4, it5.next());
        assertEquals(e8, it5.next());
        assertEquals(e2, it5.next());
        assertFalse(it5.hasNext());
        
        Iterator<Vertex<String>> it6 = m.iterator();
        assertEquals(v6, it6.next());
        assertEquals(v5, it6.next());
        assertEquals(v4, it6.next());
        assertEquals(v3, it6.next());
        assertFalse(it6.hasNext());
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
Graph<String, Integer> undirectedGraph = new AdjacencyListGraph<String, Integer>();
    	
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Map<Vertex<String>, Edge<Integer>> m = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
        
        Iterator<Edge<Integer>> it1 = m.values().iterator();
        
        assertEquals(e4, it1.next());
        assertEquals(e3, it1.next());
        assertEquals(e2, it1.next());
        assertEquals(e1, it1.next());
        assertFalse(it1.hasNext());
        
        Iterator<Vertex<String>> it2 = m.iterator();
        assertEquals(v5, it2.next());
        assertEquals(v4, it2.next());
        assertEquals(v3, it2.next());
        assertEquals(v2, it2.next());
        assertFalse(it2.hasNext());
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e1);
        undirectedGraph.removeEdge(e3);
        undirectedGraph.removeEdge(e4);
        undirectedGraph.removeEdge(e8);
        undirectedGraph.removeEdge(e9);
        
        m = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
        
		Iterator<Edge<Integer>> it3 = m.values().iterator();
        
		assertEquals(4, m.size());
		
        assertEquals(e7, it3.next());
        assertEquals(e6, it3.next());
        assertEquals(e5, it3.next());
        assertEquals(e2, it3.next());
        assertFalse(it3.hasNext());
        
        Iterator<Vertex<String>> it4 = m.iterator();
        assertEquals(v5, it4.next());
        assertEquals(v4, it4.next());
        assertEquals(v2, it4.next());
        assertEquals(v3, it4.next());
        assertFalse(it4.hasNext());
        
        
        // Test with directed graph
        
        Graph<String, Integer> directedGraph = new AdjacencyListGraph<String, Integer>(true);
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        directedGraph.removeEdge(e1);
        directedGraph.removeEdge(e3);
        directedGraph.removeEdge(e4);
        directedGraph.removeEdge(e10);
        m = GraphTraversalUtil.breadthFirstSearch(directedGraph, v1);
        
        assertEquals(4, m.size());
        
        Iterator<Edge<Integer>> it5 = m.values().iterator();
        
        assertEquals(e11, it5.next());
        assertEquals(e9, it5.next());
        assertEquals(e8, it5.next());
        assertEquals(e2, it5.next());
        assertFalse(it5.hasNext());
        
        Iterator<Vertex<String>> it6 = m.iterator();
        assertEquals(v6, it6.next());
        assertEquals(v5, it6.next());
        assertEquals(v4, it6.next());
        assertEquals(v3, it6.next());
        assertFalse(it6.hasNext());
    }
    
}