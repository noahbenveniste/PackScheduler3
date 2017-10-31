package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class for a queue that uses an ArrayList as its underlying list structure.
 * @author Noah Benveniste, Ben Gale, Brian Wu
 * @param <E> indicates that the ArrayQueue can work with any object type
 */
public class ArrayQueue<E> implements Queue<E> {
    /** The capacity of the queue */
    private int capacity;
    /** The underlying list used by the queue */
    private ArrayList<E> list;
    /** The number of elements currently in the queue */
    private int size;
    
    /**
     * Constructs an ArrayQueue with a specified initial capacity
     * @param capacity the initial capacity of the queue
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        //Instantiate an ArrayList of objects
        ArrayList<Object> o = new ArrayList<Object>();
        //Cast to generic type
        list = (ArrayList<E>) o;
        //Initialize the size to 0
        size = 0;
        //Call setCapacity()
        setCapacity(capacity);
    }
    
    /**
     * Adds an element to the back of the queue
     * @param element the element to add
     * @throws IllegalArgumentException if the queue is full
     */
    @Override
    public void enqueue(E element) {
        //Add an element to the back of the queue i.e. the front of the ArrayList
        if (size < capacity) {
            list.add(0, element);
            size++;
        } else {
            throw new IllegalArgumentException("Queue is full");
        }
    }

    /**
     * Removes the next element from the front of the queue
     * @return the element removed from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E dequeue() {
        //Remove the element at the front of the queue i.e. the back of the ArrayList
        if (size > 0) {
            E element = list.remove(list.size() - 1);
            //Decrement size
            size--;
            return element;
        } else {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    /**
     * Checks if the queue is empty
     * @return true if it is, false if it isn't
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of elements in the queue
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Sets the ArrayQueue's capacity
     * @param capacity the new capacity to set
     * @throws IllegalArgumentException if the capacity is negative or less than the current queue's size
     */
    @Override
    public void setCapacity(int capacity) {
        if (capacity < size) {
            throw new IllegalArgumentException("Invalid capacity, must be greater than or equal to the current queue size");
        } else {
            this.capacity = capacity;
        }
    }
}
