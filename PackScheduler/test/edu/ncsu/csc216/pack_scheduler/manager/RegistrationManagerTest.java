package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
//import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Tests RegistrationManager
 * 
 * @author Hubert Ngo, Dustin Hollar, Brian Wu
 */
public class RegistrationManagerTest {
	/** The RegistrationManager used for the tests */
	private RegistrationManager manager;
	/** The properties file containing sensitive user data. Used to test */
	private static final String PROP_FILE = "registrar.properties";
	private String registrarID;
	private String registrarPass;
	
	/**
	 * Sets up the CourseManager and clears the data.
	 */
	@Before
	public void setUp() {
		manager = RegistrationManager.getInstance();
		manager.clearData();
		Properties prop = new Properties();
        try (InputStream input = new FileInputStream(PROP_FILE)) {
            prop.load(input);
            registrarID = prop.getProperty("id");
            registrarPass = prop.getProperty("pw");
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create registrar.");
        }
	}
	
	/**
	 * Clears the manager between tests
	 */
	@After
	public void tearDown() {
	    manager.clearData();
	}

	/**
	 * Tests getCourseCatalog
	 */
	@Test
	public void testGetCourseCatalog() {
		assertTrue(Arrays.equals(new String[0][0], manager.getCourseCatalog().getCourseCatalog()));
	}

	/**
	 * Tests getStudentDirectory
	 */
	@Test
	public void testGetStudentDirectory() {
	    assertTrue(Arrays.equals(new String[0][0], manager.getStudentDirectory().getStudentDirectory()));
	}

	/**
	 * Test login
	 */
	@Test
	public void testLogin() {
        manager.getStudentDirectory().loadStudentsFromFile("test-files/registration_manager_test.txt");
        manager.getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records.txt");
        assertTrue(manager.login(registrarID, registrarPass));
        assertEquals(registrarID, manager.getCurrentUser().getId());
        assertFalse(manager.login("dave", "Password1"));
        manager.logout();
        try {
            assertFalse(manager.login("daved", "Password1"));
            assertFalse(manager.login(registrarID, "Password1"));
            assertTrue(manager.login("daved", "dave"));
            manager.logout();
        } catch (IllegalArgumentException e) {
            assertEquals("User doesn't exist.", e.getMessage());
        }
        try {
            assertFalse(manager.login("fakeUser", "Password1"));
        } catch (IllegalArgumentException e) {
            assertEquals("User doesn't exist.", e.getMessage());
        }
        assertFalse(manager.login("awitt", "Password1"));
        assertTrue(manager.login("awitt", "pw"));
	}

	/**
	 * Tests logout
	 */
	@Test
	public void testLogout() {
        manager.login(registrarID, registrarPass);
        assertNotNull(manager.getCurrentUser());
        manager.logout();
        assertNull(manager.getCurrentUser());
	}

	/**
	 * Tests getCurrentUser
	 */
	@Test
	public void testGetCurrentUser() {
        manager.login(registrarID, registrarPass);
        assertEquals(registrarID, manager.getCurrentUser().getId());
	}
	
	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login(registrarID, registrarPass);
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.", registrarID, manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(3, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(1, scheduleFrostArray.length);
	    assertEquals("CSC226", scheduleFrostArray[0][0]);
	    assertEquals("001", scheduleFrostArray[0][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleFrostArray[0][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleFrostArray[0][3]);
	    assertEquals("9", scheduleFrostArray[0][4]);
	            
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("8", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	    
	    manager.logout();
	    
	    /** DELETE TESTS AFTER THIS POINT UPON FINAL SUBMISSION */
//	    
//	    manager.clearData();
//	    directory.loadStudentsFromFile("test-files/debug_waitlist.txt");
//	    assertEquals(13, directory.getStudentDirectory().length);
//	    catalog.loadCoursesFromFile("test-files/course_records.txt");
//	    assertEquals(8, catalog.getCourseCatalog().length);
//	    
//	    manager.login("daustin", "pw");
//	    assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//	    String[][] s = manager.getStudentDirectory().getStudentById("daustin").getSchedule().getScheduledCourses();
//	    
//	    for (int i = 0; i < s.length; i++) {
//	        for (int j = 0; j < s[i].length; j++) {
//	            System.out.print(s[i][j]);
//	        }
//	        System.out.println();
//	    }
//	    
//	    assertEquals(0, catalog.getCourseFromCatalog("CSC216", "001").getCourseRoll().getWaitlist().size());
//	    
//	    manager.logout();
//	    
//	    manager.login("lberg", "pw");
//	    assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//	    
//	    manager.logout();
//	    
//	    manager.login("rbrennan", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//	    
//        manager.login("efrost", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("ahicks", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("zking", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("dnolan", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("rpuckett", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("cschwartz", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        manager.login("gstone", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        manager.logout();
//        
//        //The course's roll is full
//        
//        assertEquals(0, catalog.getCourseFromCatalog("CSC216", "001").getCourseRoll().getWaitlist().size());
//        
//        //Currently, if the roll is full, a student enrolled in it is added to the waitlist, but the course is still added
//        //to their schedule
//        manager.login("nnbenven", "pw");
//        assertTrue(manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
//        
//        //Print the students in the roll
//        for (int i = 0; i < manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getRoll().size(); i++) {
//            System.out.println(manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getRoll().get(i));
//        }
//        System.out.println();
//        
////        //Print the students on the waitlist
////        LinkedQueue<Student> temp = new LinkedQueue<Student>(20);
////        Student s0 = null;
////        
////        for (int i = 0; i < manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getWaitlist().size(); i++) {
////            s0 = manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getWaitlist().dequeue();
////            System.out.println(s0.toString());
////            temp.enqueue(s0);
////        }
////        System.out.println();
////        
////        while(!temp.isEmpty()) {
////            manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getWaitlist().enqueue(temp.dequeue());
////        }
//        
//        manager.logout();
//        
//        manager.login("daustin", "pw");
//        //Try to unenroll a student in the course
//        //Currently returns false when it should return true
//        
//        //also doesn't properly add the first student from the waitlist to the roll
//        //FIGURED OUT: this is because RegistrationManager allows the course to be added to their schedule, so when CourseRoll.drop()
//        //checks to see if the first student on the waitlist can be added, the course is already in their schedule, which means
//        //they cannot be enrolled
//        
//        //also doesn't properly remove the course from the student's schedule
//        boolean result = manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
//        assertTrue(result);
//        //Print the students in the roll
//        for (int i = 0; i < manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getRoll().size(); i++) {
//            System.out.println(manager.getCourseCatalog().getCourseFromCatalog("CSC216", "001").getCourseRoll().getRoll().get(i));
//        }
//        System.out.println();
//        
//        s = manager.getStudentDirectory().getStudentById("daustin").getSchedule().getScheduledCourses();
//        
//        if (s.length == 0) {
//            System.out.println("Schedule is empty");
//        }
//        
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j < s[i].length; j++) {
//                System.out.print(s[i][j]);
//            }
//            System.out.println();
//        }
	}

	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login(registrarID, registrarPass);
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.", registrarID, manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: False - student not enrolled in the course", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(7, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(2, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC116", scheduleHicksArray[1][0]);
	    assertEquals("003", scheduleHicksArray[1][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[1][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(3, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(1, scheduleHicksArray.length);
	    assertEquals("CSC116", scheduleHicksArray[0][0]);
	    assertEquals("003", scheduleHicksArray[0][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}

	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //Test if not logged in
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login(registrarID, registrarPass);
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.", registrarID, manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}
	
	/**
	 * Tests getFacultyDirectory()
	 */
	@Test
	public void testGetFacultyDirectory() {
	    assertEquals(0, manager.getFacultyDirectory().getFacultyDirectory().length);
	}
	
	/**
	 * Tests addFacultyToCourse()
	 */
	@Test
	public void testAddFacultyToCourse() {
	    
	}
	
	/**
	 * Tests removeFacultyFromCourse()
	 */
	@Test
	public void testRemoveFacultyFromCourse() {
	    
	}
	
	/**
	 * Tests resetFacultySchedule()
	 */
	@Test
	public void testResetFacultySchedule() {
	    
	}
}
