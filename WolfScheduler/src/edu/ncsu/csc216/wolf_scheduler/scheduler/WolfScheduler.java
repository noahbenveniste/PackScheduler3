/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Reads in file and sets up schedules and course catalog.
 * 
 * @author Bwu98
 */
public class WolfScheduler {

	/**
	 * This is the program to schedule course
	 * 
	 * @param args
	 */

	/** A course catalog */
	private ArrayList<Course> courseCatalog;
	/** Schedule of courses */
	private ArrayList<Activity> schedule;
	/** Title of schedule */
	private String title;

	/**
	 * Constructs the WolfScheduler object
	 * 
	 * @param fileName
	 *            string of file to be read
	 */
	public WolfScheduler(String fileName) {

		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cannot find file.");
		}
		schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
	}

	/**
	 * retrieves a specific course from the course catalog, if course is not in
	 * catalog then return null
	 * 
	 * @param name
	 *            name of the course to be retrieved
	 * @param section
	 *            section of the course to be retrieved
	 * @return the course from the catalog with name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < courseCatalog.size(); i++) {
			if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				return courseCatalog.get(i);
			}
		}
		return null;
	}

	/**
	 * retrieves the schedule of courses in a 2D string array with only showing
	 * course name, section, and title
	 * 
	 * @return the 2D string array of schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduledCourses = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			scheduledCourses[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduledCourses;
	}

	/**
	 * retrieves the schedule of courses in a 2D string array with only showing
	 * course name, section, title, credits, instructorId, and meetingString
	 * 
	 * @return the 2D string array of schedule
	 */
	public String[][] getFullScheduledActivities() {
		String[][] scheduledCourses = new String[schedule.size()][6];
		for (int i = 0; i < schedule.size(); i++) {
			scheduledCourses[i] = schedule.get(i).getLongDisplayArray();
		}
		return scheduledCourses;
	}

	/**
	 * returns the title of the schedule
	 * 
	 * @return title of the schedule
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * writes the course schedule down on a file and exports file
	 * 
	 * @param fileName
	 *            name of file to export to
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}

	/**
	 * retrieves course catalog with details about name, section, and title
	 * 
	 * @return 2D string array of course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalog = new String[courseCatalog.size()][4];
		for (int i = 0; i < courseCatalog.size(); i++) {
			catalog[i][0] = courseCatalog.get(i).getName();
		}
		for (int i = 0; i < courseCatalog.size(); i++) {
			catalog[i][1] = courseCatalog.get(i).getSection();
		}
		for (int i = 0; i < courseCatalog.size(); i++) {
			catalog[i][2] = courseCatalog.get(i).getTitle();
		}
		for (int i = 0; i < courseCatalog.size(); i++) {
			catalog[i][3] = courseCatalog.get(i).getMeetingString();
		}
		return catalog;
	}

	/**
	 * Checks to see if you can add a course to your schedule. Does not allow
	 * duplicates Also does not allow schedule to be added if it is not on the
	 * catalog. Adds course to schedule.
	 * 
	 * @param name
	 *            name of course to be added
	 * @param section
	 *            section of course to be added
	 * @return true if course can be added to schedule, false if course is already
	 *         on schedule
	 */
	public boolean addCourse(String name, String section) {
		boolean add = false;
		Course course = getCourseFromCatalog(name, section);

		for (int i = 0; i < courseCatalog.size(); i++) {
			if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				// check if in course catalog
				for (int j = 0; j < schedule.size(); j++) {
					if (schedule.get(j).isDuplicate(course)) {
						// check if already registered
						throw new IllegalArgumentException("You are already enrolled in " + name);
					}
					try {
						schedule.get(j).checkConflict(course);
					} catch (ConflictException e) {
						throw new IllegalArgumentException("The course cannot be added due to a conflict.");
					}

				}
				schedule.add(courseCatalog.get(i));
				return true;
			}
		}
		return add;
	}

	/**
	 * Checks to see if you can add an event to your schedule. Does not allow
	 * duplicates Also does not allow schedule to be added if it is not on the
	 * catalog. Adds event to schedule.
	 * 
	 * @param eventTitle
	 *            Title of event to be added
	 * @param eventMeetingDays
	 *            Days of the week that the event will occur
	 * @param eventStartTime
	 *            Time of day that event will begin
	 * @param eventEndTime
	 *            Time of day that event will end
	 * @param eventWeeklyRepeat
	 *            How many weeks with this event repeat for
	 * @param eventDetails
	 *            Details describing what the event is
	 */
	public void addEvent(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			int eventWeeklyRepeat, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventWeeklyRepeat,
				eventDetails);
		for (int j = 0; j < schedule.size(); j++) {
			if (schedule.get(j).isDuplicate(event)) {
				// check if event is duplicate
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				schedule.get(j).checkConflict(event);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(event);
	}

	/**
	 * Checks to see if you can remove a course from you schedule and then if
	 * allowed removes the course from the schedule.
	 * 
	 * @param idx
	 *            index of the activity to be removed from schedule
	 * 
	 * @return true if you can remove the course from schedule, false if you cannot
	 *         remove the course from the schedule
	 */
	public boolean removeActivity(int idx) {
		if (idx >= schedule.size()) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(schedule.get(idx))) {
				schedule.remove(schedule.get(idx));
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule and clears all courses
	 */
	public void resetSchedule() {
		schedule.clear();
	}

	/**
	 * Sets the title of the schedule, if title is null throws
	 * IllegalArgumentException
	 * 
	 * @param title
	 *            Title of the schedule
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

}
