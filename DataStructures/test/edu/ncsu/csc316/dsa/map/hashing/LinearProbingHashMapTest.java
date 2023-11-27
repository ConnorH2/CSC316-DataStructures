package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {

	/** LinearProbingHashMap object for testing. */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
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
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        
        assertEquals("string4", map.put(4, "string4.5"));
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        
        it = map.entrySet().iterator();
        
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(10, (int)it.next().getKey());
        
        
        assertNull(map.put(5, "string5"));
        
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        
        assertNull(map.put(6, "string6"));
        
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals("string4.5", it.next().getValue());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        
        assertEquals(6, map.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());

        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        
        assertNull(map.get(0));
        assertNull(map.get(-1));
        assertNull(map.get(17));
        
        assertEquals("string3", map.get(3));
        assertEquals("string10", map.get(10));
        assertEquals("string6", map.get(6));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());

        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        
        assertEquals(4, map.size());
        
        assertEquals("string11", map.remove(11));
        assertNull(map.remove(11));
        
        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        
        assertEquals(3, (int)it.next().getKey());
        
        assertEquals(4, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        
        assertNull(map.put(17, "string17"));
        
        it = map.entrySet().iterator();
        
        assertEquals(3, (int)it.next().getKey());
        assertEquals(17, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        
        assertNull(map.remove(5));
        
        assertEquals("string3", map.remove(3));
        assertEquals("string10", map.remove(10));
        
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        
        Iterator<Integer> it = map.iterator();

        assertEquals(6, (int)it.next());
        assertEquals(3, (int)it.next());
        assertEquals(11, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(10, (int)it.next());
        assertEquals(5, (int)it.next());
        
        map = new LinearProbingHashMap<Integer, String>();
        map = new LinearProbingHashMap<Integer, String>(true);
        map = new LinearProbingHashMap<Integer, String>(7);
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();     
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals("string4", it.next().getValue());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        
        Iterator<String> it = map.values().iterator();

        assertEquals("string6", it.next());
        assertEquals("string3", it.next());
        assertEquals("string11", it.next());
        assertEquals("string4", it.next());
        assertEquals("string10", it.next());
        assertEquals("string5", it.next());
    }
}