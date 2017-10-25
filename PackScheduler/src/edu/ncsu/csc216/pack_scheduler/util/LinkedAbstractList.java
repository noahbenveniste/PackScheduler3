package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom LinkedList array that doesn't allow null elements or duplicates and
 * has a capacity.
 * 
 * @author Brian Wu, Dustin Hollar, Hubert Ngo
 * 
 * @param <E> the type of elements the list contains
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

    /** The first node in the list */
    ListNode front;
    /** The size of the list */
    int size;
    /** The max capacity of the list */
    int capacity;
    
    /**
     * Creates a new LinkedAbstractList with a capacity
     * 
     * @param capacity the max capacity of the list
     * @throws IllegalArgumentException if the capacity is less than 0
     */
    public LinkedAbstractList (int capacity){
        front = null;
        size = 0;
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid capacity.");
        }
        this.capacity = capacity;
    }
    
    /**
     * Sets the new capacity of the list
     * 
     * @param capacity the new capacity of the list
     */
    public void setCapacity (int capacity) {
        this.capacity = capacity;    
    }
    
    /**
     * Adds a new element at a specified index.
     * 
     * @param index the index to add the element at
     * @param element the element to add
     * @throws IllegalArgumentException
     *              if the list has reached capacity or there is a duplicate element
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void add(int index, E element) {
        if (size == capacity) {
            throw new IllegalArgumentException("List has reached capacity.");
        }
        
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        
        if (element == null) {
            throw new NullPointerException();
        }
        
        if (index == 0) {
            ListNode newNode = new ListNode(element, null);
            if (size == 0) {
                front = newNode;
            } else {
                newNode.next = front;
                front = newNode;
            }
        } else { 
            ListNode temp = front;
            while (temp.next != null) {
                if (temp.data.equals(element)) {
                    throw new IllegalArgumentException("Duplicate element.");
                }
                temp = temp.next;
            }
            
            temp = front;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            ListNode newNode = new ListNode(element);
            ListNode nextNode = temp.next;
            temp.next = newNode;
            newNode.next = nextNode;
        }
        size++;
    }
    
    /**
     * Removes the element at the specified index and returns the old element
     * 
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        
        ListNode temp = front;
        E old = null;
        if (index == 0) {
            old = temp.data;
            front = temp.next;
        } else if (size == 1) {
            old = front.data;
            front = null;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            old = temp.next.data;
            temp.next = temp.next.next;
        }
        size--;
        return old;
    }
    
    /**
     * Sets the element at the specified index to a new value and returns the old value
     * 
     * @param index the index of the element to change
     * @param element the element to replace with
     * @return the element replaced
     * @throws IllegalArgumentException if there is a duplicate element
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        
        if (element == null) {
            throw new NullPointerException();
        }
        
        ListNode temp = front;
        while (temp.next != null) {
            if (temp.data.equals(element)) {
                throw new IllegalArgumentException("Duplicate element.");
            }
            temp = temp.next;
        }
        
        temp = front;
        E old = temp.data;
        if (index == 0) {
            temp.data = element;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            old = temp.next.data;
            temp.next.data = element;
        }
        return old;
    }
    
    /**
     * Gets the element at the specified index
     * 
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        
        ListNode temp = front;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        
        return temp.data;
    }

    /**
     * Returns the size of the list
     * 
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Sets the capacity for the list
     * 
     * @throws IllegalArguementException if the capacity is less than 0 or less than the size
     * @return the list capacity
     */
    public void setCapacity(int capacity) {
    	if (capacity < 0 || capacity < size)
    		throw new IllegalArguementException("The capacity must be greater than 0 and greater than the size");
    	this.capacity = capacity;
    }
    
    /**
     * Inner class of LinkedAbstractList that represent each element in the list.
     * Each element stores data and a reference to the next node
     * 
     * @author Brian Wu, Dustin Hollar, Hubert Ngo
     */
    private class ListNode {
        
        /** The data of the node */
        E data;
        /** The reference to the next node */
        ListNode next;
        
        /**
         * Constructs a new ListNode with given data
         * 
         * @param data the data to store
         */
        public ListNode(E data) {
            this.data = data;
        }
        
        /**
         * Constructs a new ListNode with given data and reference to the next node
         * 
         * @param data the data to store
         * @param next the reference to the next node
         */
        @SuppressWarnings("unused")
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}
