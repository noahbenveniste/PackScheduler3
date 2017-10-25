package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Custom Exception for an invalid state of the FSM for Course Name
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */
public class InvalidTransitionException extends Exception {

    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;
    
    /** Constructor with default error message */
    public InvalidTransitionException() {
        super("Invalid FSM Transition.");
    }
    
    /** 
     * Constructor with custom error message 
     * 
     * @param message the user wants to display
     */
    public InvalidTransitionException(String message) {
        super(message);
    }
    
}
