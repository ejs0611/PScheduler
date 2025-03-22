package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class ScheduleTest {

	@Test
	void testSchedule() {
		Schedule wasd = new Schedule();
		
		assertEquals(wasd.getTitle(), "My Schedule");
	}

	@Test
	void testAddCourseToSchedule() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course sss = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ddd = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");

		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(sss));
		assertTrue(wasd.addCourseToSchedule(ddd));
	}

	@Test
	void testRemoveCourseFromSchedule() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course bbb = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ccc = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");

		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(bbb));
		assertTrue(wasd.addCourseToSchedule(ccc));

		Course ttt = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		assertTrue(wasd.removeCourseFromSchedule(ttt));
		assertTrue(wasd.removeCourseFromSchedule(bbb));
		assertTrue(wasd.removeCourseFromSchedule(ccc));
	}

	@Test
	void testResetSchedule() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course bbb = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ccc = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");

		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(bbb));
		assertTrue(wasd.addCourseToSchedule(ccc));

		assertTrue(wasd.removeCourseFromSchedule(aaa));
		assertTrue(wasd.removeCourseFromSchedule(bbb));
		assertTrue(wasd.removeCourseFromSchedule(ccc));
		
		wasd.setTitle("McLovingIt");
		assertEquals(wasd.getTitle(), "McLovingIt");
		
		wasd.resetSchedule();

		assertEquals(wasd.getTitle(), "My Schedule");
	}

	@Test
	void testGetScheduledCourses() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course bbb = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ccc = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");

		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(bbb));
		assertTrue(wasd.addCourseToSchedule(ccc));
		
		String[][] ddd = new String[3][4];
		ddd[0][0] = "CSC126"; ddd[0][1] = "001"; ddd[0][2] = "wasd"; ddd[0][3] = "Arranged";
		ddd[1][0] = "CSC226"; ddd[1][1] = "002"; ddd[1][2] = "wasd1"; ddd[1][3] = "Arranged";
		ddd[2][0] = "CSC326"; ddd[2][1] = "003"; ddd[2][2] = "wasd2"; ddd[2][3] = "Arranged";

		assertEquals(wasd.getScheduledCourses()[0][0], ddd[0][0]);
		assertEquals(wasd.getScheduledCourses()[0][1], ddd[0][1]);
		assertEquals(wasd.getScheduledCourses()[0][2], ddd[0][2]);
		assertEquals(wasd.getScheduledCourses()[0][3], ddd[0][3]);

		assertEquals(wasd.getScheduledCourses()[1][0], ddd[1][0]);
		assertEquals(wasd.getScheduledCourses()[1][1], ddd[1][1]);
		assertEquals(wasd.getScheduledCourses()[1][2], ddd[1][2]);
		assertEquals(wasd.getScheduledCourses()[1][3], ddd[1][3]);

		assertEquals(wasd.getScheduledCourses()[2][0], ddd[2][0]);
		assertEquals(wasd.getScheduledCourses()[2][1], ddd[2][1]);
		assertEquals(wasd.getScheduledCourses()[2][2], ddd[2][2]);
		assertEquals(wasd.getScheduledCourses()[2][3], ddd[2][3]);
	}

	@Test
	void testSetTitle() {
		Schedule wasd = new Schedule();
		wasd.setTitle("Javawasd");
		assertEquals(wasd.getTitle(), "Javawasd");
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> wasd.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage());
	}

	@Test
	void testGetTitle() {
		Schedule wasd = new Schedule();
		wasd.setTitle("Javawasd");
		assertEquals(wasd.getTitle(), "Javawasd");
	}
	
	@Test
	void testGetScheduledCredits() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course sss = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ddd = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");

		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(sss));
		assertTrue(wasd.addCourseToSchedule(ddd));
		
		assertEquals(12, wasd.getScheduleCredits());
	}
	
	@Test
	void testCanAdd() {
		Schedule wasd = new Schedule();
		Course aaa = new Course("CSC126", "wasd", "001", 4, "jMcMullin", 50, "A");
		Course sss = new Course("CSC226", "wasd1", "002", 4, "jMcMullin", 50, "A");
		Course ddd = new Course("CSC326", "wasd2", "003", 4, "jMcMullin", 50, "A");
		assertTrue(wasd.addCourseToSchedule(aaa));
		assertTrue(wasd.addCourseToSchedule(sss));
		
		assertFalse(wasd.canAdd(null));
		assertTrue(wasd.canAdd(ddd));
		
		
	}

}
