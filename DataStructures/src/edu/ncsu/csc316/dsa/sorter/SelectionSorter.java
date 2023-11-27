package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor that creates an SelectionSorter object with no
	 * comparator specified.
	 */
    public SelectionSorter() {
        this(null);
    }
	/**
     * Constructor that creates an InsertionSorter object with a
     * comparator field specified.
     * @param comparator The comparator to be used for sorting.
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    } 

    /**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function.
	 * The insertion sort algorithm is used, in which from left to right
	 * each member of the array is moved into a sorted sub-array.
	 * @param data The array of data to sort.
	 */
    public void sort(E[] data) {
        
    	for(int i = 0; i < data.length; i++) {
    		int minidx = i;
    		for(int j = i + 1; j < data.length; j++) {
    			if(super.compare(data[j], data[minidx]) < 0) {
    				minidx = j;
    			}
    		}
    		if(i != minidx) {
    			E temp = data[i];
    			data[i] = data[minidx];
    			data[minidx] = temp;
    		}
    	}
    }
    
}
