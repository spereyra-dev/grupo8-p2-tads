package uy.edu.um.prog2.ad.tads.queue;

public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super("La cola está vacía");
    }
}
