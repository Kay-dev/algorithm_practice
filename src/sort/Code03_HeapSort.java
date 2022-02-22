package sort;

import utils.CommonUtils;

/**
 * @author weizheng
 * @Description 堆排序
 * 时间复杂度 O（N*logN）
 * 空间复杂度 O（1），不需要额外的空间
 * @date 2022/02/09
 */
public class Code03_HeapSort {

    public static void main(String[] args) {
        CommonUtils.testArraySort(10,i->heapSort(i));
    }


    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 将整个数组调整成一个大根堆
        for (int i = array.length - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }
        int heapSize = array.length;
        // 堆首位是最大值，将最大值放到从末尾
        CommonUtils.swap(array,0,--heapSize);
        // 依次将大根堆根节点放到末尾，并重新调整大根堆。
        while (heapSize > 0) {
            heapify(array,0,heapSize);
            CommonUtils.swap(array,0,--heapSize);
        }
    }


    public static void heapify(int[] array, int index, int size) {
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

}
