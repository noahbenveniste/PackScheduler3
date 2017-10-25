package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Activity object describes things that can be added to schedule is parent
 * class of Event and Course
 * 
 * @author Bwu98
 */
public abstract class Activity implements Conflict {

	/**
	 * Checks to see if there is a conflict between this Activity and the parameter
	 * Activity. First checks to see if the activities share any common days of the
	 * week, if so then it checks the time of day for each activity and sees if it
	 * overlaps
	 * 
	 * @param possibleConflictingActivity
	 *            The Activity object to be compared with this Activity
	 * @throws ConflictException
	 *             if there is a conflict between the two activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		int thisTime = (this.endTime - this.startTime);
		int posTime = (possibleConflictingActivity.endTime - possibleConflictingActivity.startTime);
		int earliestTime = Math.min(this.startTime, possibleConflictingActivity.startTime);
		int latestTime = Math.max(this.endTime, possibleConflictingActivity.endTime);
		for (int i = 0; i < this.meetingDays.length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.meetingDays.length(); j++) {
				char a = this.meetingDays.charAt(i);
				char b = possibleConflictingActivity.meetingDays.charAt(j);
				if ((a == b && (thisTime + posTime) >= (latestTime - earliestTime)) && a != 'A') {
					throw new ConflictException();
				}
			}
		}

	}

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Max time allowed in military time */
	private static final int UPPER_TIME = 2400;
	/** Upperbound for minutes in military time */
	private static final int UPPER_HOUR = 60;

	/**
	 * Constructs the Activity object with title, meeting days, start time, and end
	 * time
	 * 
	 * @param title
	 *            The title of the activity
	 * @param meetingDays
	 *            What days of the week the activity occurs on
	 * @param startTime
	 *            When does the activity start
	 * @param endTime
	 *            When does the activity end
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title
	 *            the title to set
	 * @throws IllegalArgumentException
	 *             if title is null or empty string
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Returns the Course's Meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Course's Meeting days
	 * 
	 * @param meetingDays
	 *            The days the activity meets on
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course's start time and end time
	 * 
	 * @param startTime
	 *            the start time of classes
	 * @param endTime
	 *            the end time of classes
	 * @throws IllegalArgumentException
	 *             the start time is not between 0000 and 2359 an invalid military
	 *             time the end time is not between 0000 and 2359 or an invalid
	 *             military time the end time is less than the start time (i.e., no
	 *             overnight classes) a start time and/or end time is listed when
	 *             meeting days is ‘A’
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (this.meetingDays.equals("A") && startTime != 0 && endTime != 0) {
			throw new IllegalArgumentException();
		}
		if ((startTime % 100) < 0 || (startTime % 100) >= UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
		if ((endTime % 100) < 0 || (endTime % 100) >= UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
		if ((startTime / 100) < 0 || startTime >= UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if ((endTime / 100) < 0 || endTime >= UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException();
		}

		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Returns the required string of course
	 * 
	 * @return String representation of Course
	 */
	public String getMeetingString() {
		if (meetingDays.equals("A")) {
			return "Arranged";
		}

		String s = "";

		String startHour = "";
		if ((startTime / 100) == 0) {
			startHour = "12";
		}
		if ((startTime / 100) > 12) {
			startHour = Integer.toString((startTime / 100) - 12);
		}
		if ((startTime / 100) >= 1 && (startTime / 100) <= 12) {
			startHour = Integer.toString(startTime / 100);
		}

		String startMinute = "";
		if ((startTime % 100) <= 9) {
			startMinute = "0" + Integer.toString(startTime % 100);
		}
		if ((startTime % 100) >= 10) {
			startMinute = Integer.toString(startTime % 100);
		}

		String startClass = "AM";
		if (startTime >= 1200) {
			startClass = "PM";
		}

		String endHour = "";
		if ((endTime / 100) == 0) {
			endHour = "12";
		}
		if ((endTime / 100) > 12) {
			endHour = Integer.toString((endTime / 100) - 12);
		}
		if ((endTime / 100) >= 1 && (endTime / 100) <= 12) {
			endHour = Integer.toString(endTime / 100);
		}

		String endMinute = "";
		if ((endTime % 100) <= 9) {
			endMinute = "0" + Integer.toString(endTime % 100);
		}
		if ((endTime % 100) >= 10) {
			endMinute = Integer.toString(endTime % 100);
		}

		String endClass = "AM";
		if (endTime >= 1200) {
			endClass = "PM";
		}

		s = meetingDays + " " + startHour + ":" + startMinute + startClass + "-" + endHour + ":" + endMinute + endClass;

		return s;
	}

	/**
	 * Generates a hashCode for Course using all fields
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a shortened version of activity object describing itself
	 * 
	 * @return String array of length four
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Returns a long version of activity object describing itself
	 * 
	 * @return String array of length seven
	 */

	public abstract String[] getLongDisplayArray();

	/**
	 * Checks the schedule to see if there is a duplicate object with same name
	 * inside the schedule
	 * 
	 * @param activity
	 *            The activity object to be checked for duplicates
	 * @return a boolean to see if there is already a duplicate of the object in the
	 *         schedule
	 */

	public abstract boolean isDuplicate(Activity activity);

}