package uy.edu.um.prog2.ad.tads.Tree;


public interface MyTree<K, T> {
    //Implemente los métodos insert, find y delete de forma recursiva.
    void insert(K key, T data, K parentKey);

    T find(K key);

    T findDisorder(K key);

    void delete(K key);

    boolean contains(K key);
}
