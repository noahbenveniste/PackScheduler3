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
    
    @SuppressWarnings("unchecked")
    public LinkedStack (int capacity) {
        LinkedAbstractList<Object> o = new LinkedAbstractList<Object>(capacity);
        list = (LinkedAbstractList<E>) o;
        setCapacity(capacity);
        this.size = 0;
    }

    @Override
    public void push(E element) {
        if (this.size == this.capacity) {
            throw new IllegalArgumentException("Capacity has been reached.");
        }
        // Adds element to the end of ArrayList (top of our stack)
        list.add(0, element);
        this.size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = list.remove(0);
        this.size--;
        return element;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

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
