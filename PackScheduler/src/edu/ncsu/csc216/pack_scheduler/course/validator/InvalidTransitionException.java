package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 *The unchecked exception that need 
 */
public class InvalidTransitionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * the exception with default message. 
	 * @param message the default message in the parent class.
	 */
	public InvalidTransitionException(String message) {
		super(message);
		
	}
	/**the exception with costume message. */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
}
