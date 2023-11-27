package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
/**
 * Test class for StudentManager.
 * @author Connor Hekking
 */
public class StudentManagerTest {

	/** StudentManager object for testing */
	private StudentManager sm;
	
	/**
	 * Setup function for StudentManagerTest.
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
	}
	
	/**
	 * Tests the sort method in StudentManager.
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
		
		InsertionSorter<Student> sorter1 = new InsertionSorter<Student>(new StudentGPAComparator());
		sm = new StudentManager("input/student_ascendingID.csv", sorter1);
		
		Student[] sorted2 = sm.sort();
		
		assertEquals("Nichole", sorted2[0].getFirst());
		assertEquals("Alicia", sorted2[1].getFirst());
		assertEquals("Charlene", sorted2[2].getFirst());
		assertEquals("Cristine", sorted2[3].getFirst());
		assertEquals("Dante", sorted2[4].getFirst());
		assertEquals("Lacie", sorted2[5].getFirst());
		assertEquals("Idalia", sorted2[6].getFirst());
		assertEquals("Ara", sorted2[7].getFirst());
		assertEquals("Loise", sorted2[8].getFirst());
		assertEquals("Tanner", sorted2[9].getFirst());
		assertEquals("Amber", sorted2[10].getFirst());
		assertEquals("Roxann", sorted2[11].getFirst());
		assertEquals("Tyree", sorted2[12].getFirst());
		assertEquals("Evelin", sorted2[13].getFirst());
		assertEquals("Shanti", sorted2[14].getFirst());
		assertEquals("Lewis", sorted2[15].getFirst());
	}

}
