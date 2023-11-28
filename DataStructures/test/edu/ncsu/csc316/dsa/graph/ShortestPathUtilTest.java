package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 *
 */
public class ShortestPathUtilTest {

	/** Highway Class for testing. */
	private class Highway implements Weighted {
		/** The name of the highway. */
        private String name;
        /** The length of the highway. */
        private int length;
        
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
	
    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	new ShortestPathUtil();
    	
    	Graph<String, Highway> undirectedGraph = new AdjacencyListGraph<String, Highway>();
    	
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("RAL-ASH", 5));
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("RAL-WIL", 10));
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("RAL-DUR", 15));
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("RAL-GRE", 20));
        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, new Highway("ASH-WIL", 25));
        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, new Highway("ASH-DUR", 30));
        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, new Highway("ASH-GRE", 35));
        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, new Highway("WIL-DUR", 40));
        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, new Highway("WIL-GRE", 45));
        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, new Highway("DUR-GRE", 50));
        
        Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        
        Iterator<Integer> it1 = m.values().iterator();
        assertEquals(20, (int)it1.next()); // unordered linked list adds to front
        assertEquals(15, (int)it1.next());
        assertEquals(10, (int)it1.next());
        assertEquals(5, (int)it1.next());
        assertEquals(0, (int)it1.next());
        assertFalse(it1.hasNext());
        
        Iterator<Vertex<String>> it2 = m.iterator();
        assertEquals(v5, it2.next());
        assertEquals(v4, it2.next());
        assertEquals(v3, it2.next());
        assertEquals(v2, it2.next());
        assertEquals(v1, it2.next());
        assertFalse(it2.hasNext());
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e1);
        undirectedGraph.removeEdge(e3);
        undirectedGraph.removeEdge(e4);
        undirectedGraph.removeEdge(e8);
        
        m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        
        Iterator<Integer> it3 = m.values().iterator();
        assertEquals(65, (int)it3.next());
        assertEquals(55, (int)it3.next());
        assertEquals(35, (int)it3.next());
        assertEquals(10, (int)it3.next());
        assertEquals(0, (int)it3.next());
        assertFalse(it3.hasNext());
        
        Iterator<Vertex<String>> it4 = m.iterator();
        assertEquals(v4, it4.next());
        assertEquals(v5, it4.next());
        assertEquals(v2, it4.next());
        assertEquals(v3, it4.next());
        assertEquals(v1, it4.next());
        assertFalse(it4.hasNext());
        
        // Test with directed graph
        
        Graph<String, Highway> directedGraph = new AdjacencyListGraph<String, Highway>(true);
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, new Highway("RAL-ASH", 5));
        e2 = directedGraph.insertEdge(v1, v3, new Highway("RAL-WIL", 10));
        e3 = directedGraph.insertEdge(v1, v4, new Highway("RAL-DUR", 15));
        e4 = directedGraph.insertEdge(v1, v5, new Highway("RAL-GRE", 20));
        e5 = directedGraph.insertEdge(v2, v3, new Highway("ASH-WIL", 25));
        e6 = directedGraph.insertEdge(v2, v4, new Highway("ASH-DUR", 30));
        e7 = directedGraph.insertEdge(v2, v5, new Highway("ASH-GRE", 35));
        e8 = directedGraph.insertEdge(v3, v4, new Highway("WIL-DUR", 40));
        e9 = directedGraph.insertEdge(v3, v5, new Highway("WIL-GRE", 45));
        e10 = directedGraph.insertEdge(v4, v5, new Highway("DUR-GRE", 50));
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, new Highway("GRE-BOO", 55));
        
        directedGraph.removeEdge(e1);
        directedGraph.removeEdge(e3);
        m = ShortestPathUtil.dijkstra(directedGraph, v1);
        
        Iterator<Integer> it5 = m.values().iterator();
        assertEquals(75, (int)it5.next());
        assertEquals(20, (int)it5.next());
        assertEquals(10, (int)it5.next());
        assertEquals(50, (int)it5.next());
        assertEquals(0, (int)it5.next());
        assertEquals(Integer.MAX_VALUE, (int)it5.next());
        assertFalse(it5.hasNext());
        
        Iterator<Vertex<String>> it6 = m.iterator();
        assertEquals(v6, it6.next());
        assertEquals(v5, it6.next());
        assertEquals(v3, it6.next());
        assertEquals(v4, it6.next());
        assertEquals(v1, it6.next());
        assertEquals(v2, it6.next());
        assertFalse(it6.hasNext());
        
        directedGraph.removeEdge(e11);
        directedGraph.removeEdge(e2);
        directedGraph.removeEdge(e5);
        directedGraph.removeEdge(e6);
        directedGraph.removeEdge(e7);
        directedGraph.removeEdge(e9);
        directedGraph.removeEdge(e10);
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	Graph<String, Highway> undirectedGraph = new AdjacencyListGraph<String, Highway>();
    	
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("RAL-ASH", 5));
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("RAL-WIL", 10));
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("RAL-DUR", 15));
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("RAL-GRE", 20));
        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, new Highway("ASH-WIL", 25));
        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, new Highway("ASH-DUR", 30));
        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, new Highway("ASH-GRE", 35));
        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, new Highway("WIL-DUR", 40));
        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, new Highway("WIL-GRE", 45));
        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, new Highway("DUR-GRE", 50));
        
        Map<Vertex<String>, Edge<Highway>> m = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, ShortestPathUtil.dijkstra(undirectedGraph, v1));
        
        Iterator<Edge<Highway>> it1 = m.values().iterator();
        assertEquals(e1, it1.next());
        assertEquals(e2, it1.next());
        assertEquals(e3, it1.next());
        assertEquals(e4, it1.next());
        assertFalse(it1.hasNext());
        
        Iterator<Vertex<String>> it2 = m.iterator();
        assertEquals(v2, it2.next());
        assertEquals(v3, it2.next());
        assertEquals(v4, it2.next());
        assertEquals(v5, it2.next());
        assertFalse(it2.hasNext());
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e1);
        undirectedGraph.removeEdge(e3);
        undirectedGraph.removeEdge(e4);
        undirectedGraph.removeEdge(e8);
        
        m = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, ShortestPathUtil.dijkstra(undirectedGraph, v1));
        
        Iterator<Edge<Highway>> it3 = m.values().iterator();
        assertEquals(e2, it3.next());
        assertEquals(e5, it3.next());
        assertEquals(e9, it3.next());
        assertEquals(e6, it3.next());
        assertFalse(it3.hasNext());
        
        Iterator<Vertex<String>> it4 = m.iterator();
        assertEquals(v3, it4.next());
        assertEquals(v2, it4.next());
        assertEquals(v5, it4.next());
        assertEquals(v4, it4.next());
        assertFalse(it4.hasNext());
        
        // Test with directed graph
        
        Graph<String, Highway> directedGraph = new AdjacencyListGraph<String, Highway>(true);
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, new Highway("RAL-ASH", 5));
        e2 = directedGraph.insertEdge(v1, v3, new Highway("RAL-WIL", 10));
        e3 = directedGraph.insertEdge(v1, v4, new Highway("RAL-DUR", 15));
        e4 = directedGraph.insertEdge(v1, v5, new Highway("RAL-GRE", 20));
        e5 = directedGraph.insertEdge(v2, v3, new Highway("ASH-WIL", 25));
        e6 = directedGraph.insertEdge(v2, v4, new Highway("ASH-DUR", 30));
        e7 = directedGraph.insertEdge(v2, v5, new Highway("ASH-GRE", 35));
        e8 = directedGraph.insertEdge(v3, v4, new Highway("WIL-DUR", 40));
        e9 = directedGraph.insertEdge(v3, v5, new Highway("WIL-GRE", 45));
        e10 = directedGraph.insertEdge(v4, v5, new Highway("DUR-GRE", 50));
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, new Highway("GRE-BOO", 55));
        
        directedGraph.removeEdge(e1);
        directedGraph.removeEdge(e3);
        m = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, ShortestPathUtil.dijkstra(undirectedGraph, v1));
        
        Iterator<Edge<Highway>> it5 = m.values().iterator();
        assertEquals(e2.getElement().length, it5.next().getElement().length);
        assertEquals(e5.getElement().length, it5.next().getElement().length);
        assertEquals(e9.getElement().length, it5.next().getElement().length);
        assertEquals(e6.getElement().name, it5.next().getElement().name);
        assertFalse(it5.hasNext());
        
        Iterator<Vertex<String>> it6 = m.iterator();
        assertEquals(v3.getElement(), it6.next().getElement());
        assertEquals(v2.getElement(), it6.next().getElement());
        assertEquals(v5.getElement(), it6.next().getElement());
        assertEquals(v4.getElement(), it6.next().getElement());
        assertFalse(it6.hasNext());
        
        directedGraph.removeEdge(e11);
        directedGraph.removeEdge(e7);
        directedGraph.removeEdge(e10);
    }
    
}

