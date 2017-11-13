/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The object representing a faculty user
 * 
 * @author Brian Wu, Ben Gale, Noah Benveniste
 */
public class Faculty extends User {

    /** The number of courses the faculty is going to teach this semester */
    private int maxCourses;

    /** The minimum amount of courses faculty is allowed to teach in a semester */
    public static final int MIN_COURSES = 1;

    /** The maximum amount of courses faculty is allowed to teach in a semester */
    public static final int MAX_COURSES = 3;

    /** The faculty schedule */
    private FacultySchedule fSchedule;

    /**
     * Creates a student with the given first name, last name, id, email, password,
     * and number of courses to teach this semester
     * 
     * @param firstName
     *            Faculty user's first name
     * @param lastName
     *            Faculty user's last name
     * @param id
     *            Faculty user's ID
     * @param email
     *            Faculty user's email
     * @param password
     *            Faculty user's password
     * @param maxCourses
     *            Number of classes Faculty user will teach this semester
     */
    public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
        super(firstName, lastName, id, email, password);
        setMaxCourses(maxCourses);
        fSchedule = new FacultySchedule(id);
    }

    /**
     * Sets the maxCourse value for the faculty user
     * 
     * @param c
     *            the value to be set as the maxCourse value
     */
    public void setMaxCourses(int c) {
        if (c > MAX_COURSES || c < MIN_COURSES) {
            throw new IllegalArgumentException("Invalid max courses");
        }
        this.maxCourses = c;
    }

    /**
     * Gets the maxCourse value for the faculty user
     * 
     * @return the maxCourse value for the faculty user
     */
    public int getMaxCourses() {
        return this.maxCourses;
    }

    /**
     * Gets the faculty schedule
     * 
     * @return the faculty schedule
     */
    public FacultySchedule getSchedule() {
        return fSchedule;
    }

    /**
     * If the number of courses the faculty is scheduled for is greater than the
     * MAX_COURSES returns true.
     * 
     * @return true if scheduled courses larger than MAX_COURSES, otherwise false
     */
    public boolean isOverloaded() {
        if (fSchedule.getNumScheduledCourses() > maxCourses) {
            return true;
        }
        return false;
    }

    /**
     * Generates a hashCode for Faculty
     * 
     * @return the hashCode as an int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + maxCourses;
        return result;
    }

    /**
     * Compares 2 faculty users for equality
     * 
     * @param obj
     *            object to compare
     * @return boolean determining if the 2 objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Faculty other = (Faculty) obj;
        if (maxCourses != other.maxCourses)
            return false;
        return true;
    }

    /**
     * Returns a comma separated value String of all Faculty fields.
     * 
     * @return String representation of Faculty
     */
    @Override
    public String toString() {
        return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + ","
                + super.getPassword() + "," + maxCourses + "";
    }
}
