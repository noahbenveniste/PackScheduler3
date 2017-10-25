/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

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
    
    public LinkedStack (int capacity) {
        
    }

    @Override
    public void push(E element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public E pop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCapacity(int capacity) {
        try {
            list.setCapacity(capacity); 
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }
    


   
}
