package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * Creates a registration manager that reads in a registrar.properties file and uses a singleton
 * pattern as well as an inner class
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 *
 */
public class RegistrationManager {

    
    private static RegistrationManager instance;
    private CourseCatalog courseCatalog;
    private FacultyDirectory facultyDirectory;
    private StudentDirectory studentDirectory;
    private User registrar;
    private User currentUser;
    /** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String PROP_FILE = "registrar.properties";

    private RegistrationManager() {
        createRegistrar();
        courseCatalog = new CourseCatalog();
        studentDirectory = new StudentDirectory();
        facultyDirectory = new FacultyDirectory();
    }

    private void createRegistrar() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(PROP_FILE)) {
            prop.load(input);

            String hashPW = hashPW(prop.getProperty("pw"));

            registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
                    prop.getProperty("email"), hashPW);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create registrar.");
        }
    }

    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest1.update(pw.getBytes());
            return new String(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }

    /**
     * Checks to see if the instance is null. If instance is null then creates a new RegistrationManager object.
     * @return the instance, if null then it is new RegistrationManager, else it is just current RegistrationManager.
     */
    public static RegistrationManager getInstance() {
        if (instance == null) {
            instance = new RegistrationManager();
        }
        return instance;
    }

    /**
     * Gets the courseCatalog
     * @return the courseCatalog
     */
    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }

    /**
     * Gets the studentDirectory
     * @return the studentDirectory
     */
    public StudentDirectory getStudentDirectory() {
        return studentDirectory;
    }
    
    /**
     * Gets the faculty directory
     * @return faculty directory
     */
    public FacultyDirectory getFacultyDirectory() {
        return facultyDirectory;
    }

    /**
     * Determines if the login user id and the password matchup and returns true if they do, false if they don't
     * @param id The user id for the login
     * @param password The user entered password for the login
     * @return true if the id and password match the id and password on the registrars
     */
    public boolean login(String id, String password) {
        if (currentUser == null) {
            if (registrar.getId().equals(id)) {
                MessageDigest digest;
                try {
                    digest = MessageDigest.getInstance(HASH_ALGORITHM);
                    digest.update(password.getBytes());
                    String localHashPW = new String(digest.digest());
                    if (registrar.getPassword().equals(localHashPW)) {
                        currentUser = registrar;
                        return true;
                    }
                    return false;
                } catch (NoSuchAlgorithmException e) {
                    throw new IllegalArgumentException();
                }
            }
            
            Student s = studentDirectory.getStudentById(id);
            if (s == null) {
                throw new IllegalArgumentException("User doesn't exist.");
            }
            try {
                MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
                digest.update(password.getBytes());
                String localHashPW = new String(digest.digest());          
                if (s != null && s.getPassword().equals(localHashPW)) {
                    currentUser = s;
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException();
            }
            
            Faculty f = facultyDirectory.getFacultyById(id);
            if(f == null) {
                throw new IllegalArgumentException("User doesn't exist.");
            }
            try {
                MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
                digest.update(password.getBytes());
                String localHashPW = new String(digest.digest());          
                if (f != null && s.getPassword().equals(localHashPW)) {
                    currentUser = s;
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException();
            }            
        }

        return false;
    }

    /**
     * Sets the current user back to null
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Gets the current user
     * 
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user back to null and clears all data from courseCatalog and studentDirectory
     */
    public void clearData() {
        currentUser = null;
        courseCatalog.newCourseCatalog();
        studentDirectory.newStudentDirectory();
    }

    /**
     * Creates a registrar class that extends the User
     * 
     * @author Brian Wu, Dustin Hollar, Hubert Ngo
     *
     */
    private static class Registrar extends User {
        /**
         * Create a registrar user with the user id and password in the
         * registrar.properties file.
         */
        public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
            super(firstName, lastName, id, email, hashPW);
        }
    }
    
    /**
     * Returns true if the logged in student can enroll in the given course.
     * @param c Course to enroll in
     * @return true if enrolled
     */
    public boolean enrollStudentInCourse(Course c) {
        if (currentUser == null || !(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            Schedule schedule = s.getSchedule();
            CourseRoll roll = c.getCourseRoll();
            
            if (s.canAdd(c) && roll.canEnroll(s)) {
                schedule.addCourseToSchedule(c);
                roll.enroll(s);
                return true;
            }
            
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    /**
     * Returns true if the logged in student can drop the given course.
     * @param c Course to drop
     * @return true if dropped
     */
    public boolean dropStudentFromCourse(Course c) {
        if (currentUser == null || !(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            c.getCourseRoll().drop(s);
            return s.getSchedule().removeCourseFromSchedule(c);
        } catch (IllegalArgumentException e) {
            return false; 
        }
    }

    /**
     * Resets the logged in student's schedule by dropping them
     * from every course and then resetting the schedule.
     */
    public void resetSchedule() {
        if (currentUser == null || !(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            Schedule schedule = s.getSchedule();
            String [][] scheduleArray = schedule.getScheduledCourses();
            for (int i = 0; i < scheduleArray.length; i++) {
                Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
                c.getCourseRoll().drop(s);
            }
            schedule.resetSchedule();
        } catch (IllegalArgumentException e) {
            //do nothing 
        }
    }
}
