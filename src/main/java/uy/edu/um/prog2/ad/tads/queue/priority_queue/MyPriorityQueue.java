package uy.edu.um.prog2.ad.tads.queue.priority_queue;

import uy.edu.um.prog2.ad.tads.queue.linked_queue.MyQueue;

public interface MyPriorityQueue<T> extends MyQueue<T> {
    /* inserta de acuerdo a la prioridad; si hay más de un elemento con la misma prioridad, inserta al final */
    void enqueueWithPriority(T element, int prioridad);
}
