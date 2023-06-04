public class Node <T extends Comparable<T>>{
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
