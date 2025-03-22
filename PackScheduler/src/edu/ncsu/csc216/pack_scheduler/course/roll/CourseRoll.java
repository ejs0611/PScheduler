package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;


/**
 * Course Roll
 * 
 * @author smithej
 *
 */
public class CourseRoll {

	
	/** roll array */
	private LinkedAbstractList<Student> roll;
	/** enrollment Cap*/
	private int enrollmentCap;
	/** min enrollment */
	private static final int MIN_ENROLLMENT = 10;
	/** max enrollment */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Course roll constructor
	 * 
	 * @param cap enrollment cap
	 */
	public CourseRoll(int cap) {
		setEnrollmentCap(cap);
		this.roll = new LinkedAbstractList<Student>(enrollmentCap);
	}

	/**
	 * getter for enrollment cap
	 * 
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * setter for enrollment cap
	 * 
	 * @param enrollmentCap the enrollmentCap to set
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if(enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment cap");
		}
		if(roll != null && enrollmentCap < roll.size()) {
			throw new IllegalArgumentException("Invalid enrollment cap");
		}
		this.enrollmentCap = enrollmentCap;
	}
	
	/**
	 * get number of open seats in a course
	 * 
	 * @return open seats
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	
	/**
	 * enroll
	 * 
	 * @param s student to be added
	 */
	public void enroll(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		if(getOpenSeats() <= 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		try {
			roll.add(s);
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * drop
	 * 
	 * @param s student to be added
	 */
	public void drop(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();	
		}
		try {
			roll.remove(s);
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * checks if a student can enroll
	 * 
	 * @param s student
	 * @return boolean if student can enroll
	 */
	public boolean canEnroll(Student s) {
		if(getOpenSeats() == 0) {
			return false;
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
}
