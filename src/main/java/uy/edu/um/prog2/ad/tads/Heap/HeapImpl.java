package uy.edu.um.prog2.ad.tads.Heap;

import uy.edu.um.prog2.ad.tads.MyBinarySearchingTree.MyBinarySearchingTree;

import java.util.ArrayList;
import java.util.List;

public class HeapImpl <K extends Comparable<K>, T> implements MyBinarySearchingTree<K,T> {

    private List<Node<K,T>> heap;
    public HeapImpl(){
        heap=new ArrayList<>();
    }
    @Override
    public T find(K key) {
        for (Node<K,T> node:heap){
            if (node.key.equals(key)){
                return node.data;
            }
        }
        return null;
    }
    @Override
    public void insert(K key, T data) {
        //Crear el nodo
        Node<K,T> node = new Node<>(key,data);
        //Agregar el nodo al array al final
        heap.add(node);
        //Acomodar el nodo según el valor de la key
        subir(heap.size()-1);
    }
    @Override
    public void delete(K key) {
        //Obtiene y elimina el nodo
        //Buscamos el nodo deseado
        Node<K, T> wanted;
        int indice=-5;

        for (int i = 0; i < heap.size(); i++) {
            Node<K, T> node = heap.get(i);
            if (node.key.equals(key)) {
                wanted = node;
                indice = i;
            }
        }
        //Verificamos que lo haya encontrado
        if (wanted != null){
            //Elimino el nodo
            heap.set(indice,heap.get(heap.size()-1));
        }
        //Acomodamos el nodo
    }

    private void subir(int i){
        //Acomoda el nodo en el array tomando su índice
        //Nodo padre=(i-1)/2
        int iParent = (i-1)/2;
        //Se busca que se cumpla que la key del nodo sea mayor o igual que la de su padre o que llegue a la raíz del heap
        while (i>0 && heap.get(i).key.compareTo(heap.get(iParent).key)<0){
            intercambiar(i,iParent);
            i=iParent;
            iParent=(i-1)/2;
        }
    }
    private void intercambiar(int i,int j){
        //Intercambio de nodos: utilizo un auxiliar para cambiar uno por otro
        Node<K,T> aux = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,aux);
    }
}
