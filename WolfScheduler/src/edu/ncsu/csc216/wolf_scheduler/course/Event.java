package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event object describes events that can be added to the schedule
 * 
 * @author Bwu98
 */
public class Event extends Activity {

	/** Number of weeks repeated */
	private int weeklyRepeat;
	/** Details of the event */
	private String eventDetails;

	/**
	 * Creates an event object that describes when and what event it is
	 * 
	 * @param weeklyRepeat
	 *            How many times does the event happen weekly
	 * @param eventDetails
	 *            Details of the event
	 * @param title
	 *            Title of the event
	 * @param meetingDays
	 *            The days the event happens
	 * @param startTime
	 *            When does the event start
	 * @param endTime
	 *            When does the event end
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Returns the amount of times the event repeats weekly
	 * 
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the weekly repeat for the event will throw IllegalArgumentException if
	 * weekly repeat is less than 1 or greater than 4
	 * 
	 * @param weeklyRepeat
	 *            the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Returns the details of the event
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the details of the event, will throw IllegalArgumentException if details
	 * is null
	 * 
	 * @param eventDetails
	 *            the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns a short string array description of event
	 * 
	 * @return a String array of length four
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] display = new String[4];
		display[0] = "";
		display[1] = "";
		display[2] = getTitle();
		display[3] = getMeetingString();
		return display;
	}

	/**
	 * Returns a long string array description of event
	 * 
	 * @return a String array of length seven
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] display = new String[7];
		display[0] = "";
		display[1] = "";
		display[2] = getTitle();
		display[3] = "";
		display[4] = "";
		display[5] = getMeetingString();
		display[6] = getEventDetails();
		return display;
	}

	/**
	 * Returns get meeting string with extra weekly repeat string
	 * 
	 * @return the get meeting string with extra weekly repeat string
	 */
	@Override
	public String getMeetingString() {
		String s = super.getMeetingString();
		s = s + " (every " + weeklyRepeat + " weeks)";
		return s;
	}

	/**
	 * Returns a comma separated value String of events
	 * 
	 * @return string represention of event
	 */
	@Override
	public String toString() {
		String s = getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + ","
				+ getWeeklyRepeat() + "," + getEventDetails();
		return s;
	}

	/**
	 * Sets the Course's Meeting days
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 * @throws IllegalArgumentException
	 *             if meetingDays is null or consist of any characters other than
	 *             ‘M’, ‘T’, ‘W’, ‘H’, ‘F’, ‘S’, ‘U’
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'S' && c != 'U') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Compares a given object to see if it is a event object then compares all
	 * fields
	 * 
	 * @param activity
	 *            the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event event = (Event) activity;
			return event.getTitle().equals(getTitle());
		}
		return false;
	}
}
