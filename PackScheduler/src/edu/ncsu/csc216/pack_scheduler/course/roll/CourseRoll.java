package edu.ncsu.csc216.pack_scheduler.course.roll;

import java.util.NoSuchElementException;

import javax.swing.text.LayoutQueue;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

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
    /** Waitlist for the course */
    private LinkedQueue<Student> waitlist;
    /** The course that this course roll is associated with */
    Course course;
    /** Maximum Number of Students that can enroll */
    private static final int MIN_ENROLLMENT = 10;
    /** Minimum Number of Students that can enroll */
    private static final int MAX_ENROLLMENT = 250;
    
    /** Constructs CourseRoll
     * @param enrollmentCap enrollment cap for the class
     */
    public CourseRoll(Course c, int enrollmentCap) {
        
        if (c == null) {
            throw new IllegalArgumentException("Course parameter can't be null.");
        } else {
            course = c;
        }
        
        // Create the list with a capacity of enrollmentCap
        roll = new LinkedAbstractList<Student>(enrollmentCap);
        
        waitlist = new LinkedQueue<Student>(10);
        
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
        roll.setCapacity(enrollmentCap);
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
                if (getOpenSeats() == 0) {
                    waitlist.enqueue(s);
                } else {
                    roll.add(s);
                }
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
                    if (getNumberOnWaitlist() > 0) {
                        Student newStudent = waitlist.dequeue();
                        if (newStudent.canAdd(course) && canEnroll(newStudent)) {
                            roll.add(newStudent); // adds the first student from the waitlist to course roll
                            newStudent.getSchedule().addCourseToSchedule(course); // add the course to the newly enrolled student's schedule
                        } else {
                            throw new IllegalArgumentException("First student on waitlist is either already enrolled, they have the course in "
                                    + "their schedule, or there was a conflict with their schedule");
                        }
                    }
                    return; // breaks from method;
                }
            }
            for (int i = 0; i < getNumberOnWaitlist(); i++) {
                Student other = waitlist.dequeue(); // removes student from waitlist and compares with parameter student
                if (!s.equals(other)) { 
                    waitlist.enqueue(other); // adds dequeued student back to the waitlist if not equal to parameter student
                }
            }
            return; // breaks from method
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
            if (waitlist.size() == 10) {
                return false;
            }
        } 
        
        
        
        // Student is already enrolled
        for (int i = 0; i < roll.size(); i++) {
            
            if (s.compareTo(roll.get(i)) == 0) {
                return false;
            } 
        }
        
        // Student is already in waitlist
        boolean notOnWaitlist = true; 
        int size = waitlist.size();
       
        // iterates through linked list to check if each element is equal to student
        for (int i = 0; i < size; i++) { 
            Student other = waitlist.dequeue();
            if (s.equals(other)) {
                notOnWaitlist = false;
            }
            waitlist.enqueue(other);
            
        }
        
        return notOnWaitlist;
    }
    
    /**
     * returns the number of students on the waitlist
     * @return number of students on waitlist
     */
    public int getNumberOnWaitlist() {
        return waitlist.size();
    }
}
