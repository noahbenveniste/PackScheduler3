package edu.ncsu.csc216.pack_scheduler.course;
/**
 * Interface to check for conflict between Activities
 * 
 * @author Dustin Hollar
 */
public interface Conflict {
    
    /** Check for conflict between activities
     * @param possibleConflictingActivity Activity to check for conflict
     * @throws ConflictException if a two Activities have a time conflict
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
