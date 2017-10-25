package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman, Brian Wu
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException
	 *             if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	private static Course readCourse(String nextLine) {
		String name = null;
		String title = null;
		String section = null;
		int credits = 0;
		String instructorId = null;
		String meetingDays = null;
		int startTime = 0;
		int endTime = 0;

		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		try {
			name = s.next();
			title = s.next();
			section = s.next();
			credits = Integer.parseInt(s.next());
			instructorId = s.next();
			meetingDays = s.next();
			if (s.hasNext()) {
				startTime = Integer.parseInt(s.next());
				endTime = Integer.parseInt(s.next());
			}
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException: " + e.getMessage());
		}
		Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);

		s.close();
		return course;
	}
}
