package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests InvalidTransitionException
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */
public class InvalidTransitionExceptionTest {

    /**
     * Tests InvalidTransitionException constructor
     */
    @Test
    public void testInvalidTransitionException() {
        try {
            throw new InvalidTransitionException();
        } catch (InvalidTransitionException e) {
            assertEquals("Invalid FSM Transition.", e.getMessage());
        }
    }

    /**
     * Tests InvalidTransitionException constructor with string parameter
     */
    @Test
    public void testInvalidTransitionExceptionString() {
        try {
            throw new InvalidTransitionException("Bleh");
        } catch (InvalidTransitionException e) {
            assertEquals("Bleh", e.getMessage());
        }
    }
}
