package uy.edu.um.prog2.ad.tads.Hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.tads.Hash.LinkedList.HashLinkedList;
import uy.edu.um.prog2.ad.tads.LinkedList.ListaConGenerics;

public class HashLinkedListTest {

    private ListaConGenerics<HashNode<String, Integer>> list;

    @BeforeEach
    public void setUp() {
        list = new HashLinkedList<>();
    }

    @Test
    public void testAddAndGet() {
        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        assertEquals(node1, list.get(0));
        assertEquals(node2, list.get(1));
        assertEquals(node3, list.get(2));
    }

    @Test
    public void testRemove() {
        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        list.remove(1);

        assertEquals(node1, list.get(0));
        assertEquals(node3, list.get(1));
        assertNull(list.get(2));
    }

    @Test
    public void testContains() {
        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        assertTrue(list.contains(node1));
        assertTrue(list.contains(node2));
        assertTrue(list.contains(node3));

        HashNode<String, Integer> node4 = new HashNode<>("four", 4);
        assertFalse(list.contains(node4));
    }

    @Test
    public void testGetSize() {
        assertEquals(0, list.size());

        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        assertEquals(3, list.size());

        list.remove(1);

        assertEquals(2, list.size());
    }

    @Test
    public void testGetValueByKey() {
        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        assertEquals(1, list.get(0).getValue());
        assertEquals(2, list.get(1).getValue());
        assertEquals(3, list.get(2).getValue());
        assertNull(list.get(4));
    }

    @Test
    public void testRemoveNode() {
        HashNode<String, Integer> node1 = new HashNode<>("one", 1);
        HashNode<String, Integer> node2 = new HashNode<>("two", 2);
        HashNode<String, Integer> node3 = new HashNode<>("three", 3);

        list.add(node1);
        list.add(node2);
        list.add(node3);

        list.remove(node2);

        assertTrue(list.contains(node1));
        assertFalse(list.contains(node2));
        assertTrue(list.contains(node3));
    }
}
