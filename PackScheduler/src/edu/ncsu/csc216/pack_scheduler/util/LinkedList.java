package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * 
 * @author Noah Benveniste
 * @author Brian Wu
 * @author Ben Gale
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
     * Gets the number of elements currently in the list
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * 
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
     * 
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
         * 
         * @param index the index of the element that would be retrieved by a call to next()
         * @throws IndexOutOfBoundsException
         */
        public LinkedListIterator(int index) {
            //Check for out of bounds index
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is outside of the acceptable range [0, size-1]");
            } else {
                //Iterate through the list so that previous points to node at index-1 and next points to node at index
                ListNode temp = front;
                
                //Set the indices
                nextIndex = index;
                previousIndex = index-1;
                
                //Initialize lastRetrieved to null
                lastRetrieved = null;
            }
        }
        
        /**
         * 
         */
        @Override
        public void add(E arg0) {
            // TODO Auto-generated method stub
            
        }

        /**
         * 
         */
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * 
         */
        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * 
         */
        @Override
        public E next() {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * 
         */
        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public E previous() {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * 
         */
        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        /**
         * 
         */
        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }

        /**
         * 
         */
        @Override
        public void set(E arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }

}
