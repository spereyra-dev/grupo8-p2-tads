package uy.edu.um.prog2.ad.tads.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void testPushAndPop() {
        try {
            stack.push(5);
            stack.push(10);
            stack.push(2);

            assertEquals(Integer.valueOf(2), stack.pop());
            assertEquals(Integer.valueOf(10), stack.pop());
            assertEquals(Integer.valueOf(5), stack.pop());

            assertTrue(stack.isEmpty());
        } catch (EmptyStackException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testTop() {
        try {
            stack.push(5);
            stack.push(10);
            stack.push(2);

            assertEquals(Integer.valueOf(2), stack.top());
            assertEquals(3, stack.getSize());
        } catch (EmptyStackException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testEmptyStackPop() {
        try {
            assertThrows(EmptyStackException.class, () -> stack.pop());
        } catch (EmptyStackException e) {
            assertEquals("The stack is empty", e.getMessage());
        }
    }

    @Test
    void testEmptyStackTop() {
        try {
            assertThrows(EmptyStackException.class, () -> stack.top());
        } catch (EmptyStackException e) {
            assertEquals("The stack is empty", e.getMessage());
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(5);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testMakeEmpty() {
        stack.push(5);
        stack.push(10);
        stack.push(2);

        stack.makeEmpty();

        assertTrue(stack.isEmpty());
    }
}
