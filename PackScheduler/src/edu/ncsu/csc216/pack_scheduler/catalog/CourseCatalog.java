package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * 
 * Manages Course Catalog by creating new catalog, adding, removing, and viewing the courses. Course Catalog class also loads and writes file.
 * @author Dustin Hollar, Boram Han, Jacob Myers
 *
 */

public class CourseCatalog {
    
    private SortedList<Course> courseCatalog;
    
    /** Constructor for CourseCatalog */
    public CourseCatalog() {
        newCourseCatalog();
    }

    /** Constructs an empty SortedList for courseCatalog */
    public void newCourseCatalog() {
        courseCatalog = new SortedList<Course>();
    }
    
    /**
     * Loads a course catalog from a file
     * 
     * @param fileName file name to load in
     * @throws IllegalArgumentException if the file cannot be read
     */
    public void loadCoursesFromFile(String fileName) {
        
        try {
            courseCatalog = CourseRecordIO.readCourseRecords(fileName);
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file " + fileName);
        }
    }
    
    /**
     * Adds a course to the catalog
     * 
     * @param name of the course
     * @param title of the course
     * @param section of the course
     * @param credits number for the course
     * @param instructorId of the course
     * @param enrollmentCap the roll’s enrollment capacity
     * @param meetingDays for the course
     * @param startTime of the course
     * @param endTime of the course
     * @return true if the course could be added, false otherwise
     */
    public boolean addCourseToCatalog(String name, String title, String section, int credits, 
            String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
        
        Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
        
        for (int i = 0; i < courseCatalog.size(); i++) {
            Course c = courseCatalog.get(i);
            if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
                return false;
            } 
        }
        
        return courseCatalog.add(course);
    }
    
    /**
     * Remove a course from the catalog
     * 
     * @param name of the course to be removed
     * @param section of the course to be removed
     * @return true if the course was removed, false otherwise
     */
    public boolean removeCourseFromCatalog(String name, String section) {
        
        for (int i = 0; i < courseCatalog.size(); i++) {
            Course c = courseCatalog.get(i);
            if (c.getName().equals(name) && c.getSection().equals(section)) {
                courseCatalog.remove(i);
                return true;
            } 
        }
        
        return false;
    }
    
    /**
     * Retrieve a course from the catalog based off of name and section
     * 
     * @param name of the course
     * @param section of the course
     * @return the Course object that was retrieved
     */
    public Course getCourseFromCatalog(String name, String section) {
        
        for (int i = 0; i < courseCatalog.size(); i++) {
            Course c = courseCatalog.get(i);
            if (c.getName().equals(name) && c.getSection().equals(section)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Get a 2D string array of the course catalog with each column being: name, title,
     * section, and the meeting string
     * 
     * @return 2D String Array of the catalog with information for name, title, section, 
     * and the meeting string
     */
    public String[][] getCourseCatalog() {
        
        String[][] catalog = new String[courseCatalog.size()][5];
        
        for (int i = 0; i < courseCatalog.size(); i++) {
            catalog[i] = courseCatalog.get(i).getShortDisplayArray();
        }
        
        return catalog;
    }
    
    /**
     * Write the course catalog to a file
     * 
     * @param fileName name of the file to write to
     * @throws IllegalArgumentException if the file cannot be written to
     */
    public void saveCourseCatalog(String fileName) { 
        
        try {
            CourseRecordIO.writeCourseRecords(fileName, courseCatalog);
        }
        catch(IOException e) {
            throw new IllegalArgumentException("Unable to write to file " + fileName);
        }        
    }
}
