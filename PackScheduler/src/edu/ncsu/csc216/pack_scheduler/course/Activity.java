package edu.ncsu.csc216.pack_scheduler.course;
/**
 * Parent class of Course and Event. Creates an activity based off of Title, days that the activity
 * occurs on, start and end time of the activity.  
 * 
 * @author Dustin Hollar
 */
public abstract class Activity implements Conflict {

    /** Course's title. */
    private String title;
    /** Course's meeting days */
    private String meetingDays;
    /** Course's starting time */
    private int startTime;
    /** Course's ending time */
    private int endTime;
    /** String Array that return the name, section, title, and meeting days string fields 
     * @return string array for name, section, title, and meeting days string fields
     */
    public abstract String[] getShortDisplayArray();
    /** String Array that return the name, section, title, credits, instructorId, meeting days 
     * string, empty string
     * @return string array for name, section, title, credits, instructorId, meeting days 
     * string, empty string
     */
    public abstract String[] getLongDisplayArray();

    /**
     * Constructor for Activity
     * 
     * @param title of the activity
     * @param meetingDays days the activity occurs on
     * @param startTime start time of the activity
     * @param endTime end time of the activity 
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        setTitle(title);
        setMeetingDays(meetingDays);
        setActivityTime(startTime, endTime);
    }
    
    /**
     * Abstract method to check is the activity is a duplicate
     * @param activity object to check if it is a duplicate
     * @return true if duplicate, false if not
     */
    public abstract boolean isDuplicate(Activity activity);

    /**
     * Get the title of the course
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the course
     * @param title the title to set
     * @throws IllegalArgumentException if the title is null or empty
     */
    public void setTitle(String title) {
        
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("Invalid course title");
        }
        
        this.title = title;
    }

    /**
     * Get the days that the course meets
     * @return the meetingDays
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * Set the days that the course meets
     * @param meetingDays the meetingDays to set
     * @throws IllegalArgumentException if the meeting days is null, empty string, or does not
     * contain the right character. If the meetingDays is "A" and has more than one character,
     * the exception is thrown
     */
    public void setMeetingDays(String meetingDays) {
        
        if (meetingDays == null  || meetingDays.equals("")) {
            throw new IllegalArgumentException("Invalid meeting days");
        }
        char letter;
        for (int i = 0; i < meetingDays.length(); i++) {
            letter = meetingDays.charAt(i);
            if (letter != 'M' && letter != 'T' && letter != 'W' && letter != 'H' && letter != 'F' && 
                    letter != 'A' && letter != 'U' && letter != 'S') {
                throw new IllegalArgumentException("Invalid meeting days");
            }
            if (letter == 'A' && meetingDays.length() > 1) {
                throw new IllegalArgumentException("Invalid meeting days");
            }
        }
        
        this.meetingDays = meetingDays;
    }

    /**
     * Get the start time for the course
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Get end time for the course
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /** 
     * Set the start and end time for the course
     * @param startTime start time for the course
     * @param endTime end time for the course
     * @throws IllegalArgumentException if the time does not fit standard time contraints
     */
    public void setActivityTime(int startTime, int endTime) {
        if (startTime < 0000 || startTime > 2359 || endTime < 0000 || endTime > 2359
                || endTime < startTime) {
            throw new IllegalArgumentException("Invalid course times");
        }
        int max = 59;
        int min = 0;
        if (startTime % 100 < min || startTime % 100 > max || endTime % 100 < min || 
                endTime % 100 > max) {
            throw new IllegalArgumentException("Invalid course times");
        }
        if (getMeetingDays().equals("A")) {
            startTime = 0;
            endTime = 0;
        }
        
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Takes the MeetingDays and puts the time in standard 12 hour time
     * 
     * @return a string containing meeting days, format hh:mmAM/PM-hh:mmAM/PM. If meeting days
     * is equal to "A", return "Arranged"
     */
    public String getMeetingString() {
        // TH 11:45AM-2:25PM
        
        // If the days are not assigned, Return "Arranged", Otherwise continue
        if (getMeetingDays().equals("A")) {
            return "Arranged";
        }
        
        // Puts the hour in Standard time (1-12) for the start time
        int start = getStartTime() / 100;
        String startTimeDay;
        if (start == 12) {
            startTimeDay = "PM";
        }
        else if (start > 12) {
            startTimeDay = "PM";
            start -= 12;
        }
        else {
            startTimeDay = "AM";
        }
        // Get the minutes, if % is less than 10, another 0 is needed to be added to get the format 00
        String startMin = "";
        int intStartTime = getStartTime() % 100;
        if (intStartTime < 10) {
            startMin += "0" + intStartTime;
        }
        else {
            startMin += intStartTime;
        }
        
        // Puts the hour in Standard time (1-12) for the end time
        int end = getEndTime() / 100;
        String endTimeDay;
        if (end == 12) {
            endTimeDay = "PM";
        }
        else if (end > 12) {
            endTimeDay = "PM";
            end -= 12;
        }
        else {
            endTimeDay = "AM";
        }
        
        // Get the minutes, if % is less than 0, another 0 is needed to be added to get the format 00
        String endMin = "";
        int intEndTime = getEndTime() % 100;
        if (intEndTime < 10) {
            endMin += "0" + intEndTime;
        }
        else {
            endMin += intEndTime;
        }
        
        return getMeetingDays() + " " + start + ":" + startMin + startTimeDay + "-"
                + end + ":" + endMin + endTimeDay;
    }

    /**
     * Generates hashCode for an Activity object
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
     * Checks to see if the parameter is equal to an Activity object
     * 
     * @param obj object to be compared to an Activity object
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
     * Checks for conflict in the schedule. 
     * 
     * @param possibleConflictingActivity Activity object to check for conflict
     * @throws ConflictException thrown when two event are on the same day and the time they occur
     * in overlap
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
        
        // Define the meeting days
        String daysMeet = possibleConflictingActivity.getMeetingDays();
        String daysMeetCurr = this.getMeetingDays();
        
        // Time for case 1
        int case1Start = this.startTime;
        int case1End = this.endTime;
        
        // Time for case 2
        int case2Start = possibleConflictingActivity.startTime;
        int case2End = possibleConflictingActivity.endTime;
        
        for (int i = 0; i < daysMeet.length(); i++) {
            
            char day = daysMeet.charAt(i);
            for (int k = 0; k < daysMeetCurr.length(); k++) {
                
                char dayCurr = daysMeetCurr.charAt(k);
                if ((day == dayCurr && day != 'A') && ((case1Start <= case2Start && case1End >= case2End) ||
                            (case2Start  <= case1Start && case2End <= case1End && 
                            case2End >= case1Start) || (case1Start <= case2Start &&
                            case1End <= case2End && case1End >= case2Start) || (case1Start >= case2Start
                            && case1Start <= case2End) || case1Start == case2End || case2Start == case1End)) 
                        throw new ConflictException();   
            } 
        }  
    } 
}
