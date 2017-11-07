package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class for a doubly linked list that uses an iterator for traversal.
 * @author Noah Benveniste
 * @param <E> indicates that the list can be used with any element type
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
    
    /** The number of nodes in the list */
    private int size;
    /** Points to the first node in the list */
    private ListNode front;
    /** Points to the last node in the list */
    private ListNode back;

    /**
     * Constructs an empty LinkedList
     */
   public LinkedList() {
        //Initialize nodes that represent out of bounds indices of -1 and size
        front = new ListNode(null);
        back = new ListNode(null);
        
        //Make front.next point to back
        front.next = back;
        //Make back.prev point to front
        back.prev = front;
        
        //Initialize size to zero
        size = 0;
    }
    
    /**
     * Used by the client to get a list iterator
     * @param index the index for the iterator
     * @return a list iterator for the list
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return (ListIterator<E>) new LinkedListIterator(index);
    }
    
    /**
     * Adds an element to the specified index in the list
     * @throws IllegalArgumentException if the element to add is a duplicate of an element
     * already in the list
     */
    @Override
    public void add(int index, E element) {
        //Check that the element to add isn't a duplicate
        if (contains(element)) {
            throw new IllegalArgumentException("Cannot add duplicate elements");
        } else {
            super.add(index, element);
        }
    }

    /**
     * Sets an element at a specified element in the list
     * @throws IllegalArgumentException if the element to set is a duplicate of an element
     * already in the list
     */
    @Override
    public E set(int index, E element) {
        if (contains(element)) {
            throw new IllegalArgumentException("Cannot set duplicate elements");
        } else {
            return super.set(index, element);
        }
    }

    /**
     * Gets the number of elements currently in the list
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Class for a list node object that makes up a doubly linked list. The ListNode
     * stores state for the data that it holds as well as references to the next and
     * previous node in the list.
     * @author Noah Benveniste
     * @author Brian Wu
     * @author Ben Gale
     */
    private class ListNode {
        
        /** The data stored in the node */
        private E data;
        /** The previous node in the list */
        private ListNode prev; 
        /** The next node in the list */
        private ListNode next;
        
        /**
         * Updates the listnode information
         * 
         * @param data
         * @param prev
         * @param next
         */
        private ListNode(E data, ListNode prev, ListNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        
        /**
         * Constructs a node with null next and previous pointers
         * @param data
         */
        private ListNode(E data) {
            this(data, null, null);
        }
        
    }
    
    /**
     * Class for a list iterator that is used to traverse the linked list. The list iterator
     * exists between two nodes in the list and stores state about the next and previous nodes
     * and their respective indices as well as the last node retrieved by any method calls.
     * @author Noah Benveniste
     * @author Brian Wu
     * @author Ben Gale
     */
    private class LinkedListIterator implements ListIterator<E> {

        /** The previous node in the list */
        private ListNode previous;
        /** The next node in the list */
        private ListNode next;
        /** The index of the previous node */
        private int previousIndex;
        /** The index of the next node */
        private int nextIndex;
        /** The last node retrieved via a call to previous() or next() */
        private ListNode lastRetrieved;
        
        /**
         * Creates a LinkedListIterator and positions it between index-1 and index
         * @param index the index of the element that would be retrieved by a call to next()
         * @throws IndexOutOfBoundsException
         */
        public LinkedListIterator(int index) {
            //Check for out of bounds index
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is outside of the acceptable range [0, size]");
            } else {
                //Iterate through the list so that previous points to node at index-1 and next points to node at index
                ListNode temp = front;
                for (int i = -1; i < index; i++) {
                    temp = temp.next;
                }
                this.next = temp;
                this.previous = temp.prev;
                
                //Set the indices
                nextIndex = index;
                previousIndex = index - 1;
                
                //Initialize lastRetrieved to null
                lastRetrieved = null;
            }
        }
        
        /**
         * Adds an element between the previous and next indices. The element added
         * becomes the element that would be returned by a subsequent call to previous()
         * and the element that would be returned by next() is unaffected
         * @throws NullPointerException if the element to add is null
         */
        @Override
        public void add(E element) {
            if (element == null) {
                throw new NullPointerException("Cannot add null elements");
            }
            //Create a node for the new data
            ListNode newNode = new ListNode(element);
            
            //Make the newly added element point to the next element and the previous element
            newNode.next = this.next;
            newNode.prev = this.previous;
            
            //Make the previous element's next field point to the new element
            this.previous.next = newNode;
            
            //Make the next element's previous field point to the new element
            this.next.prev = newNode;
            
            //Make the previous field of the iterator point to the newly added element
            this.previous = newNode;
            
            //Update the list iterator's other fields
            size++;
            nextIndex++;
            previousIndex++;
            lastRetrieved = null;
        }

        /**
         * Checks if the list has a next element
         * @return true if it does, false otherwise
         */
        @Override
        public boolean hasNext() {
            //Check if the next node contains non-null data
            return (this.next.data != null);
        }

        /**
         * Checks if the list has a previous element
         * @return true if it does, false otherwise
         */
        @Override
        public boolean hasPrevious() {
            //Check if the previous node contains non-null data
            return (this.previous.data != null);
        }

        /**
         * Gets the next element in the list and moves the iterator ahead an index
         * @return the next element in the list
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            //Get the element to retrieve
            E e = this.next.data;
            
            //Update the lastRetrieved field
            lastRetrieved = this.next;
            
            //Move the iterator to the next place in the list
            this.previous = next;
            this.next = this.next.next;
            
            //Increment the index
            nextIndex++;
            previousIndex++;
            
            //Return the retrieved element
            return e;
        }

        /**
         * Gets the index of the next element
         * @return the index of the next element
         */
        @Override
        public int nextIndex() {
            return this.nextIndex;
        }

        /**
         * Gets the previous element in the list and moves the iterator back an index
         * @return the previous element in the list
         * @throws NoSuchElementException if there is no previous element
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("No previous elements in the list");
            }
            //Get the element to retrieve
            E e = this.previous.data;
            
            //Update the lastRetrieved field
            lastRetrieved = this.previous;
            
            //Move the iterator to the next place in the list
            this.next = this.previous;
            this.previous = this.previous.prev;
            
            //Decrement the index
            nextIndex--;
            previousIndex--;
            
            //Return the retrieved element
            return e;
        }

        /**
         * Gets the index of the previous element
         * @return the index of the previous element
         */
        @Override
        public int previousIndex() {
            return this.previousIndex;
        }

        /**
         * Removes the element last returned by a call to next() or previous()
         * @throws IllegalStateException if this method is called directly after a call
         * to add(E) or remove(), or a call to next() or previous() has not been made,
         * meaning the lastRetrieved field is null
         */
        @Override
        public void remove() {
            if (this.lastRetrieved == null) {
                throw new IllegalStateException("remove() cannot be called directly after a call to add(E) or remove() "
                        + "or if a call to next() or previous() has not been made");
            } 
            
            //Make the node that lastRetrieved's previous field pointed to have its next field point to the node that lastRetrieved's
            //next field pointed to
            lastRetrieved.prev.next = lastRetrieved.next;
            
            //Make the node that lastRetrieved's next field point to have its prev field point to the node that lastRetrieved's
            //prev field pointed to
            lastRetrieved.next.prev = lastRetrieved.prev;
            
            //If next() was called, lastRetrieved points to ListIterator.previous
            //In this case, make the iterator's previous field point to the prev of whatever next points to
            if (previous.data.equals(lastRetrieved.data)) {
                previous = next.prev;
            //If previous() was called, lastRetrieved points to ListIterator.next
            //In this case, make the iterator's next field point to the next of whatever previous points to
            } else if (next.data.equals(lastRetrieved.data)) {
                next = previous.next;
            //This should never be thrown, only used for debugging
            } else {
                throw new IllegalArgumentException("Error when reassigning list iterator fields after removal");
            }
            
            //Update the other fields
            size--;
            nextIndex--;
            previousIndex--;
            lastRetrieved = null;
        }

        /**
         * Sets the element last returned by a call to next() or previous()
         * @throws IllegalStateException if this method is called directly after a call
         * to add(E) or remove(), or a call to next() or previous() has not been made,
         * meaning the lastRetrieved field is null
         * @throws NullPointerException if the element to set is null
         */
        @Override
        public void set(E element) {
            if (this.lastRetrieved == null) {
                throw new IllegalStateException("set(E) cannot be called directly after a call to add(E) or remove() "
                        + "or if a call to next() or previous() has not been made");
            } else if (element == null) {
                throw new NullPointerException("Cannot set an element to be null");
            } else {
                this.lastRetrieved.data = element;
            }
        }
        
    }
    
}
