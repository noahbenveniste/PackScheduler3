package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reads student records from text file. Writes a set of student records to a file
 * @author Hubert Ngo, Dustin Hollar, Brian Wu
 */
public class StudentRecordIO {

    /**
     * Reads a file that contains Student information and returns an SortedList of Students
     * 
     * @param fileName name of file to read
     * @return SortedList of Students 
     * @throws FileNotFoundException if file cannot be found or read
     */
    public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
        Scanner records = new Scanner(new FileInputStream(fileName));
        SortedList<Student> students = new SortedList<Student>();
        while(records.hasNextLine()) {
            String recordStr = records.nextLine();
            try {
                Scanner record = new Scanner(recordStr);
                record.useDelimiter(",");
                String firstName = record.next();
                String lastName = record.next();
                String id = record.next();
                String email = record.next();
                String hashPW = record.next();
                int maxCredits = record.nextInt();
                students.add(new Student(firstName, lastName, id, email, hashPW, maxCredits));
                record.close();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                //skip the line
            }
        }
        records.close();
        return students;
    }

	/**
	 * Writes the given list of Students to a given file.
	 * 
	 * @param fileName name of file
	 * @param studentDirectory SortedList of students
	 * @throws IOException Test class checks this exception
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {

		PrintStream fileWriter = null;

        fileWriter = new PrintStream(new File(fileName));

        for (int i = 0; i < studentDirectory.size(); i++) {
            fileWriter.println(studentDirectory.get(i).toString());
        }

        fileWriter.close(); 
	}
}
