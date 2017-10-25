/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the customized array list
 * 
 * @author Bwu98, Hubert Ngo, Dustin Hollar
 *
 */
public class ArrayListTest {

    /**
     * Tests the add function of the arrayList
     */
    @Test
    public void testArrayListAdd() {
        ArrayList<String> l = new ArrayList<String>();
        assertEquals(0, l.size());

        // Tests to make sure you can't add null
        try {
            l.add(0, null);
            fail("Shouldn't be able to add null object");
        } catch (NullPointerException e) {
            assertEquals(0, l.size());
        }

        // Testing adding from beginning
        l.add(0, "Zero");
        assertEquals(1, l.size());
        assertEquals("Zero", l.get(0));

        // Testing adding from end
        l.add(1, "One");
        assertEquals(2, l.size());
        assertEquals("Zero", l.get(0));
        assertEquals("One", l.get(1));

        // Testing adding from end
        l.add(2, "Three");
        assertEquals(3, l.size());
        assertEquals("Zero", l.get(0));
        assertEquals("One", l.get(1));
        assertEquals("Three", l.get(2));

        // Testing adding from the middle
        l.add(2, "Two");
        assertEquals(4, l.size());
        assertEquals("Zero", l.get(0));
        assertEquals("One", l.get(1));
        assertEquals("Two", l.get(2));
        assertEquals("Three", l.get(3));

        // Tests to make sure you can't add duplicates
        try {
            l.add(4, "Three");
            fail("Shouldn't be able to add duplicate object");
        } catch (IllegalArgumentException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't add with index out of range
        try {
            l.add(7, "Seven");
            fail("Shouldn't be able to add objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't add with index out of range
        try {
            l.add(-1, "Seven");
            fail("Shouldn't be able to add objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Make sure the array list grows properly
        l.add(4, "A");
        l.add(5, "B");
        l.add(6, "C");
        l.add(7, "D");
        l.add(8, "Q");
        l.add(9, "E");
        l.add(10, "F");
        assertEquals("Q", l.get(8));

    }

    /**
     * Tests the remove function of the arrayList
     */
    @Test
    public void testArrayListRemove() {
        ArrayList<String> l = new ArrayList<String>();
        l.add(0, "Zero");
        l.add(1, "One");
        l.add(2, "Two");
        l.add(3, "Three");

        // Tests to make sure you can't remove with index out of range
        try {
            l.remove(7);
            fail("Shouldn't be able to add objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't remove with index out of range
        try {
            l.remove(-1);
            fail("Shouldn't be able to add objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Test remove from beginning
        assertEquals("Zero", l.remove(0));
        assertEquals(3, l.size());
        assertEquals("One", l.get(0));
        assertEquals("Two", l.get(1));
        assertEquals("Three", l.get(2));

        // Test remove from the middle
        assertEquals("Two", l.remove(1));
        assertEquals(2, l.size());
        assertEquals("One", l.get(0));
        assertEquals("Three", l.get(1));

        // Test remove from the end
        assertEquals("Three", l.remove(1));
        assertEquals(1, l.size());
        assertEquals("One", l.get(0));

    }

    /**
     * Tests the set function of the arrayList
     */
    @Test
    public void testArrayListSet() {
        ArrayList<String> l = new ArrayList<String>();
        l.add(0, "Zero");
        l.add(1, "One");
        l.add(2, "Two");
        l.add(3, "Three");

        // Tests to make sure you can't set null
        try {
            l.set(3, null);
            fail("Shouldn't be able to set null object");
        } catch (NullPointerException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't set duplicates
        try {
            l.set(2, "Three");
            fail("Shouldn't be able to set duplicate object");
        } catch (IllegalArgumentException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't set with index out of range
        try {
            l.set(7, "Seven");
            fail("Shouldn't be able to set objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't set with index out of range
        try {
            l.set(-1, "Seven");
            fail("Shouldn't be able to set objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Test set in beginning
        assertEquals("Zero", l.set(0, "HI"));

        // Test set in the end
        assertEquals("Three", l.set(3, "HI!"));

        // Test set in the middle
        assertEquals("One", l.set(1, "HI!!"));

    }

    /**
     * Tests the get function of the arrayList
     */
    @Test
    public void testArrayListGet() {
        ArrayList<String> l = new ArrayList<String>();
        l.add(0, "Zero");
        l.add(1, "One");
        l.add(2, "Two");
        l.add(3, "Three");

        // Tests to make sure you can't get with index out of range
        try {
            l.get(-1);
            fail("Shouldn't be able to get objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }

        // Tests to make sure you can't get with index out of range
        try {
            l.get(7);
            fail("Shouldn't be able to get objects with index out of range");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(4, l.size());
            assertEquals("Zero", l.get(0));
            assertEquals("One", l.get(1));
            assertEquals("Two", l.get(2));
            assertEquals("Three", l.get(3));
        }
    }

}
