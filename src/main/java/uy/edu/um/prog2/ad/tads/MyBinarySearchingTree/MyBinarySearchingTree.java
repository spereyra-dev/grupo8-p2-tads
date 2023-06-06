package uy.edu.um.prog2.ad.tads.MyBinarySearchingTree;

public interface MyBinarySearchingTree <K extends Comparable<K>, T>{

    T find(K key);
    void insert (K key, T data);
    void delete (K key);

}
