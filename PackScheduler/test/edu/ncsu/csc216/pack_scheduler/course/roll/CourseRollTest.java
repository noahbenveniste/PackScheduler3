package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Unit Test Class for testing CourseRoll
 *
 * @author Brian Wu, Dustin Hollar, Hubert Ngo, Noah Benveniste, Ben Gale
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
        
        Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        Student s3 = new Student("f1", "l1", "id1", "eAnother@ncsu.edu", "hashedp");
        
        // Add a student
        roll.enroll(s1);
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
        
        //This code is used to make the waitlist queue visible for testing
        Field waitlist = null;
        try {
            waitlist = CourseRoll.class.getDeclaredField("waitlist");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        waitlist.setAccessible(true);
        LinkedQueue<Student> wl = null;
        try {
            wl = (LinkedQueue<Student>) waitlist.get(roll);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        //Used to make the course roll list visible for testing
        Field courseRoll = null;
        try {
            courseRoll = CourseRoll.class.getDeclaredField("roll");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
            courseRoll.setAccessible(true);
        LinkedAbstractList<Student> cr = null;
        try {
            cr = (LinkedAbstractList<Student>) courseRoll.get(roll);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        assertEquals(0, roll.getNumberOnWaitlist());
        
        Student s4 = new Student("f4", "l4", "id4", "id4@ncsu.edu", "pw");
        Student s5 = new Student("f5", "l5", "id5", "id5@ncsu.edu", "pw");
        Student s6 = new Student("f6", "l6", "id6", "id6@ncsu.edu", "pw");
        Student s7 = new Student("f7", "l7", "id7", "id7@ncsu.edu", "pw");
        Student s8 = new Student("f8", "l8", "id8", "id8@ncsu.edu", "pw");
        Student s9 = new Student("f9", "l9", "id9", "id9@ncsu.edu", "pw");
        Student s10 = new Student("f10", "l10", "id10", "id10@ncsu.edu", "pw");
        Student s11 = new Student("f11", "l11", "id11", "id11@ncsu.edu", "pw");
        Student s12 = new Student("f12", "l12", "id12", "id12@ncsu.edu", "pw");
        Student s13 = new Student("f13", "l13", "id13", "id13@ncsu.edu", "pw");
        
        roll.enroll(s4);
        roll.enroll(s5);
        roll.enroll(s6);
        roll.enroll(s7);
        roll.enroll(s8);
        roll.enroll(s9);
        roll.enroll(s10);
        roll.enroll(s11);
        
        assertEquals(0, roll.getNumberOnWaitlist());
        assertEquals(10, cr.size());
        assertEquals(s11, cr.get(9));
        
        //Ensure that students added now will be added to the waitlist
        roll.enroll(s12);
        roll.enroll(s13);
        
        assertEquals(2, wl.size());
        
        //Try to add a duplicate student to the waitlist
        try {
            roll.enroll(s12);
        } catch (IllegalArgumentException e) {
            assertEquals(2, wl.size());
            assertEquals("Student could not be enrolled from class.", e.getMessage());
        }
        
        //Test that dropping a student on the waitlist enrolls them in the course and adds the course to their schedule
        roll.drop(s8);
        assertEquals(s12, cr.get(9)); //Check that the first student in the waitlist queue gets added to the roll
        assertEquals(1, wl.size());
        
        String[] exp = c.getShortDisplayArray();
        String[][] actual = s12.getSchedule().getScheduledCourses();
        
        //Check that the course is in the newly enrolled student's schedule
        assertEquals(exp[0], actual[0][0]);
        assertEquals(exp[1], actual[0][1]);
        assertEquals(exp[2], actual[0][2]);
        assertEquals(exp[3], actual[0][3]);
        assertEquals(exp[4], actual[0][4]);
        
        //Try dropping another student
        roll.drop(s1);
        assertEquals(s13, cr.get(9)); //Check that the first student in the waitlist queue gets added to the roll
        assertEquals(0, wl.size());
        
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
        
        //Try dropping a null student
        try {
            roll.drop(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Student is null in Enroll Parameter.", e.getMessage());
        }
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
