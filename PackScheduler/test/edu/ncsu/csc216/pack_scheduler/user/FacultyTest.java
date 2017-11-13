package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Tests the Faculty class
 * 
 * @author Brian Wu, Ben Gale, Noah Benveniste
 */
public class FacultyTest {

    /** First name of the faculty */
    private static final String FIRST_NAME = "First";
    /** Last name of the faculty */
    private static final String LAST_NAME = "Last";
    /** ID of the faculty */
    private static final String ID = "flast";
    /** The Email address of the faculty */
    private static final String EMAIL = "flast@ncsu.edu";
    /** Password of the faculty */
    private static final String PASSWORD = "password";
    /** Max courses of the faculty */
    private static final int MAX_COURSES = 3;

    /**
     * Tests that the equals method works for all Faculty fields.
     */
    @Test
    public void testEqualsObject() {

        User f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f3 = new Faculty(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, MAX_COURSES);
        User f4 = new Faculty(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_COURSES);
        User f5 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_COURSES);
        User f6 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "hashedpassword", MAX_COURSES);
        User f7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);

        // Test for equality in both directions
        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f1));

        // Test for each of the fields
        assertFalse(f1.equals(f3));
        assertFalse(f1.equals(f4));
        assertFalse(f1.equals(f5));
        assertFalse(f1.equals(f6));
        assertFalse(f1.equals(f7));
    }

    /**
     * Test for hashCode()
     */
    @Test
    public void testHashCode() {
        User f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        User f3 = new Faculty(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, MAX_COURSES);
        User f4 = new Faculty(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_COURSES);
        User f5 = new Faculty(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_COURSES);
        User f6 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "hashedpassword", MAX_COURSES);
        User f7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);

        // Test for the same hash code for the same values
        assertEquals(f1.hashCode(), f2.hashCode());

        // Test for each of the fields
        assertNotEquals(f1.hashCode(), f3.hashCode());
        assertNotEquals(f1.hashCode(), f4.hashCode());
        assertNotEquals(f1.hashCode(), f5.hashCode());
        assertNotEquals(f1.hashCode(), f6.hashCode());
        assertNotEquals(f1.hashCode(), f7.hashCode());
    }

    /**
     * Tests the setMaxCourses method
     */
    @Test
    public void testSetMaxCoures() {
        Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        // Set courses below minimum
        try {
            f.setMaxCourses(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MAX_COURSES, f.getMaxCourses());
        }

        // Set courses above max
        try {
            f.setMaxCourses(4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(MAX_COURSES, f.getMaxCourses());
        }
    }

    /**
     * Tests that to String returns the correct value.
     */
    @Test
    public void testToString() {
        User f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        String s1 = "First,Last,flast,flast@ncsu.edu,password,3";
        assertEquals(s1, f.toString());
    }

    /**
     * Tests the isOverloaded method
     */
    @Test
    public void testIsOverloaded() {
        Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);

        Course c1 = new Course("CSC116", "comp", "001", 4, null, 10, "M");
        Course c2 = new Course("CSC117", "compu", "002", 3, null, 10, "T");
        Course c3 = new Course("CSC118", "comput", "003", 3, null, 10, "W");
        Course c4 = new Course("CSC119", "compute", "004", 3, null, 10, "F");

        FacultySchedule fSchedule = f.getSchedule();

        // Not overloaded for less than 4 courses
        fSchedule.addCourseToSchedule(c1);
        assertFalse(f.isOverloaded());
        fSchedule.addCourseToSchedule(c2);
        fSchedule.addCourseToSchedule(c3);
        assertFalse(f.isOverloaded());

        // adds 4th course and faculty is overloaded
        fSchedule.addCourseToSchedule(c4);
        assertEquals(4, fSchedule.getNumScheduledCourses());
        assertTrue(f.isOverloaded());
    }
}
