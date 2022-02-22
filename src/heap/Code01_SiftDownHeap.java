package heap;

import utils.CommonUtils;

import java.util.Random;

/**
 * @author weizheng
 * @Description 大根堆
 * <p>
 * [6,5,4,3,1,3,2,..]
 * 可以想象成一颗树。
 * 其中每个节点的父节点的下标parent = (index-1)/2
 * 左边子节点的下标 left = index*2 + 1
 * 右边子节点的下标 right = index*2 + 2
 *      6
 *    /   \
 *   5     4
 *  / \   / \
 * 3   1 3   2
 * @date 2022/02/09
 */
public class Code01_SiftDownHeap {


    private int[] array;

    /**
     * 已占用的容量
     */
    private int size;

    /**
     * 最大容量
     */
    private int capacity;

    public Code01_SiftDownHeap(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    /**
     * 向大根堆中添加元素，并平衡大根堆
     *
     * @param num
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("the heap is full!");
        }
        // 将添加的元素添加到数组末尾
        array[size] = num;
        // 调整大根堆，将添加的元素放到合适的位置
        heapInsert(array, size++);
    }

    /**
     * 弹出大根堆的根节点值，并平衡大根堆
     *
     * @return 大根堆的根节点值
     */
    public int pop() {
        // 返回0位置的数
        int result = array[0];
        // 第一个元素弹出后，数组的第一个位置空缺，我们将数组中的最后一个值移动到首位填充。
        CommonUtils.swap(array, 0, --size);
        // 将这个堆重新调整成大根堆状态
        heapify(array, 0, size);
        return result;
    }

    /**
     * 从父节点向下看，看是否需要和子节点进行交换，不断循环。直到找到合适的位置
     *
     * @param array 存放数据的数组
     * @param index 从这个位置开始
     * @param size  整个堆的实际大小
     */
    private void heapify(int[] array, int index, int size) {
        // 左节点的下标
        int left = index * 2 + 1;
        // 右节点的下标
        int right = left + 1;
        // left不越界，说明有左节点
        while (left < size) {
            // 获取左节点和右节点中较大的那个
            int largest = left;
            // 如果存在右节点，并且右节点比左节点大，则largest取右节点的值
            if (right < size && array[right] > array[left]) {
                largest = right;
            }
            // 如果index的值比子节点中较大的那个小，则两者交换
            if (array[largest] > array[index]) {
                CommonUtils.swap(array,index,largest);
            }else {
                // 否则，index已经在合适的位置了，不需要继续向下比较了，结束循环
                break;
            }
            // 交换完成后，index来到子节点largest的位置，以此为起点继续向下比较
            index = largest;
            left = index * 2 + 1;
            right = left + 1;
        }
    }

    /**
     * 从index位置，不断向上看，看是否需要和父节点进行交换。直到找到合适的位置
     *
     * @param array
     * @param index
     */
    private void heapInsert(int[] array, int index) {
        // 如果index位置的值比父节点的值大，则两者交换，直到不比父节点大为止
        while (array[index] > array[(index - 1) / 2]) {
            CommonUtils.swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // 测试
    public static void main(String[] args) {
        int times = 100_000;
        int freq = 100;
        int size = 10;
        int numRange = 100;
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            RightHeap rightHeap = new RightHeap(size);
            Code01_SiftDownHeap testHeap = new Code01_SiftDownHeap(size);
            for (int j = 0; j < freq; j++) {
                assert rightHeap.isEmpty() == testHeap.isEmpty();
                if (rightHeap.isEmpty()) {
                    int num = random.nextInt(numRange);
                    rightHeap.push(num);
                    testHeap.push(num);
                } else if (rightHeap.isFull()) {
                    assert rightHeap.pop() == testHeap.pop();
                } else {
                    if (Math.random() > 0.5) {
                        int value = random.nextInt(numRange);
                        rightHeap.push(value);
                        testHeap.push(value);
                    } else {
                        assert rightHeap.pop() == testHeap.pop(): "rightHeap="+rightHeap+",testHeap="+testHeap;
                    }
                }
            }
        }

    }
}

class RightHeap {
    private int[] array;
    private int size;
    private int capacity;

    public RightHeap(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void push(int num) {
        if (size == capacity) {
            throw new RuntimeException("heap is full!");
        }
        array[size++] = num;
    }

    public int pop() {
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        int result = array[maxIndex];
        array[maxIndex] = array[--size];
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(",");
        }
        sb.append("]");
        return sb.toString();    }
}
