/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is the Course object, reads in name, title, section, credit hours,
 * instructorId, meetingDays, starting time, and ending time
 * 
 * @author Bwu98
 */
public class Course extends Activity {

	/**
	 * This represents the Courses used in WolfScheduler.
	 * 
	 * @param args
	 */

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Upperbound for length of course name */
	private static final int MAX_NAME_LENGTH = 6;
	/** Lowerbound for length of course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** The only allowed length for a section name */
	private static final int SECTION_LENGTH = 3;
	/** The most amount of credits allowed for a schedule */
	private static final int MAX_CREDITS = 5;
	/** The least amount of credits allowed for a schedule */
	private static final int MIN_CREDITS = 1;

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * meetingDays, startTime, and endTime for the courses that are arranged.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as a series of chars
	 * @param startTime
	 *            starting time of Course
	 * @param endTime
	 *            ending time of Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or length is less than 4 or greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns the Course's Section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's Section
	 * 
	 * @param section
	 *            the section to set
	 * @throws IllegalArgumentException
	 *             if Section is not exactly three digits
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits
	 *            the credits to set
	 * @throws IllegalArgumentException
	 *             if credits less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's Instructor ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor ID
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 * @throws IllegalArgumentException
	 *             if the instructorId is null or empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
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
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields
	 * 
	 * @return hashCode for Course
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
	 * Compares a given object to this object for equality and on all fields.
	 * 
	 * @param obj
	 *            the Object to compare
	 * @return true if the objects are the same on all fields
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
	 * Returns an array of length 4 containing the Course name, section, title, and
	 * meeting days string.
	 * 
	 * @return short string array of event description
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] display = new String[4];
		display[0] = getName();
		display[1] = getSection();
		display[2] = getTitle();
		display[3] = getMeetingString();
		return display;
	}

	/**
	 * Sets the meeting days of the course object, does not allow null, empty string
	 * or characters other than M, T, W, H, F, A
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 * @throws IllegalArgumentException
	 *             if meetingDays is null or consist of any characters other than
	 *             ‘M’, ‘T’, ‘W’, ‘H’, ‘F’, or ‘A’
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'A') {
				throw new IllegalArgumentException();
			}
			if (c == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns an array of length 7 containing the Course name, section, title,
	 * credits, instructorId, meeting days string, empty string (for a field that
	 * Event will have that Course does not)
	 * 
	 * @return long string array of event description
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] display = new String[7];
		display[0] = getName();
		display[1] = getSection();
		display[2] = getTitle();
		display[3] = Integer.toString(getCredits());
		display[4] = getInstructorId();
		display[5] = getMeetingString();
		display[6] = "";
		return display;
	}

	/**
	 * Compares a given object to see if it is a course object then compares all
	 * fields
	 * 
	 * @param activity
	 *            the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			return course.getName().equals(name);
		}
		return false;
	}
}
