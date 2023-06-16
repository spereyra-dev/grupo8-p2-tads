package uy.edu.um.prog2.ad.tads.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.tads.heap.binary_node.BinaryHeap;
import uy.edu.um.prog2.ad.tads.heap.binary_node.Heap;

class BinaryHeapTest {

    private Heap<Integer> maxHeap;
    private Heap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new BinaryHeap<>(true);
        minHeap = new BinaryHeap<>(false);
    }

    @Test
    public void testInsert() {
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);

        assertEquals(8, maxHeap.peek());
        assertEquals(5, maxHeap.size());

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.peek());
        assertEquals(5, minHeap.size());
    }

    @Test
    public void testExtractRoot() {
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.insert(7);

        assertEquals(8, maxHeap.extractRoot());
        assertEquals(7, maxHeap.peek());
        assertEquals(5, maxHeap.size());

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.extractRoot());
        assertEquals(3, minHeap.peek());
        assertEquals(4, minHeap.size());
    }

    @Test
    public void testExtractValue() {
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);

        assertEquals(8, maxHeap.extractValue(8));
        assertEquals(7, maxHeap.peek());
        assertEquals(4, maxHeap.size());

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.extractValue(1));
        assertEquals(3, minHeap.peek());
        assertEquals(4, minHeap.size());
    }

    @Test
    public void testPeek() {
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);

        assertEquals(8, maxHeap.peek());

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(maxHeap.isEmpty());
        assertTrue(minHeap.isEmpty());

        maxHeap.insert(5);
        assertFalse(maxHeap.isEmpty());

        minHeap.insert(5);
        assertFalse(minHeap.isEmpty());

        maxHeap.extractRoot();
        assertTrue(maxHeap.isEmpty());

        minHeap.extractRoot();
        assertTrue(minHeap.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, maxHeap.size());
        assertEquals(0, minHeap.size());

        maxHeap.insert(5);
        assertEquals(1, maxHeap.size());

        minHeap.insert(5);
        assertEquals(1, minHeap.size());

        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.extractRoot();
        assertEquals(4, maxHeap.size());

        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);
        assertEquals(5, minHeap.size());


        assertEquals(4, maxHeap.size());

        minHeap.extractRoot();
        assertEquals(4, minHeap.size());
    }
}
