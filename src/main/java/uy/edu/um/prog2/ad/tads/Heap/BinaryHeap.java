package Heap;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    private Node<T> root;
    private int size;
    private boolean isMaxHeap;

    public BinaryHeap(boolean isMaxHeap) {
        this.root = null;
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isMaxHeap() {
        return isMaxHeap;
    }

    public void setMaxHeap(boolean maxHeap) {
        isMaxHeap = maxHeap;
    }

    private Node<T> findInsertionParent(int index) {
        String binaryRepresentation = Integer.toBinaryString(index);
        Node<T> currentNode = root;
        for (int i = 1; i < binaryRepresentation.length(); i++) {
            if (binaryRepresentation.charAt(i) == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return currentNode;
    }

    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    private void siftUp(Node<T> node) {
        while (node.parent != null && (
                (isMaxHeap && node.value.compareTo(node.parent.value) > 0) ||
                        (!isMaxHeap && node.value.compareTo(node.parent.value) < 0))) {
            swap(node, node.parent);
            node = node.parent;
        }
    }

    private void siftDown(Node<T> currentNode) {
        Node<T> largest = currentNode;
        Node<T> leftChild = currentNode.left;
        Node<T> rightChild = currentNode.right;

        if (leftChild != null && leftChild.value.compareTo(largest.value) > 0) {
            largest = leftChild;
        }

        if (rightChild != null && rightChild.value.compareTo(largest.value) > 0) {
            largest = rightChild;
        }

        if (largest != currentNode) {
            swap(currentNode, largest);
            siftDown(largest);
        }
    }

    private Node<T> findLastNode() {
        int lastNodeIndex = this.size - 1;
        String binaryRepresentation = Integer.toBinaryString(lastNodeIndex);

        Node<T> currentNode = root;
        for (int i = 1; i < binaryRepresentation.length(); i++) {
            if (binaryRepresentation.charAt(i) == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return currentNode;
    }
    public Node<T> findNode(T value) {
        return findNode(root, value);
    }

    private Node<T> findNode(Node<T> currentNode, T value) {
        if (currentNode == null || currentNode.value.equals(value)) {
            return currentNode;
        }

        Node<T> leftResult = findNode(currentNode.left, value);
        if (leftResult != null) {
            return leftResult;
        }

        Node<T> rightResult = findNode(currentNode.right, value);
        if (rightResult != null) {
            return rightResult;
        }

        return null;
    }

    @Override
    public void insert(T element) {
        Node<T> newNode= new Node<>(element);
        if (isEmpty()){
            root=newNode;
        } else{
            Node<T> parent = findInsertionParent(size+1);
            if (parent.left==null){
                parent.left=newNode;
            } else{
                parent.right=newNode;
            }
            newNode.parent=parent;
            siftUp(newNode);
        }
        size++;

    }

    @Override
    public T extractLastNode() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap.Heap is empty");
        }
        T rootValue = root.value;
        Node<T> lastNode = findLastNode();
        if (lastNode != root) {
            root.value = lastNode.value;
            siftDown(root);
        }
        if (lastNode.parent != null) {
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }
        }
        size--;
        return rootValue;

    }
    @Override
    public T extractValue(T value){
        Node<T> node = findNode(value);
        if (node != null) {
            T extractedValue = node.value;
            deleteNode(node);
            return extractedValue;
        }
        return null;
    }

    private void deleteNode(Node<T> node) {
        T lastValue = findLastNode().value;
        node.value = lastValue;
        siftUp(node);
        siftDown(node);
    }
    @Override
    public T peek() {
        if (isEmpty()){
            throw new IllegalStateException("Está vacío");
        }
        return root.value;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }
}
