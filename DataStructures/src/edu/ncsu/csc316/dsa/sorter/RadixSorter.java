package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	
	/**
	 * Function that sorts the given data in ascending order according to
	 * the compareTo function, using the Radix sort algorithm.
	 * @param data The array of data to sort.
	 */
	@Override
	public void sort(E[] data) {
		
		int maxVal = 0;
		
		// Find the largest value in the data
		for(int i = 0; i < data.length; i++) {
			maxVal = Integer.max(maxVal, data[i].getId());
		}
		
		// Find the number of digits in the largest value
		int digits = (int) Math.ceil(Math.log10(maxVal + 1));
		
		int digitDivisor = 1;
		for(int j = 0; j < digits; j++) {
			
			int[] arr = new int[10];
			
			// Enter the digit values into the array
			for(int i = 0; i < data.length; i++) {
				// The value of the current data piece
				int currentVal = data[i].getId();
				// The single digit of the current data piece
				int currentDigit = (currentVal / digitDivisor) % 10;
				arr[currentDigit]++;
			}
			
			// Accumulate the frequencies
			for(int i = 1; i < 10; i++) {
				arr[i] += arr[i - 1];
			}
			
			// Create a new array for the sorted data
			@SuppressWarnings("unchecked")
			E[] sortedData = (E[]) new Identifiable[data.length];
			
			// Sort the original data into the new array using the frequency array
			for(int i = data.length - 1; i >= 0; i--) {
				// The value of the current data piece
				int currentVal = data[i].getId();
				// The single digit of the current data piece
				int currentDigit = (currentVal / digitDivisor) % 10;
				
				// Use the frequency array value minus one to put the 
				// current data into the sorted array
				sortedData[ arr[currentDigit] - 1 ] = data[i];
				
				// Decrement the frequency array counter
				arr[currentDigit]--;
			}
			
			// Overwrite the old data array with the sorted array
			for(int i = 0; i < data.length; i++) {
				data[i] = sortedData[i];
			}
			
			// Multiply the digit divisor by 10 so that the 
			// next radix(0-9 digit) will be sorted
			digitDivisor *= 10;
		}
	}
}
