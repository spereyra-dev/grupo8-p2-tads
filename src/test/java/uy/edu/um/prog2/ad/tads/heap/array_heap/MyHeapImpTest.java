package uy.edu.um.prog2.ad.tads.heap.array_heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyHeapImpTest {

    private MyHeap<Integer> minHeap;
    private MyHeap<Integer> maxHeap;

    @BeforeEach
    public void setup() {
        minHeap = new MyHeapImp<>(true);
        maxHeap = new MyHeapImp<>(false);
    }

    @Test
    public void testInsertAndGetMin() {
        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(8);
        minHeap.insert(1);

        assertEquals(1, minHeap.get());
    }

    @Test
    public void testInsertAndGetMax() {
        maxHeap.insert(5);
        maxHeap.insert(2);
        maxHeap.insert(8);
        maxHeap.insert(1);

        assertEquals(8, maxHeap.get());
    }

    @Test
    public void testDeleteMin() {
        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(8);
        minHeap.insert(1);

        assertEquals(1, minHeap.delete());
        assertEquals(2, minHeap.get());
    }

    @Test
    public void testDeleteMax() {
        maxHeap.insert(5);
        maxHeap.insert(2);
        maxHeap.insert(8);
        maxHeap.insert(1);

        assertEquals(8, maxHeap.delete());
        assertEquals(5, maxHeap.get());
    }

    @Test
    public void testSize() {
        assertEquals(0, minHeap.size());

        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(8);
        minHeap.insert(1);

        assertEquals(4, minHeap.size());
    }
}