package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the Activity object
 * 
 * @author Dustin Hollar
 */
public class ActivityTest {

    /** Test Activity.checkConflict() */
    @Test
    public void testCheckConflict() {

        Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 35, "MW", 1330, 1445);
        Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 35, "TH", 1330, 1445);
        try {
            a1.checkConflict(a2);
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
                    a2.getMeetingString());
        } catch (ConflictException e) {
            fail("A ConflictException was thrown when two Activities at the same time on "
                    + "completely distinct days were compared.");
        }

        // Test a4 time in a3
        Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 35, "MW", 800, 1100);
        Activity a4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 35, "WH", 900, 1000);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 8:00AM-11:00AM", a3.getMeetingString());
            assertEquals("WH 9:00AM-10:00AM", a4.getMeetingString());
        }

        // Test a4 end time in a3
        a4.setActivityTime(700, 900);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 8:00AM-11:00AM", a3.getMeetingString());
            assertEquals("WH 7:00AM-9:00AM", a4.getMeetingString());
        }

        // Test a3 end time in a4
        a3.setActivityTime(700, 900);
        a4.setActivityTime(800, 1100);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 7:00AM-9:00AM", a3.getMeetingString());
            assertEquals("WH 8:00AM-11:00AM", a4.getMeetingString());
        }

        // Test a4 start time in a3
        a3.setActivityTime(800, 1100);
        a4.setActivityTime(1000, 1200);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 8:00AM-11:00AM", a3.getMeetingString());
            assertEquals("WH 10:00AM-12:00PM", a4.getMeetingString());
        }

        // Test a3 start time in a4
        a3.setActivityTime(1000, 1200);
        a4.setActivityTime(800, 1100);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 10:00AM-12:00PM", a3.getMeetingString());
            assertEquals("WH 8:00AM-11:00AM", a4.getMeetingString());
        }

        // Test same a3 end time as a4 start time
        a3.setActivityTime(800, 1100);
        a4.setActivityTime(1100, 1200);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 8:00AM-11:00AM", a3.getMeetingString());
            assertEquals("WH 11:00AM-12:00PM", a4.getMeetingString());
        }

        // Test same a3 end time as a4 start time
        a3.setActivityTime(1200, 1300);
        a4.setActivityTime(1100, 1200);
        try {
            a3.checkConflict(a4);
            fail(); // ConflictException should have been thrown, but was not.
        } catch (ConflictException e) {
            // Check that the internal state didn't change during method call.
            assertEquals("MW 12:00PM-1:00PM", a3.getMeetingString());
            assertEquals("WH 11:00AM-12:00PM", a4.getMeetingString());
        }

        // Test same a3 end time as a4 start time
        a3.setActivityTime(800, 900);
        a3.setMeetingDays("MT");
        a4.setActivityTime(800, 900);
        try {
            a3.checkConflict(a4);
            assertEquals("MT 8:00AM-9:00AM", a3.getMeetingString());
            assertEquals("WH 8:00AM-9:00AM", a4.getMeetingString());
        } catch (ConflictException e) {
            fail(); // ConflictException should have been thrown, but was not.
        }
    }
}
