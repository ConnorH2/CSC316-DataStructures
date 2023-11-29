package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 *
 */
public class MinimumSpanningTreeUtilTest {

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
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	new MinimumSpanningTreeUtil();
    	
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
        
        PositionalList<Edge<Highway>> pl = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
        
        Iterator<Edge<Highway>> it1 = pl.iterator();
        assertEquals(e1, it1.next());
        assertEquals(e2, it1.next());
        assertEquals(e3, it1.next());
        assertEquals(e4, it1.next());
        assertFalse(it1.hasNext());
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e1);
        undirectedGraph.removeEdge(e3);
        undirectedGraph.removeEdge(e4);
        undirectedGraph.removeEdge(e7);
        undirectedGraph.removeEdge(e8);
        
        pl = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
        
        Iterator<Edge<Highway>> it2 = pl.iterator();
        assertEquals(e2, it2.next());
        assertEquals(e5, it2.next());
        assertEquals(e6, it2.next());
        assertEquals(e9, it2.next());
        assertFalse(it2.hasNext());
        
        
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
        directedGraph.removeEdge(e10);
        
        pl = MinimumSpanningTreeUtil.primJarnik(directedGraph);
        
        Iterator<Edge<Highway>> it3 = pl.iterator();
        assertEquals(e2, it3.next());
        assertEquals(e4, it3.next());
        assertEquals(e8, it3.next());
        assertEquals(e11, it3.next());
        assertFalse(it3.hasNext());
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
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
        
        PositionalList<Edge<Highway>> pl = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
        
        Iterator<Edge<Highway>> it1 = pl.iterator();
        assertEquals(e1, it1.next());
        assertEquals(e2, it1.next());
        assertEquals(e3, it1.next());
        assertEquals(e4, it1.next());
        assertFalse(it1.hasNext());
        
        
        // Test after removing some edges
        
        undirectedGraph.removeEdge(e1);
        undirectedGraph.removeEdge(e3);
        undirectedGraph.removeEdge(e4);
        undirectedGraph.removeEdge(e7);
        undirectedGraph.removeEdge(e8);
        
        pl = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
        
        Iterator<Edge<Highway>> it2 = pl.iterator();
        assertEquals(e2, it2.next());
        assertEquals(e5, it2.next());
        assertEquals(e6, it2.next());
        assertEquals(e9.getElement().name, it2.next().getElement().name);
        assertFalse(it2.hasNext());
        
        
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
        directedGraph.removeEdge(e10);
        
        pl = MinimumSpanningTreeUtil.kruskal(directedGraph);
        
        Iterator<Edge<Highway>> it3 = pl.iterator();
        assertEquals(e2, it3.next());
        assertEquals(e4, it3.next());
        assertEquals(e5, it3.next());
        assertEquals(e6, it3.next());
        assertEquals(e11, it3.next());
        assertFalse(it3.hasNext());
        
    }
    
}
