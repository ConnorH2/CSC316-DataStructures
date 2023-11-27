package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function, using the counting sort algorithm.
	 * @param data The array of data to sort.
	 */
	@Override
	public void sort(E[] data) {
		int minVal = data[0].getId();
		int maxVal = data[0].getId();
		
		//Find minimum and maximum value
		for(int i = 0; i < data.length; i++) {
			minVal = Integer.min(minVal, data[i].getId());
			maxVal = Integer.max(maxVal, data[i].getId());
		}
		
		// Calculate the range of the elements
		int range = maxVal - minVal + 1;
		
		int[] arr = new int[range];
		
		// Process the value of each object into an int array.
		for(int i = 0; i < data.length; i++) {
			int currentVal = data[i].getId();
			// The value of the current object relative to 
			// the start of the array at minval.
			int relativeVal = currentVal - minVal;
			arr[relativeVal] += 1;
		}
		
		// Accumulate the frequencies in the array
		// to prepare for producing sorted output
		for(int i = 1; i < range; i++) {
			arr[i] += arr[i - 1];
		}
		
		
		@SuppressWarnings("unchecked")
		E[] sortedData = (E[]) new Identifiable[data.length];
		
		// Increment backwards pr
		for(int i = data.length - 1; i >= 0; i--) {
			int currentVal = data[i].getId();
			// The value of the current object relative to 
			// the start of the array at minval.
			int relativeVal = currentVal - minVal;
			
			// Use the frequency array to put the 
			// current data into the sorted array
			sortedData[ arr[relativeVal] - 1] = data[i];
			// Decrement the frequency array counter
			arr[relativeVal]--;
		}
		
		// Replace data with sorted data
		for(int i = 0; i < data.length; i++) {
			data[i] = sortedData[i];
		}
	}
	
}
