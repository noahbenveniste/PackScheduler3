package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog Class
 * @author Dustin Hollar, Boram Han, Jacob Myers
 *
 */
public class CourseCatalogTest {

    /** Valid course records */
    private final String validTestFile = "test-files/course_records.txt";
    /** Invalid course records */
    private final String invalidTestFile = "test-files/invalid_course_records.txt";
    
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
    /** Course Enrollment Cap */
    private static final int ENROLL_CAP = 10;
    /** Course meeting days */
    private static final String MEETING_DAYS = "TH";
    /** Course start time */
    private static final int START_TIME = 1330;
    /** Course end time */
    private static final int END_TIME = 1445;
    
    /**
     * Resets course_records.txt for use in other tests.
     * @throws IOException if file cannot be reset.
     */
    @Before
    public void setUp() throws Exception {
        //Reset course_records.txt so that it's fine for other needed tests
        Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
        Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
        try {
            Files.deleteIfExists(destinationPath);
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            fail("Unable to reset files");
        }
    }
    
    
    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#CourseCatalog()}.
     */
    @Test
    public void testCourseCatalog() {
        
        CourseCatalog catalog = new CourseCatalog();
        assertEquals(0, catalog.getCourseCatalog().length);
        
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#loadCoursesFromFile(java.lang.String)}.
     */
    @Test
    public void testLoadCoursesFromFile() {
        
        //Test with invalid file.  Should have an empty catalog and schedule. 
        CourseCatalog cs1 = new CourseCatalog();
        assertEquals(0, cs1.getCourseCatalog().length);
        
        cs1.loadCoursesFromFile(invalidTestFile);
        assertEquals(0, cs1.getCourseCatalog().length);
        
        //Test with valid file containing 8 courses.  Will test other methods in other tests.
        CourseCatalog cs2 = new CourseCatalog();
        cs2.loadCoursesFromFile(validTestFile);
        assertEquals(8, cs2.getCourseCatalog().length); 
    }

    /**
     * Test method for addCourseToCatalog().
     */
    @Test
    public void testAddCourseToCatalog() {
        CourseCatalog cs1 = new CourseCatalog();
        assertTrue(cs1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL_CAP, MEETING_DAYS, START_TIME, END_TIME));
        assertEquals(1, cs1.getCourseCatalog().length);
        //add duplicate course
        assertFalse(cs1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL_CAP, MEETING_DAYS, START_TIME, END_TIME));
    
    }

    /**
     * Test method removeCourseFromCatalog().
     */
    @Test
    public void testRemoveCourseFromCatalog() {
    	CourseCatalog cs1 = new CourseCatalog();
        cs1.loadCoursesFromFile(validTestFile);

        assertFalse(cs1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(cs1.removeCourseFromCatalog("CSC116", "001"));
        
    }

    /**
     * Test method for getCourseFromCatalog(String name, String section).
     */
    @Test
    public void testGetCourseFromCatalog() {
        CourseCatalog cs1 = new CourseCatalog();
        cs1.loadCoursesFromFile(validTestFile);

		//Attempt to get a course that doesn't exist
		assertNull(cs1.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cs1.getCourseFromCatalog("CSC216", "001"));    
		}
    
    /**
     * Test method for getCourseCatalog().
     */
    @Test
    public void testGetCourseCatalog() {
        CourseCatalog cs1 = new CourseCatalog();
        cs1.loadCoursesFromFile(validTestFile);
        
        String [][] catalog = cs1.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
    }

    /**
     * Tests saveCourseCatalog().
     */
    @Test
    public void testSaveCourseCatalog() {
        CourseCatalog cs1 = new CourseCatalog();
    
		//Add courses and test that exports correctly
		cs1.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL_CAP, MEETING_DAYS, START_TIME, END_TIME);

		assertEquals(1, cs1.getCourseCatalog().length);
		cs1.saveCourseCatalog("test-files/actual_schedule_export.txt");	
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");

    }


	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
				
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
			} catch (IOException e) {
			fail("Error reading files.");
		}
		

    }

}
