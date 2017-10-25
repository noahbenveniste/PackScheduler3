package edu.ncsu.csc216.pack_scheduler.util;

/**
 * An interface to delegate the methods that the ArrayQueue
 * and LinkedQueue classes will implement
 * @author Noah Benveniste, Ben Gale, Brian Wu
 */
public interface Queue<E> {
    /**
     * Adds an element to the back of the queue
     * @param element
     */
    void enqueue(E element);
    
    /**
     * Removes the next element from the front of the queue
     * @return the element removed from the front of the queue
     */
    E dequeue();
    
    /**
     * Checks if the queue is empty
     * @return true if it is, false if it isn't
     */
    boolean isEmpty();
    
    /**
     * Returns the number of elements in the queue
     * @return the number of elements
     */
    int size();
    
    /**
     * Sets the number of elements that the queue can hold
     * @param capacity the number of elements
     */
    void setCapacity(int capacity);
}
