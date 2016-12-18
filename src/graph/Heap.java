package graph;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Created by takirala on 10/26/2016.
 */
public class Heap {

    public static void main(String[] args) {
        int[] numbers = new int[10];
    }

    private static final int d = 2;
    private int heapSize;
    private int[] heap;

    Heap(int capacity) {
        this.heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public void clear() {
        heapSize = 0;
    }

    private int parent(int i) {
        return (i - 1) / d;
    }


    private int kthChild(int i, int k) {
        return d * i + k;
    }

    void insert(int x) {
        if (isFull()) throw new NoSuchElementException("Full heap");
        heap[heapSize++] = x;
        //bubbleUp(heapSize - 1);
    }

    int findMin() {
        if (isEmpty()) throw new NoSuchElementException("Full heap");
        return heap[0];
    }

    public int deleteMin() {
        int keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    public int delete(int index) {
        if (isEmpty()) throw new NoSuchElementException("no elem");
        int keyItem = heap[index];
        heap[index] = heap[heapSize - 1];
        heapSize--;
        //sinkDown(index);
        return keyItem;
    }

    void heapify(int childIndex) {
        int temp = heap[childIndex];
        while (childIndex > 0 && temp < heap[parent(childIndex)]) {
            //keep going up.
            //heap[childIndex] = heap[parent(ch)]
        }
    }
}

