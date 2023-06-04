package uy.edu.um.prog2.ad.tads.Queue.LinkedQueue;

import uy.edu.um.prog2.ad.tads.Queue.EmptyQueueException;

public interface MyQueue<T> {
    void enqueue(T element);

    T dequeue() throws EmptyQueueException;

    boolean isEmpty();
}
