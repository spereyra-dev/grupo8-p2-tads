package uy.edu.um.prog2.ad.tads.Queue.DoubleQueue;

import uy.edu.um.prog2.ad.tads.Queue.EmptyQueueException;

public interface MyDoubleQueue<T> {
    void enqueueLeft(T element);

    T dequeueLeft() throws EmptyQueueException;

    void enqueueRight(T element);

    T dequeueRight() throws EmptyQueueException;

    boolean isEmpty();
}
