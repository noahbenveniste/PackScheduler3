package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests sortedList
 * @author Boram Han, Jacob Myers, Dusting Hollar
 *
 */
public class SortedListTest {

    /**
     * Tests sorted list constructor
     */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		list.add("apple");
		list.add("orange");
		list.add("banana");
		list.add("grapefruit");
		list.add("grapes");
		list.add("kiwi");
		list.add("pickle");
		list.add("milk");
		list.add("tea");
		list.add("lemon");
		list.add("broom");
		assertEquals(11, list.size());
		
	}

	/**
	 * Tests .add() method for sortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		
		list.add("kiwi");
		assertEquals(3, list.size());
		assertEquals("kiwi", list.get(2));
		
		list.add("grape");
		assertEquals(4, list.size());
		assertEquals("grape", list.get(2));
		
		// Test adding a null element
		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		// Test adding a duplicate element
		try {
			list.add("apple");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
		
	}
	
	/**
	 * tests .get() for sortedList
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		try { 
		    list.get(0);
		    fail();
		} catch (IndexOutOfBoundsException e) {
		    assertNull(e.getMessage());
		}
		
		list.add("banana");
		list.add("apple");
		list.add("grapes");
		list.add("lemon");
		
		try {
		    list.get(-1);
		    fail();
		} catch (IndexOutOfBoundsException f) {
		    assertNull(f.getMessage());
		}
		
		try { 
            list.get(list.size());
            fail();
        } catch (IndexOutOfBoundsException g) {
            assertNull(g.getMessage());
        }
		
	}
	
	/**
	 * Tests .remove() in sortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
		    list.remove(0);
		    fail();
		}
		catch (IndexOutOfBoundsException e) {
		    assertNull(e.getMessage());
		}

		// Add some elements to the list
		list.add("banana");
		list.add("apple");
		list.add("grapes");
		list.add("lemon");

		// Test removing an element at an index < 0
		try {
		    list.remove(-1);
		    fail();
		}
		catch (IndexOutOfBoundsException e) {
		    assertNull(e.getMessage());
		}
		
		// Test removing an element at size
		try {
            list.remove(list.size());
            fail();
        }
        catch (IndexOutOfBoundsException e) {
            assertNull(e.getMessage());
        }
		
		// Test removing a middle element
		int midElement = (list.size() - 1) / 2;
		list.remove(midElement);
		assertEquals(3, list.size());
		
		// Test removing the last element
		list.remove(list.size() - 1 );
		assertEquals(2, list.size());
		
		// Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		
		// Test removing the last element
		list.remove(0);
        assertEquals(0, list.size());
	}
	
	/**
	 * test indexOf() for sortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
        assertEquals(-1, list.indexOf("jacob"));
        
        //Add some elements
        list.add("banana");
        list.add("apple");
        list.add("grapes");
        list.add("lemon");
        
        //Test various calls to indexOf for elements in the list
        //and not in the list
        assertEquals(1, list.indexOf("banana"));
        assertEquals(0, list.indexOf("apple"));
        assertEquals(2, list.indexOf("grapes"));
        assertEquals(3, list.indexOf("lemon"));

        // Test something not in list
        assertEquals(-1, list.indexOf("orange"));
        
        //Test checking the index of null
        try { 
            list.indexOf(null);
            fail();
        } catch (NullPointerException g) {
            assertNull(g.getMessage());
        }
		
	}
	
	/**
	 * test .clear() for sortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
        list.add("apple");
        list.add("grapes");
        list.add("orange");
        
        // Clear the list
        list.clear();
        
        // Test that the list is empty
        assertEquals(0, list.size());
	}

	/**
	 * test isEmpty() for sortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		list.isEmpty();
		assertEquals(0, list.size());

		// Add at least one element
		list.add("apple");
		list.add("banana");
		assertEquals(2, list.size());

		// Check that the list is no longer empty
		list.isEmpty();
		assertEquals(false, list.isEmpty());
		assertEquals(2, list.size());

	}

	/**
	 * Tests contains() for sortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
        assertFalse(list.contains("jacob"));

        // Add elements
        list.add("banana");
        list.add("apple");
        list.add("grapes");
        list.add("orange");

        //Test some true and false cases
        assertTrue(list.contains("banana"));
        assertTrue(list.contains("apple"));
        assertTrue(list.contains("grapes"));
        assertTrue(list.contains("orange"));
        assertFalse(list.contains("jacob"));
        assertFalse(list.contains("computer"));
        assertFalse(list.contains("table"));
        assertFalse(list.contains("desk"));
	}
	
	/**
	 * tests equals() for sortedList.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("banana");
        list1.add("apple");
        list1.add("grapes");
        list1.add("lemon");
        
        list2.add("banana");
        list2.add("apple");
        list2.add("grapes");
        list2.add("lemon");
        
        list3.add("broom");
        list3.add("desk");
        list3.add("table");
        list3.add("chair");
		
		// Test for equality and non-equality
        assertTrue(list1.equals(list2));
        assertFalse(list1.equals(list3));
	}
	
	/** Test hashCode for sortedList */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("banana");
        list1.add("apple");
        list1.add("grapes");
        list1.add("lemon");
        
        list2.add("banana");
        list2.add("apple");
        list2.add("grapes");
        list2.add("lemon");
        
        list3.add("broom");
        list3.add("desk");
        list3.add("table");
        list3.add("chair");
		
		// Test for the same and different hashCodes
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 