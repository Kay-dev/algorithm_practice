package sort;

import static utils.CommonUtils.*;

/**
 * @author weizheng
 * @Description 冒泡排序
 * @date 2022/01/24
 */
public class Code08_BubbleSort {


    public static void main(String[] args) {
        testArraySort(20, i -> bubbleSort(i));

    }

    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

}
