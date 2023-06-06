package uy.edu.um.prog2.ad.tads.hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.tads.hash.table.MyHashTable;

class MyHashTableTest {

    private HashTable<String, Integer> hashtable;

    @BeforeEach
    public void setUp() {
        hashtable = new MyHashTable<>();
    }

    @Test
    public void testPutAndGet() {
        hashtable.put("one", 1);
        hashtable.put("two", 2);
        hashtable.put("three", 3);

        assertEquals(1, hashtable.get("one"));
        assertEquals(2, hashtable.get("two"));
        assertEquals(3, hashtable.get("three"));
    }

    @Test
    public void testContains() {
        hashtable.put("one", 1);
        hashtable.put("two", 2);
        hashtable.put("three", 3);

        assertTrue(hashtable.contains("one"));
        assertTrue(hashtable.contains("two"));
        assertTrue(hashtable.contains("three"));
        assertFalse(hashtable.contains("four"));
    }

    @Test
    public void testRemove() {
        hashtable.put("one", 1);
        hashtable.put("two", 2);
        hashtable.put("three", 3);

        hashtable.remove("two");

        assertTrue(hashtable.contains("one"));
        assertFalse(hashtable.contains("two"));
        assertTrue(hashtable.contains("three"));
    }

    @Test
    public void testSize() {
        assertEquals(0, hashtable.size());

        hashtable.put("one", 1);
        hashtable.put("two", 2);
        hashtable.put("three", 3);

        assertEquals(3, hashtable.size());

        hashtable.remove("two");

        assertEquals(2, hashtable.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashtable.isEmpty());

        hashtable.put("one", 1);

        assertFalse(hashtable.isEmpty());

        hashtable.remove("one");

        assertTrue(hashtable.isEmpty());
    }
}
