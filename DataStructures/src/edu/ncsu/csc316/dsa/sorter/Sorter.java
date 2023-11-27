package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior.
 * @author Dr. King
 * @param <E> The type of data the Sorter uses.
 */
public interface Sorter<E> {
	
	/**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function.
	 * @param data The array of data to sort.
	 */
	void sort(E[] data);
}
