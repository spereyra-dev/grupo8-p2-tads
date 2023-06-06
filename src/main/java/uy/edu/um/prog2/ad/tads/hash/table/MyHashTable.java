package uy.edu.um.prog2.ad.tads.hash.table;

import uy.edu.um.prog2.ad.tads.hash.HashNode;
import uy.edu.um.prog2.ad.tads.hash.HashTable;
import uy.edu.um.prog2.ad.tads.hash.linked_list.HashLinkedList;

public class MyHashTable<K, V> implements HashTable<K, V> {

    HashLinkedList<K, V>[] buckets;
    int size;

    public MyHashTable(int size) {
        this.size = size;
        buckets = (HashLinkedList<K, V>[]) new HashLinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new HashLinkedList<>();
        }
    }

    public MyHashTable() {
        this(10); // Tamaño predeterminado de 10
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, V value) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        if (contains(key)) {
            HashNode<K, V> existingNode = bucket.getNode(key);
            existingNode.setValue(value);
        } else {
            bucket.add(new HashNode<>(key, value));
        }
    }

    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        HashNode<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    @Override
    public V get(K key) {
        int code = hashCode(key);
        return buckets[code].get(key);
    }


    @Override
    public void remove(K key) {
        int code = hashCode(key);
        HashNode<K, V> cleannode = buckets[code].getNode(key);
        buckets[code].remove(cleannode);
    }

    @Override
    public int size() {
        int count = 0;
        for (HashLinkedList<K, V> bucket : buckets) {
            count += bucket.size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
