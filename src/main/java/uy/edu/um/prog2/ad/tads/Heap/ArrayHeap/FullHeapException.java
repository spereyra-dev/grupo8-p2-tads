package uy.edu.um.prog2.ad.tads.Heap.ArrayHeap;

public class FullHeapException extends Throwable {
    public FullHeapException() {
        super("El heap está lleno");
    }
}
