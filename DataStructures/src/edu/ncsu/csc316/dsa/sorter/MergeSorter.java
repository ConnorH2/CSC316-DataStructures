package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

    /**
     * Sorts a given data array using the merge sort method,
     * in which elements are split up into smaller sub arrays
     * then combined together in sorted order.
     * @param data The data array to sort.
     */
	@Override
	public void sort(E[] data) {
		// If only one element, no need to sort
		if(data.length < 2)
		{
			return;
		}
		int mid = data.length / 2;
		E[] left = copyArray(data, 0, mid - 1);
		E[] right = copyArray(data, mid, data.length - 1);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}
	
	/**
	 * Constructs a new array from the source containing the specified indexes.
	 * @param source The array to copy from.
	 * @param start The first index to be included in the new array.
	 * @param end The last index to be included in the new array.
	 * @return Returns a new array copied from the source.
	 */
	private E[] copyArray(E[] source, int start, int end) {
		int newSize = end - start + 1;
		
		@SuppressWarnings("unchecked")
		E[] dest = (E[]) new Comparable[newSize];
		
		int destIdx = 0;
		for(int srcIdx = start; srcIdx <= end; srcIdx++) {
			dest[destIdx] = source[srcIdx];
			destIdx++;
		}
		return dest;
	}
    
	/**
	 * Private helper function responsible for merging two
	 * arrays together in sorted order for merge sort.
	 * @param left The left side array to be combined.
	 * @param right The right side array to be combined.
	 * @param sorted The destination array for the left and right arrays to be merged into.
	 */
    private void merge(E[] left, E[] right, E[] sorted) {
    	int leftIdx = 0;
    	int rightIdx = 0;
    	
    	// While there are still elements from left and right to merge
    	while(leftIdx + rightIdx < sorted.length) {
    		// If at end of right array, insert from left array
    		// If not at end of left array and left value > right value, insert from left array
    		// Otherwise, insert from right array
    		if(rightIdx == right.length || 
    				leftIdx < left.length && compare(left[leftIdx], right[rightIdx]) < 0) {
    			sorted[leftIdx + rightIdx] = left[leftIdx];
    			leftIdx++;
    		} else {
    			sorted[leftIdx + rightIdx] = right[rightIdx];
    			rightIdx++;
    		}
    	}
    }

}
