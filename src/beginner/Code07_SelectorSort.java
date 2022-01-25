package beginner;

import utils.CommonUtils.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static utils.CommonUtils.*;

/**
 * @author weizheng
 * @Description 选择排序
 * @date 2022/01/24
 */
public class Code07_SelectorSort {

    public static void main(String[] args) {
        testArraySort(20, i -> selectSort(i));
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j <= length - 1; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }
}
