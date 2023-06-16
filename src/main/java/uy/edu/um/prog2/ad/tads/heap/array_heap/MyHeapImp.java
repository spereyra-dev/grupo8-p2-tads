package uy.edu.um.prog2.ad.tads.heap.array_heap;

public class MyHeapImp<T extends Comparable<T>> implements MyHeap<T> {

    private static final int DEFAULT_CAPACITY = 10; // Maximum initial size
    private final boolean isMax; // True if it's a max heap, False if it's a min heap
    private final T[] array;
    private int size;

    public MyHeapImp(boolean isMax) {
        this.array = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
        this.isMax = isMax;
    }

    public MyHeapImp(boolean isMax, int initialCapacity) {
        this.array = (T[]) new Comparable[initialCapacity];
        this.size = 0;
        this.isMax = isMax;
    }

    // The children of i are at 2i+1 and 2i+2
// The parent of i is at (i-1)/2
    @Override
    public void add(T element) throws FullHeapException {
        if (element != null) {
            if (this.size < array.length && this.size != 0) {
                // There is space to add
                array[size] = element; // Add it at the end of the array
                int i = size;
                this.size++; // Increase the size

                if (this.isMax) {
                    // It's a max heap

                    // Sort it until the parent is greater than the element or it becomes the root.
                    // The parent of i is at (i-1)/2
                    while (i > 0 && array[(i - 1) / 2].compareTo(element) < 0) {
                        // Swap parent with the element
                        array[i] = array[(i - 1) / 2];
                        array[(i - 1) / 2] = element;
                        i = (i - 1) / 2;
                    }
                } else {
                    // It's a min heap

                    // Sort it until the parent is smaller than the element or it becomes the root.
                    // The parent of i is at (i-1)/2
                    while (i > 0 && array[(i - 1) / 2].compareTo(element) > 0) {
                        // Swap parent with the element
                        array[i] = array[(i - 1) / 2];
                        array[(i - 1) / 2] = element;
                        i = (i - 1) / 2;
                    }
                }
            } else if (this.size == 0) {
                // The array is empty, add the element at position 0
                array[0] = element;
                size++;
            } else {
                throw new FullHeapException();
            }
        } else {
            throw new IllegalArgumentException("The element cannot be null");
        }
    }

    @Override
    public T retrieveAndRemove() throws EmptyTreeException {
        if (this.size == 0) {
            // Empty tree case
            throw new EmptyTreeException();
        } else if (this.size == 1) {
            // Only one element case
            T toReturn = array[0];
            this.array[0] = null;
            size = 0;
            return toReturn;
        } else {
            // Common case with multiple elements
            T toReturn = array[0]; // Save the first element to return it later
            this.array[0] = array[size - 1]; // Put the last element at the beginning
            size--;
            // Reorder until:
            // The children have all keys smaller than the node's key
            // or the node is a leaf
            if (this.isMax) {
                // Max Heap case
                int currentIdx = 0;
                while (true) {
                    int leftChildIdx = 2 * currentIdx + 1;
                    int rightChildIdx = 2 * currentIdx + 2;
                    int largestChildIdx = currentIdx;

                    // Compare with the left child
                    if (array[leftChildIdx] != null) {
                        if (leftChildIdx < size && array[leftChildIdx].compareTo(array[largestChildIdx]) > 0) {
                            largestChildIdx = leftChildIdx;
                        }
                    }

                    // Compare with the right child
                    if (array[rightChildIdx] != null) {
                        if (rightChildIdx < size && array[rightChildIdx].compareTo(array[largestChildIdx]) > 0) {
                            largestChildIdx = rightChildIdx;
                        }
                    }

                    if (largestChildIdx == currentIdx) {
                        // The current node is the largest, so the heap is sorted
                        break;
                    }

                    // Swap the current node with the largest child
                    T temp = array[currentIdx];
                    array[currentIdx] = array[largestChildIdx];
                    array[largestChildIdx] = temp;

                    // Move one level down the tree
                    currentIdx = largestChildIdx;
                }
            } else {
                // Min Heap case
                int currentIdx = 0;
                while (true) {
                    int leftChildIdx = 2 * currentIdx + 1;
                    int rightChildIdx = 2 * currentIdx + 2;
                    int smallestChildIdx = currentIdx;

                    // Compare with the left child
                    if (leftChildIdx < size && array[leftChildIdx] != null &&
                            array[leftChildIdx].compareTo(array[smallestChildIdx]) < 0) {
                        smallestChildIdx = leftChildIdx;
                    }

                    // Compare with the right child
                    if (rightChildIdx < size && array[rightChildIdx] != null &&
                            array[rightChildIdx].compareTo(array[smallestChildIdx]) < 0) {
                        smallestChildIdx = rightChildIdx;
                    }

                    if (smallestChildIdx == currentIdx) {
                        // The current node is the smallest, so it's already sorted
                        break;
                    }

                    // Swap the current node with the smallest child
                    T temp = array[currentIdx];
                    array[currentIdx] = array[smallestChildIdx];
                    array[smallestChildIdx] = temp;

                    // Move one level down the tree
                    currentIdx = smallestChildIdx;
                }
            }

            return toReturn;
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

}
