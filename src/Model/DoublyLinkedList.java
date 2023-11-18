package Model;

import java.util.HashMap;

public class DoublyLinkedList <T>{
    private Node<T> head;
    private Node<T> tail;
    private final HashMap<Integer, Node<T>> indexNodeMap;
    private int size; 

    static class Node<T> {
        Node<T> next;
        Node<T> prev;
        int index;
        T item;
        // Otros campos según tus necesidades

        Node(int index, T item) {
            this.index = index;
            this.item = item;
            // Inicializar otros campos si es necesario
        }
    }
    public DoublyLinkedList() {
        indexNodeMap = new HashMap<>();
        size = 0;
    }

    // Método para agregar un nodo al final de la lista
    public void add(int index, T item) {
        Node<T> newNode = new Node<>(index, item);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

        indexNodeMap.put(index, newNode);
        size++; // Incrementar el tamaño
    }

    // Método para eliminar un nodo dado su índice
    public void remove(int index) {
        Node<T> nodeToRemove = indexNodeMap.get(index);

        if (nodeToRemove != null) {
            if (nodeToRemove.prev != null) {
                nodeToRemove.prev.next = nodeToRemove.next;
            } else {
                head = nodeToRemove.next;
            }

            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
            } else {
                tail = nodeToRemove.prev;
            }

            indexNodeMap.remove(index);
            size--; // Decrementar el tamaño
        }
    }

    // Método para obtener un nodo por su índice utilizando el mapa
    private Node<T> getNodeByIndex(int index) {
        return indexNodeMap.get(index);
    }

    // Método para verificar si un nodo con un índice dado tiene un nodo siguiente
    public boolean hasNext(int index) {
        Node<T> current = getNodeByIndex(index);
        return current != null && current.next != null;
    }

    // Método para verificar si un nodo con un índice dado está en la lista
    public boolean contains(int index) {
        Node<T> current = head;
        while (current != null) {
            if (current.index == index) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Método para obtener el tamaño de la lista
    public int getSize() {
        return size;
    }

    // Método para intercambiar los elementos 't' de dos nodos dados sus índices
    private void swapElementsByIndex(int index1, int index2) {
        Node<T> node1 = getNodeByIndex(index1);
        Node<T> node2 = getNodeByIndex(index2);

        if (node1 != null && node2 != null) {
            // Intercambiar los elementos 't' de los nodos
            T tempT = node1.item;
            node1.item = node2.item;
            node2.item = tempT;
        }
    }

    // Método de ordenamiento utilizando el algoritmo de la burbuja
    public void sort() {
        if (head == null || head.next == null) {
            // La lista está vacía o tiene un solo elemento, ya está ordenada
            return;
        }

        boolean swapped;
        Node<T> current;
        Node<T> last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.index > current.next.index) {
                    swapElementsByIndex(current.index, current.next.index);
                    swapped = true;
                }
                current = current.next;
            }
            last = current;

        } while (swapped);
    }
}
