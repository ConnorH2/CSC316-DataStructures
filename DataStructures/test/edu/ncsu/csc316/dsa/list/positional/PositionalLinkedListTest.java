package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {

	/** PositionalList object for testing PositionalLinkedList */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> newFirst = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(newFirst, list.first());
        
        Position<String> last = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(newFirst, list.first());
        
        list.remove(first);
        list.remove(newFirst);
        list.remove(last);
        
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        Position<String> newFirst = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(first, list.last());
        
        Position<String> last = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(last, list.last());
        
        list.remove(first);
        list.remove(newFirst);
        list.remove(last);
        
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
        Position<String> first2 = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(first2, list.first());
        
        Position<String> first3 = list.addFirst("three");
        Position<String> first4 = list.addFirst("four");
        Position<String> first5 = list.addFirst("five");
        
        assertEquals(first5, list.first());
        assertEquals(first4, list.after(first5));
        assertEquals(first3, list.after(first4));
        assertEquals(first2, list.after(first3));
        assertEquals(first, list.after(first2));
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        Position<String> two = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(two, list.last());
        
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        Position<String> last = list.addLast("five");
        
        assertEquals(last, list.last());
        assertEquals(four, list.before(last));
        assertEquals(three, list.before(four));
        assertEquals(two, list.before(three));
        assertEquals(first, list.before(two));
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	assertEquals(0, list.size());
    	Position<String> first = list.addLast("one");
    	assertNull(list.before(first));
    	Position<String> second = list.addLast("one");
    	assertNull(list.before(first));
    	assertEquals(first, list.before(second));
    	Position<String> newFirst = list.addFirst("three");
    	assertNull(list.before(newFirst));
    	assertEquals(newFirst, list.before(first));
    	assertEquals(first, list.before(second));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	assertEquals(0, list.size());
    	Position<String> first = list.addLast("one");
    	assertNull(list.after(first));
    	Position<String> second = list.addLast("one");
    	assertNull(list.after(second));
    	assertEquals(second, list.after(first));
    	Position<String> newFirst = list.addFirst("three");
    	assertNull(list.after(second));
    	assertEquals(first, list.after(newFirst));
    	assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	assertEquals(0, list.size());
    	Position<String> first = list.addLast("one");
    	Position<String> two = list.addBefore(first, "two");
    	assertEquals(2, list.size());
    	assertEquals(two, list.before(first));
    	Position<String> three = list.addBefore(first, "three");
    	assertEquals(3, list.size());
    	assertEquals(two, list.before(three));
    	assertEquals(three, list.before(first));
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	assertEquals(0, list.size());
    	Position<String> first = list.addLast("one");
    	Position<String> two = list.addAfter(first, "two");
    	assertEquals(2, list.size());
    	assertEquals(two, list.after(first));
    	Position<String> three = list.addAfter(first, "three");
    	assertEquals(3, list.size());
    	assertEquals(three, list.after(first));
    	assertEquals(two, list.after(three));
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> one = list.addLast("one");
    	Position<String> two = list.addLast("two");
    	Position<String> three = list.addLast("three");
    	Position<String> four = list.addLast("four");
    	
    	assertEquals(4, list.size());
    	assertEquals("one", list.set(one, "six"));
    	assertEquals("four", list.set(four, "nine"));
    	assertEquals("three", list.set(three, "eight"));
    	assertEquals("two", list.set(two, "seven"));
    	
    	Position<String> five = list.addLast("ten");
    	
    	assertEquals("eight", list.set(three, "twenty"));
    	
    	assertEquals("six", one.getElement());
    	assertEquals("seven", two.getElement());
    	assertEquals("twenty", three.getElement());
    	assertEquals("nine", four.getElement());
    	assertEquals("ten", five.getElement());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> one = list.addLast("one");
    	Position<String> two = list.addLast("two");
    	Position<String> three = list.addLast("three");
    	Position<String> four = list.addLast("four");
    	Position<String> five = list.addLast("five");
    	
    	assertEquals("two", list.remove(two));
    	
    	assertEquals(4, list.size());
    	assertEquals(one, list.first());
    	assertEquals(three, list.after(one));
    	assertEquals(four, list.after(three));
    	assertEquals(five, list.after(four));
    	
    	assertEquals("one", list.remove(one));
    	
    	assertEquals(3, list.size());
    	assertEquals(three, list.first());
    	assertEquals(four, list.after(three));
    	assertEquals(five, list.after(four));
    	
    	assertEquals("four", list.remove(four));
    	
    	assertEquals(2, list.size());
    	assertEquals(three, list.first());
    	assertEquals(five, list.after(three));
    	
    	assertEquals("five", list.remove(five));
    	
    	assertEquals(1, list.size());
    	assertEquals(three, list.first());
    	
    	assertEquals("three", list.remove(three));
    	
    	assertEquals(0, list.size());
    	assertNull(list.first());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        
        
        Iterator<String> it = list.iterator();
        
        
        assertThrows(IllegalStateException.class, () -> it.remove());
        assertFalse(it.hasNext());

        
        Position<String> one = list.addLast("one");
        
        
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(one, list.first());
        
        Position<String> two = list.addFirst("two");
        
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals(two, list.first());
        assertEquals(one, list.after(two));
        
        assertFalse(it.hasNext());
        
        Position<String> three = list.addLast("three");
        Position<String> four = list.addLast("four");
        Position<String> five = list.addLast("five");
                
        assertEquals(two, list.first());
        assertEquals(one, list.after(two));
        assertEquals(three, list.after(one));
        assertEquals(four, list.after(three));
        assertEquals(five, list.after(four));
        
        assertEquals("three", it.next());
        assertEquals("four", it.next());
        assertEquals("five", it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        
        Iterator<String> it2 = list.iterator();
        
        assertEquals("two", it2.next());
        assertEquals("one", it2.next());
        assertEquals("three", it2.next());
        assertEquals("four", it2.next());
        assertEquals("five", it2.next());
        assertFalse(it2.hasNext());
        
        Iterator<String> it3 = list.iterator();
        
        assertThrows(IllegalStateException.class, () -> it3.remove());
        
        it3.next();
        it3.remove();
        assertThrows(IllegalStateException.class, () -> it3.remove());
        
        assertEquals(one, list.first());
        assertEquals(three, list.after(one));
        assertEquals(four, list.after(three));
        assertEquals(five, list.after(four));
        
        it3.next();
        it3.next();
        it3.remove();
        
        assertEquals(one, list.first());
        assertEquals(four, list.after(one));
        assertEquals(five, list.after(four));
        
        it3.next();
        it3.next();
        it3.remove();
        
        assertEquals(one, list.first());
        assertEquals(four, list.last());
        
        assertFalse(it3.hasNext());
        
        Iterator<String> it4 = list.iterator();
        
        it4.next();
        it4.remove();
        it4.next();
        it4.remove();
        
        assertTrue(list.isEmpty());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        assertFalse(it.hasNext());
        
        list.addLast("four");
        list.addLast("five");
        it = list.positions().iterator();
        
        Position<String> current = first;
        for(String s : list) {
        	assertEquals(current.getElement(), s);
        	current = list.after(current);
        }
    }

}
