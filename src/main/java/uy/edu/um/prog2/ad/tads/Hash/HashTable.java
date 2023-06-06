package uy.edu.um.prog2.ad.tads.Hash;

public interface HashTable<K, V> {
    void put(K key, V value);

    V get(K key);

    boolean contains(K key);

    void remove(K key);

    int size();

    boolean isEmpty();
}
