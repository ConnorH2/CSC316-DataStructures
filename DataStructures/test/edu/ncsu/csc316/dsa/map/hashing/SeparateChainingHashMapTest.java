package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 *
 */
public class SeparateChainingHashMapTest {

	/** HashMap object for testing. */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertEquals("string4", map.put(4, "string4.5"));
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(11, (int)it.next().getKey());
        
        assertNull(map.put(5, "string5"));
        assertNull(map.put(75, "string75"));
        assertNull(map.put(40, "string40"));
        
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(40, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertNull(map.put(6, "string6"));
        assertNull(map.put(-1, "string-1"));
        
        it = map.entrySet().iterator();
        assertEquals(-1, (int)it.next().getKey());
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(40, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertEquals(9, map.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(75, "string75"));
        assertNull(map.put(40, "string40"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(-1, "string-1"));

        assertEquals(9, map.size());
        
        assertNull(map.get(0));
        assertNull(map.get(47));
        assertNull(map.get(17));
        
        assertEquals("string-1", map.get(-1));
        assertEquals("string75", map.get(75));
        assertEquals("string5", map.get(5));
        assertEquals("string40", map.get(40));
        assertEquals("string6", map.get(6));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        
        assertNull(map.remove(3));
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(75, "string75"));
        assertNull(map.put(40, "string40"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(-1, "string-1"));

        assertEquals(9, map.size());
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        
        assertEquals("string-1", map.remove(-1));
        assertNull(map.remove(-1));
        assertEquals(8, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(40, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertEquals("string40", map.remove(40));
        assertNull(map.remove(40));
        assertEquals(7, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertEquals("string5", map.remove(5));
        assertNull(map.remove(5));
        assertEquals(6, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertEquals("string6", map.remove(6));
        assertNull(map.remove(6));
        assertEquals(5, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */   
    @Test
    public void testIterator() {
    	 assertNull(map.put(3, "string3"));
         assertNull(map.put(4, "string4"));
         assertEquals(2, map.size());
         assertFalse(map.isEmpty());
         
         assertNull(map.put(10, "string10"));
         assertNull(map.put(11, "string11"));
         assertNull(map.put(5, "string5"));
         assertNull(map.put(75, "string75"));
         assertNull(map.put(40, "string40"));
         assertNull(map.put(6, "string6"));
         assertNull(map.put(-1, "string-1"));

         assertEquals(9, map.size());
        
        Iterator<Integer> it = map.iterator();
        
        assertEquals(-1, (int)it.next());
        assertEquals(6, (int)it.next());
        assertEquals(3, (int)it.next());
        assertEquals(10, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(11, (int)it.next());
        assertEquals(5, (int)it.next());
        assertEquals(40, (int)it.next());
        assertEquals(75, (int)it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        
        
        map = new SeparateChainingHashMap<Integer, String>();
        map = new SeparateChainingHashMap<Integer, String>(true);
        map = new SeparateChainingHashMap<Integer, String>(7);
    }
    
    /**
     * Test the output of the entrySet() behavior
     */   
    @Test
    public void testEntrySet() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(75, "string75"));
        assertNull(map.put(40, "string40"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(-1, "string-1"));

        assertEquals(9, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertEquals(-1, (int)it.next().getKey());
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals("string4", it.next().getValue());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(40, (int)it.next().getKey());
        assertEquals(75, (int)it.next().getKey());
        
        assertEquals(9, map.size());
    }
    
    /**
     * Test the output of the values() behavior
     */   
    @Test
    public void testValues() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(75, "string75"));
        assertNull(map.put(40, "string40"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(-1, "string-1"));

        assertEquals(9, map.size());
        
        Iterator<String> it = map.values().iterator();
        
        assertEquals("string-1", it.next());
        assertEquals("string6", it.next());
        assertEquals("string3", it.next());
        assertEquals("string10", it.next());
        assertEquals("string4", it.next());
        assertEquals("string11", it.next());
        assertEquals("string5", it.next());
        assertEquals("string40", it.next());
        assertEquals("string75", it.next());
        assertFalse(it.hasNext());
    }
}
