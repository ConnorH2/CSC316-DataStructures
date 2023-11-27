package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * Private method that adds the given element in a new PositionalNode between the two given nodes.
     * @param element The new element to be added in a PositionalNode.
     * @param next The PositionalNode to be next after the inserted node.
     * @param prev The PositionalNode to be previous before the inserted node.
     * @return The position of the newly created PositionalNode.
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
        next.setPrevious(newNode);
        prev.setNext(newNode);
        size++;
        return newNode;
    }
    
    /**
     * Adds the given value in a new Position at the front of the list.
     * @param value The value to be added at the front of the list.
     * @return The position of the new element at the front of the list.
     */
    public Position<E> addFirst(E value){
        return addBetween(value, front.getNext(), front);
    }
    
    /**
     * Adds the given value in a new Position at the front of the list.
     * @param value The value to be added at the front of the list.
     * @return The position of the new element at the front of the list.
     */
    public Position<E> addLast(E value){
        return addBetween(value, tail, tail.getPrevious());
    }
    
    /**
     * Adds the given value in a new Position before the specified Position.
     * @param p The position from which the new element will be added before.
     * @param value The value to be added.
     * @return The position of the new element in the list.
     */
    public Position<E> addBefore(Position<E> p, E value){
    	PositionalNode<E> pn = validate(p);
    	return addBetween(value, pn, pn.getPrevious());
    }
    
    /**
     * Adds the given value in a new Position after the specified Position.
     * @param p The position from which the new element will be added after.
     * @param value The value to be added.
     * @return The position of the new element in the list.
     */
    public Position<E> addAfter(Position<E> p, E value){
    	PositionalNode<E> pn = validate(p);
    	return addBetween(value, pn.getNext(), pn);
    }
    
    /**
     * Returns the next Position in the list, or null if there is no Position after.
     * @param p The PositionalNode from which the Position after will be returned.
     * @return Return the Position after the given PositionalNode.
     */
    public Position<E> after(Position<E> p){
    	PositionalNode<E> pn = validate(p);
    	if(pn.getNext() == tail) {
    		return null;
    	}
    	return pn.getNext();
    }
    
    /**
     * Returns the previous Position in the list, or null if there is no Position before.
     * @param p The PositionalNode from which the Position before will be returned.
     * @return Return the Position before the given PositionalNode.
     */
    public Position<E> before(Position<E> p){
    	PositionalNode<E> pn = validate(p);
    	if(pn.getPrevious() == front) {
    		return null;
    	}
    	return pn.getPrevious();
    }
    
    /**
     * Returns the first Position in the PositionalLinkedList, or null if the list is empty.
     * @return The first Position in the list.
     */
    public Position<E> first(){
    	if(size == 0) {
    		return null;
    	}
    	return front.getNext();
    }
    
    /**
     * Returns the last Position in the PositionalLinkedList, or null if the list is empty.
     * @return The last Position in the list.
     */
    public Position<E> last(){
    	if(size == 0) {
    		return null;
    	}
    	return tail.getPrevious();
    }
    
    /**
     * Returns the size of the PositionalLinkedList.
     * @return The size of the PositionalLinkedList as an int.
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns a boolean indicating if the PositionalLinkedList is empty.
     * @return True if the list is empty, false if not.
     */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /**
     * Removes the given position from the PositionalLinkedList and returns it's data element.
     * @param p The position to remove from the list.
     * @return The removed position's data element.
     */
    public E remove(Position<E> p) {
    	PositionalNode<E> pn = validate(p);
    	
    	PositionalNode<E> pNext = pn.getNext();
    	PositionalNode<E> pPrev = pn.getPrevious();
    	
    	pNext.setPrevious(pPrev);
    	pPrev.setNext(pNext);
    	
    	size--;
    	return pn.getElement();
    }
    
    /**
     * Sets the given position's data element to a new value and returns the old value.
     * @param p The position to set the new data element.
     * @param value The new data value to set into the given position.
     * @return The old data value in the position.
     */
    public E set(Position<E> p, E value) {
    	PositionalNode<E> pn = validate(p);
    	E removed = pn.getElement();
    	
    	pn.setElement(value);
    	return removed;
    }
    
    /**
     * Returns a new ElementIterator.
     * @return An iterator which can iterate over elements in the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Returns a new PositionIterable object which 
     * allows positions to be used in a for-each loop.
     * @return A new PositionIterable object.
     */
    @Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
    
    /**
     * The PositionalNode class has a data element as well as a next and previous node field.
     * This allows traversing the PositionalLinkedList in both directions.
     * @author Connor Hekking
     *
     * @param <E> The data type the PositionalNode holds.
     */
    private static class PositionalNode<E> implements Position<E> {

    	/** The data element the PositionalNode holds. */
        private E element;
        /** The next node in the PositionalLinkedList */
        private PositionalNode<E> next;
        /** The previous node in the PositionalLinkedList */
        private PositionalNode<E> previous;

        /**
         * Constructor for PositionalNode where both next and previous nodes are null.
         * @param value The value this node will hold.
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructor for PositionalNode with a next node specified.
         * @param value The value this node will hold.
         * @param next The next PositionalNode in the list.
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructor for PositionalNode with next and previous nodes specified.
         * @param value The value this node will hold.
         * @param next The next PositionalNode in the list.
         * @param prev The previous PositionalNode in the list.
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous node in the list.
         * @param prev The previous PositionalNode.
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Returns the previous node in the list.
         * @return The previous PositionalNode.
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next node in the list.
         * @param next The next PositionalNode.
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Returns the next node in the list.
         * @return The next PositionalNode.
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
         * Returns this node's data element.
         * @return The data this position contains.
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets this node's data element.
         * @param element The data to be set at this position.
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Iterator inner class which iterates over Position objects.
     * @author Connor Hekking
     */
    private class PositionIterator implements Iterator<Position<E>> {

    	/** The current position of the iterator */
        private Position<E> current;
        /** Boolean flag indicating whether remove can be used. */
        private boolean removeOK;

        /**
         * Constructor for PositionIterator, sets the current position 
         * to the front of the list and sets removeOK to false.
         */
        public PositionIterator() {
            current = front;
            removeOK = false;
        }

        /**
         * Function which returns a boolean indicating whether there is a next position.
         * @return True if there is a next position, false if not.
         */
        @Override
        public boolean hasNext() {
            return after(current) != null;
        }

        /**
         * Returns the next position in the list if there is one.
         * @throws NoSuchElementException If there is no next position in the list.
         * @return The next position after current.
         */
        @Override
        public Position<E> next() {
            if(!hasNext()) {
            	throw new NoSuchElementException();
            }
            current = after(current);
            removeOK = true;
            return current;
        }

        /**
         * Removes the last returned Position from the list.
         * @throws IllegalStateException If next was not called since the last remove.
         */
        @Override
        public void remove() {
        	if(!removeOK) {
        		throw new IllegalStateException();
        	}
        	PositionalLinkedList.this.remove(current);
        	removeOK = false;
        }
    }
    
    /**
     * Iterator inner class which iterates over each element in the list.
     * @author Connor Hekking
     */
    private class ElementIterator implements Iterator<E> {

    	/** Iterator field used to iterate over each position */
        private Iterator<Position<E>> it;

        /**
         * Constructor which creates a new PositionIterator object.
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /**
         * Returns true if the iterator has a next element.
         * @return True if there is a next element, false if not.
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Moves to the next element in the list and returns it.
         * @return The element in the next Position.
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Removes the last returned Position from the list.
         * @throws IllegalStateException If next was not called since the last remove.
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * Wrapper class for PositionIterator that implements
     * Iterable, allowing for-each loops to be used.
     * @author Connor Hekking
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
    	/**
    	 * Returns a new PositionIterator object.
    	 * @return A new PositionIterator object.
    	 */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    

}
