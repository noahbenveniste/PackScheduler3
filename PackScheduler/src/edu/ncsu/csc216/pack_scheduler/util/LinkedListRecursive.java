package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A linked list that uses recursion for its method implementations.
 * @author Noah Benveniste
 * @author Brian Wu
 */
public class LinkedListRecursive<E> {
    
    //THROWAWAY COMMENT
    
    /** */
    private ListNode front;
    /** */
    private int size;

    /**
     * Constructs an empty LinkedListRecursive
     */
    public LinkedListRecursive() {
        //Set the front field to point to null
        front = null;
        //Set the size to 0
        size = 0;
    }
    
    /**
     * 
     * @param e
     * @return
     */
    public boolean contains(E e) {
        //Check if the list is empty
        if (front == null) {
            return false;
        //Call method that handles recursion
        } else {
            return front.contains(e);
        }
    }
    
    /**
     * Adds an element to the end of the list
     * @param e
     * @throws IllegalArgumentException
     * @throws NullPointerException if the element to add is null
     * @return
     */
    public boolean add(E e) {
        //Check if the list already contains the element being added
        if (this.contains(e)) {
            throw new IllegalArgumentException("Cannot add duplicate elements");
        //Check for null elements
        } else if (e == null) {
            throw new NullPointerException("Cannot add null elements");
        } else {
            //Special case of adding to an empty list
            if (front == null) {
                front = new ListNode(e, front);
                size++;
                return true;
            //Call method that handles recursion
            } else {
                front.add(e);
                return true;
            }
        }
    }
    
    /**
     * 
     * @param index
     * @param e
     * @throws IllegalArgumentException
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException if the element to add is null
     */
    public void add(int index, E e) {
        //Check if the list already contains the element being added
        if (this.contains(e)) {
            throw new IllegalArgumentException("Cannot add duplicate elements");
        //Index is out of bounds
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
        //Check for null elements
        } else if (e == null) {
            throw new NullPointerException("Cannot add null elements");
        //Adding to the end of the list
        } else if (index == size) {
            add(e);
        //Call method that handles recursion
        } else {
            front.add(index, e);
        }
    }
    
    /**
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public E get(int index) {
        //Check for out of bounds index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
        //Call method that handles recursion
        } else {
            return front.get(index);
        }
    }
    
    /**
     * Removes an element at a specified index
     * @param index
     * @return
     */
    public E remove(int index) {
        //Check for out of bounds index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
        //Special case of removing the first node in the list
        } else if (index == 0) {
            //Get the old data
            E old = front.data;
            //Reassign front
            front = front.next;
            //Decrement the size
            size--;
            //Return the data
            return old;
        //Call method that handles recursion
        } else {
            return front.remove(index);
        }
    }
    
    /**
     * Removes a specified element
     * @param e
     * @return
     */
    public boolean remove(E e) {
        //Check if the element is null
        if (e == null) {
            throw new NullPointerException("Cannot remove null");
        //Check if the list is empty
        } else if (size == 0) {
            return false;
        //Check if the list doesn't contain the element
        } else if (!contains(e)) {
            return false;
        //Special case of removing the first element in the list
        } else if ((front.data).equals(e)) {
            front = front.next;
            size--;
            return true;
        //Call method that handles recursion
        } else {
            return front.remove(e);
        }
    }
    
    /**
     * 
     * @param index
     * @param e
     * @return
     */
    public E set(int index, E e) {
        return null;
    }
    
    /**
     * 
     * @return
     */
    public int size() {
        return size;
    }
    
    /**
     * 
     * @author Noah Benveniste
     * @author Brian Wu
     */
    private class ListNode {
        
        /** */
        private E data;
        /** */
        private ListNode next;
        
        /**
         * 
         * @param data
         * @param next
         */
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * 
         * @param e
         * @return
         */
        private boolean contains(E e) {
            //If the node we're at contains the data, return true
            if ((data).equals(e)) {
                return true;
            //Base case where the end of the list is reached without finding the element
            } else if (next == null){
                return false;
            //If not, recursive call on the next node
            } else {
                return (next).contains(e);
            }
        }
        
        /**
         * 
         * @param e
         */
        private void add(E e) {
            //Base case where the end of the list is reached, just add the node
            if (next == null) {
                next = new ListNode(e, null);
                size++;
            //If the end of the list hasn't been reached, recursive call
            } else {
                next.add(e);
            }
        }
        
        /**
         * 
         * @param index
         * @param e
         */
        private void add(int index, E e) {
            //Reached the node just before the index we want to add at
            if (index == 1) {
                //Make the new node point to this node's next field
                ListNode newNode = new ListNode(e, next);
                //Make this node point to the new node
                next = newNode;
            //Recursive call
            } else {
                index--;
                next.add(index, e);
            }
        }
        
        /**
         * 
         * @param index
         * @return
         */
        private E get(int index) {
            if (index == 0) {
                return data;
            } else {
                index--;
                return next.get(index);
            }
        }
        
        /**
         * Recursively removes an element at a specified index from the list using a lookahead
         * technique by decrementing the passed index before each recursive method call.
         * @param index
         * @return
         */
        private E remove(int index) {
            //At the element just before the element to remove
            if (index == 1) {
               //Store the data to be removed
               E oldData = next.data;
               //Make this node point to the node that remove element pointed to
               this.next = this.next.next;
               //Decrement the size
               size--;
               //Return the old data
               return oldData;
            //Recursive call
            } else {
                index--;
                return next.remove(index);
            }
        }
        
        /**
         * 
         * @param e
         * @return
         */
        private boolean remove(E e) {
            return false;
        }
        
        /**
         * 
         * @param index
         * @param e
         * @return
         */
        private E set(int index, E e) {
            return null;
        }
        
    }

}
