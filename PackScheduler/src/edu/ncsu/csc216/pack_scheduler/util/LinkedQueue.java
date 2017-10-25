package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Class for a queue that uses a LinkedAbstractList as its underlying list structure.
 * @author Noah Benveniste, Ben Gale, Brian Wu
 * @param <E> indicates that the LinkedQueue can work with any element type
 */
public class LinkedQueue<E> implements Queue<E> {
    /** The number of elements the queue can hold */
    private int capacity;
    /** The underlying list used by the queue */
    private LinkedAbstractList<E> list;
    /** The number of elements in the queue */
    private int size;
    
    /**
     * Constructs a LinkedQueue with a specified initial capacity
     * @param capacity the initial capacity of the queue
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    @SuppressWarnings("unchecked")
    public LinkedQueue(int capacity) {
        //Instantiate the list as a list of objects
        LinkedAbstractList<Object> o = new LinkedAbstractList<Object>(capacity);
        //Cast to generic type
        list = (LinkedAbstractList<E>) o;
        //Initialize size to 0
        size = 0;
        //Try to set the capacity
        setCapacity(capacity);
    }
    
    /**
     * Adds an element to the back of the queue
     * @param element the element to add
     * @throws IllegalArgumentException if the queue is full
     */
    @Override
    public void enqueue(E element) {
        //Add an element to the back of the queue i.e. the front of the LinkedAbstractList
        if (size < capacity) {
            list.add(0, element);
        } else {
            throw new IllegalArgumentException("Queue is full");
        }
    }

    /**
     * Remove the element at the front of the queue
     * @return the element at the front of the queue
     * @throws IllegalArgumentException if the queue is empty
     */
    @Override
    public E dequeue() {
        //Remove the element at the front of the queue i.e. the end of the LinkedAbstractList
        if (size > 0) { 
            E element = list.remove(list.size()-1);
            //Decrement size
            size--;
            return element;
        } else {
            throw new IllegalArgumentException("Queue is empty");
        }
    }

    /**
     * Checks if there are elements in the queue
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Gets the number of elements currently in the queue
     * @return the size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 
     */
    @Override
    public void setCapacity(int capacity) {
        try {
            list.setCapacity(capacity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            this.capacity = capacity;
        }
    }
}