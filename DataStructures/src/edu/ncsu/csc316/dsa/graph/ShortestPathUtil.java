package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.UnorderedLinkedMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        AdaptablePriorityQueue<Integer, Vertex<V>> vertexAPQ = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> costMap = new UnorderedLinkedMap<Vertex<V>, Integer>();
        Set<Vertex<V>> found = new HashSet<Vertex<V>>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> entryMap = new UnorderedLinkedMap<Vertex<V>, Entry<Integer, Vertex<V>>>();
        
        // Insert all the vertices into our costMap, entryMap, and vertexAPQ
        for(Vertex<V> v : graph.vertices())
        {
        	if(v.equals(start))
        	{
        		costMap.put(v, 0);
        	}
        	else 
        	{
        		costMap.put(v, Integer.MAX_VALUE);
        	}
        	Integer currentCost = costMap.get(v);
        	Entry<Integer, Vertex<V>> apqEntry = vertexAPQ.insert(currentCost, v);
        	entryMap.put(v, apqEntry);
        }
        // Loop through entries in the APQ, updating the weights of connected vertices and adding the vertex to found
        while(!vertexAPQ.isEmpty())
        {
        	Entry<Integer, Vertex<V>> current = vertexAPQ.deleteMin();
        	Vertex<V> currentVertex = current.getValue();
        	found.add(currentVertex);
        	
        	for(Edge<E> edge : graph.outgoingEdges(currentVertex))
        	{
        		Vertex<V> oppositeVertex = graph.opposite(currentVertex, edge);
        		if(!found.contains(oppositeVertex)) 
        		{
        			int weight = edge.getElement().getWeight() + costMap.get(currentVertex);
        			if(weight < costMap.get(oppositeVertex))
        			{
        				costMap.put(oppositeVertex, weight);
        				vertexAPQ.replaceKey(entryMap.get(oppositeVertex), weight);
        			}
        		}
        	}
        }
        return costMap;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	// Create a map to store edges in the shortest path tree
    	Map<Vertex<V>, Edge<E>> m = new UnorderedLinkedMap<Vertex<V>, Edge<E>>();
        
    	// Iterate over all the edges
        for(Vertex<V> v : costs)
        {
        	if(v != start)
        	{
        		for(Edge<E> e : graph.incomingEdges(v))
        		{
        			Vertex<V> u = graph.opposite(v, e);
        			// If this edge is the one in the shortest path then add it to the map
        			if(costs.get(v).equals(costs.get(u) + e.getElement().getWeight()))
        			{
        				m.put(v, e);
        			}
        		}
        	}
        }
        
    	return m;
    }
}