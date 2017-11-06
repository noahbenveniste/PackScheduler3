package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

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
}
