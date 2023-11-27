package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for StudentGPAComparator.
 * @author Connor Hekking
 */
public class StudentGPAComparatorTest {

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
	/** StudentGPAComparator test object */
	private StudentGPAComparator comparator;

	/**
	 * Setup function for StudentGPAComparatorTest.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests the compare method of StudentGPAComparator.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);

		assertEquals(comparator.compare(sTwo, sTwo), 0);
		
		assertTrue(comparator.compare(sFive, sThree) < 0);
		assertTrue(comparator.compare(sOne, sFour) > 0);
	}

}
