package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

public class FacultyRecordIOTest {

    /** Valid student records */
    private final String validTestFile = "test-files/faculty_records.txt";
    /** Invalid student records */
    private final String invalidTestFile = "test-files/invalid_faculty_records.txt";

    /** Expected results for valid faculty */
    private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
    private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
    private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
    private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
    private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
    private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
    private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
    private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

    /** A LinkedList object reference to be used throughout testing */
    private LinkedList<Faculty> validFacultyList;
    /** A ListIterator object reference to be used throughout testing */
    private ListIterator<String> i;

    private String hashPW;
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Helper method to replace password to hashed value
     */
    @Before
    public void setUp() {
        validFacultyList.add(0, validFaculty0);
        validFacultyList.add(1, validFaculty1);
        validFacultyList.add(2, validFaculty2);
        validFacultyList.add(3, validFaculty3);
        validFacultyList.add(4, validFaculty4);
        validFacultyList.add(5, validFaculty5);
        validFacultyList.add(6, validFaculty6);
        validFacultyList.add(7, validFaculty7);

        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            hashPW = new String(digest.digest());

            for (Faculty f : validFacultyList) {

                validFacultyList[f] = validFacultyList[f].set(",pw,", "," + hashPW + ",");

                // for (int i = 0; i < validFacultyList.size(); i++) {
                // validFacultyList[i] = validFacultyList[i].replace(",pw,", "," + hashPW +
                // ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    /**
     * Tests readFacultyRecords().
     */
    @Test
    public void testReadFacultyRecords() {
        try {
            LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
            assertEquals(8, faculty.size());
            // Test if list is sorted
            assertEquals("Witt", faculty.get(0).getLastName());
            assertEquals("Walls", faculty.get(7).getLastName());

        } catch (FileNotFoundException e) {
            fail("Unexpected error reading " + validTestFile);
        }
    }

    /**
     * Tests readInvalidFacultyRecords().
     */
    @Test
    public void testReadInvalidFacultyRecords() {
        LinkedList<Faculty> faculty;
        try {
            faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);

            assertEquals(0, faculty.size());
        } catch (FileNotFoundException | IllegalArgumentException e) {
            fail("Unexpected FileNotFoundException, IllegalArgumentException");
        }

    }

    @Test
    public void testWriteFacultyRecords() {
        LinkedList<Faculty> faculty = new LinkedList<Faculty>();
        faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 2));

        try {
            FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
        } catch (IOException e) {
            fail("Cannot write to student records file");
        }

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

            while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
                String exp = expScanner.nextLine();
                String act = actScanner.nextLine();
                assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
            }
            if (expScanner.hasNextLine()) {
                fail("The expected results expect another line " + expScanner.nextLine());
            }
            if (actScanner.hasNextLine()) {
                fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }

}
