package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {

	/** Map object used for testing UnorderedLinkedMapTest. */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        
        assertEquals("string3", map.put(3, "string3x"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals("string3x", map.put(3, "string3"));
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        
        assertEquals(4, map.size());
        assertEquals("UnorderedLinkedMap[4, 2, 1, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 2, 1]", map.toString());
        
        assertEquals("string1", map.put(1, "string5"));
        assertEquals("UnorderedLinkedMap[1, 4, 3, 2]", map.toString());
        
        assertEquals("string5", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 2, 1]", map.toString());
        
        assertEquals("string3", map.put(3, "string7"));
        assertEquals("UnorderedLinkedMap[3, 4, 2, 1]", map.toString());
        
        assertEquals("string5", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string7", map.get(3));
        assertEquals("string4", map.get(4));
        
        assertEquals("string5", map.put(1, "string11"));
        assertEquals("string2", map.put(2, "string12"));
        assertEquals("UnorderedLinkedMap[2, 1, 4, 3]", map.toString());
        
        assertEquals("string11", map.get(1));
        assertEquals("string12", map.get(2));
        assertEquals("string7", map.get(3));
        assertEquals("string4", map.get(4));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.get(null));
        assertNull(map.get(0));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertNull(map.get(0));
        assertNull(map.get(6));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 5, 1, 4, 3]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertNull(map.get(4));
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 2, 5, 1]", map.toString());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertNull(map.remove(0));
        
        assertEquals("string5", map.remove(5));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 3]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[4, 2, 3]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("UnorderedLinkedMap[4, 3]", map.toString());
        assertEquals(2, map.size());
        
        assertNull(map.remove(2));
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(3, "string3"));
        assertEquals(2, map.size());
        assertEquals("UnorderedLinkedMap[3, 4]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string4", map.remove(4));
        assertEquals("UnorderedLinkedMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(4));
        assertNull(map.get(5));
        
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> it1 = map.iterator();
        assertTrue(it1.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it1.remove());
        
        assertEquals((Integer) 1, it1.next());
        assertTrue(it1.hasNext());
        assertEquals((Integer) 4, it1.next());
        assertEquals((Integer) 2, it1.next());
        assertEquals((Integer) 5, it1.next());
        assertEquals((Integer) 3, it1.next());
        assertFalse(it1.hasNext());
        assertThrows(NoSuchElementException.class, () -> it1.next());
        
        assertNull(map.put(6, "string6"));
        assertFalse(it1.hasNext());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Entry<Integer, String>> it1 = map.entrySet().iterator();
        
        assertTrue(it1.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it1.remove());
        
        Entry<Integer, String> e1 = it1.next();
        Entry<Integer, String> e2 = it1.next();
        assertEquals("string1", e1.getValue());
        assertEquals((Integer) 4, e2.getKey());
        assertEquals("string2", it1.next().getValue());
        assertEquals("string5", it1.next().getValue());
        assertEquals((Integer) 3, it1.next().getKey());
        assertFalse(it1.hasNext());
        assertThrows(NoSuchElementException.class, () -> it1.next());
        
        assertNull(map.put(6, "string6"));
        assertFalse(it1.hasNext());
        
        assertEquals(0, e1.compareTo(e1));
        assertTrue(e1.compareTo(e2) < 0);
        assertTrue(e2.compareTo(e1) > 0);
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> it1 = map.values().iterator();
        assertTrue(it1.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it1.remove());
        
        assertEquals("string1", it1.next());
        assertTrue(it1.hasNext());
        assertEquals("string4", it1.next());
        assertEquals("string2", it1.next());
        assertEquals("string5", it1.next());
        assertEquals("string3", it1.next());
        assertFalse(it1.hasNext());
        assertThrows(NoSuchElementException.class, () -> it1.next());
        
        assertNull(map.put(6, "string6"));
        assertFalse(it1.hasNext());
    }
}
