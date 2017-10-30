package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The student constructor class gets and sets the student's information for future use.
 * @author Hubert Ngo, Dustin Hollar, Brian Wu
 *
 */
public class Student extends User implements Comparable<Student> {

	/** Student's maxCredits */
	private int maxCredits;
	
	/** Student's Schedule */
	private Schedule schedule;
	
	/** constant maxCredits */
	public static final int MAX_CREDITS = 18;
	
	/**
	 * Constructs a Student object with values for all fields.
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param password Student's password
	 * @param maxCredits Student's max credits
	 */

     public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
        super(firstName, lastName, id, email, password);
  		setMaxCredits(maxCredits);
  		schedule = new Schedule(); 
     }
     
	/**
	 * Creates a student with the given first name, last name, id, email, password.
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id student's id
	 * @param email student's email
	 * @param password student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	
	/**
	 * Returns the max credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Returns the max credits
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if max credits is below 3 or above 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated value String of all student fields.
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail()
				+ "," + super.getPassword() + "," + maxCredits + "";
	}

	/**
	 * Compares this student object with the specified student object for order. Students are ordered by
	 * last name, then first name, then their unity id.
	 * @param s student object
	 * @return a negative integer, zero, or a positive integer as this object is less than, 
	 * equal to, or greater than the specified object.
	 */
    @Override
    public int compareTo(Student s) {

        // If the two Object are equal
        if (this == s) return 0;
        
        // Their levels of sort: last name, first name, and then unity id
        if(super.getLastName().equals(s.getLastName())) {
            // if last name are equal, check first names
            if (super.getFirstName().equals(s.getFirstName())) {
                // if first names are equal, compare unity id's
                return super.getId().compareTo(s.getId());
            }
            else {
                // if last names are equal, but not first name, compare first name
                return super.getFirstName().compareTo(s.getFirstName());
            }
        }
        else {
            // If last names are not equal, compare last names
            return super.getLastName().compareTo(s.getLastName());
        }
    }

    /**
     * Generates a hashCode for Student
     * 
     * @return the hashCode as an int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + maxCredits;
        return result;
    }

    /**
     * Compares 2 Students for equality
     * 
     * @param obj object to compare
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
        Student other = (Student) obj;
        if (maxCredits != other.maxCredits)
            return false;
        return true;
    }
    
    /**
     * Returns the students schedule
     * 
     * @return students schedule
     */
    public Schedule getSchedule() {
        return this.schedule;
    }
    
    /**
     * Determines if a course can be added the student's schedule
     * 
     * @param course the course to attempt to add
     * @return if the course can be added to the schedule
     */
    public boolean canAdd(Course course) {
        if (course.getCredits() + schedule.getScheduleCredits() > maxCredits) {
            return false;
        }
        return schedule.canAdd(course);
    }
}