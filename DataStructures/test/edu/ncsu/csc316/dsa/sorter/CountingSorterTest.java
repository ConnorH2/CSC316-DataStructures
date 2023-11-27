package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for CountingSorter.
 * @author Connor Hekking
 */
public class CountingSorterTest {
	
	/** Student one test object */
	private Student sOne;
	/** Student two test object */
	private Student sTwo;
	/** Student three test object */
	private Student sThree;
	/** Student four test object */
	private Student sFour;
	/** Student five test object */
	private Student sFive;
	/** CountingSorter object for testing */
	private CountingSorter<Student> sorter;

	/**
	 * Setup function for CountingSorterTest.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Tests sorting students using CountingSorter.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student[] sorted = { sOne, sTwo, sThree, sFour, sFive };
		Student[] reverse = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(reverse);
		sorter.sort(sorted);

		assertEquals(sOne, sorted[0]);
		assertEquals(sTwo, sorted[1]);
		assertEquals(sThree, sorted[2]);
		assertEquals(sFour, sorted[3]);
		assertEquals(sFive, sorted[4]);
		
		assertEquals(sOne, reverse[0]);
		assertEquals(sTwo, reverse[1]);
		assertEquals(sThree, reverse[2]);
		assertEquals(sFour, reverse[3]);
		assertEquals(sFive, reverse[4]);
		
		Student stOne = new Student("OneFirst", "OneLast", -100, 1, 1.0, "oneUnityID");
		Student stTwo = new Student("TwoFirst", "TwoLast", 0, 2, 2.0, "twoUnityID");
		Student stThree = new Student("ThreeFirst", "ThreeLast", 1, 3, 3.0, "threeUnityID");
		Student stFour = new Student("FourFirst", "FourLast", 400, 4, 4.0, "fourUnityID");
		Student stFive = new Student("FiveFirst", "FiveLast", 401, 5, 5.0, "fiveUnityID");
		
		Student[] array4 = {stThree, stTwo, stFive, stOne, stFour};
		sorter.sort(array4);
		
		assertEquals(stOne, array4[0]);
		assertEquals(stTwo, array4[1]);
		assertEquals(stThree, array4[2]);
		assertEquals(stFour, array4[3]);
		assertEquals(stFive, array4[4]);
	}
	

}
