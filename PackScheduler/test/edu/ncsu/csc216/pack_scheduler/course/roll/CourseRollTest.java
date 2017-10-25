package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Unit Test Class for testing CourseRoll
 *
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */

public class CourseRollTest {

    /** Test the constructor */
    @Test
    public void testCourseRoll() {
        
        CourseRoll course;
        
        // Create invalid courseRolls
        try {
            course = new CourseRoll(9);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Enrollment cap is too large/small.", e.getMessage());
        }
        
        try {
            course = new CourseRoll(251);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Enrollment cap is too large/small.", e.getMessage());
        }
        
        // Create a valid courseRoll
        course = new CourseRoll(20);
        assertEquals(20, course.getEnrollmentCap());
        assertEquals(20, course.getOpenSeats());
//        fail("Not yet implemented");
    }

    /** Test enroll a student */
    @Test
    public void testEnroll() {
        
        CourseRoll c = new CourseRoll(10);
        
        Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s3 = new Student("f", "l", "i", "eAnother@ncsu.edu", "hashedp");
        
        // Add a student
        c.enroll(s);
        assertEquals(9, c.getOpenSeats());
        
        // Add duplicate student
        try {
            c.enroll(s2);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Student could not be enrolled from class.", e.getMessage());
        }
        
        // Add second student
        c.enroll(s3);
        assertEquals(8, c.getOpenSeats());
        
        // Add null Student
        try {
            c.enroll(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Student is null in Enroll Parameter.", e.getMessage());
        }
        
//        fail("Not yet implemented");
    }

    /** Test drop a student */
    @Test
    public void testDrop() {
        
        CourseRoll c = new CourseRoll(10);
        
        Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        
        // Add null student
        try {
            c.enroll(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Student is null in Enroll Parameter.", e.getMessage());
        }
        
        // Add student
        c.enroll(s);
        assertEquals(9, c.getOpenSeats());
        

        // Remove existing student
        c.drop(s);
        assertEquals(10, c.getOpenSeats());
    }

}
