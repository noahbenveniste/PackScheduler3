package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads a file and creates a course object based off of the lines in the file. Can write
 * to a file to export an ArrayList of courses.
 * 
 * @author Dustin Hollar
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws IllegalArgumentException if the line cannot be read from the file
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) 
            throws FileNotFoundException {
        
        Scanner fileReader = null;

        fileReader = new Scanner(new FileInputStream(fileName));
        
        SortedList<Course> courses = new SortedList<Course>();
        while (fileReader.hasNextLine()) {
            try {
                Course course = readCourse(fileReader.nextLine());
                boolean duplicate = false;
                for (int i = 0; i < courses.size(); i++) {
                    Course c = courses.get(i);
                    if (course.getName().equals(c.getName()) &&
                            course.getSection().equals(c.getSection())) {
                        //it's a duplicate
                        duplicate = true;
                    }
                }
                if (!duplicate) {
                    courses.add(course);
                }
            } catch (IllegalArgumentException e) {
                //skip the line
            }
        }

        fileReader.close();
        return courses;
    }

    /**
     * Scans the line of a file into a Course object. Uses ',' as a Delimiter
     * 
     * @param nextLine line from the file to scan
     * @throws IllegalArgumentException if the meetingDays equals "A" and there are more tokens
     * in the line
     * @return Course object for the course in the file
     */
    private static Course readCourse(String nextLine) {

        Scanner s = new Scanner(nextLine);
        s.useDelimiter(",");
        
        String name = null;
        String title = null;
        String section = null;
        int credits = 0;
        String instructorId;
        int enrollmentCap;
        String meetingDays = null;
        int startTime = 0;
        int endTime = 0;
        
        Course c = null;

        try {
            
            // Scan the name
            name = s.next();
            // Scan the title
            title = s.next();
            // Scan the section
            section = s.next();
            // Scan the credits
            credits = s.nextInt();
            // Scan the instrcutorId
            instructorId = s.next();
            // Scan the enrollment cap
            enrollmentCap = s.nextInt();
            // Scan the meeting days
            meetingDays = s.next();
            
            /*
             *  If meeting days is equal to A, call the constructor without start and
             *  end time
             */
            if (meetingDays.equals("A")) {
                // Check to make sure there is no input after A
                if (s.hasNext()) {
                    s.close();
                    throw new IllegalArgumentException();
                }
                c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
            }
            else {
                // Scan the start and end time, check if input is valid
                startTime = s.nextInt();
                endTime = s.nextInt();

                c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays,
                        startTime, endTime);
            }
        }
        catch(NoSuchElementException e) {
            s.close();
            throw new IllegalArgumentException();
        }

        s.close();
        return c;
    }
    
    /**
     * Writes the given list of Courses to a given file.
     * @param fileName name of the file written.
     * @param courses list of courses to save.
     * @throws FileNotFoundException if file cannot be written
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) 
            throws FileNotFoundException {
        
        PrintStream fileWriter = new PrintStream(new File(fileName));
        
        for (int i = 0; i < courses.size(); i++) {
            fileWriter.println(courses.get(i).toString());
        }
        
        fileWriter.close();
    }
    
}
