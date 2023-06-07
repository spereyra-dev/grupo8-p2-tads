package uy.edu.um.prog2.ad.tads.tree.binary.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BinarySearchingTreeImplTest {
    MyBinarySearchingTree<Integer, String> bst = new BinarySearchingTreeImpl<>();

    @Test
    public void testInsertAndFind() {
        bst.insert(50, "A");
        bst.insert(30, "B");
        bst.insert(70, "C");
        bst.insert(20, "D");
        bst.insert(40, "E");
        bst.insert(60, "F");
        bst.insert(80, "G");

        assertEquals("A", bst.find(50));
        assertEquals("B", bst.find(30));
        assertEquals("C", bst.find(70));
        assertEquals("D", bst.find(20));
        assertEquals("E", bst.find(40));
        assertEquals("F", bst.find(60));
        assertEquals("G", bst.find(80));
        assertNull(bst.find(10));
    }

    @Test
    public void testDelete() {
        bst.insert(50, "A");
        bst.insert(30, "B");
        bst.insert(70, "C");
        bst.insert(20, "D");
        bst.insert(40, "E");
        bst.insert(60, "F");
        bst.insert(80, "G");

        bst.delete(30);
        assertNull(bst.find(30));

        bst.delete(50);
        assertNull(bst.find(50));
        assertEquals("F", bst.find(60)); // El nuevo nodo raíz debe ser el sucesor inorden del nodo eliminado
        assertEquals("C", bst.find(70)); // El hijo derecho del nodo eliminado se convierte en hijo derecho del nuevo nodo raíz
    }

    @Test
    void testTreeString() {
        MyBinarySearchingTree<String, Integer> bst = new BinarySearchingTreeImpl<>();
        bst.insert("A", 1);
        bst.insert("B", 2);
        bst.insert("C", 3);
        bst.insert("D", 4);
        assertEquals(3, bst.find("C"));
    }

}
