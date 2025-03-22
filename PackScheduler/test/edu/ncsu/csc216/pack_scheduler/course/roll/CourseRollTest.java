package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

class CourseRollTest {

	@Test
	void testConstructor() {
		CourseRoll x = new CourseRoll(50);
		assertEquals(50, x.getOpenSeats());
		assertEquals(50, x.getEnrollmentCap());
	}

	@Test
	void testSetEnrollmentCap() {
		CourseRoll x = new CourseRoll(50);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> x.setEnrollmentCap(5));
		assertEquals("Invalid enrollment cap", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> x.setEnrollmentCap(251));
		assertEquals("Invalid enrollment cap", e2.getMessage());
	}
	
	@Test
	void testEnroll() {
		CourseRoll x = new CourseRoll(10);
		Student s = new Student("fred", "jones", "fjones", "fjones@ncsu.edu", "pw");
		Student s2 = new Student("fred", "jones", "fjones", "fjones@ncsu.edu", "pw");
		x.enroll(s);
		assertEquals(9, x.getOpenSeats());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> x.enroll(null));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> x.enroll(s2));
		assertEquals(null, e2.getMessage());
	}
	
	@Test
	void testDrop() {
		CourseRoll x = new CourseRoll(10);
		Student s = new Student("fred", "jones", "fjones", "fjones@ncsu.edu", "pw");
		x.enroll(s);
		x.drop(s);
		assertEquals(10, x.getOpenSeats());
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> x.drop(null));
		assertEquals(null, e1.getMessage());
	}
	
	@Test
	void testCanEnroll() {
		CourseRoll x = new CourseRoll(10);
		Student s = new Student("fred", "jones", "fjones", "fjones@ncsu.edu", "pw");
		assertTrue(x.canEnroll(s));
		x.enroll(s);
		assertFalse(x.canEnroll(s));
		
		x.enroll(new Student("fred2", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred3", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred4", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred5", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred6", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred7", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred8", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred9", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		x.enroll(new Student("fred10", "jones", "fjones", "fjones@ncsu.edu", "pw"));
		assertFalse(x.canEnroll(new Student("fred11", "jones", "fjones", "fjones@ncsu.edu", "pw")));
		
		
	}
	
	
	
}
