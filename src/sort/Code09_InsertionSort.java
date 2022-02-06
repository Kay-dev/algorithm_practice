package sort;

import static utils.CommonUtils.swap;
import static utils.CommonUtils.testArraySort;

/**
 * @author weizheng
 * @Description 插入排序
 * 先保证0-0区间有序
 * 再保证0-1区间有序，从第2个数开始从后往前看，比前面的数小，则交换
 * ...
 * 保证0-N区间有序。
 * @date 2022/01/24
 */
public class Code09_InsertionSort {

    public static void main(String[] args) {
        testArraySort(20, i -> insertionSort(i));
    }

    private static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int end = i;
            // 0-i 范围内做到有序
            while (end > 0 && arr[end] < arr[end - 1]) {
                swap(arr,end,end-1);
                end--;
            }
        }
    }
}
