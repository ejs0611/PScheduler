package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/** the test class that is used to test the course name validator FSM.*/
public class CourseNameValidatorFSMTest {
	
	/** test the method testIsValid works correctly*/
	@Test
	public void testIsValid() throws InvalidTransitionException{
		// valid letter tests
		String oneLThreeDZeroS = "c116";
		String twoLThreeDZeroS = "cs116";
		String threeLThreeDZeroS = "csc116";
		String fourLThreeDZeroS = "cscs116";
		String fourLThreeDOneS = "cscs116a";
		
		
		
		CourseNameValidatorFSM thing = new CourseNameValidatorFSM();
		assertTrue(thing.isValid(oneLThreeDZeroS));
		assertTrue(thing.isValid(twoLThreeDZeroS));
		assertTrue(thing.isValid(threeLThreeDZeroS));
		assertTrue(thing.isValid(fourLThreeDZeroS));
		assertTrue(thing.isValid(fourLThreeDOneS));
		
		//false tests
		String oneLZeroDZeroS = "c";
		
		
		assertFalse(thing.isValid(oneLZeroDZeroS));
		
	
		//throws tests
		String oneLOneDOneS = "c1c";
		String oneLTwoDOneS = "c11c";
		String zeroLOneDZeroS = "1";
		String oneExclam = "!";
		String fiveL = "abcde";
		String oneLFourDZeroS = "c1111";
		String fourLThreeDOneSOneD = "cscs116a1";
		
		
		InvalidTransitionException e1 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(oneLOneDOneS));
		assertEquals("Course name must have 3 digits.", e1.getMessage());
		
		InvalidTransitionException e2 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(oneLTwoDOneS));
		assertEquals("Course name must have 3 digits.", e2.getMessage());
		
		InvalidTransitionException e3 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(zeroLOneDZeroS));
		assertEquals("Course name must start with a letter.", e3.getMessage());
		
		InvalidTransitionException e4 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(oneExclam));
		assertEquals("Course name can only contain letters and digits.", e4.getMessage());
		
		InvalidTransitionException e5 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(fiveL));
		assertEquals("Course name cannot start with more than 4 letters.", e5.getMessage());
		
		InvalidTransitionException e6 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(oneLFourDZeroS));
		assertEquals("Course name can only have 3 digits.", e6.getMessage());
		
		InvalidTransitionException e7 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(fourLThreeDOneSOneD));
		assertEquals("Course name cannot contain digits after the suffix.", e7.getMessage());
		
		InvalidTransitionException e8 = assertThrows(InvalidTransitionException.class,
				() -> thing.isValid(fourLThreeDOneSOneD));
		assertEquals("Course name cannot contain digits after the suffix.", e8.getMessage());
		
		
		

	}
	
}
