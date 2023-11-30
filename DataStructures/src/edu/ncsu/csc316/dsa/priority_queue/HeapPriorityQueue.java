package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A HeapPriorityQueue is an array-based min-heap implementation of the
 * {@link PriorityQueue} abstract data type. HeapPriorityQueue ensures a O(logn)
 * worst-case runtime for {@link PriorityQueue.insert} and
 * {@link PriorityQueue.deleteMin}. HeapPriorityQueue ensures a O(1) worst-case
 * runtime for {@link PriorityQueue.min}, {@link PriorityQueue.size}, and
 * {@link PriorityQueue.isEmpty}.
 * 
 * The HeapPriorityQueue class is based on an implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys (priorities) stored in the priority queue
 * @param <V> the type of values that are associated with keys in the priority
 *            queue
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    /**
     * The internal array-based list used to model the heap
     */
    protected ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new HeapPriorityQueue using a custom comparator
     * 
     * @param comparator the custom Comparator to use when comparing keys (priorities)
     */
    public HeapPriorityQueue(Comparator<K> comparator) {
        super(comparator);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Constructs a new HeapPriorityQueue that compares keys (priorities) using the
     * natural ordering of the key type
     */
    public HeapPriorityQueue() {
        this(null);
    }

    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in determining parent or children in
    // an array-based implementation of a min-heap
    //////////////////////////////////////////////////

    /**
     * Returns the index of the parent of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              parent
     * @return the index of the parent of the entry at the given index
     */
    protected int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              left child
     * @return the index of the left child of the entry at the given index
     */
    protected int left(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              right child
     * @return the index of the right child of the entry at the given index
     */
    protected int right(int index) {
        return 2 * index + 2;
    }

    /**
     * Returns true if the entry at the given index has a left child
     * 
     * @param index the index of the entry for which to check for a left child
     * @return true if the entry at the given index has a left child; otherwise,
     *         return false
     */
    protected boolean hasLeft(int index) {
        return left(index) < list.size();
    }

    /**
     * Returns true if the entry at the given index has a right child
     * 
     * @param index the index of the entry for which to check for a right child
     * @return true if the entry at the given index has a right child; otherwise,
     *         return false
     */
    protected boolean hasRight(int index) {
        return right(index) < list.size();
    }

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////

    @Override
    public Entry<K, V> insert(K key, V value) {
    	// Create the new entry
        Entry<K, V> temp = createEntry(key, value);
        
        // Add the entry at the next spot in the heap
        list.addLast(temp);
        // upHeap the entry until the new entry > its parent
        upHeap(list.size() - 1);
        
        return temp;
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        // Return the first element in the array as it is the minimum
        return list.first();
    }

    @Override
    public Entry<K, V> deleteMin() {
        if (list.isEmpty()) {
            return null;
        }
        // Get the min entry
        Entry<K, V> removed = list.first();
        // Set the first entry to the last entry in the heap, then downheap, unless we are removing the root
        if(list.size() > 1) { 
        	// Only do this if we are not removing the last entry in the list
        	list.set(0, list.removeLast());
        	downHeap(0);
        } else {
        	// We are removing the root, no need to downheap
        	list.removeLast();
        }
        
        return removed;
    }

    @Override
    public int size() {
        return list.size();
    }

    /**
     * Ensures the min-heap ordering property is maintained appropriately by
     * comparing an entry's key (priority) with the key of its parent, swapping the
     * entries if necessary
     * 
     * @param index the index of the entry at which to determine if up-heap is
     *              necessary to preserve the min-heap ordering property
     */
    protected void upHeap(int index) {
    	// If we reach the top of the list, no more upHeap-ing is needed
    	if(list.get(index).equals(list.first())) {
    		return;
    	}
    	
    	Entry<K, V> parent = list.get(parent(index));
    	Entry<K, V> current = list.get(index);
    	
    	// Parent should be less than the child in an up heap
        if(compare(parent.getKey(), current.getKey()) >= 0) {
        	// Parent greater than child, need to swap
        	swap(index, parent(index));
        	
        	// Call upHeap recursively
        	upHeap(parent(index));
        }
    }

    /**
     * Swaps the entry at index1 with the entry at index2
     * 
     * @param index1 the index of the first entry involved in the swap
     * @param index2 the index of the second entry involved in the swap
     */
    protected void swap(int index1, int index2) {
        Entry<K, V> temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Ensures the min-heap ordering property is maintained appropriately by
     * comparing an entry's key (priority) with the keys of its children, swapping
     * the entry with its lowest-priority child if necessary
     * 
     * @param index the index of the entry at which to determine if down-heap is
     *              necessary to preserve the min-heap ordering property
     */
    protected void downHeap(int index) {
    	// If there is no children, no more downHeap-ing is needed
    	if(!hasLeft(index) && !hasRight(index)) {
    		return;
    	}
    	
    	Entry<K, V> current = list.get(index);
    	
    	if(hasLeft(index) && hasRight(index)) {
    		// Has both children, find out if either key is lower than the current
    		Entry<K, V> left = list.get(left(index));
    		Entry<K, V> right = list.get(right(index));
    		
    		// Check if either left or right lower than both
    		if(compare(left.getKey(), current.getKey()) < 0 && compare(left.getKey(), right.getKey()) < 0) {
            	// Swap current with left
            	swap(index, left(index));
            	
            	// Call downHeap recursively
            	downHeap(left(index));
            } else if(compare(right.getKey(), current.getKey()) < 0 && compare(right.getKey(), left.getKey()) < 0) {
            	// Swap current with right
            	swap(index, right(index));
            	
            	// Call downHeap recursively
            	downHeap(right(index));
            } else {
            	// Current lower than both children, no swap needed
            	return;
            }
    	} else if (hasLeft(index) && compare(list.get(left(index)).getKey(), current.getKey()) < 0) {
    		// Left child less than current, swap current with left
    		swap(index, left(index));
        	
        	// Call downHeap recursively
        	downHeap(left(index));
    	} else if (hasRight(index) && compare(list.get(right(index)).getKey(), current.getKey()) < 0) {
    		// Right child less than current, swap current with right
    		swap(index, right(index));
        	
        	// Call downHeap recursively
        	downHeap(right(index));
    	} else {
    		// Current lower than its only child, no swap needed
    		return;
    	}
    }
}
