package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 *
 */
public class ArrayBasedListTest {

	/** List for testing ArrayBasedList class */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "abc"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "abc"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        
        list.add(1, "four");
        assertEquals(4, list.size());
        assertEquals("one", list.get(0));
        assertEquals("four", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("three", list.get(3));
        
        list.add(0, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("two", list.get(3));
        assertEquals("three", list.get(4));
        
        list.add(3, "six");
        assertEquals(6, list.size());
        assertEquals("five", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("six", list.get(3));
        assertEquals("two", list.get(4));
        assertEquals("three", list.get(5));
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("four", list.get(3));
        assertEquals("five", list.get(4));
        assertEquals(5, list.size());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.last());
    	list.addLast("one");
    	assertEquals("one", list.last());
    	list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertEquals("five", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	list.addFirst("one");
    	assertEquals("one", list.get(0));
        list.addFirst("two");
        list.addFirst("three");
        list.addFirst("four");
        list.addFirst("five");
        assertEquals("five", list.get(0));
        assertEquals("four", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("two", list.get(3));
        assertEquals("one", list.get(4));
        assertEquals(5, list.size());
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.first());
    	list.addFirst("one");
    	assertEquals("one", list.first());
    	list.addFirst("two");
        list.addFirst("three");
        assertEquals("three", list.first());
        list.addFirst("four");
        list.addFirst("five");
        assertEquals("five", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        it.remove();
        assertEquals(0, list.size());
        
        list.addLast("two");
        assertTrue(it.hasNext());
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        
        it = list.iterator();
        list.addFirst("one");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        list.addLast("six");
        
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("four", list.get(3));
        assertEquals("five", list.get(4));
        assertEquals("six", list.get(5));
        assertEquals(6, list.size());
        
        assertEquals("one", it.next());
        assertEquals("two", it.next());
        it.remove();
        
        assertEquals("one", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("five", list.get(3));
        assertEquals("six", list.get(4));
        assertEquals(5, list.size());
        
        assertEquals("three", it.next());
        it.remove();
        assertEquals("four", it.next());
        assertEquals("five", it.next());
        assertTrue(it.hasNext());
        assertEquals("six", it.next());
        assertFalse(it.hasNext());
        it.remove();
        
        assertEquals("one", list.get(0));
        assertEquals("four", list.get(1));
        assertEquals("five", list.get(2));
        assertEquals(3, list.size());
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        list.addLast("one");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertEquals("one", list.remove(0));
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
        assertEquals("two", list.remove(1));
        assertEquals("four", list.remove(2));
        assertEquals("three", list.remove(1));
        assertEquals("five", list.remove(1));
        assertEquals("one", list.remove(0));
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertEquals("one", list.removeFirst());
        assertEquals("two", list.removeFirst());
        assertEquals("three", list.removeFirst());
        assertEquals("four", list.removeFirst());
        assertEquals("five", list.removeFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertEquals("five", list.removeLast());
        assertEquals("four", list.removeLast());
        assertEquals("three", list.removeLast());
        assertEquals("two", list.removeLast());
        assertEquals("one", list.removeLast());
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "one"));
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        list.addLast("five");
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "six"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "six"));
        list.set(0, "six");
        list.set(1, "seven");
        list.set(2, "eight");
        list.set(3, "nine");
        list.set(4, "ten");
        list.set(4, "eleven");
        assertEquals("six", list.get(0));
        assertEquals("seven", list.get(1));
        assertEquals("eight", list.get(2));
        assertEquals("nine", list.get(3));
        assertEquals("eleven", list.get(4));
    }
}
