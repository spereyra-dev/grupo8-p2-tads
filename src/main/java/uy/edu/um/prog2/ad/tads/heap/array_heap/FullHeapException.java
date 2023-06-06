package uy.edu.um.prog2.ad.tads.heap.array_heap;

public class FullHeapException extends Throwable {
    public FullHeapException() {
        super("El heap está lleno");
    }
}
