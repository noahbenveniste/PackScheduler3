package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Unit Test Class for Schedule
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */

public class ScheduleTest {
    
    private Schedule schedule;
    
    /** Course name */
    private static final String NAME = "CSC216";
    /** Course title */
    private static final String TITLE = "Programming Concepts - Java";
    /** Course section */
    private static final String SECTION = "001";
    /** Course credits */
    private static final int CREDITS = 4;
    /** Course instructor id */
    private static final String INSTRUCTOR_ID = "sesmith5";
    /** Course enrollment cap */
    private static final int ENROLLMENT_CAP = 35;
    /** Course meeting days */
    private static final String MEETING_DAYS = "TH";
    /** Course start time */
    private static final int START_TIME = 1330;
    /** Course end time */
    private static final int END_TIME = 1445;

    /**
     * Sets up schedule object to be tested
     */
    @Before
    public void setUp() {
        schedule = new Schedule();
    }
    
    /** 
     * Test the constructor for schedule 
     */
    @Test
    public void testSchedule() {

        assertEquals("My Schedule", schedule.getTitle());
        assertEquals(0, schedule.getSchedule().size());
        // fail("Not yet implemented");
    }

    /**
     * Tests the add course to schedule
     */
    @Test
    public void testAddCourseToSchedule() {
        
        try {
            schedule.addCourseToSchedule(null);
        }
        catch(NullPointerException e) {
            assertEquals("Course cannot be null", e.getMessage());
        }
        
        // Add A Valid Course
        Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        assertTrue(schedule.addCourseToSchedule(course1));
        
        try {
            schedule.addCourseToSchedule(course1);
        }
        catch(IllegalArgumentException e) {
            assertEquals("You are already enrolled in CSC216", e.getMessage());
        }
        
        Course course2 = new Course("CSC116", "Introduction to Java", "002", 3, 
                INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        try {
            schedule.addCourseToSchedule(course2);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Time conflict.", e.getMessage());
        }
        
    }

    /**
     * Tests the remove course funtion
     */
    @Test
    public void testRemoveCourseFromSchedule() {
        
        Course course1 = new Course("CSC116", "Introduction to Java", "002", 3, 
                INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        
        // Remove a Course that Does not exist
        assertFalse(schedule.removeCourseFromSchedule(course1));
        
        // Remove a course that does exist
        course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        assertFalse(schedule.removeCourseFromSchedule(course1));
        
        // Check Size
        assertEquals(0, schedule.getSchedule().size());
        
    }

    /**
     * Tests resetting the schedule
     */
    @Test
    public void testResetSchedule() {
        
        // Add courses
        Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        schedule.addCourseToSchedule(course1);
        Course course2 = new Course("CSC116", "Introduction to Java", "002", 3, 
                "drhollar", ENROLLMENT_CAP, "MW", START_TIME, END_TIME);
        schedule.addCourseToSchedule(course2);
        
        assertEquals(2, schedule.getSchedule().size());
        
        // Reset the course and check the size
        schedule.resetSchedule();
        assertEquals(0, schedule.getSchedule().size());
        
    }

    /**
     * Tests the get schedule function
     */
    @Test
    public void testGetScheduledcourse() {
        
     // Add courses
        Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        schedule.addCourseToSchedule(course1);
        String[][] arr = schedule.getScheduledCourses();
        assertTrue(course1.getShortDisplayArray()[0].equals(arr[0][0]));
        assertTrue(course1.getShortDisplayArray()[1].equals(arr[0][1]));
        assertTrue(course1.getShortDisplayArray()[2].equals(arr[0][2]));
        assertTrue(course1.getShortDisplayArray()[3].equals(arr[0][3]));
        
    }

    /**
     * Tests set title function for schedule
     */
    @Test
    public void testSetTitle() {
        
        try {
            schedule.setTitle(null);
        }
        catch(IllegalArgumentException e) {
            assertEquals("No title input.", e.getMessage());
        }
        
        try {
            schedule.setTitle("");
        }
        catch(IllegalArgumentException e) {
            assertEquals("No title input.", e.getMessage());
        }
        
        schedule.setTitle("Super cool title");
        assertEquals("Super cool title", schedule.getTitle());
     
    }
    
    /**
     * Tests getScheduleCredits method
     */
    @Test
    public void testGetScheduleCredits() {
        Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        Course course2 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                END_TIME + 100, END_TIME + 200);
        schedule.addCourseToSchedule(course1);
        schedule.addCourseToSchedule(course2);
        assertEquals(schedule.getScheduleCredits(), 8);
    }
    
    /**
     * Tests canAdd method
     */
    @Test
    public void testCanAdd() {
        Course course1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                START_TIME, END_TIME);
        Course course2 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 
                END_TIME + 100, END_TIME + 200);
        Course course3 = null;
        assertTrue(schedule.canAdd(course1));
        schedule.addCourseToSchedule(course1);
        assertFalse(schedule.canAdd(course1));
        assertTrue(schedule.canAdd(course2));
        assertFalse(schedule.canAdd(course3));
    }
}
