package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * 
 * @author Noah Benveniste
 * @author Ben Gale
 * @author Brian Wu
 */
public class InstructorAssignmentPanel {

    /** ID number used for object serialization. */
    private static final long serialVersionUID = 1L;
    private JButton btnAssignFacultyToCourse;
    private JButton btnRemoveFacultyFromCourse;
    private JButton btnReset;
    private JTable tableCatalog;
    private JTable tableFaculty;
    private CourseCatalogTableModel catalogTableModel;
    private FacultyDirectoryTableModel facultyTableModel;
    private JPanel pnlCourseDetails;
    private JLabel lblNameTitle;
    private JLabel lblSectionTitle;
    private JLabel lblTitleTitle;
    private JLabel lblInstructorTitle;
    private JLabel lblCreditsTitle;
    private JLabel lblMeetingTitle;
    private JLabel lblEnrollmentCapTitle;
    private JLabel lblOpenSeatsTitle;
    private JLabel lblName;
    private JLabel lblSection;
    private JLabel lblTitle;
    private JLabel lblInstructor;
    private JLabel lblCredits;
    private JLabel lblMeeting;
    private JLabel lblEnrollmentCap;
    private JLabel lblOpenSeats;
    private JPanel pnlFacultyDetails;
    private JLabel lblFirstNameTitle;
    private JLabel lblLastNameTitle;
    private JLabel lblIdTitle;
    private JLabel lblEmailTitle;
    private JLabel lblMaxCourseTitle;
    private JLabel lblOverloadedTitle;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblId;
    private JLabel lblEmail;
    private JLabel lblMaxCourse;
    private JLabel lblOverloaded;
    private CourseCatalog catalog;
    private FacultyDirectory facultyDirectory;
    
    /**
     * 
     */
    public InstructorAssignmentPanel() {
        
    }
    
    /**
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        
    }
    
    /**
     * 
     */
    public void updateTables() {
        
    }
    
    /**
     * 
     * @param c
     */
    private void updateCourseDetails(Course c) {
        
    }
    
    /**
     * 
     * @param f
     */
    private void updateFacultyDetails(Faculty f) {
        
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        
    }
    
    /**
     * {@link FacultyDirectoryTableModel} is the object underlying the
     * {@link JTable} object that displays the list of Faculty to the system.
     * 
     * @author Sarah Heckman
     */
    private class FacultyDirectoryTableModel extends AbstractTableModel {

        /** ID number used for object serialization. */
        private static final long serialVersionUID = 1L;
        /** Column names for the table */
        private String[] columnNames = { "First Name", "Last Name", "Faculty ID" };
        /** Data stored in the table */
        private Object[][] data;

        /**
         * Constructs the {@link FacultyDirectoryTableModel} by requesting the latest
         * information from the {@link RequirementTrackerModel}.
         */
        public FacultyDirectoryTableModel() {
            updateData();
        }

        /**
         * Returns the number of columns in the table.
         * 
         * @return the number of columns in the table.
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Returns the number of rows in the table.
         * 
         * @return the number of rows in the table.
         */
        @Override
        public int getRowCount() {
            if (data == null)
                return 0;
            return data.length;
        }

        /**
         * Returns the column name at the given index.
         * 
         * @return the column name at the given column.
         */
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        /**
         * Returns the data at the given {row, col} index.
         * 
         * @return the data at the given location.
         */
        @Override
        public Object getValueAt(int row, int col) {
            if (data == null)
                return null;
            return data[row][col];
        }

        /**
         * Sets the given value to the given {row, col} location.
         * 
         * @param value
         *            Object to modify in the data.
         * @param row
         *            location to modify the data.
         * @param column
         *            location to modify the data.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        /**
         * Updates the given model with {@link Faculty} information from the
         * {@link FacultyDirectory}.
         */
        public void updateData() {
            //data = facultyDirectory.getFacultyDirectory();
        }
        
    }
    
    /**
     * {@link CourseCatalogTableModel} is the object underlying the {@link JTable}
     * object that displays the list of Courses to the user.
     * 
     * @author Sarah Heckman
     */
    private class CourseCatalogTableModel extends AbstractTableModel {

        /** ID number used for object serialization. */
        private static final long serialVersionUID = 1L;
        /** Column names for the table */
        private String[] columnNames = { "Name", "Section", "Title", "Meeting Information", "Open Seats" };
        /** Data stored in the table */
        private Object[][] data;

        /**
         * Constructs the {@link CourseCatalogTableModel} by requesting the latest
         * information from the {@link RequirementTrackerModel}.
         */
        public CourseCatalogTableModel() {
            updateData();
        }

        /**
         * Returns the number of columns in the table.
         * 
         * @return the number of columns in the table.
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Returns the number of rows in the table.
         * 
         * @return the number of rows in the table.
         */
        @Override
        public int getRowCount() {
            if (data == null)
                return 0;
            return data.length;
        }

        /**
         * Returns the column name at the given index.
         * 
         * @return the column name at the given column.
         */
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        /**
         * Returns the data at the given {row, col} index.
         * 
         * @return the data at the given location.
         */
        @Override
        public Object getValueAt(int row, int col) {
            if (data == null)
                return null;
            return data[row][col];
        }

        /**
         * Sets the given value to the given {row, col} location.
         * 
         * @param value
         *            Object to modify in the data.
         * @param row
         *            location to modify the data.
         * @param column
         *            location to modify the data.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        /**
         * Updates the given model with {@link Course} information from the
         * {@link CourseCatalog}.
         */
        public void updateData() {
            //data = catalog.getCourseCatalog();
        }
        
    }

}
