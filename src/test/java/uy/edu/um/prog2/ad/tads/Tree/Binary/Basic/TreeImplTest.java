package uy.edu.um.prog2.ad.tads.Tree.Binary.Basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testDeleteLeafNode() {
        // Insertar nodos en el árbol binario utilizando parentKey
        tree.insert(50, "A", null);  // Insertar nodo raíz

        tree.insert(50, "B", 50);  // Insertar nodo hijo izquierdo de 50
        tree.insert(70, "C", 50);  // Insertar nodo hijo derecho de 50

        tree.insert(20, "D", 30);  // Insertar nodo hijo izquierdo de 30
        tree.insert(40, "E", 30);  // Insertar nodo hijo derecho de 30

        tree.insert(60, "F", 70);  // Insertar nodo hijo izquierdo de 70
        tree.insert(80, "G", 70);  // Insertar nodo hijo derecho de 70
        tree.delete(20);

        assertNull(tree.find(20));
        assertNull(tree.findDisorder(20));
        assertFalse(tree.contains(20));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        // Insertar nodos en el árbol binario utilizando parentKey
        tree.insert(50, "A", null);  // Insertar nodo raíz

        tree.insert(50, "B", 50);  // Insertar nodo hijo izquierdo de 50
        tree.insert(70, "C", 50);  // Insertar nodo hijo derecho de 50

        tree.insert(20, "D", 30);  // Insertar nodo hijo izquierdo de 30
        tree.insert(40, "E", 30);  // Insertar nodo hijo derecho de 30

        tree.insert(60, "F", 70);  // Insertar nodo hijo izquierdo de 70
        tree.insert(80, "G", 70);  // Insertar nodo hijo derecho de 70
        tree.delete(30);

        assertNull(tree.find(30));
        assertNull(tree.findDisorder(30));
        assertFalse(tree.contains(30));

        assertEquals("E", tree.find(40));
        assertEquals("E", tree.findDisorder(40));
        assertTrue(tree.contains(40));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        // Insertar nodos en el árbol binario utilizando parentKey
        tree.insert(50, "A", null);  // Insertar nodo raíz

        tree.insert(50, "B", 50);  // Insertar nodo hijo izquierdo de 50
        tree.insert(70, "C", 50);  // Insertar nodo hijo derecho de 50

        tree.insert(20, "D", 30);  // Insertar nodo hijo izquierdo de 30
        tree.insert(40, "E", 30);  // Insertar nodo hijo derecho de 30

        tree.insert(60, "F", 70);  // Insertar nodo hijo izquierdo de 70
        tree.insert(80, "G", 70);  // Insertar nodo hijo derecho de 70
        tree.delete(50);

        assertTrue(tree.contains(50));

        assertEquals("B", tree.find(50)); // El nuevo nodo raíz debe ser el hijo izquierdo original
        assertEquals("B", tree.findDisorder(50));
        assertTrue(tree.contains(50));

        assertEquals("C", tree.find(70)); // El hijo derecho del nodo eliminado se convierte en hijo derecho del nuevo nodo raíz
        assertEquals("C", tree.findDisorder(70));
        assertTrue(tree.contains(70));
    }
}
