package Heap;

public interface Heap <T extends Comparable<T>>{
    void insert(T element);
    T extractLastNode();
    public T extractValue(T value);
    T peek();
    boolean isEmpty();
    int size();
}
