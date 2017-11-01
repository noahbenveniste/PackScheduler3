package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.fail;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /** Expected results for valid students */
    // MANUALLY ADD EACH FACULTY :(
    //private final LinkedList<Faculty> validFaculty = { validFaculty0, validFaculty1, validFaculty2, validFaculty3,
    //        validFaculty4, validFaculty5, validFaculty6, validFaculty7 };

    private String hashPW;
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Helper method to replace password to hashed value
     */
    @Before
    public void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            hashPW = new String(digest.digest());

            //for (Faculty f : validFaculty)
            //    validFaculty[f] = validFaculty[f].replace(",pw,", "," + hashPW + ",");

            //for (int i = 0; i < validFaculty.size(); i++) {
            //    validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
            //}
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    @Test
    public void testReadFacultyRecords() {
        fail("Not yet implemented");
    }

    @Test
    public void testWriteFacultyRecords() {
        fail("Not yet implemented");
    }

}
