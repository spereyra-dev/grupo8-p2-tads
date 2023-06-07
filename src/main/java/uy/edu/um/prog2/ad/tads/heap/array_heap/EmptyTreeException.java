package uy.edu.um.prog2.ad.tads.heap.array_heap;

public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException() {
        super("El árbol está vacío");
    }
}
