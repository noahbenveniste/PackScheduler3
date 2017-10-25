package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * FSM that validates if the Course Name is in the correct syntax.
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 */
public class CourseNameValidator {
    
    /** Start state of the FSM */
    private final State start = new InitialState();
    /** Number State for the FSM */
    private final State number = new NumberState();
    /** Letter State for the FSM */
    private final State letter = new LetterState();
    /** Suffix State for the FSM */
    private final State suffix = new SuffixState();
    
    /** Current state of the FSM */
    private State state;
    
    /** Number of letters found in Course Name */
    private int letterCount;
    /** NUmber of digits found in Course Name */
    private int digitCount;
     
    /** Constructs the FSM */
    public CourseNameValidator() {
        //Literally does nothing.
    }
    
    /**
     * Determines if the parameter is a valid CourseName
     * 
     * @param courseName name of the course to validate
     * @return true if a valid CourseName, false otherwise
     * @throws InvalidTransitionException if the State is incorrect or illegal
     */
    public boolean isValid(String courseName) throws InvalidTransitionException {
        
        if (courseName == null || courseName.equals("")) {
            return false;
        }
        
        letterCount = 0;
        digitCount = 0;
        state = start;
        
        // Character in String
        char ch;
        // Traversing index
        int i = 0;
        
        // FSM
        while (i < courseName.length()) {
            ch = courseName.charAt(i++);
            if (Character.isDigit(ch)) {
                state.onDigit();
            }
            else if (Character.isLetter(ch)) {
                state.onLetter();
            }
            else {
                state.onOther();
            }
        }
        
        return true;
    }
    
    /** Determines the initial State of the FSM */
    private class InitialState extends State {
        
        /** Constructs InitialState */
        public InitialState() { 
            //Literally does nothing.
        }
        
        /** Check if the current character is a letter */
        @Override
        public void onLetter() {
            state = letter;
            letterCount++;
        }
        
        /** 
         * Check if the current character is a digit 
         * 
         * @throws InvalidTransitionException if the current character is a digit
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name must start with a letter.");
        }
    }
    
    /** Determines the letter State of the FSM */
    private class LetterState extends State {
        
        private static final  int MAX_PREFIX_LETTERS = 4;
        
        public LetterState() { 
            //Literally does nothing.
        }
        
        /** 
         * Check if the current character is a letter 
         * 
         * @throws InvalidTransitionException if the current character is not a letter
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            if (letterCount < MAX_PREFIX_LETTERS) {
                state = letter;
                letterCount++;
            } else {
                throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
            }
        }
        
        /** Check if the current character is a digit, changes the state to NUMBER */
        @Override
        public void onDigit() {
            state = number;
            digitCount++;
        }  
    }
    
    /** Determines the number State of the FSM */
    private class NumberState extends State {
        
        /** The allowed count for digits in a CourseName */
        private static final int COURSE_NUMBER_LENGTH = 3;
        
        /** Constructs NumberState */
        public NumberState() { 
            //Literally does nothing.
        }
        
        /** 
         * Check if the current character is a letter 
         * 
         * @throws InvalidTransitionException if the current character is not a letter
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            if (digitCount == COURSE_NUMBER_LENGTH) {
                state = suffix;
                letterCount++;
            } else {
                throw new InvalidTransitionException("Course name must have 3 digits.");
            }
        }
        
        /** 
         * Check if the current character is a digit 
         * 
         * @throws InvalidTransitionException if the current character is not a digit
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            if (digitCount < COURSE_NUMBER_LENGTH) {
                state = number;
                digitCount++;
            } else {
                throw new InvalidTransitionException("Course name can only have 3 digits.");
            }
            
        } 
    }
    
    /** Determines the suffix State of the FSM */
    private class SuffixState extends State {
        
        /** Construct SuffixState */
        public SuffixState() { 
            //Literally does nothing.
        }
        
        /** 
         * Check if the current character is a letter 
         * 
         * @throws InvalidTransitionException if the current character is a letter
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
        }
        
        /** 
         * Check if the current character is a digit 
         * 
         * @throws InvalidTransitionException if the current character is a digit
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
        }  
    }
}
