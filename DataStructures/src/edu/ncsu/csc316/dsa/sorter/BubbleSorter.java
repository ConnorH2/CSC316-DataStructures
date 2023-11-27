/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data.
 * 
 * @author Connor Hekking
 * @param <E> The type of data the sorter uses
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
     * Constructor that creates a BubbleSorter object with a
     * comparator field specified.
     * @param comparator The comparator to be used for sorting.
     */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor that creates a BubbleSorter object with no
	 * comparator specified.
	 */
	public BubbleSorter() {
		this(null);
	}

	/**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function.
	 * The bubble sort algorithm is used, in which the data is 
	 * sorted by multiple passes, swapping each unsorted pair until finished.
	 * @param data The array of data to sort.
	 */
	@Override
	public void sort(E[] data) {
		boolean newLoop = true;
		
		// Loop while there are still swaps being made
		while(newLoop) {
			newLoop = false;
			for(int i = 1; i < data.length; i++) {
				if(compare(data[i], data[i - 1]) < 1) {
					E temp = data[i - 1];
					data[i - 1] = data[i];
					data[i] = temp;
					newLoop = true;
				}
			}
		}
	}
	
}
