package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class contains the first name, last name, id, email, password, and
 * maximum credits for the student to enter in their directory while creating
 * their directory. A Student will be listed in the directory after they add the
 * student to it.
 * 
 * @author Jerolyn ClementRaj
 * @author Thien Nguyen
 * @author Eric Smith
 *
 */
public class Student extends User implements Comparable<Student> {
	/** Students maxCredits */
	private int maxCredits;
	/** maximum number of credits */
	public static final int MAX_CREDITS = 18;
	/** Students schedule */
	private Schedule schedule;

	/**
	 * Constructs a Student object with values for all fields.
	 * 
	 * @param firstName  first name of Student 
	 * @param lastName   last name of Student
	 * @param id         id of Student
	 * @param email      email of Student
	 * @param hashPW     password of Student
	 * @param maxCredits maximum number of credits of Student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (!super.equals(obj)) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
		Student other = (Student) obj;
//		if (maxCredits != other.maxCredits) {
//			return false;
//		}
//		return true;
		return this == obj || super.equals(obj) && getClass() == obj.getClass() && maxCredits == other.maxCredits;
	}

	/**
	 * Constructs a Student object with values for all fields but max credits.
	 * 
	 * @param firstName first name of Student
	 * @param lastName  last name of Student
	 * @param id        id of Student
	 * @param email     email of Student 
	 * @param hashPW    password of Student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		schedule = new Schedule();
	}

	/**
	 * Returns a comma separated value String of all Student fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	/**
	 * Sets the Student's maximum credits.
	 * 
	 * @param maxCredits the maximum credits to set
	 * @throws IllegalArgumentException if the maxCredits parameter is invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns the Student's maximum credits. 
	 * 
	 * @return the maximum credits
	 */
	public int getMaxCredits() { 
		return maxCredits;
	}

	/**
	 * The method compares the current instance Student with the parameter s,
	 * another Student, for the sorted order based on their last name, first name,
	 * and then unity id.
	 * 
	 * @return 1 if this Student is lexicographically follows s, the Student being
	 *         compared to, -1 if this Student is lexicographically precedes s, or 0
	 *         if this Student is equal to s.
	 * @param s the Student being compared to
	 * @throws NullPointerException if the Student s is null
	 * @throws ClassCastException   if s specified object's type prevents it from
	 *                              being compared to this Student object
	 */
	@Override
	public int compareTo(Student s) {
		if (s == null) {
			throw new NullPointerException();
		}
		if (getClass() != s.getClass()) {
			throw new ClassCastException();
		}
		if (this.getLastName().compareTo(s.getLastName()) > 0) {
			return 1;
		} else if (this.getLastName().compareTo(s.getLastName()) < 0) {
			return -1;
		} else {
			if (this.getFirstName().compareTo(s.getFirstName()) > 0) {
				return 1;
			} else if (this.getFirstName().compareTo(s.getFirstName()) < 0) {
				return -1;
			} else {
				if (this.getId().compareTo(s.getId()) > 0) {
					return 1;
				} else if (this.getId().compareTo(s.getId()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 *  return the schedule
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	
	
	/**
	 * can add
	 * 
	 * @param c course
	 * @return boolean if can add
	 */
	public boolean canAdd(Course c) {
		int x = c.getCredits() + schedule.getScheduleCredits();
		if(x > maxCredits) {
			return false;
		}
		if(!schedule.canAdd(c)) {
			return false;
		}
		
		return true;
	}

}