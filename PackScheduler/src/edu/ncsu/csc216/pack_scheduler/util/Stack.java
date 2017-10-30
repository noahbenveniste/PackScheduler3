/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack interface used for writing lists of items
 * 
 * @author Brian Wu, Noah Benveniste, Ben Gale
 * @param <E>
 *            the general object to be composing the stack
 */
public interface Stack<E> {

    /**
     * Pushes an element to the top of the stack
     * 
     * @param element
     *            the element we want to add to the stack
     */
    void push(E element);

    /**
     * Pops off the element at the top of the stack
     * 
     * @return the element the user just popped off
     */
    E pop();

    /**
     * Checks if the stack is empty
     * 
     * @return true if there is nothing in the stack, otherwise returns false
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack
     * 
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Sets the capacity of the stack
     * 
     * @param capacity
     *            the number you want to cap your stack at
     */
    void setCapacity(int capacity);

}
