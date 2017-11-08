/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all faculty members at NC State.
 * All faculty members have a unique id.
 * @author Brian Wu, Ben Gale, Noah Benveniste
 */
public class FacultyDirectory {
    
    /** Linked list of faculty in the directory */
    private LinkedList<Faculty> facultyDirectory;
    /** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    
    /**
     * Creates an empty faculty directory.
     */
    public FacultyDirectory() {
        newFacultyDirectory();
    }

    /**
     * Creates an empty faculty directory.  All faculty members in the previous
     * list are lost unless saved by the user.
     */
    public void newFacultyDirectory() {
        facultyDirectory = new LinkedList<Faculty>();
    }
    
    /**
     * Constructs the faculty directory by reading in student information
     * from the given file.  Throws an IllegalArgumentException if the 
     * file cannot be found.
     * @param fileName file containing list of students
     */
    public void loadFacultyFromFile(String fileName) {
        try {
            facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file " + fileName);
        }
    }
    
    /**
     * Adds a Faculty to the directory.  Returns true if the faculty is added and false if
     * the faculty is unable to be added because their id matches another faculty's id.
     * 
     * This method also hashes the faculty's password for internal storage.
     * 
     * @param firstName faculty's first name
     * @param lastName faculty's last name
     * @param id faculty's id
     * @param email faculty's email
     * @param password faculty's password
     * @param repeatPassword faculty's repeated password
     * @param maxCourses faculty's max courses.
     * @return true if added
     */
    public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
        String hashPW = "";
        String repeatHashPW = "";
        if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
            throw new IllegalArgumentException("Invalid password");
        }
        try {
            MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest1.update(password.getBytes());
            hashPW = new String(digest1.digest());
            
            MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest2.update(repeatPassword.getBytes());
            repeatHashPW = new String(digest2.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
        
        if (!hashPW.equals(repeatHashPW)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        //If an IllegalArgumentException is thrown, it's passed up from Student
        //to the GUI
        Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
        
//      for (int i = 0; i < facultyDirectory.size(); i++) {
//          User f = facultyDirectory.get(i);
//          if (f.getId().equals(faculty.getId())) {
//              return false;
//         }
//      }
        
        for (User f : facultyDirectory) {
            if (f.getId().equals(faculty.getId())) {
                return false;
            }
        }
        return facultyDirectory.add(faculty);
    }
    
    /**
     * Removes the faculty with the given id from the list of faculty members with the given id.
     * Returns true if the faculty is removed and false if the faculty is not in the list.
     * @param facultyId faculty member's id
     * @return true if removed
     */
    public boolean removeFaculty(String facultyId) {
        
        for (int i = 0; i < facultyDirectory.size(); i++) {
            User f = facultyDirectory.get(i);
            if (f.getId().equals(facultyId)) {
                facultyDirectory.remove(i);
                return true;
            }
        }
        

//        for (User f : facultyDirectory) {
//            if (f.getId().equals(facultyId)) {
//                facultyDirectory.remove(f);
//                return true;
//            }
//        }
        return false;
    }
    
    /**
     * Returns all faculties in the directory with a column for first name, last name, and id.
     * @return String array containing faculties first name, last name, and id.
     */
    public String[][] getFacultyDirectory() {
        String [][] directory = new String[facultyDirectory.size()][3];
        for (int i = 0; i < facultyDirectory.size(); i++) {
            User f = facultyDirectory.get(i);
            directory[i][0] = f.getFirstName();
            directory[i][1] = f.getLastName();
            directory[i][2] = f.getId();
        }
        return directory;
    }
    
    /**
     * Saves all faculty members in the directory to a file.
     * @param fileName name of file to save facultyDirectory to.
     */
    public void saveFacultyDirectory(String fileName) {
        try {
            FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to write to file " + fileName);
        }
    }
    
    /**
     * Return a Faculty based off of ID
     * 
     * @param id of faculty to find
     * @return Faculty that has the same ID as the parameter
     */
    public Faculty getFacultyById(String id) {
        
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Id is null or an empty String.");
        }
        
        for (int i = 0; i < facultyDirectory.size(); i++) {
            
            // If the ID for the Faculty is equal to the parameter, return that student
            if(id.equals(facultyDirectory.get(i).getId())) {
                return facultyDirectory.get(i);
            } 
        }
        
        // If no faculty has that has an ID equal to the parameter, return null
        return null;
    }

}
