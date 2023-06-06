package uy.edu.um.prog2.ad.tads.linked_list;

public interface Lista {
    void add(Object value);

    void remove(int position);

    Object get(int position);

    boolean contains(Object value);

    Integer size();
}
