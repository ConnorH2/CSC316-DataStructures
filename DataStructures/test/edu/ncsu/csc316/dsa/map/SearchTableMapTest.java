package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 *
 */
public class SearchTableMapTest {

	/** Map object for testing SearchTableMap using basic data types. */
    private Map<Integer, String> map;
    /** Map object for testing SearchTableMap using students. */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        
        assertEquals("string3", map.put(3, "string3x"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals("string3x", map.put(3, "string3"));
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        
        assertEquals(4, map.size());
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string1", map.put(1, "string5"));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string5", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string3", map.put(3, "string7"));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string5", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string7", map.get(3));
        assertEquals("string4", map.get(4));
        
        assertEquals("string5", map.put(1, "string11"));
        assertEquals("string2", map.put(2, "string12"));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string11", map.get(1));
        assertEquals("string12", map.get(2));
        assertEquals("string7", map.get(3));
        assertEquals("string4", map.get(4));
        
        assertNull(map.put(10, "string10"));
        assertNull(map.put(8, "string8"));
        
        assertEquals("string11", map.get(1));
        assertEquals("string12", map.get(2));
        assertEquals("string7", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string8", map.get(8));
        assertEquals("string10", map.get(10));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.get(0));
        assertNull(map.get(6));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("string2", map.get(2));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.put(6, "string6"));
        assertEquals("string6", map.get(6));
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.remove(0));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals("SearchTableMap[2, 3, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("SearchTableMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertNull(map.remove(2));
        
        assertEquals("string3", map.remove(3));
        assertEquals("SearchTableMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(3, "string3"));
        assertEquals(2, map.size());
        assertEquals("SearchTableMap[3, 4]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("SearchTableMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string4", map.remove(4));
        assertEquals("SearchTableMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
        assertEquals("SearchTableMap[]", studentMap.toString());
        
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s1, 1));
        assertEquals(5, studentMap.size());
        assertFalse(studentMap.isEmpty());
        
        assertEquals(1, (int) studentMap.get(s1));
        assertEquals(2, (int) studentMap.get(s2));
        assertEquals(3, (int) studentMap.get(s3));
        assertEquals(4, (int) studentMap.get(s4));
        assertEquals(5, (int) studentMap.get(s5));
        
        assertEquals(3, (int) studentMap.remove(s3));
        assertNull(studentMap.get(s3));
        assertNull(studentMap.remove(s3));
        
        assertEquals(1, (int) studentMap.get(s1));
        assertEquals(2, (int) studentMap.get(s2));
        assertEquals(4, (int) studentMap.get(s4));
        assertEquals(5, (int) studentMap.get(s5));
        
        assertEquals(1, (int) studentMap.remove(s1));
        assertEquals(3, studentMap.size());
        
        assertNull(studentMap.put(s1, 1));
        assertEquals(1, (int) studentMap.put(s1, 11));
        
        assertEquals(11, (int) studentMap.remove(s1));
        assertEquals(2, (int) studentMap.remove(s2));
        assertEquals(2, studentMap.size());
        assertNull(studentMap.remove(s3));
        assertEquals(4, (int) studentMap.remove(s4));
        assertEquals(5, (int) studentMap.remove(s5));
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
        
        
        
        
        
        
        studentMap = new SearchTableMap<Student, Integer>(new StudentIDComparator());
        
        s1 = new Student("A", "B", 0, 0, 0, "ab");
        s2 = new Student("A", "B", 2, 0, 0, "ab");
        s3 = new Student("A", "B", 3, 0, 0, "ab");
        s4 = new Student("A", "B", 6, 0, 0, "ab");
        s5 = new Student("A", "B", 11, 0, 0, "ab");
        
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
        assertEquals("SearchTableMap[]", studentMap.toString());
        
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s1, 1));
        assertEquals(5, studentMap.size());
        assertFalse(studentMap.isEmpty());
        
        assertEquals(1, (int) studentMap.get(s1));
        assertEquals(2, (int) studentMap.get(s2));
        assertEquals(3, (int) studentMap.get(s3));
        assertEquals(4, (int) studentMap.get(s4));
        assertEquals(5, (int) studentMap.get(s5));
        
        Iterator<Student> it1 = studentMap.iterator();
        assertTrue(it1.hasNext());
        assertEquals(s1, it1.next());
        assertEquals(s2, it1.next());
        assertEquals(s3, it1.next());
        assertEquals(s4, it1.next());
        assertEquals(s5, it1.next());
        assertFalse(it1.hasNext());
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
        assertEquals((Integer) 2, it1.next());
        assertEquals((Integer) 3, it1.next());
        assertEquals((Integer) 4, it1.next());
        assertEquals((Integer) 5, it1.next());
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));

        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        
        assertNull(map.put(6, "string6"));
        assertFalse(it.hasNext());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals("string1", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string5", it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        
        assertNull(map.put(6, "string6"));
        assertFalse(it.hasNext());
    }
}
