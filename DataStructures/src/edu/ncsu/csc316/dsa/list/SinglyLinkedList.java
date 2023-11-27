package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list 
     * with the front node being a dummy node.
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
    /**
     * Adds a new LinkedListNode with the given value at the specified index.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index the new node should be placed at.
     * @param value The value the new list node should have.
     */
    public void add(int index, E value) {
    	if(index < 0 || index > size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	
    	// Traverse to the node before the one being added
    	LinkedListNode<E> current = front;
    	for(int i = 0; i < index; i++) {
    		current = current.next;
    	}
    	
    	// Create the new node
    	LinkedListNode<E> newNode = new LinkedListNode<E>(value, current.next);
    	// Insert the new node
    	current.next = newNode;
    	if(newNode.next == null) {
    		tail = newNode;
    	}
    	size++;
    }
    
    /**
     * Removes the LinkedListNode at the given index and returns it's value.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index of the list node to be removed.
     * @return Returns the data value of the removed list node.
     */
    public E remove(int index) {
    	if(index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	// Traverse to the node before the one being removed
    	LinkedListNode<E> current = front;
    	for(int i = 0; i < index; i++) {
    		current = current.next;
    	}
    	
    	// Remove the list node after current
    	E removed = current.next.data;
    	current.next = current.next.next;
    	if(index == size - 1) {
    		tail = current;
    	}
    	size--;
    	return removed;
    }
    
    /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.data;
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
        LinkedListNode<E> newNode = new LinkedListNode<E>(element);
        if(tail != null) {
        	tail.next = newNode;
        	tail = newNode;
        	size++;
        } else {
        	// Use add if tail is null because
        	// the list must be empty.
        	// Adding at index 0 is O(1)
        	add(0, element);
        }
    }
    
    /**
     * Returns the element at the specified index.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index of the requested element.
     * @return The data at the specified index.
     */
    public E get(int index) {
    	if(index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	LinkedListNode<E> current = front;
    	for(int i = 0; i <= index; i++) {
    		current = current.next;
    	}
    	
    	return current.data;
    }
    
    /**
     * Sets the element at the given index to a new value and returns the old value.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index to set the new element at.
     * @param value The new value to be set at the specified index.
     * @return Returns the old value which was replaced.
     */
    public E set(int index, E value) {
    	if(index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	LinkedListNode<E> current = front;
    	for(int i = 0; i <= index; i++) {
    		current = current.next;
    	}
    	
    	E removed = current.data;
    	current.data = value;
    	return removed;
    }
    
    /**
     * Returns the current size of the SinglyLinkedList.
     * @return An integer representing the size of the list.
     */
    public int size() {
    	return size;
    }
    
    /**
     * Inner static list node class of SinglyLinkedList.
     * @author Connor Hekking
     *
     * @param <E> The data type the LinkedListNode holds.
     */
    private static class LinkedListNode<E> {
    	/** The data the LinkedListNode holds. */
    	private E data;
    	/** The next LinkedListNode in the linked list. */
    	private LinkedListNode<E> next;
    	
    	/**
    	 * Constructor for LinkedListNode class without a next node parameter.
    	 * @param data The data this LinkedListNode contains.
    	 */
    	public LinkedListNode(E data) {
    		this.data = data;
    		this.next = null;
    	}
    	
    	/**
    	 * Constructor for LinkedListNode class with a next node parameter.
    	 * @param data The data this LinkedListNode contains.
    	 * @param next The next LinkedListNode in the linked list.
    	 */
    	public LinkedListNode(E data, LinkedListNode<E> next) {
    		this.data = data;
    		this.next = next;
    	}
    }
    
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
        }

        /**
         * Iterator method that returns a boolean indicating 
         * whether there is a next element in the list.
         * @return True if there is a next element, false if not.
         */
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        /**
         * Iterator method that moves to the next entry in the list and returns its value.
         * @return The next list node's value.
         */
        @Override
        public E next() {
            if(!hasNext()) {
            	throw new NoSuchElementException();
            }
            current = current.next;
            return current.data;
        }
        
        /**
         * Iterator method that removes the current element from the list. 
         * This method is currently not supported by SinglyLinkedList.
         */
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }

    /**
     * Returns a new iterator created at the start of the LinkedList.
     * @return Returns a new ElementIterator object.
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
}
