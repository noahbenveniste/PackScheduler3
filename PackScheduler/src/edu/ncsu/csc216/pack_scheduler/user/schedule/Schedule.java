package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Create a schedule for a list of courses.
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */
public class Schedule {
    
    /** Generic Array List for a schedule of Courses */
    private ArrayList<Course> schedule;
    /** Title of the Schedule */
    private String title;
    
    /** Construct the schedule */
    public Schedule() {
        
        // The constructor of Schedule should create an empty ArrayList of Courses.
        schedule = new ArrayList<Course>();
        // The title should be initialized to "My Schedule"
        title = "My Schedule";
    }
    
    /**
     * Get the schedule
     * 
     * @return ArrayList for schedule
     */
    public ArrayList<Course> getSchedule() {
        return schedule;
    }
    
    /**
     * Add a specified course to the schedule
     * 
     * @param course to add to the schedule
     * @return true if the course was added correctly, false otherwise
     */
    public boolean addCourseToSchedule(Course course) {

        if (course == null) {
            throw new NullPointerException("Course cannot be null");
        }
        
        for (int i = 0; i < schedule.size(); i++) {
            
            // Check if course is a duplicate
            if (schedule.get(i).isDuplicate(course)) {
                throw new IllegalArgumentException("You are already enrolled in " + course.getName());
            }
            
            // Check if course has a time conflict
            try {
                schedule.get(i).checkConflict(course);
            }
            catch (ConflictException e) {
                throw new IllegalArgumentException("Time conflict.");
            }
        }

        // Attempt to add course to list
        return schedule.add(course);
    }
    
    /**
     * Remove a course from the schedule
     * 
     * @param course to remove from schedule
     * @return true if the course was removed correctly, false otherwise
     */
    public boolean removeCourseFromSchedule(Course course) {
        if (course != null) {
            for (int i = 0; i < schedule.size(); i++) {
                
                Course c = schedule.get(i);
                
                // Check if course exists in the array
                if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
                    return schedule.remove(c);
                }
            } 
        }
        
        return false;
    }
    
    /** Reset schedule, clearing the ArrayList */
    public void resetSchedule() {
        schedule.clear();
    }
    
    /**
     * Get the scheduled courses in schedule. Each row is a course with a column pertaining to
     * name, section, title, and the meeting string.
     * 
     * @return 2D string array giving the information of the courses in schedule
     */
    public String[][] getScheduledCourses() {
        
        int numCol = 4;
        
        String[][] scheduledCourses = new String[schedule.size()][numCol];
        
        for (int i = 0; i < schedule.size(); i++) {
            
            // columns are name, section, title, and the meeting string
            scheduledCourses[i] = schedule.get(i).getShortDisplayArray();
        }
        
        return scheduledCourses;
    }
    
    /**
     * Set the title of the schedule
     * 
     * @param title of the schedule to set
     */
    public void setTitle(String title) {
        
        // Check if title is valid
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("No title input.");
        }
        
        this.title = title;
    }
    
    /**
     * Get the title of the schedule
     * 
     * @return title of the schedule
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns the total number of credits in the schedule
     * 
     * @return the total number of credits in the schedule
     */
    public int getScheduleCredits() {
        int sum = 0;
        for (Course course : schedule) {
            sum += course.getCredits();
        }
        return sum;
    }
    
    /**
     * Checks if a course can be added to the schedule
     * 
     * @param course the course to attempt to add
     * @return if the course can be added to the schedule
     */
    public boolean canAdd(Course course) {
        if (course == null) {
            return false;
        }
        
        for (int i = 0; i < schedule.size(); i++) {
            
            // Check if course is a duplicate
            if (schedule.get(i).isDuplicate(course)) {
                return false;
            }
            
            // Check if course has a time conflict
            try {
                schedule.get(i).checkConflict(course);
            }
            catch (ConflictException e) {
                return false;
            }
        }
        return true;
    }
}
