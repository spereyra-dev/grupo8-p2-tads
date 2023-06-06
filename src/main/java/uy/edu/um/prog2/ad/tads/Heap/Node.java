package uy.edu.um.prog2.ad.tads.Heap;

public class Node<T> {
    public T value;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
