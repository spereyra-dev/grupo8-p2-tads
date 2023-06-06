package uy.edu.um.prog2.ad.tads.Heap.ArrayHeap;

public class EmptyTreeException extends Exception {
    public EmptyTreeException() {
        super("El árbol está vacío");
    }
}
