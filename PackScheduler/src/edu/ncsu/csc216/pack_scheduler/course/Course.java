package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Creates a course object containing a courses title, section, number of
 * credits, instructorId, days the course meets, and start/end time of the
 * course.
 * 
 * @author Brian Wu, Noah Benveniste, Ben Gale
 */
public class Course extends Activity implements Comparable<Course> {

    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /** Course's roll */
    private CourseRoll roll;

    /**
     * Creates a Course object with values for all fields
     * 
     * @param name
     *            name of the course
     * @param title
     *            title of the course
     * @param section
     *            section of the course
     * @param credits
     *            credits for the course
     * @param instructorId
     *            id for the instructor
     * @param enrollmentCap
     *            the roll’s enrollment capacity
     * @param meetingDays
     *            days that course meets
     * @param startTime
     *            start time for the course
     * @param endTime
     *            end time for the course
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
            String meetingDays, int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        setName(name);
        this.roll = new CourseRoll(this, enrollmentCap);
    }

    /**
     * Creates a Course with the given name, title, section, credits, instructorId,
     * and meetingDays for courses that are arranged.
     * 
     * @param name
     *            name of the course
     * @param title
     *            title of the course
     * @param section
     *            section of the course
     * @param credits
     *            number of credits for the course
     * @param instructorId
     *            instructor ID
     * @param enrollmentCap
     *            the roll’s enrollment capacity
     * @param meetingDays
     *            days the course meets
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
            String meetingDays) {
        this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
    }

    /**
     * Return's the courses name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the course's name
     * 
     * @param name
     *            the name to set
     * @throws IllegalArgumentException
     *             if the name is a null value or contain less than 4 characters or
     *             more than 6 characters
     */
    private void setName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        if (name.length() < 4 || name.length() > 6) {
            throw new IllegalArgumentException("Invalid course name");
        }

        CourseNameValidator validator = new CourseNameValidator();
        try {
            if (!validator.isValid(name)) {
                throw new IllegalArgumentException("Invalid course name.");
            }
        } catch (InvalidTransitionException e) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        this.name = name;
    }

    /**
     * Get the section of the course
     * 
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Set the section of the course
     * 
     * @param section
     *            the section to set
     * @throws IllegalArgumentException
     *             if the section is null or does not contain 3 characters
     */
    public void setSection(String section) {

        if (section == null || section.length() != 3) {
            throw new IllegalArgumentException("Invalid section number");
        }

        this.section = section;
    }

    /**
     * Get the number of credits of the course
     * 
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Set the number of credits for the Course
     * 
     * @param credits
     *            the credits to set
     * @throws IllegalArgumentException
     *             if the number of credits is less than 1 or more than 5
     */
    public void setCredits(int credits) {

        if (credits < 1 || credits > 5) {
            throw new IllegalArgumentException();
        }

        this.credits = credits;
    }

    /**
     * Get the instructor ID
     * 
     * @return the instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Set the instructor ID
     * 
     * @param instructorId
     *            the instructorId to set
     * @throws IllegalArgumentException
     *             if the instructorId is null or empty
     */
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    /**
     * Gets the course roll of the course
     * 
     * @return the course roll
     */
    public CourseRoll getCourseRoll() {
        return this.roll;
    }

    /**
     * Returns a comma separated value String of all Course fields.
     * 
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if (getMeetingDays().equals("A")) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
                    + roll.getEnrollmentCap() + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
                + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
    }

    /**
     * Generates hashCode for the Course object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + credits;
        result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        return result;
    }

    /**
     * Checks to see if the parameter is equal to a Course object
     * 
     * @param obj
     *            object to compare to a Course Object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (credits != other.credits)
            return false;
        if (instructorId == null) {
            if (other.instructorId != null)
                return false;
        } else if (!instructorId.equals(other.instructorId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;
        return true;
    }

    /**
     * Displays the name, section, title, and meeting days string into a String
     * array.
     * 
     * @return a String array containing name, section, title, and meeting days
     *         string
     */
    @Override
    public String[] getShortDisplayArray() {
        String[] shortDisplayArray = { name, section, getTitle(), getMeetingString(),
                Integer.toString(roll.getOpenSeats()) };

        return shortDisplayArray;
    }

    /**
     * Displays the name, section, title, credits, instructorId, meeting days
     * string, empty string (for a field that Event will have that Course does not
     * 
     * @return a String array containing name, section, title, credits,
     *         instructorId, meeting days string, empty string
     */
    @Override
    public String[] getLongDisplayArray() {

        String[] shortDisplayArray = { name, section, getTitle(), "" + getCredits(), instructorId, getMeetingString(),
                "" };

        return shortDisplayArray;
    }

    /**
     * Course cannot have a meeting day for Saturday and Sunday, so further checks
     * must be implemented to prevent such an entry
     * 
     * @param meetingDays
     *            days the course meets
     * @throws IllegalArgumentException
     *             if meetingDays is null, an empty string, or equal to 'U' (Sunday)
     *             or 'S' (Saturday).
     */
    @Override
    public void setMeetingDays(String meetingDays) {

        if (meetingDays == null || meetingDays.equals("")) {
            throw new IllegalArgumentException("Invalid meeting days");
        }

        if (meetingDays.contains("U") || meetingDays.contains("S")) {
            throw new IllegalArgumentException("Invalid meeting days");
        }

        super.setMeetingDays(meetingDays);
    }

    @Override
    public boolean isDuplicate(Activity activity) {

        if (activity instanceof Course) {

            // Event event = (Event) activity;
            return ((Course) activity).getName().equals(this.getName());

            // check for same title
        }

        return false;
    }

    /**
     * Compare current Course with the parameter Object
     * 
     * @param c
     *            Course object to compare to current course
     * @return int value representing the comparison between two objects
     */
    @Override
    public int compareTo(Course c) {

        // If two objects are equal, return 0
        if (this == c)
            return 0;

        // Compare course name then section;
        if (this.getName().equals(c.getName())) {
            return this.getSection().compareTo(c.getSection());
        } else {
            return this.getName().compareTo(c.getName());
        }
    }
}
