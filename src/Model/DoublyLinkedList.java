package Model;

import java.util.HashMap;

public class DoublyLinkedList <T>{
    private Node<T> head;
    private Node<T> tail;
    private final HashMap<Integer, Node<T>> indexNodeMap;
    private final HashMap<T, Integer> itemIndexMap;
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
        itemIndexMap = new HashMap<>();
        size = 0;
    }
    public DoublyLinkedList(DoublyLinkedList<T> otherList) {
        this();
        Node<T> otherCurrent = otherList.head;
        while (otherCurrent != null) {
            this.add(otherCurrent.index, otherCurrent.item);
            otherCurrent = otherCurrent.next;
        }
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
        itemIndexMap.put(item, index);
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
            itemIndexMap.remove(nodeToRemove.item);
            size--; // Decrementar el tamaño
        }
    }

    // Método para obtener un nodo por su índice utilizando el mapa
    private Node<T> getNodeByIndex(int index) {
        return indexNodeMap.get(index);
    }
    public int getIndexByItem(T item) {
        return itemIndexMap.getOrDefault(item, -1);
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

    private void swapElementsByIndex(int index1, int index2) {
        Node<T> node1 = getNodeByIndex(index1);
        Node<T> node2 = getNodeByIndex(index2);

        if (node1 != null && node2 != null) {
            T tempT = node1.item;
            node1.item = node2.item;
            node2.item = tempT;
            itemIndexMap.put(node1.item, node2.index);
            itemIndexMap.put(node2.item, node1.index);
        }
    }

    // Método de ordenamiento utilizando el algoritmo QuickSort
    public void quicksort(int izquierda, int derecha) {
        if (izquierda < derecha) {
            int indiceParticion = partition(izquierda, derecha);
            quicksort(izquierda, indiceParticion - 1);
            quicksort(indiceParticion + 1, derecha);
        }
    }

    private int partition(int izquierda, int derecha) {
        Node<T> pivote = getNodeByIndex(derecha);
        int i = izquierda - 1;

        for (int j = izquierda; j < derecha; j++) {
            if (getNodeByIndex(j).index < pivote.index) {
                i++;
                swapElementsByIndex(i, j);
            }
        }
        swapElementsByIndex(i + 1, derecha);
        return i + 1;
    }
    
    

    public void updateItemIndexMapAfterSort() {
        // Actualizar el mapa inverso después de ordenar
        HashMap<T, Integer> newItemIndexMap = new HashMap<>();
        for (Node<T> node : indexNodeMap.values()) {
            newItemIndexMap.put(node.item, node.index);
        }
        itemIndexMap.clear();
        itemIndexMap.putAll(newItemIndexMap);
    }
}
