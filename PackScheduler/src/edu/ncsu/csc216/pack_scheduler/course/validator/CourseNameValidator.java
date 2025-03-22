package edu.ncsu.csc216.pack_scheduler.course.validator;


/** the test class that is used to check the course name that is valid.*/
public class CourseNameValidator {
	
	/** Initial state before input is examined */
	private final int stateInitial = 0;
	
	/** State at which one letter has been identified */
	private final int stateL = 1;
	
	/** State at which two letters have been identified */
	private final int stateLL = 2;
	
	/** State at which three letters have been identified */
	private final int stateLLL = 3;
	
	/** State at which four letters have been identified */
	private final int stateLLLL = 4;
	
	/** State at which one digit has been identified */
	private final int stateD = 5;
	
	/** State at which two digits have been identified */
	private final int stateDD = 6;
	
	/** State at which three digits have been identified */
	private final int stateDDD = 7;
	
	/** State at which a suffix letter has been identified */
	private final int stateSuffix = 8;
	
	/** The state variable keeps track of the current FSM state we are in */
	private int state;
	
	/** Checks if the course name is valid
	 * @param courseName - the course name being added
	 * @return - True or false based on if it was added or not
	 * @throws InvalidTransitionException - Catches the invalid transitions of the name 
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException{
		
		// Puts the state that we're tracking into existence
		State currentState = null;
		
		// Checks if courseName is null
		if(courseName == null) {
			throw new InvalidTransitionException("Input for isValid is null.");
		}
		
		// Resets the state variable
		state = 0;
		
		// Goes through each of the characters and checks it
		for(int c = 0; c < courseName.length(); c++) {
			char s = courseName.charAt(c);
			
			
			// Checks to see what state the currentState var is at
			if(state == stateInitial) {
				
				// Sets the current state to Initial because that is what the currentState variable at
				currentState = new InitialState();
			} else if (state == 1 || state == 2 || state == 3 || state == 4) {
				
				// Sets the currentState to LetterState because it has gone into the next transition
				currentState = new LetterState();
			} else if (state ==  5 || state == 6 || state == 7) {

				// Sets the currentState to NumberState because it has gone into the next transition
				currentState = new NumberState();
			} else if (state == 8) {

				// Sets the currentState to SuffixState because it has gone into the next transition
				currentState = new SuffixState();
			}
			// Might have to add a error above it the state is not one of the nombers
			
			// Checks if the number isn't a character or digit
			if(!Character.isLetter(s) && !Character.isDigit(s)) {
				currentState.onOther();
			}
			
			// If the character is a letter, it goes into the onLetter() method in the respective class
			if(Character.isLetter(s)) {
				currentState.onLetter();
			} else {
				
				// Else it goes to onDigit()
				currentState.onDigit();
			}
		}
		
		// This is just a placeholder for now because I can't really test the tests out
		return state == stateDDD || state == stateSuffix;
		
	}
	
	/**A state class that is used to track on the statues of the program. */
	public abstract class State {
		
		/**A method that indicate the current input is a letter. */
		public abstract void onLetter() throws InvalidTransitionException;
		/**A method that indicate the current input is a digit. */
		public abstract void onDigit() throws InvalidTransitionException;
		/**A method that indicate the current input not a letter or digit . */
		public void onOther() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	/**the initial state of the program. */
	public class InitialState extends State {
		
		/**check the input is on letter */
		public void onLetter() throws InvalidTransitionException{
			state = stateL;
		}
		/**check the input is on digit */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 *The inner class that is used to check the input on letter and update the state.
	 */
	public class LetterState extends State {
		

		/**check the input is on letter */
		public void onLetter() throws InvalidTransitionException {
			if(state == stateL) {
				state = stateLL;
			} else if(state == stateLL) {
				state = stateLLL;
			} else if(state == stateLLL) {
				state = stateLLLL;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**A method that indicates the current input is a digit. */
		public void onDigit() throws InvalidTransitionException {
			if(state == stateL) {
				state = stateD;
			} else if(state == stateLL) {
				state = stateD;
			} else if(state == stateLLL) {
				state = stateD;
			} else if(state == stateLLLL) {
				state = stateD;
			} else {
				throw new InvalidTransitionException("Course name must start with a letter.");
			}
		}
	}
	
	/**the state that used to indicate the current state is on number */
	public class NumberState extends State { 
		
		/**A method that indicate the current input is a letter. */
		public void onLetter() throws InvalidTransitionException{
			if(state == stateDDD) {
				state = stateSuffix;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		/**A state that indicate the current input is a digit. */
		public void onDigit() throws InvalidTransitionException {
			if(state == stateD) {
				state = stateDD;
			} else if(state == stateDD) {
				state = stateDDD;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
	
	/**A state that is used to check the suffix of the input */
	public class SuffixState extends State { 
		/**
		 * onLetters
		 */



		
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
		/**A state that indicate the current input is a digit. */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
	
}
