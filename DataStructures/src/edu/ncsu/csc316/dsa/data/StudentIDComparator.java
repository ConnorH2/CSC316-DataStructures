package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * @param one The first student to compare
	 * @param two The second student to compare
	 * @return Returns 1 if one's id is greater than two, -1 if two is greater than one, or 0 if equal.
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getId() > two.getId()) {
			return 1;
		} else if (one.getId() < two.getId()) {
			return -1;
		}
		return 0;
	}

}
