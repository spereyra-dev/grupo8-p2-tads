package uy.edu.um.prog2.ad.tads.tree.binary.basic;

public class TreeImpl<K extends Comparable<K>, T> implements MyTree<K, T> {
    private Node<K, T> root;

    public TreeImpl() {
        this.root = null;
    }

    private T findRecursive(Node<K, T> node, K key) {
        if (node == null) {
            return null;
        }

        int comparison = compareKeys(key, node.key);

        if (comparison == 0) {
            return node.data;
        } else if (comparison < 0) {
            return findRecursive(node.leftChild, key);
        } else {
            return findRecursive(node.rightChild, key);
        }
    }

    private Node<K, T> insertRecursive(Node<K, T> node, K key, T data, K parentKey) {
        if (node == null) {
            return new Node<>(key, data);
        }

        int comparison = compareKeys(parentKey, node.key);
        int comparisonActualKey = compareKeys(parentKey, key);
        if (comparison == 0) {
            if (node.leftChild == null && comparisonActualKey > 0) {
                node.leftChild = new Node<>(key, data);
            } else if (node.rightChild == null && comparisonActualKey < 0) {
                node.rightChild = new Node<>(key, data);
            } else if (comparisonActualKey == 0 && node.leftChild == null) {
                node.leftChild = new Node<>(key, data);
            } else if (comparisonActualKey == 0 && node.rightChild == null) {
                node.rightChild = new Node<>(key, data);
            } else {
                throw new IllegalArgumentException("Parent node already has two children.");
            }
        } else if (comparison < 0) {
            node.leftChild = insertRecursive(node.leftChild, key, data, parentKey);
        } else {
            node.rightChild = insertRecursive(node.rightChild, key, data, parentKey);
        }

        return node;
    }

    private Node<K, T> deleteRecursive(Node<K, T> node, K key) {
        if (node == null) {
            return null;
        }

        int comparison = compareKeys(key, node.key);

        if (comparison < 0) {
            node.leftChild = deleteRecursive(node.leftChild, key);
        } else if (comparison > 0) {
            node.rightChild = deleteRecursive(node.rightChild, key);
        } else {
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            }

            Node<K, T> successor = findMinimum(node.rightChild);
            node.key = successor.key;
            node.data = successor.data;
            node.rightChild = deleteRecursive(node.rightChild, successor.key);
        }

        return node;
    }


    private Node<K, T> findMinimum(Node<K, T> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    @Override
    public void insert(K key, T data, K parentKey) {
        root = insertRecursive(root, key, data, parentKey);
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private int compareKeys(K key1, K key2) {
        return key1.compareTo(key2);
    }

    private boolean containsRecursive(Node<K, T> node, K key) {
        if (node == null) {
            return false;
        }

        if (node.key.equals(key)) {
            return true;
        }

        boolean leftResult = containsRecursive(node.leftChild, key);
        boolean rightResult = containsRecursive(node.rightChild, key);

        return leftResult || rightResult;
    }

    @Override
    public boolean contains(K key) {
        return containsRecursive(root, key);
    }

    private T findRecursiveDisorder(Node<K, T> node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            return node.data;
        }

        T leftResult = findRecursiveDisorder(node.leftChild, key);
        T rightResult = findRecursiveDisorder(node.rightChild, key);

        if (leftResult != null) {
            return leftResult;
        } else {
            return rightResult;
        }
    }

    @Override
    public T findDisorder(K key) {
        return findRecursiveDisorder(root, key);
    }
}
