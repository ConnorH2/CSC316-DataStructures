/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for BubbleSorter.
 * @author Connor Hekking
 */
public class BubbleSorterTest {

	/** Ascending data array for testing */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Descending data array for testing */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Random data array for testing */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** BubbleSorter object for testing */
	private BubbleSorter<Integer> integerSorter;

	/**
	 * Setup function for BubbleSorterTest.
	 */
	@Before
	public void setUp() {
		integerSorter = new BubbleSorter<Integer>();
	}

	/**
	 * Tests sorting integers using BubbleSorter.
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
	}

	/**
	 * Tests sorting students using BubbleSorter.
	 */
	@Test
	public void testSortStudent() {
		Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		BubbleSorter<Student> studentSorter = new BubbleSorter<Student>();
		
		Student[] dataStudent = { sOne, sTwo, sThree, sFour, sFive };
		
		studentSorter.sort(dataStudent);
		assertEquals(sFive, dataStudent[0]);
		assertEquals(sFour, dataStudent[1]);
		assertEquals(sOne, dataStudent[2]);
		assertEquals(sThree, dataStudent[3]);
		assertEquals(sTwo, dataStudent[4]);
		
		sOne = new Student("bFirst", "last", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("aFirst", "last", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("cFirst", "last", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("cFirst", "last", 2, 4, 4.0, "fourUnityID");
		sFive = new Student("cFirst", "last", 1, 5, 5.0, "fiveUnityID");
		Student[] dataStudent2 = { sOne, sTwo, sThree, sFour, sFive };
		
		studentSorter.sort(dataStudent2);
		assertEquals(sTwo, dataStudent2[0]);
		assertEquals(sOne, dataStudent2[1]);
		assertEquals(sFive, dataStudent2[2]);
		assertEquals(sFour, dataStudent2[3]);
		assertEquals(sThree, dataStudent2[4]);
		
		StudentGPAComparator comparator2 = new StudentGPAComparator();
		BubbleSorter<Student> studentSorter2 = new BubbleSorter<Student>(comparator2);
		
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student[] dataStudent3 = { sOne, sTwo, sThree, sFour, sFive };
		Student[] dataStudent4 = { sOne, sTwo, sThree, sFour, sFive };
		
		studentSorter2.sort(dataStudent3);
		assertEquals(sFive, dataStudent3[0]);
		assertEquals(sFour, dataStudent3[1]);
		assertEquals(sThree, dataStudent3[2]);
		assertEquals(sTwo, dataStudent3[3]);
		assertEquals(sOne, dataStudent3[4]);
		
		studentSorter2.sort(dataStudent3);
		assertEquals(sOne, dataStudent4[0]);
		assertEquals(sTwo, dataStudent4[1]);
		assertEquals(sThree, dataStudent4[2]);
		assertEquals(sFour, dataStudent4[3]);
		assertEquals(sFive, dataStudent4[4]);
		
	}

}
