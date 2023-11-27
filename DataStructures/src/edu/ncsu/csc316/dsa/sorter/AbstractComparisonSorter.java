/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * AbstractComparisonSorter provides shared functionality between
 * several of the sorter classes.
 * @author Connor Hekking
 * @param <E> The type of object to be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** This field holds the comparator to be used for sorting */
    private Comparator<E> comparator;
    
    /**
     * Constructor that sets the comparator with the given field.
     * @param comparator The comparator the AbstractComparisonSorter should use.
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Private function which sets the comparator to be used for sorting.
     * @param comparator The comparator to be used for sorting.
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
	 * Private inner class of AbstractComparisonSorter containing a method 
	 * that sorts items based on the natural ordering (AKA default ordering).
	 * @author Connor Hekking
	 */
    private class NaturalOrder implements Comparator<E> {
    	/**
    	 * Method that compares two items based on the natural ordering (AKA default ordering).
    	 * @param first The first object to compare.
    	 * @param second The second object to compare.
    	 * @return Return an integer representing if the one object is greater than another(1 or -1) or they are equal(0).
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * This wrapper method calls the compare method of the 
     * comparator that is in use for the sorter.
     * @param first The first object to be compared.
     * @param second The second object to be compared.
     * @return Returns the result from the comparator's compare method.
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}