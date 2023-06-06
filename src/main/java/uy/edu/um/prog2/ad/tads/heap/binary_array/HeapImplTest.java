package uy.edu.um.prog2.ad.tads.heap.binary_array;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.tads.tree.binary.search.MyBinarySearchingTree;

public class HeapImplTest {
    MyBinarySearchingTree<Integer,String> heap = new HeapImpl<>();

    @Test
    void findTest(){
        //Insertamos nodos en el heap
        heap.insert(2,"A");
        heap.insert(3,"B");
        heap.insert(0,"C");

        //Realizar pruebas y afirmaciones
        assert heap.find(3).equals("B");
        assert heap.find(0).equals("C");
        assertNotEquals("B",heap.find(2));
    }

    @Test
    void deleteTest(){
        heap.insert(2,"A");
        heap.insert(3,"B");
        heap.insert(0,"C");

        heap.delete(3);

        //Realizar pruebas y afirmaciones
        assert heap.find(3) == null;
    }

}
