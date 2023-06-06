package uy.edu.um.prog2.ad.tads.MyBinarySearchingTree;

public class NodeBST <K extends Comparable<K>, T> {
    K key;
    T data;
    NodeBST<K, T> leftChild;
    NodeBST<K, T> rightChild;

    public NodeBST(K key, T data) {
        this.key=key;
        this.data=data;
        this.leftChild=null;
        this.rightChild=null;
    }




}
