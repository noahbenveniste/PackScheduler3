package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests FacultyDirectory
 * 
 * @author Brian Wu, Ben Gale, Noah Benveniste
 *
 */
public class FacultyDirectoryTest {

    /** Valid faculty records */
    private final String validTestFile = "test-files/faculty_records.txt";
    /** Test first name */
    private static final String FIRST_NAME = "Fac";
    /** Test last name */
    private static final String LAST_NAME = "Ulty";
    /** Test id */
    private static final String ID = "fulty";
    /** Test email */
    private static final String EMAIL = "fulty@ncsu.edu";
    /** Test password */
    private static final String PASSWORD = "pw";
    /** Test courses */
    private static final int COURSES = 2;

    /**
     * Resets faculty_records.txt for use in other tests.
     * 
     * @throws Exception
     *             if something fails during setup.
     */
    @Before
    public void setUp() throws Exception {
        // Reset student_records.txt so that it's fine for other needed tests
        Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
        Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
        try {
            Files.deleteIfExists(destinationPath);
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            fail("Unable to reset files");
        }
    }

    /** Tests facultyDirectory */
    @Test
    public void testFacultyDirectory() {
        // Test that the FacultyDirectory is initialized to an empty list
        FacultyDirectory fd = new FacultyDirectory();
        assertFalse(fd.removeFaculty("awitt"));
        assertEquals(0, fd.getFacultyDirectory().length);
    }

    /** Tests newFacultyDirectory method */
    @Test
    public void testNewFacultyDirectory() {
        // Test that if there are faculty in the directory, they
        // are removed after calling newFacultyDirectory().
        FacultyDirectory fd = new FacultyDirectory();

        fd.loadFacultyFromFile(validTestFile);
        assertEquals(8, fd.getFacultyDirectory().length);

        fd.newFacultyDirectory();
        assertEquals(0, fd.getFacultyDirectory().length);
    }

    /** Tests loadFacultyFromFile method */
    @Test
    public void testLoadFacultyFromFile() {
        FacultyDirectory fd = new FacultyDirectory();

        // Test valid file
        fd.loadFacultyFromFile(validTestFile);
        assertEquals(8, fd.getFacultyDirectory().length);
    }

    /** Tests addFaculty method */
    @Test
    public void testAddFaculty() {
        FacultyDirectory fd = new FacultyDirectory();

        // Test valid Student
        fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, COURSES);
        String[][] facultyDirectory = fd.getFacultyDirectory();
        assertEquals(1, facultyDirectory.length);
        assertEquals(FIRST_NAME, facultyDirectory[0][0]);
        assertEquals(LAST_NAME, facultyDirectory[0][1]);
        assertEquals(ID, facultyDirectory[0][2]);

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid password", e.getMessage());
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid password", e.getMessage());
            assertEquals(1, facultyDirectory.length);
            assertEquals(FIRST_NAME, facultyDirectory[0][0]);
            assertEquals(LAST_NAME, facultyDirectory[0][1]);
            assertEquals(ID, facultyDirectory[0][2]);
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid password", e.getMessage());
            assertEquals(1, facultyDirectory.length);
            assertEquals(FIRST_NAME, facultyDirectory[0][0]);
            assertEquals(LAST_NAME, facultyDirectory[0][1]);
            assertEquals(ID, facultyDirectory[0][2]);
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid password", e.getMessage());
            assertEquals(1, facultyDirectory.length);
            assertEquals(FIRST_NAME, facultyDirectory[0][0]);
            assertEquals(LAST_NAME, facultyDirectory[0][1]);
            assertEquals(ID, facultyDirectory[0][2]);
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", "", COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid password", e.getMessage());
            assertEquals(1, facultyDirectory.length);
            assertEquals(FIRST_NAME, facultyDirectory[0][0]);
            assertEquals(LAST_NAME, facultyDirectory[0][1]);
            assertEquals(ID, facultyDirectory[0][2]);
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "hello", "world", COURSES);
        } catch (IllegalArgumentException e) {
            assertEquals("Passwords do not match", e.getMessage());
            assertEquals(1, facultyDirectory.length);
            assertEquals(FIRST_NAME, facultyDirectory[0][0]);
            assertEquals(LAST_NAME, facultyDirectory[0][1]);
            assertEquals(ID, facultyDirectory[0][2]);
        }

        try {
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, COURSES);
            fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, COURSES);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /** Tests removeFaculty method */
    @Test
    public void testRemoveFaculty() {
        FacultyDirectory fd = new FacultyDirectory();

        // Add faculty and remove
        fd.loadFacultyFromFile(validTestFile);
        assertEquals(8, fd.getFacultyDirectory().length);
        assertTrue(fd.removeFaculty("awitt"));
        String[][] facultyDirectory = fd.getFacultyDirectory();
        assertEquals(7, facultyDirectory.length);
        assertEquals("Norman", facultyDirectory[5][0]);
        assertEquals("Brady", facultyDirectory[5][1]);
        assertEquals("nbrady", facultyDirectory[5][2]);
    }

    // @Test
    // public void testGetFacultyDirectory() {
    // fail("Not yet implemented");
    // }

    /** Tests saveFacultyDirectory method */
    @Test
    public void testSaveFacultyDirectory() {
        FacultyDirectory fd = new FacultyDirectory();

        // Add a faculty
        fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
        fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
        fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
        assertEquals(3, fd.getFacultyDirectory().length);
        fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
        checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
    }

    /**
     * Helper method to compare two files for the same contents
     * 
     * @param expFile
     *            expected output
     * @param actFile
     *            actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try {
            Scanner expScanner = new Scanner(new FileInputStream(expFile));
            Scanner actScanner = new Scanner(new FileInputStream(actFile));

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }

    /** Tests getFacultyById method */
    @Test
    public void testGetFacultyById() {
        FacultyDirectory fd = new FacultyDirectory();
        // Test valid Faculty 1
        fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, COURSES);
        // Test valid Faculty 2
        fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);

        // Very an ID is found
        assertEquals("awitt", fd.getFacultyById("awitt").getId());
        assertEquals("fulty", fd.getFacultyById("fulty").getId());

        // Verify if an ID is not found
        assertNull(fd.getFacultyById("dsgd"));

        // Test Invalid Input: null
        try {
            fd.getFacultyById(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Id is null or an empty String.", e.getMessage());
        }

        // Test Invalid Input: empty string
        try {
            fd.getFacultyById("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Id is null or an empty String.", e.getMessage());
        }
    }

}
