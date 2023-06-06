package uy.edu.um.prog2.ad.tads.linked_list.simple;

import uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics;

import java.util.Comparator;

public class LinkedList<T> implements ListaConGenerics<T> {
    private Nodo<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        Nodo<T> newNode = new Nodo<>(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Nodo<T> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public void remove(int position) {
        if (position < 0 || position >= this.size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        if (position == 0) {
            this.head = this.head.next;
        } else {
            Nodo<T> previous = this.head;
            Nodo<T> current = this.head.next;
            for (int i = 1; i < position; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
        }
        this.size--;
    }

    @Override
    public void remove(T value) {
        if (this.head.value.equals(value)) {
            this.head = this.head.next;
        } else {
            Nodo<T> previous = this.head;
            Nodo<T> current = this.head.next;
            while (current != null && !current.value.equals(value)) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
            }
        }
        this.size--;
    }

    @Override
    public T get(int position) {
        if (position < 0 || position >= this.size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        Nodo<T> current = this.head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public boolean contains(T value) {
        Nodo<T> current = this.head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Integer size() {
        return this.size;
    }

    public void addFirst(T value) {
        Nodo<T> newNode = new Nodo<>(value);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    public void addLast(T value) {
        Nodo<T> newNode = new Nodo<>(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Nodo<T> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (this.size <= 1) {
            return;
        }
        quickSort(this.head, null, comparator);
    }

    private void quickSort(Nodo<T> start, Nodo<T> end, Comparator<? super T> comparator) {
        if (start == end || start == null || start.next == null) {
            return;
        }
        Nodo<T> pivot = partition(start, end, comparator);
        if (pivot != null) {
            quickSort(start, pivot, comparator);
            quickSort(pivot.next, end, comparator);
        }
    }

    private Nodo<T> partition(Nodo<T> start, Nodo<T> end, Comparator<? super T> comparator) {
        Nodo<T> pivot = start;
        Nodo<T> current = start;
        T pivotValue = pivot.value;

        while (current != end && current.next != null) {
            if (comparator.compare(current.next.value, pivotValue) < 0) {
                swapValues(current.next, pivot.next);
                pivot = pivot.next;
            }
            current = current.next;
        }

        swapValues(start, pivot);
        return pivot;
    }

    private void swapValues(Nodo<T> node1, Nodo<T> node2) {
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

}