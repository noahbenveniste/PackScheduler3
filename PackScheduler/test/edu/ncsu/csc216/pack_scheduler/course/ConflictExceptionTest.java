package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ConflictException
 * 
 * @author Dustin Hollar
 */
public class ConflictExceptionTest {

    /** Test conflictException with a message parameter */
    @Test
    public void testConflictExceptionString() {
        ConflictException ce = new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /** Test conflictException without a message parameter */
    @Test
    public void testConflictException() {
        ConflictException ce = new ConflictException();
        assertEquals("Schedule conflict.", ce.getMessage());
    }

}
