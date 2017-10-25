/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * ActivityRecordIO reads in the activities on the schedule and also writes them
 * out on a file
 * 
 * @author Bwu98
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName
	 *            string that is the name of the file to be read
	 * @param courses
	 *            an ArrayList of courses
	 * @throws IOException
	 *             throws exception if input or output is failed
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}
		fileWriter.close();
	}

}
