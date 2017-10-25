/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * @author Brian Wu, Noah Benveniste, Ben Gale
 *
 */
public interface Stack<E> {
    
    void push(E element);
    E pop();
    boolean isEmpty();
    int size();
    void setCapacity(int capacity);
    
}
