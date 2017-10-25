package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Student class.
 * @author Boram Han, Jacob Myers, Dustin Hollar
 *
 */

public class StudentTest {

	/** First name of student */
	private static final String FIRST_NAME = "First";
	/** Last name of student */
	private static final String LAST_NAME = "Last";
	/** Unity id of student */
	private static final String ID = "flast";
	/** Email of student */
	private static final String EMAIL = "flast@ncsu.edu";
	/** Password of student */
	private static final String PASSWORD = "password";
	/** Maximum credit of student */
	private static final int MAX_CREDITS = 15;

	/**
	 * Tests the Student constructor with all field parameters.
	 */
    @Test
    public void testStudentStringStringStringStringStringInt() {
        
        // Testing for null first name
    	User s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

        // Testing for empty string first name ""
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test last name null
    	s = null;
		try {
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test last name ""
		s = null;
		try {
			s = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test id null
    	s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, null , EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test  id ""
		s = null;
		try {
			s = new Student (FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test first name null
    	 s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test first name ""
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "" , PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
		// Test password null
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL , null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
				
		// Test password ""
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL , "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
    }

    /** 
     * Tests the Student constructor with five constructors.
     */
    @Test
    public void testStudentStringStringStringStringString() {
    	User s = null;
      	try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
			//fail();
		} catch (IllegalArgumentException e) {
			fail();
		}
    	
      	s = null;
      	try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

        // Test first name ""
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test last name null
    	    s = null;
		try {
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test last name ""
		s = null;
		try {
			s = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test id null
      	s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, null , EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test  id ""
		s = null;
		try {
			s = new Student (FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		 // Test email null
    	    s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
        
        // Test email ""
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "" , PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Test password null
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL , null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		// Test password ""
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL , "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
    	
    }

    /**
     * Tests setFirstName().
     */
    @Test
    public void testSetFirstName() {
        
      //Construct a valid Student
      User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
      
      // Try to set first name as null
      try {
          s.setFirstName(null);
          fail(); //We don't want to reach this point - an exception should be thrown!
      } catch (IllegalArgumentException e) {
          //We've caught the exception, now we need to make sure that the field didn't change
          assertEquals("first", s.getFirstName());
      }
    	  
      // Try to set first name as an empty string
      try {
          s.setFirstName("");
          fail(); //We don't want to reach this point - an exception should be thrown!
      } catch (IllegalArgumentException e) {
          //We've caught the exception, now we need to make sure that the field didn't change
          assertEquals("first", s.getFirstName());
      }
    	
      // Try to set first name a valid first name
      try {
          s.setFirstName("George");
          //fail(); 
      } catch (IllegalArgumentException e) {
          //We've caught the exception, now we need to make sure that the test fails
          fail(); 
      }
      
    }

    /**
     * Tests setLastName().
     */
    @Test
    public void testSetLastName() {
        
        //Construct a valid Student
        User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        
        // Try to set last name as null
        try {
            s.setLastName(null);
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("last", s.getLastName());
        }
            
        // Try to set last name as an empty string
        try {
            s.setLastName("");
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("last", s.getLastName());
        }
          
        // Try to set last name a valid first name
        try {
            s.setLastName("Thomas");
            //fail(); 
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the test fails
            fail(); 
        }
        
    }

    /**
     * Tests SetEmail()
     */
    @Test
    public void testSetEmail() {
        
        //Construct a valid Student
        User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        
        // Try to set email as an empty string
        try {
            s.setEmail("");
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("email@ncsu.edu", s.getEmail());
        }
        
        // Try to set email as null
        try {
            s.setEmail(null);
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("email@ncsu.edu", s.getEmail());
        }
        
        // Try to set email as without @
        try {
            s.setEmail("emailncsu.edu");
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("email@ncsu.edu", s.getEmail());
        }
        
        // Try to set email as without . after the @
        try {
            s.setEmail("email@ncsuedu");
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("email@ncsu.edu", s.getEmail());
        }
        
        // Try to set a valid email
        try {
            s.setEmail("georgeJones@ncsu.edu");
             //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            fail();
        }        
    }
    
    
    /**
     * Tests setPassword()
     */
    @Test
    public void testSetPassword() {
        
        //Construct a valid Student
        User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        
        // Try to set password as null
        try {
            s.setPassword(null);
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("hashedpassword", s.getPassword());
        }
            
        // Try to set password as an empty string
        try {
            s.setPassword("");
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals("hashedpassword", s.getPassword());
        }
        
    }

    /**
     * Tests setMaxCredits()
     */
    @Test
    public void testSetMaxCredits() {
        
        //Construct a valid Student 
        Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", MAX_CREDITS);
        
        // Try to set credits less than 3
        try {
            s.setMaxCredits(1);
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals(MAX_CREDITS, s.getMaxCredits());
        }
        
        // Try to set credits more than 18
        try {
            s.setMaxCredits(19);
            fail(); //We don't want to reach this point - an exception should be thrown!
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            assertEquals(MAX_CREDITS, s.getMaxCredits());
        }
        
       // Try to set valid max credits
        try {
            s.setMaxCredits(9);
            
        } catch (IllegalArgumentException e) {
            //We've caught the exception, now we need to make sure that the field didn't change
            fail();
        }       
    }
    
    /**
     * Tests HashCode()
     */
    @Test
    public void testHashCode() { 
        
        User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s3 = new Student(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s4 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
        User s5 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
        User s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "hashedpassword", MAX_CREDITS);
        User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 9);
        
        //Test for the same hash code for the same values
        assertEquals(s1.hashCode(), s2.hashCode());
        
        //Test for each of the fields
        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s1.hashCode(), s4.hashCode());
        assertNotEquals(s1.hashCode(), s5.hashCode());
        assertNotEquals(s1.hashCode(), s6.hashCode());
        assertNotEquals(s1.hashCode(), s7.hashCode());
        
    }
    
    /**
     * Tests that the equals method works for all Student fields.
     */
    @Test
    public void testEqualsObject() {
        
        User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s3 = new Student(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s4 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
        User s5 = new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsu.edu", PASSWORD, MAX_CREDITS);
        User s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "hashedpassword", MAX_CREDITS);
        User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 9);
        
        //Test for equality in both directions
        assertTrue(s1.equals(s2));
        assertTrue(s2.equals(s1));
        
        //Test for each of the fields
        assertFalse(s1.equals(s3));
        assertFalse(s1.equals(s4));
        assertFalse(s1.equals(s5));
        assertFalse(s1.equals(s6));
        assertFalse(s1.equals(s7));          
    }

    /**
     * Tests that to String returns the correct value.
     */
    @Test
    public void testToString() {
        
        User c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        String s1 = "First,Last,flast,flast@ncsu.edu,password,15";
        assertEquals(s1, c1.toString());
        
        User c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
        String s2 = "First,Last,flast,flast@ncsu.edu,password,18";
        assertEquals(s2, c2.toString());
        }
    
    /** 
     * Test .compareTo()
     */
    @Test
    public void testCompareTo() {
        
        Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s3 = new Student(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s4 = new Student(FIRST_NAME, "lower", ID, EMAIL, PASSWORD, MAX_CREDITS);
        
        // compare two equal objects;
        assertEquals(s1.compareTo(s1), 0);
        
        
        // Use assert true for the statements
        
        assertTrue(s4.compareTo(s1) > 0);
        assertTrue(s1.compareTo(s4) < 0);
        assertEquals(s4.compareTo(s1), -s1.compareTo(s4));
        
        // Return 0
        assertEquals(0, s1.compareTo(s2));
        assertEquals(s1.compareTo(s2), -s2.compareTo(s1));
                
        // Test if the relation is transitive
        assertTrue(s4.compareTo(s1) > 0);
        assertTrue(s1.compareTo(s3) > 0);
        assertTrue(s4.compareTo(s3) > 0);
          
        // Return negative integer
        assertTrue(s3.compareTo(s1) < 0);
        assertEquals(s3.compareTo(s1), -s1.compareTo(s3));
        
        // Compare first names if last names are the same
        // Update s1
        s1 = new Student("Aaron", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        assertTrue(s1.compareTo(s2) < 0);
        assertTrue(s2.compareTo(s1) > 0);
        
        // Compare unity id if first names are also same
        // update s2
        s2 = new Student("Aaron", LAST_NAME, "flast2", EMAIL, PASSWORD, MAX_CREDITS);
        assertTrue(s1.compareTo(s2) < 0);

        
    }

}
