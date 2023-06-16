package uy.edu.um.prog2.ad.tads.queue.double_queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.tads.queue.EmptyQueueException;

class DoubleQueueTest {

    DoubleQueue<Integer> queue = new DoubleQueue<>();

    @Test
    @DisplayName("usando enqueueLeft, dequeueLeft y dequeueRight devuelve los valores correctos")
    void testDequeueLeft() throws EmptyQueueException {
        queue.enqueueLeft(1);
        queue.enqueueLeft(2);
        queue.enqueueLeft(3);

        assertEquals(3, queue.dequeueLeft());
        assertEquals(1, queue.dequeueRight());
    }

    @Test
    @DisplayName("usando enqueueRight, dequeueLeft y dequeueRight devuelve los valores correctos")
    void testDequeueRight() throws EmptyQueueException {
        queue.enqueueRight(1);
        queue.enqueueRight(2);
        queue.enqueueRight(3);

        assertEquals(3, queue.dequeueRight());
        assertEquals(1, queue.dequeueLeft());
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsNotEmpty() {
        queue.enqueueLeft(1);
        assertFalse(queue.isEmpty());
    }
}