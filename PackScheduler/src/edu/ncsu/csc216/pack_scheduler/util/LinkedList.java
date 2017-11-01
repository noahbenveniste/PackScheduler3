package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * 
 * @author Noah Benveniste
 * @author Brian Wu
 * @author Ben Gale
 * @param <E>
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

    /**
     * 
     */
    LinkedList() {
        
    }
    
    /**
     * 
     */
    @Override
    public ListIterator<E> listIterator(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * 
     * @author Noah Benveniste
     * @author Brian Wu
     * @author Ben Gale
     */
    private class ListNode {
        
    }
    
    /**
     * 
     * @author Noah Benveniste
     * @author Brian Wu
     * @author Ben Gale
     */
    private class LinkedListIterator implements ListIterator<E> {

        /**
         * 
         * @param n
         */
        public LinkedListIterator(int n) {
            
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
