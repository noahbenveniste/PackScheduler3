/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * @author Brian Wu, Noah Beneviste, Ben Gale
 *
 */
public class LinkedStack<E> implements Stack<E> {
    /** List of objects */
    private LinkedAbstractList<E> list;
    /** Capacity of list */
    private int capacity;
    /** Size of list */
    private int size;

    /**
     * Constructs the linked stack list and sets capacity
     * 
     * @param capacity
     *            the number to set the capacity for the linked stack list (max amount for size)
     */
    @SuppressWarnings("unchecked")
    public LinkedStack(int capacity) {
        LinkedAbstractList<Object> o = new LinkedAbstractList<Object>(capacity);
        list = (LinkedAbstractList<E>) o;
        setCapacity(capacity);
        this.size = 0;
    }

    /**
     * Pushes an element to the top of the stack
     * 
     * @param element
     *            the element we want to add to the stack
     */
    @Override
    public void push(E element) {
        if (this.size == this.capacity) {
            throw new IllegalArgumentException("Capacity has been reached.");
        }
        // Adds element to the end of ArrayList (top of our stack)
        list.add(0, element);
        this.size++;
    }

    /**
     * Pops off the element at the top of the stack
     * 
     * @return the element the user just popped off
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = list.remove(0);
        this.size--;
        return element;
    }

    /**
     * Checks if the stack is empty
     * 
     * @return true if there is nothing in the stack, otherwise returns false
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Returns the number of elements in the stack
     * 
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Sets the capacity of the stack
     * 
     * @param capacity
     *            the number you want to cap your stack at
     */
    @Override
    public void setCapacity(int capacity) {
        try {
            list.setCapacity(capacity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        this.capacity = capacity;
    }

}
