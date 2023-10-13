package com.ufersa.ed2praticaoffline3.huffman;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void add(T item) {
        heap.add(item);
        int current = heap.size() - 1;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap.get(current).compareTo(heap.get(parent)) < 0) {
                // Se o elemento atual for menor que o pai, troque-os.
                T temp = heap.get(current);
                heap.set(current, heap.get(parent));
                heap.set(parent, temp);
                current = parent;
            } else {
                break;
            }
        }
    }

    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T min = heap.get(0);
        int last = heap.size() - 1;
        heap.set(0, heap.get(last));
        heap.remove(last);

        int current = 0;
        while (true) {
            int leftChild = 2 * current + 1;
            int rightChild = 2 * current + 2;
            int smallest = current;

            if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != current) {
                // Trocar com o filho de menor valor.
                T temp = heap.get(current);
                heap.set(current, heap.get(smallest));
                heap.set(smallest, temp);
                current = smallest;
            } else {
                break;
            }
        }

        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}