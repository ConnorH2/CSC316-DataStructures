package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King
 *
 */
public class TreeSetTest {

	/** TreeSet object for testing. */
    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(11);
        set.add(13);
        
        assertEquals(3, set.size());
        
        set.add(11);
        assertEquals(3, set.size());
        
        set.add(7);
        
        assertEquals(4, set.size());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(15));
        assertFalse(set.contains(0));
        assertFalse(set.contains(30));
        
        assertTrue(set.contains(25));
        set.add(30);
        assertTrue(set.contains(30));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertTrue(set.contains(15));
        
        assertEquals(15, (int)set.remove(15));
        assertEquals(null, set.remove(15));
        assertFalse(set.contains(15));
        assertEquals(4, set.size());
        
        assertEquals(5, (int)set.remove(5));
        assertEquals(10, (int)set.remove(10));
        
        assertEquals(2, set.size());
        
        assertTrue(set.contains(20));
        assertFalse(set.contains(5));
        assertTrue(set.contains(25));
        assertFalse(set.contains(10));
        
        assertEquals(20, (int)set.remove(20));
        assertEquals(25, (int)set.remove(25));
        
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains(20));
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        assertEquals(2, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(30));
        assertFalse(set.contains(15));
        
        assertEquals(3, other.size());
        assertTrue(other.contains(10));
        assertTrue(other.contains(20));
        assertTrue(other.contains(30));
        
        other.retainAll(other);
        assertEquals(3, other.size());
        assertTrue(other.contains(10));
        assertTrue(other.contains(20));
        assertTrue(other.contains(30));
        
        other.retainAll(set);
        assertEquals(2, other.size());
        assertTrue(other.contains(10));
        assertTrue(other.contains(20));
        assertFalse(other.contains(30));
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertEquals(3, set.size());
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        
        assertEquals(3, other.size());
        assertTrue(other.contains(10));
        assertTrue(other.contains(20));
        assertTrue(other.contains(30));
        
        other.removeAll(set);
        assertEquals(3, other.size());
        assertTrue(other.contains(10));
        assertTrue(other.contains(20));
        assertTrue(other.contains(30));
        
        other.removeAll(other);
        assertTrue(other.isEmpty());
        assertEquals(0, other.size());
        assertFalse(other.contains(20));
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertEquals(8, set.size());
        assertTrue(set.contains(15));
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
        
        assertEquals(3, other.size());
        assertFalse(other.contains(15));
        assertTrue(other.contains(30));
        assertTrue(other.contains(40));
        assertTrue(other.contains(50));
        
        other.addAll(other);
        assertFalse(other.contains(15));
        assertTrue(other.contains(30));
        assertTrue(other.contains(40));
        assertTrue(other.contains(50));
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it1 = set.iterator();
        
        assertEquals(5, (int)it1.next());
        assertEquals(10, (int)it1.next());
        assertEquals(15, (int)it1.next());
        assertEquals(20, (int)it1.next());
        assertEquals(25, (int)it1.next());
        assertEquals(5, set.size());
        assertFalse(it1.hasNext());
    }
}
