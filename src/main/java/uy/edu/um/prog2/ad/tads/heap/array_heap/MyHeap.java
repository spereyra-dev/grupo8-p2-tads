package uy.edu.um.prog2.ad.tads.heap.array_heap;

public interface MyHeap<T extends Comparable<T>> {

    void add(T elemento) throws FullHeapException;

    T retrieveAndRemove() throws EmptyTreeException;

    int getSize();
}
