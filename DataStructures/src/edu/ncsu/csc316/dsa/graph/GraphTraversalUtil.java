package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.UnorderedLinkedMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * GraphTraversalUtil provides a collection of behaviors for traversing graphs,
 * including depth-first search and breadth-first search.
 * 
 * The GraphTraversalUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 */
public class GraphTraversalUtil {
    
    /**
     * Returns a map of discovery edges that represent a depth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the depth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	// Use a set to keep track of visited vertices
    	Set<Vertex<V>> s = new HashSet<Vertex<V>>();
    	// Use a map to keep track of the forest of discovery edges that discover each vertex
    	Map<Vertex<V>, Edge<E>> m = new UnorderedLinkedMap<Vertex<V>, Edge<E>>();
    	dfsHelper(graph, start, s, m);
    	return m;
    }
    
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> v, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
    	known.add(v);
    	for (Edge<E> e : graph.outgoingEdges(v)) {
    	   Vertex<V> u = graph.opposite(v, e);
    	   if (!known.contains(u)){
    	      forest.put(u, e);
    	      dfsHelper(graph, u, known, forest);
    	   }
    	}
    }

    /**
     * Returns a map of discovery edges that represent a breadth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the breadth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */    
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	// Use a set to keep track of visited vertices
    	Set<Vertex<V>> s = new HashSet<Vertex<V>>();
    	// Use a map to keep track of the forest of discovery edges that discover each vertex
    	Map<Vertex<V>, Edge<E>> m = new UnorderedLinkedMap<Vertex<V>, Edge<E>>();
    	// Use a queue to keep track of reachable vertices
    	Queue<Vertex<V>> q = new ArrayBasedQueue<Vertex<V>>();

    	// Enqueue the start vertex and add it to the visited set
    	s.add(start);
    	q.enqueue(start);
    	// Loop over every vertex in the queue
    	while (!q.isEmpty()) {
    	   // Add each non-discovered edge to the queue, set, and map
    	   Vertex<V> u = q.dequeue();
    	   for (Edge<E> e : graph.outgoingEdges(u)) {
    	      Vertex<V> w = graph.opposite(u, e);
    	      if (!s.contains(w)) {
    	         s.add(w);
    	         m.put(w, e);
    	         q.enqueue(w);
    	      }
    	   }
    	}
    	return m;
    }
}
