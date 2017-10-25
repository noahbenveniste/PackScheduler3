/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * An interface that manages all the conflicts in WolfScheduler
 * 
 * @author Bwu98
 */
public interface Conflict {

	/**
	 * Checks for conflicts between activities
	 * 
	 * @param possibleConflictingActivity
	 *            The Activity to be checked for conflicts
	 * @throws ConflictException
	 *             Throws exception when two activites conflict
	 */
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
