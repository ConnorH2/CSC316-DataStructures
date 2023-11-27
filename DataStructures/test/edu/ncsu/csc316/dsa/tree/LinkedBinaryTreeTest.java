package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {

	/** The LinkedBinaryTree object used for testing. */
    private LinkedBinaryTree<String> tree;
    /** The first tree node used for testing. */
    private Position<String> one;
    /** The second tree node used for testing. */
    private Position<String> two;
    /** The third tree node used for testing. */
    private Position<String> three;
    /** The fourth tree node used for testing. */
    private Position<String> four;
    /** The fifth tree node used for testing. */
    private Position<String> five;
    /** The sixth tree node used for testing. */
    private Position<String> six;
    /** The seventh tree node used for testing. */
    private Position<String> seven;
    /** The eighth tree node used for testing. */
    private Position<String> eight;
    /** The ninth tree node used for testing. */
    private Position<String> nine;
    /** The tenth tree node used for testing. */
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        
        assertThrows(IllegalArgumentException.class, () -> tree.set(null, "eleven"));
        
        assertEquals("ten", ten.getElement());
        assertEquals("ten", tree.set(ten, null));
        assertEquals(null, ten.getElement());
        
        assertEquals(null, ten.getElement());
        assertEquals(null, tree.set(ten, "eleven"));
        assertEquals("eleven", ten.getElement());
        
        assertEquals("eleven", tree.right(tree.left(tree.root())).getElement());
        
        assertEquals("one", one.getElement());
        assertEquals("one", tree.set(one, "zero"));
        assertEquals("zero", one.getElement());
        
        assertEquals("nine", nine.getElement());
        assertEquals("nine", tree.set(nine, "twelve"));
        assertEquals("twelve", nine.getElement()); // tempting
        
        assertEquals("eleven", ten.getElement());
        assertEquals("zero", one.getElement());
        assertEquals("twelve", nine.getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        createTree();
        
        assertEquals(10, tree.size());
        tree.remove(eight);
        assertEquals(9, tree.size());
        tree.remove(three);
        assertEquals(8, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.numChildren(null));

        assertEquals(2, tree.numChildren(one));
        assertEquals(0, tree.numChildren(five));
        assertEquals(1, tree.numChildren(three));
        
        assertEquals(2, tree.numChildren(four));
        tree.remove(eight);
        assertEquals(1, tree.numChildren(four));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.parent(null));

        assertEquals(null, tree.parent(one));
        assertEquals(two, tree.parent(ten));
        assertEquals(four, tree.parent(nine));
        
        assertEquals(three, tree.parent(four));
        tree.remove(three);
        assertEquals(one, tree.parent(four));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(null, tree.sibling(one));
        
        
        assertEquals(two, tree.sibling(three));
        assertEquals(null, tree.sibling(four));
        
        assertEquals(seven, tree.sibling(five));
        assertEquals(five, tree.sibling(seven));
        tree.remove(seven);
        assertEquals(null, tree.sibling(five));
        
        assertEquals(null, tree.sibling(seven));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.isInternal(null));
        
        assertFalse(tree.isInternal(six));
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(three));
        assertFalse(tree.isInternal(five));
        
        assertTrue(tree.isInternal(four));
        tree.remove(eight);
        tree.remove(nine);
        assertFalse(tree.isInternal(four));
        
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.isLeaf(null));
        
        assertTrue(tree.isLeaf(six));
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(ten));
        assertFalse(tree.isLeaf(three));
        assertTrue(tree.isLeaf(five));
        
        assertFalse(tree.isLeaf(four));
        tree.remove(eight);
        tree.remove(nine);
        assertTrue(tree.isLeaf(four));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(ten));
        assertFalse(tree.isRoot(three));
        
        assertFalse(tree.isRoot(two));
        tree.remove(eight);
        tree.remove(nine);
        tree.remove(three);
        tree.remove(four);
        tree.remove(one);
        assertTrue(tree.isRoot(two));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
    	Iterable<Position<String>> pEmpty = tree.levelOrder();
        Iterator<Position<String>> itEmpty = pEmpty.iterator();
        assertFalse(itEmpty.hasNext());
    	
        createTree();
        
        Iterable<Position<String>> p = tree.preOrder();
        Iterator<Position<String>> it = p.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(seven, it.next());
        
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals(five, it.next());
        assertEquals(three, it.next());
        assertEquals(four, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertFalse(it.hasNext());
        
        tree.addLeft(nine, "11");
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
    	Iterable<Position<String>> pEmpty = tree.levelOrder();
        Iterator<Position<String>> itEmpty = pEmpty.iterator();
        assertFalse(itEmpty.hasNext());
    	
        createTree();
        Iterable<Position<String>> p = tree.postOrder();
        Iterator<Position<String>> it = p.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(six, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(ten, it.next());
        assertEquals(two, it.next());
        
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertEquals(four, it.next());
        assertEquals(three, it.next());
        assertEquals(one, it.next());
        assertFalse(it.hasNext());
        
        tree.addLeft(nine, "11");
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
    	Iterable<Position<String>> pEmpty = tree.levelOrder();
        Iterator<Position<String>> itEmpty = pEmpty.iterator();
        assertFalse(itEmpty.hasNext());
    	
        createTree();
        Iterable<Position<String>> p = tree.inOrder();
        Iterator<Position<String>> it = p.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(six, it.next());
        assertEquals(two, it.next());
        assertEquals(seven, it.next());
        assertEquals(ten, it.next());
        assertEquals(five, it.next());
        
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals(one, it.next());
        assertEquals(eight, it.next());
        assertEquals(four, it.next());
        assertEquals(nine, it.next());
        assertEquals(three, it.next());
        assertFalse(it.hasNext());
        
        tree.addLeft(nine, "11");
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertEquals(null, tree.root());
        assertEquals(0, tree.size());
        
        assertThrows(IllegalArgumentException.class, () -> tree.addLeft(tree.root(), "one"));
        assertThrows(IllegalArgumentException.class, () -> tree.addRight(tree.root(), "one"));
        
        one = tree.addRoot("1");
        assertThrows(IllegalArgumentException.class, () -> tree.addRoot("two"));
        assertEquals(one, tree.root());
        assertEquals(1, tree.size());
        
        assertTrue(tree.isLeaf(one));
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isInternal(one));
        
        assertNull(tree.left(one));
        assertNull(tree.right(one));
        
        two = tree.addLeft(one, "2");
        assertEquals(2, tree.size());
        three = tree.addRight(one, "3");
        assertEquals(3, tree.size());
        
        assertEquals(two, tree.left(one));
        assertEquals(three, tree.right(one));
        assertFalse(tree.isLeaf(one));
        assertTrue(tree.isInternal(one));
        
        assertEquals("3", tree.remove(three));
        assertEquals("2", tree.remove(two));
        assertEquals(1, tree.size());
        
        
        assertEquals(one, tree.root());
        assertEquals("1", tree.remove(one));
        
        
        assertEquals(0, tree.size());
        assertNull(tree.root());
        
        tree.addRoot("5");
        assertEquals(1, tree.size());
        assertEquals("2", tree.setRoot(two).getElement());
        assertEquals(two, tree.root());
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */     
    @Test
    public void testLevelOrder() {
    	Iterable<Position<String>> pEmpty = tree.levelOrder();
        Iterator<Position<String>> itEmpty = pEmpty.iterator();
        assertFalse(itEmpty.hasNext());
    	
        createTree();
        Iterable<Position<String>> p = tree.levelOrder();
        Iterator<Position<String>> it = p.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals(four, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertFalse(it.hasNext());
        
        tree.addLeft(nine, "11");
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	
    	assertThrows(IllegalArgumentException.class, () -> tree.addLeft(null, "1"));
    	assertEquals(0, tree.size());
    	one = tree.addRoot("1");
    	assertEquals(1, tree.size());
    	
    	two = tree.addLeft(one, "2");
    	assertEquals(2, tree.size());
    	assertThrows(IllegalArgumentException.class, () -> tree.addLeft(one, "3"));
    	
    	three = tree.addLeft(two, "3");
    	assertEquals(3, tree.size());
    	
    	tree.remove(three);
    	
    	four = tree.addLeft(two, "4");
    	
    	assertEquals(3, tree.size());
    	assertEquals("1", tree.remove(tree.root()));
    	assertEquals("2", tree.remove(tree.root()));
    	assertEquals("4", tree.remove(tree.root()));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	assertThrows(IllegalArgumentException.class, () -> tree.addRight(null, "1"));
    	assertEquals(0, tree.size());
    	one = tree.addRoot("1");
    	assertEquals(1, tree.size());
    	
    	two = tree.addRight(one, "2");
    	assertEquals(2, tree.size());
    	assertThrows(IllegalArgumentException.class, () -> tree.addRight(one, "3"));
    	
    	three = tree.addRight(two, "3");
    	assertEquals(3, tree.size());
    	
    	tree.remove(three);
    	
    	four = tree.addRight(two, "4");
    	
    	assertEquals(3, tree.size());
    	assertEquals("1", tree.remove(tree.root()));
    	assertEquals("2", tree.remove(tree.root()));
    	assertEquals("4", tree.remove(tree.root()));
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertThrows(IllegalArgumentException.class, () -> tree.remove(null));
        
        assertEquals("eight", tree.remove(eight));
        assertEquals(9, tree.size());

        assertEquals(null, tree.left(four));
        
        assertThrows(IllegalArgumentException.class, () -> tree.remove(ten));
        
        assertEquals("nine", tree.remove(nine));
        assertEquals(8, tree.size());
        assertEquals(0, tree.numChildren(four));
        
        assertNull(tree.remove(nine));
        assertEquals(8, tree.size());
        
        assertEquals("three", tree.remove(three));
        assertEquals(7, tree.size());
        assertEquals(four, tree.right(one));
        assertEquals(null, tree.parent(three));
        
        assertEquals("four", tree.remove(four));
        assertEquals(6, tree.size());
        assertEquals(null, tree.right(one));
        assertEquals(null, tree.sibling(two));
        
        assertEquals("one", tree.remove(one));
        assertEquals(5, tree.size());
        assertEquals(two, tree.root());
        
        assertNull(tree.remove(one));
        assertEquals(5, tree.size());
        assertNull(tree.parent(one));
        
        assertEquals("six", tree.remove(six));
        assertEquals(4, tree.size());
        assertEquals(1, tree.numChildren(two));
        
        assertEquals("seven", tree.remove(seven));
        assertEquals(3, tree.size());
        
        
        assertEquals("two", tree.remove(tree.root()));
        assertEquals(2, tree.size());
        
        assertEquals("ten", tree.remove(tree.root()));
        assertEquals(1, tree.size());
        assertEquals(null, tree.parent(ten));
        
        assertEquals("five", tree.remove(tree.root()));
        assertEquals(0, tree.size());
        
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.remove(one));
        assertEquals(0, tree.size());
        
        assertTrue(tree.isEmpty());
        assertNull(tree.root());
    }
    
    /**
     * Test the output of the toString() behavior
     */         
    @Test
    public void testToString() {
    	assertEquals("LinkedBinaryTree[\n]", tree.toString());
    	createTree();
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   eight\n   nine\n]", tree.toString());
    }
}
