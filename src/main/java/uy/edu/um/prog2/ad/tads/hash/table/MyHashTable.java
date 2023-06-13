package uy.edu.um.prog2.ad.tads.hash.table;

import java.util.Enumeration;
import java.util.NoSuchElementException;
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
        this(1000); // Tamaño predeterminado de 10
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
            //capaz aca podemos poner una especie de resize
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
        return buckets[code].getValue(key);
    }


    @Override
    public void remove(K key) {
        int code = hashCode(key);
        HashNode<K, V> cleanNode = buckets[code].getNode(key);
        buckets[code].remove(cleanNode);
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

    @Override
    public V getOrDefault(K key, V defaultValue) {
        if (contains(key)) {
            return get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Enumeration<K> keys() {
        return new Enumeration<>() {
            int currentBucket = 0;
            HashNode<K, V> currentNode = buckets[currentBucket].getFirst();

            @Override
            public boolean hasMoreElements() {
                // Check if there are more elements in the current bucket
                if (currentNode != null) {
                    return true;
                }
                // Check if there are more buckets
                while (currentBucket < size - 1) {
                    currentBucket++;
                    currentNode = buckets[currentBucket].getFirst();
                    if (currentNode != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K nextElement() {
                if (!hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                K key = currentNode.getKey();
                // Move to the next node in the current bucket
                currentNode = currentNode.getNext();
                // Check if we need to move to the next bucket
                if (currentNode == null) {
                    while (currentBucket < size - 1) {
                        currentBucket++;
                        currentNode = buckets[currentBucket].getFirst();
                        if (currentNode != null) {
                            break;
                        }
                    }
                }
                return key;
            }
        };
    }

}
