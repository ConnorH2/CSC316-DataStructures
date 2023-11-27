package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    /**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * Adds the given value at the specified index. 
     * Any value at or after the given index will be 
     * shifted right one index to make room.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index to add data at.
     * @param value The data to be added to the array.
     */
    public void add(int index, E value) {
    	super.checkIndexForAdd(index);
    	
    	size++;
    	ensureCapacity(size);
    	// Move the data forward to accommodate the new entry
    	for(int i = size - 2; i >= index; i--) {
    		data[i + 1] = data[i];
    	}
    	data[index] = value;
    }
    
    /**
     * Removes and returns the value at a given index.
     * Any value after the given index will be 
     * shifted left one index to fill in the gap.
     * @throws IndexOutOfBoundsException If the given index is out of bounds of the list.
     * @param index The index to remove the data from.
     * @return Return the removed data.
     */
    public E remove(int index) {
    	super.checkIndex(index);
    	
    	E removed = data[index];
    	for(int i = index; i < size - 1; i++) {
    		data[i] = data[i + 1];
    	}
    	size--;
    	return removed;
    }
    
    /**
     * Returns the value at a given index.
     * @throws IndexOutOfBoundsException If the index is outside the bounds of the list.
     * @param index The index to return the value from.
     * @return The value at the given index.
     */
    public E get(int index) {
    	super.checkIndex(index);
    	
    	return data[index];
    }
    
    /**
     * Sets the new value at a given index and returns the old value.
     * @throws IndexOutOfBoundsException If the index is outside the bounds of the list.
     * @param index The index to set the value at.
     * @param value The value to set at the index.
     * @return The value overwritten at the given index.
     */
    public E set(int index, E value) {
    	super.checkIndex(index);
    	
    	E removed = data[index];
    	data[index] = value;
    	return removed;
    }
    
    /**
     * Returns the current size of the list.
     * @return The size of the list as an integer.
     */
    public int size() {
    	return size;
    }
    
    private class ElementIterator implements Iterator<E> {
    	/** The pointer position of the iterator */
        private int position;
        /** Boolean flag representing whether remove can be used */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            position = 0;
            removeOK = false;
        }

        /**
         * Returns true if the list has a next element.
         * @return true if the list has a next element, false if not.
         */
        @Override
        public boolean hasNext() {
            return position < size;
        }

        /**
         * Moves the pointer forward and returns the next element.
         * @throws NoSuchElementException If there is no next element in the list.
         * @return Returns the next element in the list.
         */
        @Override
        public E next() {
            if(!hasNext()) {
            	throw new NoSuchElementException();
            }
            E val = data[position];
            position++;
            removeOK = true;
            return val;
        }
            
        /**
         * Removes the element that was just returned by next().
         * @throws IllegalStateException If next() has not been called since
         * the last remove() call.
         */
        @Override
        public void remove() {
            if(!removeOK) {
            	throw new IllegalStateException();
            }
            
        	for(int i = position - 1; i < size - 1; i++) {
        		data[i] = data[i + 1];
        	}
        	position--;
        	size--;
        	removeOK = false;
        }
    }

    /**
     * Returns a new ElementIterator for the list.
     * @return An Iterator which can traverse the list.
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
}
