package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 * @param one The first student to compare
	 * @param two The second student to compare
	 * @return Returns -1 if one's gpa is greater than two, 1 if two is greater than one, or 0 if equal.
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getGpa() > two.getGpa()) {
			return -1;
		} else if (one.getGpa() < two.getGpa()) {
			return 1;
		}
		return 0;
	}

}
