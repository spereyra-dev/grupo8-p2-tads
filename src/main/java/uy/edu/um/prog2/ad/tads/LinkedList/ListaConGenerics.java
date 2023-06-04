package uy.edu.um.prog2.ad.tads.LinkedList;

import java.util.Comparator;

public interface ListaConGenerics<T> {
    void add(T value);

    void remove(int position);

    void remove(T value);

    T get(int position);

    boolean contains(T value);

    Integer size();

    void sort(Comparator<? super T> comparator);
}
