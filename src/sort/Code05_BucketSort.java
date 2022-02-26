package sort;

import utils.CommonUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 桶排序
 * 不基于比较的排序，不过应用范围有限，需要样本的数据状况满足桶的划分
 * 一般来讲，基数排序要求，样本是十进制的正整数
 *
 * @author weizheng
 * @date 2022/02/26
 */
public class Code05_BucketSort {

    public static void main(String[] args) {

        int[] array = {23, 45, 1, 865, 20, 6, 133, 77};
        System.out.println(Arrays.toString(array));
        bucketSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void bucketSort(int[] arr) {
        int maxDigit = getMaxDigit(arr);
        Queue[] help = new LinkedList[10];

        // 遍历数组，看个位是多少，就放进help数组对应下标的队列中
        // 再将help数组中所有队列的元素取出放回源数组
        // 继续看十位是多少，放进help数组对应下标的队列中，循环往复
        // ...
        // 直到最高位也处理完成，放回源数组，则排序完成。
        for (int i = 0; i < maxDigit; i++) {
            int divide = (int) Math.pow(10, i);
            for (int j : arr) {
                // 取出个/十/百...位上的数
                int temp = j / divide % 10;
                // 如果还没有队列，就创建一个新的
                if (help[temp] == null) {
                    help[temp] = new LinkedList();
                }
                // 加到help数组对应下标中的队列中
                help[temp].add(j);
            }

            int index = 0;
            for (Queue queue : help) {
                if (queue == null) {
                    continue;
                }
                while (queue.size() > 0) {
                    arr[index++] = (int) queue.poll();
                }
            }
        }

    }

    /**
     * 获取数组中元素位数最高是多少
     *
     * @param arr
     * @return
     */
    public static int getMaxDigit(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int result = 0;
        for (int i : arr) {
            result = Math.max(getDigit(i), result);
        }
        return result;

    }

    /**
     * 获取一个整数有多少位数
     *
     * @param num
     * @return
     */
    public static int getDigit(int num) {
        int i = 0;
        while (num != 0) {
            i++;
            num /= 10;
        }
        return i;
    }

}
