package uy.edu.um.prog2.ad.tads.heap.binary_node;

public interface Heap<T extends Comparable<T>> {
    void insert(T element);

    T extractRoot();

    T extractValue(T value);

    T peek();

    boolean isEmpty();

    int size();
}
