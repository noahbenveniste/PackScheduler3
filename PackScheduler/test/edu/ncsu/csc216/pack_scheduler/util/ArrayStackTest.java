/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * @author Bwu98
 *
 */
public class ArrayStackTest {

    @Test
    public void test() {
        ArrayStack<Integer> a = new ArrayStack<Integer>(5);
        assertEquals(0, a.size());
        
        // Tests add single element to stack
        a.push(1);
        assertEquals(1, a.size());
        
        // Tests add multiple elements to stack
        a.push(2);
        a.push(3);
        assertEquals(3, a.size());
        
        // Tests set capacity to less than size
        try {
            a.setCapacity(1);
            fail("Shouldn't be able to set capacity to less than size.");
        } catch (IllegalArgumentException e) {
            assertEquals(3, a.size());
        }
        
        // Tests set capacity to negative number
        try {
            a.setCapacity(-1);
            fail("Shouldn't be able to set capacity to less than size.");
        } catch (IllegalArgumentException e) {
            assertEquals(3, a.size());
        }
        
        a.setCapacity(3);
        
        try {
            a.push(4);
            fail("Capacity has been reached.");
        } catch (IllegalArgumentException e) {
            assertEquals(3, a.size());
        }
        
        // Tests removes single element to stack
        int n1 = a.pop();
        assertEquals(3, n1);
        assertEquals(2, a.size());
        
        // Tests removes multiple elements to stack
        int n2 = a.pop();
        assertEquals(2, n2);
        assertEquals(1, a.size());
        int n3 = a.pop();
        assertEquals(1, n3);
        assertEquals(0, a.size());
        
        // Tests removes when stack is empty
        try {
            a.pop();
            fail("Can't pop from empty stack.");
        } catch (EmptyStackException e) {
            assertEquals(0, a.size());
        }
    }

}
