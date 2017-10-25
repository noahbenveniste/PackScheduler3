package edu.ncsu.csc216.pack_scheduler.course.roll;

import java.util.NoSuchElementException;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Object that creates a list of students enrolled in a class.
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */

public class CourseRoll {
    
    /** LinkedList for Students */
    private LinkedAbstractList<Student> roll;
    /** The roll's enrollment capacity */
    private int enrollmentCap;
    /** Maximum Number of Students that can enroll */
    private static final int MIN_ENROLLMENT = 10;
    /** Minimum Number of Students that can enroll */
    private static final int MAX_ENROLLMENT = 250;
    
    /** Constructs CourseRoll
     * @param enrollmentCap enrollment cap for the class
     */
    public CourseRoll(int enrollmentCap) {
        // Create the list with a capacity of enrollmentCap
        roll = new LinkedAbstractList<Student>(enrollmentCap);
        
        // Set enrollment cap
        setEnrollmentCap(enrollmentCap);
    }
    
    /**
     * Get the enrollment cap
     * @return the enrollment cap
     */
    public int getEnrollmentCap() {
        return enrollmentCap;
    }
    
    /**
     * Set the enrollmentCap for the course
     * 
     * @param enrollmentCap the roll’s enrollment capacity
     * @throws IllegalArgumentException if enrollmentCap is larger/smaller than the 
     *          allowed enrollment
     */
    public void setEnrollmentCap(int enrollmentCap) {

        if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT || roll.size() > enrollmentCap) {
            throw new IllegalArgumentException("Enrollment cap is too large/small.");
        }
        roll.setCapacity(enrollmentcap);
        this.enrollmentCap = enrollmentCap; 
    }
    
    /**
     * Get the OpenSeats in the class based on the difference between enrollmentCap and the size 
     * of roll
     * @return Open Seats in the class
     */
    public int getOpenSeats() {
        
        // returns the difference between the enrollmentCap and the size of the roll.
        return enrollmentCap - roll.size();
    }
    
    /**
     * Enroll a student in the class.
     * @param s student to enroll in the class
     * @throws IllegalArgumentException if the student is null value, roll throws an exception, and
     * if the student cannot be enrolled
     */
    public void enroll(Student s) {

        if (s == null) {
            throw new IllegalArgumentException("Student is null in Enroll Parameter.");
        }
        
        try {
            
            if (canEnroll(s)) {
                roll.add(s);
            }
            else {
                throw new IllegalArgumentException("Student could not be enrolled from class.");
            }
            
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Student could not be enrolled from class.");
        }
    }
    
    /**
     * Drop a student from the class.
     * @param s student to drop from the class
     * @throws IllegalArgumentException if the student does not exist, or if an index is called not in list
     */
    public void drop(Student s)  {
        
        // Check if parameter is null
        if (s == null) {
            throw new IllegalArgumentException("Student is null in Enroll Parameter.");
        }
        
        // Try to remove student from list
        try {
            for (int i = 0; i < roll.size(); i++) {
                if (s.equals(roll.get(i))) {
                    roll.remove(i);
                } 
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Student could not be removed from class.");
        }
    }
    
    /**
     * Check to find out if a student can be added to the class.
     * @param s student to add to the class
     * @return true if student can be added, false otherwise
     */
    public boolean canEnroll(Student s) {
        
        // Check for room in the class
        if (getOpenSeats() == 0) {
            return false;
        }
        
        // Student is already enrolled
        for (int i = 0; i < roll.size(); i++) {
            
            if (s.compareTo(roll.get(i)) == 0) {
                return false;
            } 
        }
  
        return true;
    }
}
