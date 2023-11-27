package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Student.
 * @author Connor Hekking
 */
public class StudentTest {

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

	/**
	 * Setup function for StudentTest.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Test the setFirst method for Student.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test the setLast method for Student.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Test the setId method for Student.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Test the setGpa method for Student.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Test the setUnityID method for Student.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Test the compareTo method for Student.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertEquals(sOne.compareTo(sOne), 0);
		assertEquals(sTwo.compareTo(sTwo), 0);
	}
	
	/**
	 * Test the equals method for Student.
	 */
	@Test
	public void testEquals() {
		assertFalse(sOne.equals(sFour));
		Student sSix = new Student("FiveFirst", "FiveLast", 5, 6, 6.0, "sixUnityID");
		assertTrue(sFive.equals(sSix));
		assertTrue(sOne.equals(sOne));
		//assertFalse(sOne.equals(1));
		//assertFalse(sOne.equals(null));
	}
	
	/**
	 * Test the toString method for Student.
	 */
	@Test
	public void testToString() {
		assertEquals(
		"Student [first=OneFirst, last=OneLast, "
		+ "id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]", 
		sOne.toString());
	}
	
	/**
	 * Test the hashCode method for Student.
	 */
	@Test
	public void testHashCode() {
		assertEquals(2080733795, sTwo.hashCode());
	}
	
	/**
	 * Test the setCreditHours method for Student.
	 */
	@Test
	public void testSetCreditHours() {
		sThree.setCreditHours(5);
		assertEquals(sThree.getCreditHours(), 5);
	}
}
