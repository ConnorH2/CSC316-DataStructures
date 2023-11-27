package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {

	/** The RedBlackTreeMap object for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(30, "30");
        assertEquals(1, tree.size());
        assertEquals("30", tree.root().getElement().getValue());
        
        tree.put(20, "20");
        assertEquals(2, tree.size());
        assertEquals("30", tree.root().getElement().getValue());
        assertEquals("20", tree.left(tree.root()).getElement().getValue());
        
        // Case 1
        tree.put(10, "10");
        assertEquals(3, tree.size());
        assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        
        tree.put(15, "15");
        assertEquals(4, tree.size());
        assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        
        tree.put(5, "5");
        assertEquals(5, tree.size());
        assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("5", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        
        // Case 2
        tree.put(3, "3");
        assertEquals(6, tree.size());
        assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("5", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("3", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        tree.put(4, "4");
        assertEquals(7, tree.size());
        assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("4", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("3", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        assertEquals("5", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	tree.put(30, "30");
    	tree.put(20, "20");
    	tree.put(10, "10");
    	tree.put(15, "15");
    	tree.put(5, "5");
    	tree.put(3, "3");
    	tree.put(4, "4");
    	
    	assertEquals("5", tree.get(5));
    	tree.put(5, "55");
    	assertEquals("55", tree.get(5));
    	
    	assertEquals("30", tree.get(30));
    	tree.remove(30);
    	assertNull(tree.get(30));
    	
    	assertNull(tree.get(2));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(30, "30");
    	tree.put(20, "20");
    	tree.put(10, "10");
    	tree.put(15, "15");
    	tree.put(5, "5");
    	tree.put(3, "3");
    	tree.put(4, "4");
    	
    	assertEquals(7, tree.size());
    	assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("4", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("3", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        assertEquals("5", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        assertEquals("3", tree.remove(3));
        assertNull(tree.remove(3));
        assertNull(tree.remove(25));
        
        assertEquals(6, tree.size());
    	assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("4", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        assertNull(tree.left(tree.left(tree.left(tree.root()))).getElement());
        assertEquals("5", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
    	
        assertEquals("4", tree.remove(4));
        
        assertEquals(5, tree.size());
    	assertEquals("20", tree.root().getElement().getValue());
        assertEquals("10", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("5", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("15", tree.right(tree.left(tree.root())).getElement().getValue());
        assertNull(tree.left(tree.left(tree.left(tree.root()))).getElement());
        assertNull(tree.right(tree.left(tree.left(tree.root()))).getElement());
        
        
        tree.put(3, "3");
        
        // Case 1
        tree.remove(15);
        assertEquals(5, tree.size());
    	assertEquals("20", tree.root().getElement().getValue());
        assertEquals("5", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertEquals("3", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("10", tree.right(tree.left(tree.root())).getElement().getValue());
        assertNull(tree.left(tree.left(tree.left(tree.root()))).getElement());
        assertNull(tree.right(tree.left(tree.left(tree.root()))).getElement());
        
        // Case 2
        tree.remove(3);
        assertEquals(4, tree.size());
    	assertEquals("20", tree.root().getElement().getValue());
        assertEquals("5", tree.left(tree.root()).getElement().getValue());
        assertEquals("30", tree.right(tree.root()).getElement().getValue());
        assertNull(tree.left(tree.left(tree.root())).getElement());
        assertEquals("10", tree.right(tree.left(tree.root())).getElement().getValue());
        
        
        tree.put(3, "3");
        tree.put(4, "4");
        // Case 3
        tree.remove(30);
        assertEquals(5, tree.size());
    	assertEquals("5", tree.root().getElement().getValue());
        assertEquals("3", tree.left(tree.root()).getElement().getValue());
        assertEquals("20", tree.right(tree.root()).getElement().getValue());
        assertEquals("4", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("10", tree.left(tree.right(tree.root())).getElement().getValue());
    }
    
    
    /**
     * Test the behaviors of the tree with a custom comparator.
     */     
    @Test
    public void testCustomComparator() {
    	StudentGPAComparator c1 = new StudentGPAComparator();
    	BinarySearchTreeMap<Student, String> tree2 = new RedBlackTreeMap<Student, String>(c1);
    	
    	Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		
		tree2.put(sOne, "1");
		tree2.put(sTwo, "2");
		
		assertEquals(2, tree2.size());
		assertEquals("1", tree2.root().getElement().getValue());
		assertNull(tree2.right(tree2.root()).getElement());
		assertEquals("2", tree2.left(tree2.root()).getElement().getValue());
		
		tree2.put(sThree, "3");
		
		assertEquals(3, tree2.size());
		assertEquals("2", tree2.root().getElement().getValue());
		assertEquals("1", tree2.right(tree2.root()).getElement().getValue());
		assertEquals("3", tree2.left(tree2.root()).getElement().getValue());
		
		tree2.put(sFour, "4");
		
		assertEquals(4, tree2.size());
		assertEquals("2", tree2.root().getElement().getValue());
		assertEquals("1", tree2.right(tree2.root()).getElement().getValue());
		assertEquals("3", tree2.left(tree2.root()).getElement().getValue());
		assertEquals("4", tree2.left(tree2.left(tree2.root())).getElement().getValue());
		
		tree2.remove(sOne);
		
		assertEquals(3, tree2.size());
		assertEquals("3", tree2.root().getElement().getValue());
		assertEquals("4", tree2.left(tree2.root()).getElement().getValue());
		assertEquals("2", tree2.right(tree2.root()).getElement().getValue());
    }
}
