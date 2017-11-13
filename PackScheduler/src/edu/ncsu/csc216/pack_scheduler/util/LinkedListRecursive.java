package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A linked list that uses recursion for its method implementations. Functions identically to the other lists
 * we've implemented from the client's perspective with some additional functionality, including the ability
 * to remove elements by entering the element to remove, rather than its index. Does not allow duplicate or
 * null elements.
 * @author Noah Benveniste
 * @author Brian Wu
 * @param E indicates that the list can work with any object or primitive type
 */
public class LinkedListRecursive<E> {

    /** Points to the front of the list */
    private ListNode front;
    /** The number of elements currently in the list */
    private int size;

    /**
     * Constructs an empty LinkedListRecursive
     */
    public LinkedListRecursive() {
        // Set the front field to point to null
        front = null;
        // Set the size to 0
        size = 0;
    }

    /**
     * Checks if the list contains a specified element. Handles the special case of
     * the list being empty. Otherwise, calls the method that handles recursion.
     * @param e the element to check the list for
     * @return true if the list contains the element, false otherwise.
     */
    public boolean contains(E e) {
        // Check if the list is empty
        if (front == null) {
            return false;
            // Call method that handles recursion
        } else {
            return front.contains(e);
        }
    }

    /**
     * Adds an element to the end of the list. Handles the special case of adding an element
     * to an empty list. Otherwise, calls the method that handles recursion.
     * @param e the element to add
     * @throws IllegalArgumentException if the element is already in the list
     * @throws NullPointerException if the element to add is null
     * @return true if the element was added
     */
    public boolean add(E e) {
        // Check if the list already contains the element being added
        if (this.contains(e)) {
            throw new IllegalArgumentException("Cannot add duplicate elements");
            // Check for null elements
        } else if (e == null) {
            throw new NullPointerException("Cannot add null elements");
        } else {
            // Special case of adding to an empty list
            if (front == null) {
                front = new ListNode(e, front);
                size++;
                return true;
                // Call method that handles recursion
            } else {
                front.add(e);
                return true;
            }
        }
    }

    /**
     * Adds an element at a specified index to the list. Handles the special case of adding
     * to the front of the list. Otherwise, calls the method that handles recursion.
     * @param index the index to add the element at
     * @param e the element to add
     * @throws IllegalArgumentException if the element is a duplicate of an element already in the list
     * @throws IndexOutOfBoundsException if the index is not on the range [0, size)
     * @throws NullPointerException if the element to add is null
     */
    public void add(int index, E e) {
        // Check if the list already contains the element being added
        if (this.contains(e)) {
            throw new IllegalArgumentException("Cannot add duplicate elements");
            // Index is out of bounds
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
            // Check for null elements
        } else if (e == null) {
            throw new NullPointerException("Cannot add null elements");
            // Adding to the front of the list
        } else if (index == 0) {
            // Create a new list node that points to what the current front points to
            ListNode newNode = new ListNode(e, front);
            // Make front point to the new node
            this.front = newNode;
            // Increment the size
            size++;
            // Adding to the end of the list
        } else if (index == size) {
            add(e);
            // Call method that handles recursion
        } else {
            front.add(index, e);
        }
    }

    /**
     * Gets the element at a specified index
     * @param index the index of the element
     * @return the element at the index
     * @throws IndexOutOfBoundsException if the index is not on the range [0, size)
     */
    public E get(int index) {
        // Check for out of bounds index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
            // Call method that handles recursion
        } else {
            return front.get(index);
        }
    }

    /**
     * Removes an element at a specified index.
     * @param index the index of the element to remove
     * @return the element data belonging to the removed element
     * @throws IndexOutOfBoundsException if the specified index is not on the range [0, size)
     */
    public E remove(int index) {
        // Check for out of bounds index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
            // Special case of removing the first node in the list
        } else if (index == 0) {
            // Get the old data
            E old = front.data;
            // Reassign front
            front = front.next;
            // Decrement the size
            size--;
            // Return the data
            return old;
            // Call method that handles recursion
        } else {
            return front.remove(index);
        }
    }

    /**
     * Removes a specified element from the list. Handles the special case of the element
     * to remove being the first element in the list. Otherwise, calls the method that 
     * handles recursion.
     * @param e the element to remove
     * @return true if the element was removed, false if it was not i.e. it was not in the list
     */
    public boolean remove(E e) {
        /**
        // Check if the element is null
        if (e == null) {
            throw new NullPointerException("Cannot remove null");
            // Check if the list is empty
        } else 
        */
        if (size == 0) {
            return false;
            // Check if the list doesn't contain the element
        } else if (!contains(e)) {
            return false;
            // Special case of removing the first element in the list
        } else if (front.data.equals(e)) {
            front = front.next;
            size--;
            return true;
            // Call method that handles recursion
        } else {
            return front.remove(e);
        }
    }

    /**
     * Sets an element at a specified index in the list
     * @param index the index of the element to set
     * @param e the element data to overwrite the specified index with
     * @return the data that was overwritten at the specified index
     * @throws IndexOutOfBoundsException if the index is not in the range [0, size)
     * @throws NullPointerException if the element to set is null
     * @throws IllegalArgumentException if the element to set is a duplicate of an element already in the list
     */
    public E set(int index, E e) {
        // Bounds checking
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
            // Check for null element being set
        } else if (e == null) {
            throw new NullPointerException("Cannot set null elements");
            // Check for duplicates
        } else if (this.contains(e)) {
            throw new IllegalArgumentException("Cannot set duplicate elements");
            // Call method that handles recursion if inputs are valid
        } else {
            return front.set(index, e);
        }
    }

    /**
     * Returns the number of elements in the list
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the list contains an elements
     * @return true if the list empty, false if it is not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * A list node that composes a linked list
     * @author Noah Benveniste
     * @author Brian Wu
     */
    private class ListNode {

        /** Stores an element that the node represents */
        private E data;
        /** A pointer to the next node in the list */
        private ListNode next;

        /**
         * Constructs a list node with a specified data value and a pointer to another node
         * @param data the element that the node represents
         * @param next a pointer to the next node in the list
         */
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Checks if the list contains a specified element recursively
         * @param e the element to check the list for
         * @return true if the list contains e, false otherwise
         */
        private boolean contains(E e) {
            // If the node we're at contains the data, return true
            if (data.equals(e)) {
                return true;
                // Base case where the end of the list is reached without finding the element
            } else if (next == null) {
                return false;
                // If not, recursive call on the next node
            } else {
                return next.contains(e);
            }
        }

        /**
         * Adds an element to the end of the list recursively
         * @param e the element to add
         */
        private void add(E e) {
            // Base case where the end of the list is reached, just add the node
            if (next == null) {
                next = new ListNode(e, null);
                size++;
                // If the end of the list hasn't been reached, recursive call
            } else {
                next.add(e);
            }
        }

        /**
         * Adds an element at a specified index recursively using a look ahead
         * algorithm by decrementing the passed index before each recursive method call
         * @param index the index where the new element is being added
         * @param e the element to add
         */
        private void add(int index, E e) {
            // Reached the node just before the index we want to add at
            if (index == 1) {
                // Make the new node point to this node's next field
                ListNode newNode = new ListNode(e, next);
                // Make this node point to the new node
                next = newNode;
                // Increment the size
                size++;
                // Recursive call
            } else {
                index--;
                next.add(index, e);
            }
        }

        /**
         * Gets an element at a specified index recursively
         * @param index the index of the element to get
         * @return the element data at the specified index
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
         * Recursively removes an element at a specified index from the list using a
         * look ahead algorithm by decrementing the passed index before each recursive
         * method call.
         * @param index the index of the element to remove
         * @return the element data at the index of the removed element
         */
        private E remove(int index) {
            // At the element just before the element to remove
            if (index == 1) {
                // Store the data to be removed
                E oldData = next.data;
                // Make this node point to the node that remove element pointed to
                this.next = this.next.next;
                // Decrement the size
                size--;
                // Return the old data
                return oldData;
                // Recursive call
            } else {
                index--;
                return next.remove(index);
            }
        }

        /**
         * Recursively removes a specified element from the list using a look ahead
         * algorithm
         * @param e the element to remove
         * @return true if the element was removed, false if it wasn't i.e. the element wasn't in the list
         */
        private boolean remove(E e) {
            // Check if the next node in the list is null, meaning the end of the list has
            // been reached
            if (next == null) {
                return false;
                // Look ahead to see if the data in the next node is the data we want to remove
            } else if (next.data.equals(e)) {
                // Make this node point to the node that remove element pointed to
                this.next = this.next.next;
                // Decrement the size
                size--;
                return true;
                // Recursive call
            } else {
                return next.remove(e);
            }
        }

        /**
         * Sets an element at a specified index recursively
         * @param index the index of the element to set
         * @param e the element to set
         * @return the old element data at the specified index
         */
        private E set(int index, E e) {
            // Reached the node we want to set at
            if (index == 0) {
                // Store the old data, overwrite the data field with the input data, return the
                // old data
                E oldData = this.data;
                this.data = e;
                return oldData;
                // Recursive call if we haven't reached the desired index
            } else {
                // Decrement index, pass new value to recursive method call
                index--;
                return next.set(index, e);
            }
        }

    }

}
