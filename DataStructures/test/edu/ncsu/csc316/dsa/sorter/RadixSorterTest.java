/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for RadixSorter.
 * @author Connor Hekking
 */
public class RadixSorterTest {

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
	/** Student six test object */
	private Student sSix;
	/** Student seven test object */
	private Student sSeven;
	/** Student eight test object */
	private Student sEight;
	/** Student nine test object */
	private Student sNine;
	/** Student ten test object */
	private Student sTen;
	/** RadixSorter object for testing */
	private RadixSorter<Student> sorter;

	/**
	 * Setup function for RadixSorterTest.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 0, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 6, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 34, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 99, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 100, 5, 5.0, "fiveUnityID");
		sSix = new Student("SixFirst", "SixLast", 101, 6, 6.0, "sixUnityID");
		sSeven = new Student("SevenFirst", "SevenLast", 111, 7, 7.0, "sevenUnityID");
		sEight = new Student("EightFirst", "EightLast", 130, 8, 8.0, "eightUnityID");
		sNine = new Student("NineFirst", "NineLast", 623, 9, 9.0, "nineUnityID");
		sTen = new Student("TenFirst", "TenLast", 999, 10, 10.0, "tenUnityID");
		
		sorter = new RadixSorter<Student>();
	}
	
	/**
	 * Tests RadixSorter using student objects.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTen, sTwo, sSix, sEight, sOne, sFour, sSeven, sThree, sFive, sNine };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		assertEquals(sSix, original[5]);
		assertEquals(sSeven, original[6]);
		assertEquals(sEight, original[7]);
		assertEquals(sNine, original[8]);
		assertEquals(sTen, original[9]);
		
		Student[] sorted = { sOne, sTwo, sThree, sFour, sFive, sSix, sSeven, sEight, sNine, sTen };
		Student[] reverse = { sTen, sNine, sEight, sSeven, sSix, sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(reverse);
		sorter.sort(sorted);

		assertEquals(sOne, sorted[0]);
		assertEquals(sTwo, sorted[1]);
		assertEquals(sThree, sorted[2]);
		assertEquals(sFour, sorted[3]);
		assertEquals(sFive, sorted[4]);
		assertEquals(sSix, sorted[5]);
		assertEquals(sSeven, sorted[6]);
		assertEquals(sEight, sorted[7]);
		assertEquals(sNine, sorted[8]);
		assertEquals(sTen, sorted[9]);
		
		assertEquals(sOne, reverse[0]);
		assertEquals(sTwo, reverse[1]);
		assertEquals(sThree, reverse[2]);
		assertEquals(sFour, reverse[3]);
		assertEquals(sFive, reverse[4]);
		assertEquals(sSix, reverse[5]);
		assertEquals(sSeven, reverse[6]);
		assertEquals(sEight, reverse[7]);
		assertEquals(sNine, reverse[8]);
		assertEquals(sTen, reverse[9]);
	}
}
