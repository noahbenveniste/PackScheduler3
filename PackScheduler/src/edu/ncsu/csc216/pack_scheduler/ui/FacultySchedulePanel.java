package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * 
 * @author Noah Benveniste
 * @author Ben Gale
 * @author Brian Wu
 */
public class FacultySchedulePanel extends JPanel implements ActionListener {

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
    /** Label for Course Details name title */
    private JLabel lblNameTitle = new JLabel("Name: ");
    /** Label for Course Details section title */
    private JLabel lblSectionTitle = new JLabel("Section: ");
    /** Label for Course Details title title */
    private JLabel lblTitleTitle = new JLabel("Title: ");
    /** Label for Course Details instructor title */
    private JLabel lblInstructorTitle = new JLabel("Instructor: ");
    /** Label for Course Details credit hours title */
    private JLabel lblCreditsTitle = new JLabel("Credits: ");
    /** Label for Course Details meeting title */
    private JLabel lblMeetingTitle = new JLabel("Meeting: ");
    /** Label for Course Details enrollment cap title */
    private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
    /** Label for Course Details open seats title */
    private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
    /** Label for Course Details waitlist title */
    private JLabel lblWaitlistTitle = new JLabel("Number on Waitlist: ");
    /** Label for Course Details name */
    private JLabel lblName = new JLabel("");
    /** Label for Course Details section */
    private JLabel lblSection = new JLabel("");
    /** Label for Course Details title */
    private JLabel lblTitle = new JLabel("");
    /** Label for Course Details instructor */
    private JLabel lblInstructor = new JLabel("");
    /** Label for Course Details credit hours */
    private JLabel lblCredits = new JLabel("");
    /** Label for Course Details meeting */
    private JLabel lblMeeting = new JLabel("");
    /** Label for Course Details enrollment cap */
    private JLabel lblEnrollmentCap = new JLabel("");
    /** Label for Course Details open seats */
    private JLabel lblOpenSeats = new JLabel("");
    /** Label for waitlist seats */
    private JLabel lblWaitlist = new JLabel("");
    private Faculty currentUser;
    private FacultySchedule schedule;
    private RegistrationManager manager;
    private CourseCatalog catalog;
    
    /**
     * 
     */
    public FacultySchedulePanel() {
        //Set up the layout
        super(new GridBagLayout());
        
        //Get the registration manager instance
        manager = RegistrationManager.getInstance();
        //Get the current user
        currentUser = (Faculty) manager.getCurrentUser();
        //Get the user's schedule
        schedule = currentUser.getSchedule();
        
        //Set up the schedule table
        scheduleTableModel = new CourseTableModel(false);
        tableSchedule = new JTable(scheduleTableModel) {
            private static final long serialVersionUID = 1L;

            /**
             * Set custom tool tips for cells
             * 
             * @param e
             *            MouseEvent that causes the tool tip
             * @return tool tip text
             */
            @Override
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                return (String) scheduleTableModel.getValueAt(rowIndex, realColumnIndex);
            }
        };
        tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tableSchedule.setFillsViewportHeight(true);
        tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
                String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
                Course c = manager.getCourseCatalog().getCourseFromCatalog(name, section);
                updateCourseDetails(c);
            }

        });
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
        if (c != null) {
            lblName.setText(c.getName());
            lblSection.setText(c.getSection());
            lblTitle.setText(c.getTitle());
            lblInstructor.setText(c.getInstructorId());
            lblCredits.setText("" + c.getCredits());
            lblMeeting.setText(c.getMeetingString());
            lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
            lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
            lblWaitlist.setText("" + c.getCourseRoll().getNumberOnWaitlist());
        }
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
            if (isCatalog) {
                data = catalog.getCourseCatalog();
            } else {
                currentUser = (Faculty) RegistrationManager.getInstance().getCurrentUser();
                if (currentUser != null) {
                    schedule = currentUser.getSchedule();
                    data = schedule.getScheduledCourses();
                    FacultySchedulePanel.this.repaint();
                    FacultySchedulePanel.this.validate();
                }
            }
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
