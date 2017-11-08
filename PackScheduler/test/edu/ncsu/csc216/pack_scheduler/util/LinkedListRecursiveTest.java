/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Brian Wu, Noah Benveniste, Ben Gale
 *
 */
public class LinkedListRecursiveTest {

    /**
     * Initializes objects to be used throughout testing
     */
    @Before
    public void setUp() {
        list = new LinkedListRecursive<String>();
        i = null;
    }

    /**
     * Test for constructor
     */
    @Test
    public void testLinkedList() {
        assertEquals(0, list.size());
    }

    /**
     * Test for listIterator(int)
     */
    @Test
    public void testListIteratorInt() {
        //Try to create an iterator at an out of bounds index when the list is empty
        try {
            i = list.listIterator(1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertNull(i);
        }
        
        //Try to create an iterator when the list is empty
        i = list.listIterator(0);
        
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
    }

//    /**
//     * Test for size()
//     */
//    @Test
//    public void testSize() {
//        fail("Not yet implemented");
//    }

    /**
     * Test for add(int, E)
     */
    @Test
    public void testAddIntE() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        String s6 = "f";
        String s7 = "h";
        String s8 = "i";
        
        assertEquals(0, list.size());
        list.add(0, s1);
        assertEquals(1, list.size());
        list.add(1, s2);
        assertEquals(2, list.size());
        list.add(2, s3);
        assertEquals(3, list.size());
        list.add(3, s4);
        assertEquals(4, list.size());
        list.add(4, s5);
        assertEquals(5, list.size());
        
        //Try adding outside the size of the list
        try {
            list.add(-1, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.add(6, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try adding a null element
        try {
            list.add(4, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Cannot add null elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try adding a duplicate element
        try {
            list.add(5, s3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add duplicate elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Add to the front of the list
        list.add(0, s6);
        assertEquals(6, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s3, list.get(3));
        assertEquals(s4, list.get(4));
        assertEquals(s5, list.get(5));
        
        //Add to the middle of the list
        list.add(3, s7);
        assertEquals(7, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s7, list.get(3));
        assertEquals(s3, list.get(4));
        assertEquals(s4, list.get(5));
        assertEquals(s5, list.get(6));
        
        
        //Add to the back of the list
        list.add(7, s8);
        assertEquals(8, list.size());
        assertEquals(s6, list.get(0));
        assertEquals(s1, list.get(1));
        assertEquals(s2, list.get(2));
        assertEquals(s7, list.get(3));
        assertEquals(s3, list.get(4));
        assertEquals(s4, list.get(5));
        assertEquals(s5, list.get(6));
        assertEquals(s8, list.get(7));
    }

    /**
     * Test for remove(int) [calls the method from AbstractSequentialList, used to test inner classes]
     */
    @Test
    public void testRemoveInt() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
        
        //Try removing outside the size of the list
        try {
            list.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.remove(6);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s3, list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        
        //Remove from the middle
        list.remove(2);
        assertEquals(4, list.size());
        assertEquals(s1, list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s4, list.get(2));
        assertEquals(s5, list.get(3));
        
        //Remove from the front
        list.remove(0);
        assertEquals(3, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
        assertEquals(s5, list.get(2));
        
        //Remove from the back
        list.remove(2);
        assertEquals(2, list.size());
        assertEquals(s2, list.get(0));
        assertEquals(s4, list.get(1));
        
        list.remove(0);
        list.remove(0);
        assertEquals(0, list.size());
        
        //Try removing from an empty list
        try {
            list.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, list.size());
        }
    }

    /**
     * Test for set(int, E)
     */
    @Test
    public void testSetIntE() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
        
        //Try setting outside the size of the list
        try {
            list.set(-1, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        try {
            list.set(6, "f");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is outside of the acceptable range [0, size]", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try setting a null element
        try {
            list.set(4, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Cannot set an element to be null", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Try setting a duplicate element
        try {
            list.set(5, s3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot set duplicate elements", e.getMessage());
            assertEquals(5, list.size());
        }
        
        //Set at the front of the list
        list.set(0, "x");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals(s3, list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        assertEquals(5, list.size());
        
        //Set at the middle of the list
        list.set(2, "y");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals("y", list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals(s5, list.get(4));
        assertEquals(5, list.size());
        
        //Set at the end of the list
        list.set(4, "z");
        assertEquals("x", list.get(0));
        assertEquals(s2, list.get(1));
        assertEquals("y", list.get(2));
        assertEquals(s4, list.get(3));
        assertEquals("z", list.get(4));
        assertEquals(5, list.size());
    }
    
    /**
     * Tests the previous() method for the iterator inner class
     */
    @Test
    public void testIteratorPrevious() {
        //Add some elements to the list
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = "e";
        
        String[] arr = new String[5];
        arr[0] = s1;
        arr[1] = s2;
        arr[2] = s3;
        arr[3] = s4;
        arr[4] = s5;
        
        
        list.add(0, s1);
        list.add(1, s2);
        list.add(2, s3);
        list.add(3, s4);
        list.add(4, s5);
       
        //Creates a public instance of the LinkedListIterator to test methods on it directly
        Class<?> listIterator = null;
        try {
            listIterator = Class.forName("edu.ncsu.csc216.pack_scheduler.util.LinkedList$LinkedListIterator");
        } catch (ClassNotFoundException e) {
            fail("Class not found");
        }
        
        Method method = null;
        try {
            method = listIterator.getDeclaredMethod("previous");
        } catch (NoSuchMethodException | SecurityException e) {
            fail();
        }
        method.setAccessible(true);
        
        i = list.listIterator(3);
        
        try {
            System.out.println(method.invoke(i));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            fail();
        }
    }
    
    /**
     * Test indexOf() method
     */
    @Test
    public void testIndexOf() {
        list.add("orange");
        list.add("banana");
        list.add("apple");
        list.add("kiwi");
        
        assertEquals(0, list.indexOf("orange"));
    }
    
    /**
     * Test for lastIndexOf() method
     */
    @Test
    public void testLastIndexOf() {
        list.add("orange");
        list.add("banana");
        list.add("apple");
        list.add("kiwi");
        
        assertEquals(0, list.lastIndexOf("orange"));
    }

}
