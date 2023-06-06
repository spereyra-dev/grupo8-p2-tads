package uy.edu.um.prog2.ad.tads.heap.binary_node;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {
    private final boolean isMaxHeap;
    private Node<T> root;
    private int size;

    public BinaryHeap(boolean isMaxHeap) {
        this.root = null;
        this.size = 0;
        this.isMaxHeap = isMaxHeap;
    }

    private Node<T> findInsertionParent(int index) {
        if (index <= 0) {
            return root; // Devolver el nodo raíz cuando el índice es menor o igual a 0
        }
        int parentIndex = (index - 1) / 2;
        return findNodeByIndex(root, parentIndex);
    }

    private Node<T> findNodeByIndex(Node<T> currentNode, int index) {
        while (index > 0 && currentNode != null) {
            if (index % 2 == 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
            index = (index - 1) / 2;
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

        if (isMaxHeap) {
            if (leftChild != null && leftChild.value.compareTo(largest.value) > 0) {
                largest = leftChild;
            }

            if (rightChild != null && rightChild.value.compareTo(largest.value) > 0) {
                largest = rightChild;
            }
        } else {
            if (leftChild != null && leftChild.value.compareTo(largest.value) < 0) {
                largest = leftChild;
            }

            if (rightChild != null && rightChild.value.compareTo(largest.value) < 0) {
                largest = rightChild;
            }
        }

        if (largest != currentNode) {
            swap(currentNode, largest);
            siftDown(largest);
        }
    }

    private Node<T> findLastNode() {
        int index = size - 1;
        Node<T> currentNode = root;
        while (index > 0 && currentNode != null) {
            if (index % 2 == 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
            index = (index - 1) / 2;
        }
        if (currentNode == null) {
            currentNode = root; // El último nodo es el root
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

        return findNode(currentNode.right, value);
    }

    @Override
    public void insert(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            root = newNode;
        } else {
            Node<T> parent = findInsertionParent(size);
            if (parent.left == null) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.parent = parent;
            siftUp(newNode);
        }
        size++;
    }

    @Override
    public T extractRoot() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T rootValue = root.value;
        if (size == 1) {
            root = null;
        } else {
            Node<T> lastParent = findNodeByIndex(root, (size - 2) / 2);
            Node<T> lastNode;
            if (lastParent.right != null) {
                lastNode = lastParent.right;
                lastParent.right = null;
            } else {
                lastNode = lastParent.left;
                lastParent.left = null;
            }
            root.value = lastNode.value;
            siftDown(root);
        }
        size--; // Decrementar el tamaño antes de extraer la raíz
        return rootValue;
    }

    @Override
    public T extractValue(T value) {
        Node<T> node = findNode(value);
        if (node != null) {
            T extractedValue = node.value;
            deleteNode(node);
            if (size == 0) {
                root = null; // Establecer root en null si el árbol está vacío
            }
            return extractedValue;
        }
        return null;
    }

    private void deleteNode(Node<T> node) {
        if (node == root) {
            extractRoot();
        } else {
            node.value = findLastNode().value;
            siftUp(node);
            siftDown(node);
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return root != null ? root.value : null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}