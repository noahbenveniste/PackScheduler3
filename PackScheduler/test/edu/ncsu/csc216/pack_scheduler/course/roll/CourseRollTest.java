package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Unit Test Class for testing CourseRoll
 *
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */

public class CourseRollTest {
    /** Course to be used in all tests */
    private Course c;
    /** Course roll to be used in all tests */
    private CourseRoll roll;

    @Before
    public void setUp() {
        c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        roll = c.getCourseRoll();
    }
    
    /** Test the constructor */
    @Test
    public void testCourseRoll() {
        
        CourseRoll course;
        
        // Create invalid courseRolls
        try {
            course = new CourseRoll(c, 9);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Enrollment cap is too large/small.", e.getMessage());
        }
        
        try {
            course = new CourseRoll(c, 251);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Enrollment cap is too large/small.", e.getMessage());
        }
        
        // Create a valid courseRoll
        course = new CourseRoll(c, 20);
        assertEquals(20, course.getEnrollmentCap());
        assertEquals(20, course.getOpenSeats());
//        fail("Not yet implemented");
    }

    /** Test enroll a student */
    @Test
    public void testEnroll() {
        
        CourseRoll roll = new CourseRoll(c, 10);
        
        Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s3 = new Student("f", "l", "i", "eAnother@ncsu.edu", "hashedp");
        
        // Add a student
        roll.enroll(s);
        assertEquals(9, roll.getOpenSeats());
        
        // Add duplicate student
        try {
            roll.enroll(s2);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Student could not be enrolled from class.", e.getMessage());
        }
        
        // Add second student
        roll.enroll(s3);
        assertEquals(8, roll.getOpenSeats());
        
        // Add null Student
        try {
            roll.enroll(null);
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
        
        CourseRoll roll = new CourseRoll(c, 10);
        
        Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        
        // Add null student
        try {
            roll.enroll(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Student is null in Enroll Parameter.", e.getMessage());
        }
        
        // Add student
        roll.enroll(s);
        assertEquals(9, roll.getOpenSeats());
        

        // Remove existing student
        roll.drop(s);
        assertEquals(10, roll.getOpenSeats());
    }

    /** 
     * Test the setEnrollmentCap method for CourseRoll 
     */
    @Test
    public void testSetEnrollmentCap() {
        CourseRoll roll = new CourseRoll(c, 11);
        
        //Students that can enroll
        Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s2 = new Student("b", "ba", "bad", "bad@ncsu.edu", "hashedpassword");
        Student s3 = new Student("c", "ca", "cad", "cad@ncsu.edu", "hashedpassword");
        Student s4 = new Student("d", "da", "dad", "dad@ncsu.edu", "hashedpassword");
        Student s5 = new Student("e", "ea", "ead", "ead@ncsu.edu", "hashedpassword");
        Student s6 = new Student("f", "fa", "fad", "fad@ncsu.edu", "hashedpassword");
        Student s7 = new Student("g", "ga", "gad", "gad@ncsu.edu", "hashedpassword");
        Student s8 = new Student("h", "ha", "had", "had@ncsu.edu", "hashedpassword");
        Student s9 = new Student("i", "ia", "iad", "iad@ncsu.edu", "hashedpassword");
        Student s10 = new Student("j", "ja", "jad", "jad@ncsu.edu", "hashedpassword");
        Student s11 = new Student("k", "ka", "kad", "kad@ncsu.edu", "hashedpassword");
        
        //Invalid minimum enrollment
        try {
            roll.setEnrollmentCap(9);
            fail("Can't set enrolllment cap below minimum");
        }catch (IllegalArgumentException e) {
            assertEquals(11, roll.getEnrollmentCap());
        }
        
        //Invalid max enrollment
        try {
            roll.setEnrollmentCap(950);
            fail("Can't set enrolllment cap above maximum");
        }catch (IllegalArgumentException e) {
            assertEquals(11, roll.getEnrollmentCap());
        }
        
        //Test enrollment smaller than size
        roll.enroll(s1);
        roll.enroll(s2);
        roll.enroll(s3);
        roll.enroll(s4);
        roll.enroll(s5);
        roll.enroll(s6);
        roll.enroll(s7);
        roll.enroll(s8);
        roll.enroll(s9);
        roll.enroll(s10);
        roll.enroll(s11);
        try {
            roll.setEnrollmentCap(10);
            fail("Can't set enrolllment cap smaller than size");
        }catch (IllegalArgumentException e) {
            assertEquals(11, roll.getEnrollmentCap());
        }
        
        
        //Test valid enrollment size
        try {
            roll.setEnrollmentCap(100);
            assertEquals(100, roll.getEnrollmentCap());
        }catch (IllegalArgumentException e) {
            fail();   
        }
    }
}   
