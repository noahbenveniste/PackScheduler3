/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * @author Brian Wu, Noah Beneviste, Ben Gale
 * @param <E>
 *
 */
public class ArrayStack<E> implements Stack<E> {
    /** List of objects */
    private ArrayList<E> list;
    /** Capacity of list */
    private int capacity;
    /** Size of list */
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        ArrayList<Object> o = new ArrayList<Object>();
        list = (ArrayList<E>) o;
        setCapacity(capacity);
        this.size = 0;
    }

    @Override
    public void push(E element) {
        if (this.size == this.capacity) {
            throw new IllegalArgumentException("Capacity has been reached.");
        }
        // Adds element to the end of ArrayList (top of our stack)
        list.add(size, element);
        this.size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = list.remove(size - 1);
        size--;
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
        if (capacity < 0 || capacity < this.size) {
            throw new IllegalArgumentException(
                    "Invalid capacity, must be greater than zero and lest than or equal to size.");
        }
        this.capacity = capacity;
    }

}
