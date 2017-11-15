package edu.ncsu.csc216.pack_scheduler.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * 
 * @author Noah Benveniste
 * @author Ben Gale
 * @author Brian Wu
 */
public class FacultySchedulePanel {

    /** ID number used for object serialization. */
    private static final long serialVersionUID = 1L;
    /** */
    private JTable tableSchedule;
    /** */
    private JTable tableRoll;
    /** */
    private CourseTableModel scheduleTableModel;
    /** */
    private StudentTableModel rollTableModel;
    /** */
    private Border lowerEtched;
    /** */
    private JScrollPane scrollSchedule;
    private JScrollPane scrollRoll;
    private JPanel pnlCourseDetails;
    private JLabel lblNameTitle;
    private JLabel lblSectionTitle;
    private JLabel lblTitleTitle;
    private JLabel lblInstructorTitle;
    private JLabel lblCreditsTitle;
    private JLabel lblMeetingTitle;
    private JLabel lblEnrollmentCapTitle;
    private JLabel lblOpenSeatsTitle;
    private JLabel lblWaitlistTitle;
    private JLabel lblName;
    private JLabel lblSection;
    private JLabel lblTitle;
    private JLabel lblInstructor;
    private JLabel lblCredits;
    private JLabel lblMeeting;
    private JLabel lblEnrollmentCap;
    private JLabel lblOpenSeats;
    private JLabel lblWaitlist;
    private FacultySchedule schedule;
    
    
    /**
     * 
     */
    public FacultySchedulePanel() {
        
    }
    
    /**
     * 
     */
    private void initFacultySchedule() {
        
    }
    
    /**
     * 
     */
    private void initCourseDetails() {
        
    }
    
    /**
     * 
     */
    private void initCourseRoll() {
        
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
     * @param args
     */
    public static void main(String[] args) {
        
    }
    
    /**
     * {@link CourseTableModel} is the object underlying the {@link JTable} object
     * that displays the list of {@link Course}s to the user.
     * 
     * @author Sarah Heckman
     */
    private class CourseTableModel extends AbstractTableModel {

        /** ID number used for object serialization. */
        private static final long serialVersionUID = 1L;
        /** Column names for the table */
        private String[] columnNames = { "Name", "Section", "Title", "Meeting Days", "Open Seats" };
        /** Data stored in the table */
        private Object[][] data;
        /** Boolean flag if the model applies to the catalog or schedule */
        private boolean isCatalog;

        /**
         * Constructs the {@link CourseTableModel} by requesting the latest information
         * from the {@link RequirementTrackerModel}.
         */
        public CourseTableModel(boolean isCatalog) {
            this.isCatalog = isCatalog;
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
         * {@link WolfScheduler}.
         */
        private void updateData() {
//            if (isCatalog) {
//                data = catalog.getCourseCatalog();
//            } else {
//                currentUser = (Student) RegistrationManager.getInstance().getCurrentUser();
//                if (currentUser != null) {
//                    schedule = currentUser.getSchedule();
//                    txtScheduleTitle.setText(schedule.getTitle());
//                    borderSchedule.setTitle(schedule.getTitle());
//                    scrollSchedule.setToolTipText(schedule.getTitle());
//                    data = schedule.getScheduledCourses();
//
//                    StudentRegistrationPanel.this.repaint();
//                    StudentRegistrationPanel.this.validate();
//                }
//            }
        }
        
    }
    
    private class StudentTableModel extends AbstractTableModel {

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public Object getValueAt(int arg0, int arg1) {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

}
