package uy.edu.um.prog2.ad.tads.Tree;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class TreeImplTest {
    MyTree<Integer, String> tree = new TreeImpl<>();

    @Test
    void insert() {
        // Insertar nodos en el árbol binario utilizando parentKey
        tree.insert(50, "A", null);  // Insertar nodo raíz

        tree.insert(50, "B", 50);  // Insertar nodo hijo izquierdo de 50
        tree.insert(70, "C", 50);  // Insertar nodo hijo derecho de 50

        tree.insert(20, "D", 30);  // Insertar nodo hijo izquierdo de 30
        tree.insert(40, "E", 30);  // Insertar nodo hijo derecho de 30

        tree.insert(60, "F", 70);  // Insertar nodo hijo izquierdo de 70
        tree.insert(80, "G", 70);  // Insertar nodo hijo derecho de 70

        // Realizar pruebas y afirmaciones
        assert tree.find(50).equals("A");
        assertNotEquals("B", tree.find(50));
        assert tree.find(20).equals("D");
    }

    @Test
    void insertTest() {
        // Insertar nodos en el árbol binario utilizando parentKey
        tree.insert(50, "A", null);  // Insertar nodo raíz

        tree.insert(70, "B", 50);  // Insertar nodo hijo izquierdo de 50
        tree.insert(30, "C", 50);  // Insertar nodo hijo derecho de 50


        // Realizar pruebas y afirmaciones
        assert tree.findDisorder(50).equals("A");
        assert tree.findDisorder(70).equals("B");
        assert tree.findDisorder(30).equals("C");
    }

    @Test
    void find() {
    }

    @Test
    void delete() {
    }
}