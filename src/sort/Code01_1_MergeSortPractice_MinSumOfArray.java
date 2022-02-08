package sort;

import javax.sound.midi.MidiChannel;

/**
 * @author weizheng
 * @Description
 *
 * 归并练习
 * 在一个数组中，一个数左边比它小的数的总和，叫数的最小和，所有的最小和相加，叫数组最小和。求数组的最小和。
 *
 * 例如：[1,3,4,2]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1，3
 * 2左边比2小的数：1
 *
 * 所以数组最小和为 1+1+3+1=6
 *
 * @date 2022/02/08
 */
public class Code01_1_MergeSortPractice_MinSumOfArray {

    public static void main(String[] args) {
        int[] arr = {1,3,4,2};
        System.out.println(minSum(arr));
    }

    public static int minSum(int[] array) {
        if (array == null) {
            throw new RuntimeException("array can not be null!");
        }
        if (array.length < 2) {
            return array[0];
        }
        return process(array, 0, array.length - 1);
    }

    private static int process(int[] array, int L, int R) {
        if (L == R) {
            return 0;
        }
        // 利用归并的思想，将数组分成左右两部分，分别求出两部分的最小和
        int M = L + (R - L) / 2;
        return process(array, L, M) + process(array, M + 1, R) + merge(array, L, M, R);
    }

    private static int merge(int[] array, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        int sum = 0;

        while (left <= M && right <= R) {
            if (array[left] < array[right]) {
                // 当左组某个数小于右组的数时，判断右组中共有多少个数比该数大，统计到结果中。
                sum += array[left] * (R - right + 1);
                help[index] = array[left];
                left++;
            } else {
                help[index] = array[right];
                right++;
            }
            index++;
        }
        while (left <= M) {
            help[index++] = array[left++];
        }
        while (right <= R) {
            help[index++] = array[right++];
        }
        System.arraycopy(help, 0, array, L, help.length);
        return sum;
    }
}
