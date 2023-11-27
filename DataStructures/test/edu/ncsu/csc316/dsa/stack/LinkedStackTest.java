package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LinkedStack.
 * Checks the expected outputs of the Stack abstract data type behaviors when using
 * a singly-linked list data structure
 *
 * @author Dr. King
 *
 */
public class LinkedStackTest {

	/** Stack object for testing */
    private Stack<String> stack;
    
    /**
     * Create a new instance of a linked list-based stack before each test case executes
     */
    @Before
    public void setUp() {
        stack = new LinkedStack<String>();
    }
    
    /**
     * Test the output of the push(e) behavior
     */ 
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals("one", stack.top());
        
        stack.push("two");
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals("two", stack.top());
        
        stack.push("three");
        assertEquals(3, stack.size());
        assertEquals("three", stack.top());
        
        stack.push("four");
        assertEquals(4, stack.size());
        assertEquals("four", stack.top());
        
        stack.pop();
        assertEquals(3, stack.size());
        assertEquals("three", stack.top());
        
        stack.push("five");
        assertEquals(4, stack.size());
        assertEquals("five", stack.top());
    }

    /**
     * Test the output of the pop() behavior, including expected exceptions
     */
    @Test
    public void testPop() {
        assertEquals(0, stack.size());
        
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        assertEquals("one", stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        stack.push("one");
        stack.push("two");
        stack.push("three");
        stack.push("four");
        stack.push("five");
        
        assertEquals(5, stack.size());
        assertFalse(stack.isEmpty());
        
        assertEquals("five", stack.pop());
        assertEquals(4, stack.size());
        assertEquals("four", stack.pop());
        assertEquals(3, stack.size());
        assertEquals("three", stack.pop());
        assertEquals(2, stack.size());
        assertEquals("two", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("one", stack.pop());
        
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
    }

    /**
     * Test the output of the top() behavior, including expected exceptions
     */
    @Test
    public void testTop() { 
        assertEquals(0, stack.size());
        try {
            stack.top();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        assertEquals("one", stack.top());
        
        stack.pop();
        assertEquals(0, stack.size());
        try {
            stack.top();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        stack.push("two");
        stack.push("three");
        assertEquals("three", stack.top());
        stack.push("four");
        stack.push("five");
        assertEquals("five", stack.top());
        assertEquals("five", stack.top());
        
        stack.pop();
        assertEquals("four", stack.top());
    }

}
