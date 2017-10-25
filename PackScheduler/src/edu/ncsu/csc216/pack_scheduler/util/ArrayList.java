package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Generic ArrayList class that doesn't allow null or duplicate elements
 * 
 * @author Hubert Ngo, Dustin Hollar, Brian Wu
 * @param <E> generic element type
 */
public class ArrayList<E> extends AbstractList<E> {
    /** Initial list size */
    private static final int INIT_SIZE = 10;
    /** The list of elements */
    private E[] list;
    /** The number of elements */
    private int size;
    
    /**
     * Constructs a new generic list with no elements
     */
    @SuppressWarnings("unchecked")
    public ArrayList () {
        size = 0;
        list = (E[]) (new Object[INIT_SIZE]);
    }
    
    /**
     * Adds an element at the specified index
     * 
     * @param index the index to add the element
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NullPointerException if the element is null
     * @throws IllegalArgumentException if the element is a duplicate
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                throw new IllegalArgumentException();
            }
        }
        size++;
        if (size > list.length) {
            growArray();
        }
        for (int i = size - 1; i > index; i--){
            list[i] = list[i - 1];
        }
        list[index] = element;
    }
    
    /**
     * Removes an element at the specified index
     * 
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        E element = list[index];
        for (int i = index; i < size; i++){
            list[i] = list[i + 1];
        }
        list[size] = (E) (new Object());
        return element;
    }
    
    /**
     * Sets an element at the specified index to a new element
     * 
     * @param index the index to set the element
     * @param element the element to replace
     * @return the element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NullPointerException if the element is null
     * @throws IllegalArgumentException if the element is a duplicate
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                throw new IllegalArgumentException();
            }
        }
        E oldElement = list[index];
        list[index] = element;
        return oldElement;
    }
    
    /**
     * Returns the element at the index
     * 
     * @param index the index of the element to get
     * @return the element to get
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
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
     * Grows the list by 1
     */
    private void growArray() {
        int newSize = list.length + 1;
        @SuppressWarnings("unchecked")
        E[] newList = (E[]) (new Object[newSize]);
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
}
