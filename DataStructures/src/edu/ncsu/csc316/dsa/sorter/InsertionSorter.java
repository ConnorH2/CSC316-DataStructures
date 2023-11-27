package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @param <E> The type of data the sorter uses
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
	 * Constructor that creates an InsertionSorter object with no
	 * comparator specified.
	 */
    public InsertionSorter() {
        this(null);
    }
    
    /**
     * Constructor that creates an InsertionSorter object with a
     * comparator field specified.
     * @param comparator The comparator to be used for sorting.
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
	
	/**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function.
	 * The insertion sort algorithm is used, in which from left to right
	 * each member of the array is moved into a sorted sub-array.
	 * @param data The array of data to sort.
	 */
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E item = data[i];
			int j = i - 1;
			while(j >= 0 && super.compare(data[j], item) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = item;
		}
	}
	
}
