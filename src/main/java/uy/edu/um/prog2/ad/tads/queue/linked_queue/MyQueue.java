package uy.edu.um.prog2.ad.tads.queue.linked_queue;

import uy.edu.um.prog2.ad.tads.queue.EmptyQueueException;

public interface MyQueue<T> {
    void enqueue(T element);

    T dequeue() throws EmptyQueueException;

    boolean isEmpty();
}
