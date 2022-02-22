package heap;

import utils.CommonUtils;

/**
 * @author weizheng
 * @Description 小根堆
 * @date 2022/02/21
 */
public class Code02_SliftUpHeap {

    private int[] array;

    private int size;

    private int capacity;

    public Code02_SliftUpHeap(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void push(int i) {
        if (isFull()) {
            throw new RuntimeException("heap is full");
        }
        array[size] = i;
        heapInsert(array, size++);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        int result = array[0];
        CommonUtils.swap(array,0,--size);
        heapify(array, 0, size);
        return result;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void heapInsert(int[] array, int index) {
        while (array[index] < array[(index - 1) / 2]) {
            CommonUtils.swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    private void heapify(int[] array, int index, int size) {
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        while (leftChild < size) {
            int smallest = leftChild;
            if (rightChild < size && array[rightChild]<array[leftChild]) {
                smallest = rightChild;
            }
            if (array[index] > array[smallest]) {
                CommonUtils.swap(array, index, smallest);
            } else {
                break;
            }
            index = smallest;
            leftChild = index * 2 + 1;
            rightChild = index * 2 + 2;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        Code02_SliftUpHeap heap = new Code02_SliftUpHeap(10);
        heap.push(5);
        heap.push(3);
        heap.push(1);
        heap.push(8);
        heap.push(8);
        heap.push(6);
        heap.push(9);
        System.out.println(heap);
        while (!heap.isEmpty()) {
            System.out.print(heap.pop()+" ");
        }

    }

}
