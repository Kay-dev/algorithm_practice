package sort;

import utils.CommonUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 计数排序
 * 应用范围有限，要求样本是整数，且范围比较窄，一旦数据范围发生变化，就需要改写。
 * @author weizheng
 * @date 2022/02/25
 */
public class Code04_CountSort {

    public static void main(String[] args) {
        CommonUtils.testArraySort(10, Code04_CountSort::countSort);
    }

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 根据原数组中的最大值，创建一个容量相当的help数组
        int[] help = new int[arr.length];
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        // 遍历原数组中的值，若值为x，则在help数组下标为x的位置计数++
        Arrays.stream(arr)
                .forEach(i -> help[i]++);
        // 遍历help数组，将数放回原数组，获得排完序后的数组结果
        int j = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] > 0) {
                for (int k = 0; k < help[i]; k++) {
                    arr[j++] = i;
                }
            }
        }
    }
}
