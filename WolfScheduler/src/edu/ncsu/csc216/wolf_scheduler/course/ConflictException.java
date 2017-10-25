
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The custom exception for when there are conflicting activities
 * 
 * @author Bwu98
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Method for conflict exceptions with string parameter
	 * 
	 * @param message
	 *            A specific message for the Exception object to be passed along to
	 *            the parent constructor
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Method of conflict exception without parameters. Will give the generated
	 * message.
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

}
