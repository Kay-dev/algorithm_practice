package sort;

import utils.CommonUtils;

/**
 * @author weizheng
 * @Description 归并排序
 * @date 2022/02/05
 */
public class Class01_MergeSort {

    public static void main(String[] args) {
        CommonUtils.testArraySort(10, Class01_MergeSort::mergeSort2);
    }

    /**
     * 递归方式实现归并排序
     *
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);

    }

    private static void process(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        process(arr, start, mid);
        process(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    /**
     * 以非递归的方式实现归并排序
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            while (L < N) {
                // 当最后剩余的元素不够能组成左组，则此次步长循环调整结束
                if (step >= N - L) {
                    break;
                }
                // 中点位置
                int M = L + step - 1;
                // 右组的起始位置，有可能剩余的元素数量不够一个步长
                int R = M + Math.min(step, N - M - 1);
                merge(arr, L, M, R);
                // 下一组左组的起始位置
                L = R + 1;
            }
            // 如果当前步长> N/2，那么步长*2之后，就会大于N，没有必要做处理。而且直接step*2还有整型越界异常
            if (step > N / 2) {
                break;
            }
            step <<= 1;
        }


    }


    private static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = 0;
        int p1 = start;
        int p2 = mid + 1;
        // 左边部分和右边部分都没越界之前，进行比较，谁小谁加入到临时数组中
        while (p1 <= mid && p2 <= end) {
            if (arr[p1] <= arr[p2]) {
                temp[i] = arr[p1];
                p1++;
            } else {
                temp[i] = arr[p2];
                p2++;
            }
            i++;
        }
        // 其中一边已经越界，无法继续比较，将另一边的剩余数据复制到临时数组中。
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= end) {
            temp[i++] = arr[p2++];
        }
        // 将临时数组中的数据拷贝到原始数组中，完成部分排序
        System.arraycopy(temp, 0, arr, start, temp.length);
    }

}
