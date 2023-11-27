package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 *
 */
public class BinarySearchTreeMapTest {

	/** BinarySearchTreeMap object for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertEquals("one", tree.put(1, "onex"));
        assertEquals(1, tree.size());
        assertEquals("onex", tree.root().getElement().getValue());
        
        assertNull(tree.put(2, "two"));
        assertEquals(2, tree.size());
        
        assertNull(tree.put(0, "zero"));
        assertEquals(3, tree.size());
        
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(9, "nine"));
        assertNull(tree.put(3, "three"));
        
        assertEquals(5, tree.size());
        
        assertEquals(3, (int)tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertEquals("BalanceableBinaryTree[\nnull\n]", tree.toString());
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        Iterator<Entry<Integer, String>> it1 = tree.entrySet().iterator();
        assertEquals("one", it1.next().getValue());
        assertFalse(it1.hasNext());
        
        assertEquals("one", tree.get(1));
        assertNull(tree.get(0));
        assertNull(tree.get(2));
        
        tree.put(5,  "five");
        tree.put(11,  "eleven");
        tree.put(9,  "nine");
        tree.put(7,  "seven");
        tree.put(-5, "-five");
        tree.put(-4, "-four");
        
        
        assertEquals("-four", tree.get(-4));
        assertEquals("seven", tree.get(7));
        assertEquals("one", tree.get(1));
        assertEquals("-five", tree.get(-5));
        
        assertNull(tree.get(0));
        assertNull(tree.get(2));
        assertNull(tree.get(-7));
        
        assertEquals("five", tree.right(tree.root()).getElement().getValue());
        
        tree.rotate(tree.right(tree.right(tree.root())));
        
        assertEquals("eleven", tree.right(tree.root()).getElement().getValue());
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(0, "zero");
        
        assertEquals(3, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("zero", tree.left(tree.root()).getElement().getValue());
        assertEquals(null, tree.right(tree.root()).getElement());
        assertNull(tree.remove(1));
        assertEquals(2, tree.size());
        
        
        assertEquals("two", tree.remove(2));
        assertNull(tree.remove(2));
        assertEquals(1, tree.size());
        assertEquals("zero", tree.root().getElement().getValue());
        assertEquals(null, tree.left(tree.root()).getElement());
        assertEquals(null, tree.right(tree.root()).getElement());
        
        assertEquals("zero", tree.remove(0));
        assertNull(tree.remove(0));
        assertEquals(0, tree.size());
        
        assertNull(tree.root().getElement());
        
        tree.put(1,  "one");
        tree.put(5,  "five");
        tree.put(11,  "eleven");
        tree.put(9,  "nine");
        tree.put(7,  "seven");
        tree.put(-5, "-five");
        tree.put(-4, "-four");
        tree.put(3, "three");
        assertEquals(8, tree.size());
        
        tree.remove(1);
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals(7, tree.size());
        
        tree.remove(11);
        assertEquals(6, tree.size());
        assertEquals("nine", tree.right(tree.right(tree.root())).getElement().getValue());
        
        tree.remove(-5);
        assertEquals(5, tree.size());
        assertEquals("-four", tree.left(tree.root()).getElement().getValue());
        
        assertNull(tree.left(tree.left(tree.root())).getElement());
        assertNull(tree.right(tree.left(tree.root())).getElement());
        tree.remove(-4);
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(4, tree.size());
        
        
        assertNull(tree.get(1));
        assertNull(tree.get(11));
        assertNull(tree.get(-5));
        assertNull(tree.get(-4));
        
        assertNull(tree.remove(10));
        assertEquals(4, tree.size());
    }
    
    
}

