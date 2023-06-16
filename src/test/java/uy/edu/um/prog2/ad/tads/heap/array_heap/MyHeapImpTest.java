package uy.edu.um.prog2.ad.tads.heap.array_heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class MyHeapImpTest {
    private final MyHeapImp<Integer> maxHeap = new MyHeapImp<>(true);
    private final MyHeapImp<Integer> minHeap = new MyHeapImp<>(false);

    @Test
    void testMaxHeapAddAndRetrieveAndRemove() {
        try {
            maxHeap.add(5);
            maxHeap.add(10);
            maxHeap.add(2);
            maxHeap.add(8);

            assertEquals(4, maxHeap.getSize());

            assertEquals(Integer.valueOf(10), maxHeap.retrieveAndRemove());
            assertEquals(3, maxHeap.getSize());

            assertEquals(Integer.valueOf(8), maxHeap.retrieveAndRemove());
            assertEquals(2, maxHeap.getSize());

            assertEquals(Integer.valueOf(5), maxHeap.retrieveAndRemove());
            assertEquals(1, maxHeap.getSize());

            assertEquals(Integer.valueOf(2), maxHeap.retrieveAndRemove());
            assertEquals(0, maxHeap.getSize());
        } catch (FullHeapException | EmptyTreeException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testMinHeapAddAndRetrieveAndRemove() {
        try {
            minHeap.add(5);
            minHeap.add(10);
            minHeap.add(2);
            minHeap.add(8);

            assertEquals(4, minHeap.getSize());

            assertEquals(Integer.valueOf(2), minHeap.retrieveAndRemove());
            assertEquals(3, minHeap.getSize());

            assertEquals(Integer.valueOf(5), minHeap.retrieveAndRemove());
            assertEquals(2, minHeap.getSize());

            assertEquals(Integer.valueOf(8), minHeap.retrieveAndRemove());
            assertEquals(1, minHeap.getSize());

            assertEquals(Integer.valueOf(10), minHeap.retrieveAndRemove());
            assertEquals(0, minHeap.getSize());
        } catch (FullHeapException | EmptyTreeException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testEmptyHeapRetrieveAndRemove() {
        assertThrows(EmptyTreeException.class, maxHeap::retrieveAndRemove);
    }

    @Test
    void testFullHeapAdd() {
        try {
            maxHeap.add(5);
            maxHeap.add(10);
            maxHeap.add(2);
            maxHeap.add(8);
            maxHeap.add(15);
            maxHeap.add(7);
            maxHeap.add(12);
            maxHeap.add(1);
            maxHeap.add(9);
            maxHeap.add(3);

            assertThrows(FullHeapException.class, () -> maxHeap.add(20));
        } catch (FullHeapException e) {
            assertEquals("The heap is full", e.getMessage());
        }
    }

    @Test
    void testConstructorWithGivenCapacity() throws FullHeapException {
        MyHeapImp<Integer> heap = new MyHeapImp<>(true, 5);
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(5);
        assertThrows(FullHeapException.class, () -> heap.add(5));
    }
}
