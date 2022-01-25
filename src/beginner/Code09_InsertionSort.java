package beginner;

import static utils.CommonUtils.swap;
import static utils.CommonUtils.testArraySort;

/**
 * @author weizheng
 * @Description TODO
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
            // for (int j = i; j > 0; j--) {
            //     if (arr[j] < arr[j - 1]) {
            //         swap(arr, j, j - 1);
            //     } else {
            //         break;
            //     }
            // }
            int end = i;
            while (end > 0 && arr[end] < arr[end - 1]) {
                swap(arr,end,end-1);
                end--;
            }
        }
    }
}
