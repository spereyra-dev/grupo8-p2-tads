package uy.edu.um.prog2.ad.tads.linked_list.simple;

class Nodo<T> {
    T value;
    Nodo<T> next;

    public Nodo(T value) {
        this.value = value;
        this.next = null;
    }

    //Getters y setters de Value y de Next, necesarios para poder acceder a ellos desde la clase LinkedList
    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}