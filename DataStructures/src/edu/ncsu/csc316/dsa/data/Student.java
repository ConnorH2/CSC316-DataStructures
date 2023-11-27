package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 */
public class Student implements Comparable<Student>, Identifiable {
	/** The first name of the student */
	private String first;
	/** The last name of the student */
	private String last;
	/** The id number of the student */
	private int id;
	/** The number of credit hours the student is taking */
	private int creditHours;
	/** The GPA value of the student */
	private double gpa;
	/** The unity id of the student */
	private String unityID;
	
	/**
	 * Constructor for the student class which 
	 * initializes all of Student's fields with the given values.
	 * @param first The first name of the student.
	 * @param last The last name of the student.
	 * @param id The id number of the student.
	 * @param creditHours The number of credit hours the student is taking.
	 * @param gpa The GPA value of the student.
	 * @param unityID The unity id of the student.
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * Creates a String representation of the Student's fields.
	 * @return Returns a string representation of the Student.
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	/**
	 * Creates a unique hash code using the Student's
	 * first name, last name, and id number
	 * @return Returns a unique integer hash code for the student.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(first, id, last);
	}

	/**
	 * Determines if this and another Student are equal to one another
	 * using their first names, last names, and id numbers.
	 * @param obj The object to compare this Student to.
	 * @return Returns true if the two Students are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(first, other.first) && id == other.id && Objects.equals(last, other.last);
	}

	/**
	 * Returns the first name of the student.
	 * @return The first name of the student.
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first name of the student.
	 * @param first The first name to set.
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns the last name of the student.
	 * @return The last name of the student.
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last name of the student.
	 * @param last The last name to set.
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Returns the id number of the student.
	 * @return The id number of the student.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id number of the student.
	 * @param id The id number to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the number of credit hours the student is taking.
	 * @return The number of credit hours the student is taking.
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the number of credit hours the student is taking.
	 * @param creditHours The number of credit hours to set.
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns the student's gpa.
	 * @return The student's gpa.
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the student's gpa.
	 * @param gpa The gpa to set.
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns the unity id of the student
	 * @return The unity id of the student.
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the unity id of the student.
	 * @param unityID The unity id to set.
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Compares two student objects and returns 1 if this student is 
	 * greater, -1 if the other student is greater, and 0 if both students 
	 * are equal. The comparison is done using last name, then first name, 
	 * then student id.
	 * @param o The other student to compare to.
	 * @return The integer 1 if greater than, -1 if less than, or 0 if equal to the other student.
	 */
	@Override
	public int compareTo(Student o) {
		int comparison1 = this.getLast().compareTo(o.getLast());
		if(comparison1 != 0) {
			return comparison1;
		}
		int comparison2 = this.getFirst().compareTo(o.getFirst());
		if(comparison2 != 0) {
			return comparison2;
		}
		int comparison3 = this.getId() - o.getId();
		if(comparison3 > 0) {
			return 1;
		} else if(comparison3 < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
