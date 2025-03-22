package edu.ncsu.csc216.pack_scheduler.course.validator;

import edu.ncsu.csc216.pack_scheduler.course.Activity;

/** 
 * the interface that is used to check the invalid transition.
 */
public interface InvalidTransition {
	/**
	 * the method that need to be override in the 
	 * @param activity the given activity
	 * @throws InvalidTransitionException the exception that need to be thrown if there are any conflict.
	 */
	void checkInvalidTransition(Activity activity) throws InvalidTransitionException;
}
