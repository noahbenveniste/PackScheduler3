/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads student records from text file. Writes a set of student records to a file
 * @author Brian Wu, Ben Gale, Noah Benveniste
 */
public class FacultyRecordIO {
    
    /**
     * Reads a file that contains Faculty information and returns an LinkedList of Students
     * 
     * @param fileName name of file to read
     * @return LinkedList of faculty
     * @throws FileNotFoundException if file cannot be found or read
     */
    public static LinkedList<Faculty> readFacultyRecords (String fileName) throws FileNotFoundException {
        Scanner records = new Scanner(new FileInputStream(fileName));
        LinkedList<Faculty> faculties = new LinkedList<Faculty>();
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
                int maxCourses = record.nextInt();
                faculties.add(new Faculty(firstName, lastName, id, email, hashPW, maxCourses));
                record.close();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                //skip the line
            }
        }
        records.close();

        return faculties;
    }
    
    /**
     * Writes the given list of Faculty to a given file.
     * 
     * @param fileName name of file
     * @param Faculties LinkedList of faculties
     * @throws IOException Test class checks this exception
     */
    public static void writeFacultyRecords (String fileName, LinkedList<Faculty> faculties) throws IOException {
        PrintStream fileWriter = null;

        fileWriter = new PrintStream(new File(fileName));
        
//      for (int i = 0; i < faculties.size(); i++) {
//          fileWriter.println(faculties.listIterator(i).next().toString());
//      }
        
        for (Faculty f : faculties) {
            fileWriter.println(f.toString());
        }

        fileWriter.close(); 
    }

}
