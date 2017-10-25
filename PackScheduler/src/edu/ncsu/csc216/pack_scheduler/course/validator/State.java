package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Parent class that oversees the state of the FSM
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */

public abstract class State {
    
    /** Construct the State Object */
    public State() {
        //Literally does nothing.
    }
    
    /**
     * Check if the State is on a character
     * 
     * @throws InvalidTransitionException if there is an invalid state transition
     */
    public abstract void onLetter() throws InvalidTransitionException;
    
    /**
     * Check if the State is on a character
     * 
     * @throws InvalidTransitionException if there is an invalid state transition
     */
    public abstract void onDigit() throws InvalidTransitionException;
    
    /**
     * Check if the State is on a digit
     * 
     * @throws InvalidTransitionException if there is an invalid state transition or is not on
     * a letter or digit
     */
    public void onOther() throws InvalidTransitionException {
        
        throw new InvalidTransitionException("Course name can only contain letters and digits.");
        
    }

}
