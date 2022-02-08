package sort;

import utils.CommonUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author weizheng
 * @Description 快排
 * @date 2022/02/06
 */
public class Code02_QuickSort {

    public static void main(String[] args) {
        int[] array = CommonUtils.createArray(15);
        CommonUtils.printArray(array);
        // quickSortWithRecurse(array);
        quickSortWithStack(array);
        CommonUtils.printArray(array);

    }

    /**
     * 递归实现快排
     *
     * @param array
     */
    public static void quickSortWithRecurse(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);

    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机选一个数，换到末尾
        CommonUtils.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        // 开始分区
        int[] equal = partition(arr, L, R);
        process(arr, L, equal[0] - 1);
        process(arr, equal[1] + 1, R);

    }

    /**
     * 用栈实现快排
     *
     * @param array
     */
    public static void quickSortWithStack(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 准备一个栈用来存储任务
        Deque<Job> stack = new ArrayDeque<>();
        // 放入第一个任务：将整个数组排序
        stack.push(new Job(0, array.length - 1));
        // 从栈中取出任务，执行
        while (stack.size() > 0) {
            Job job = stack.pop();
            int[] partition = partition(array, job.L, job.R);
            // 判断是否需要将任务拆成子任务,即：是否存在小于区间？是否存在大于区间？
            if (partition[0] > job.L) {
                stack.push(new Job(job.L, partition[0] - 1));
            }
            if (partition[1] < job.R) {
                stack.push(new Job(partition[1] + 1, job.R));
            }
        }
    }


    /**
     * 分区函数，以最后一个数为基准进行分区
     *
     * @param arr 需要分区的数组
     * @param L   分区左边界
     * @param R   分区右边界
     * @return 等于区域的下标
     */
    public static int[] partition(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int idx = L;
        int left = L - 1;
        int right = R;
        int last = arr[R];
        while (idx < right) {
            if (arr[idx] < last) {
                CommonUtils.swap(arr, idx++, ++left);
            } else if (arr[idx] > last) {
                CommonUtils.swap(arr, idx, --right);
            } else {
                idx++;
            }
        }
        CommonUtils.swap(arr, right, R);
        return new int[]{left + 1, right};
    }


}

class Job {
    public int L;
    public int R;

    public Job(int l, int r) {
        L = l;
        R = r;
    }
}