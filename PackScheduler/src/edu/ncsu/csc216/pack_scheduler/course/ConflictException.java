package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Exception that calls when there is conflict in Activities in the Schedule
 * 
 * @author Dustin Hollar
 */
public class ConflictException extends Exception {
    
    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Send a message to the terminal for the exception
     * @param message send a message to the terminal
     */
    public ConflictException(String message) {
        super(message);
    }
    
    /** Send default message to the terminal */
    public ConflictException() {
        super("Schedule conflict.");
    }

}
