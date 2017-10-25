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

    /** 
     * Test the setEnrollmentCap method for CourseRoll 
     */
    @Test
    public void testSetEnrollmentCap() {
        CourseRoll c = new CourseRoll(10);
        
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
        
        //Tests valid enrollmentCap
        
        try {
            c.setEnrollmentCap(10);
            assertEquals(10, c.getEnrollmentCap());
        }catch (IllegalArgumentException e) {
                fail();
            
        }
        
        c.enroll(s1);
        c.enroll(s2);
        c.enroll(s3);
        c.enroll(s4);
        c.enroll(s5);
        c.enroll(s6);
        c.enroll(s7);
        c.enroll(s8);
        c.enroll(s9);
        c.enroll(s10);
        
        try {
            c.enroll(s11);
            fail();
        }catch(IllegalArgumentException e) {
                assertEquals("Enrollment cap is too large/small.", e.getMessage());
            }
        }
    
        
    }

