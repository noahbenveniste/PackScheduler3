package edu.ncsu.csc216.pack_scheduler.course.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests CourseNameValidatorFSM
 *  
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */
public class CourseNameValidatorFSMTest {
    
    /**
     * Tests isValid method
     */
    @Test
    public void testIsValid() {
        CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
        try {
            assertTrue(validator.isValid("CSC116"));
        } catch (InvalidTransitionException e) {
            fail();
        }
        
        try {
            validator.isValid(" CSC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("1CSC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name must start with a letter.", e.getMessage());
        }
        
        try {
            validator.isValid(".CSC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("C1SC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name must have 3 digits.", e.getMessage());
        }
        
        try {
            validator.isValid("C.SC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CS1C116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name must have 3 digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CS.C116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            assertTrue(validator.isValid("CSCC116"));
        } catch (InvalidTransitionException e1) {
            fail();
        }
        
        try {
            validator.isValid("CSC.116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCCC116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC.116");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC1.16");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSC11C6");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name must have 3 digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC11.6");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            assertTrue(validator.isValid("CSCC116C"));
        } catch (InvalidTransitionException e) {
            fail();
        }
        
        try {
            validator.isValid("CSCC1161");
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only have 3 digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC116.");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC116CC");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC116C1");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
        }
        
        try {
            validator.isValid("CSCC116C.");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
        }
        
        try {
            assertFalse(validator.isValid(""));
        } catch (InvalidTransitionException e) {
            fail();
        }
    }
}
