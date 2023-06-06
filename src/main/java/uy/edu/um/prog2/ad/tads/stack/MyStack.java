package uy.edu.um.prog2.ad.tads.stack;

import java.util.EmptyStackException;

public interface MyStack<T> {
    T pop() throws EmptyStackException;

    T top() throws EmptyStackException;

    void push(T element);

    boolean isEmpty();

    void makeEmpty();
}
