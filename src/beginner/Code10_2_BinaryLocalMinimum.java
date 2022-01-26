package beginner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utils.CommonUtils.createArray;

/**
 * @author weizheng
 * @Description 已知：存在一个两两不重复的无序数组。
 * 求：找出一个局部最小的数，如 [i-1]>[i]<[i+1] ,则称[i]为局部最小。
 * @date 2022/01/25
 */
public class Code10_2_BinaryLocalMinimum {

    public static void main(String[] args) {

        testManyTimes(10000);
    }

    public static void testManyTimes(int times) {
        for (int i = 0; i < times; i++) {
            int[] array = createArray(10);
            List<Integer> set = Arrays.stream(array)
                    .distinct()
                    .boxed()
                    .collect(Collectors.toList());
            int[] array2 = new int[set.size()];
            for (int j = 0; j < array2.length; j++) {
                array2[j] = set.get(j);
            }
            int index = findLocalMinimum(array2);
            if (!check(array2, index)) {
                System.out.println("出错了！");
                System.out.println("原数组：" + Arrays.toString(array2) + ", 找到的下标：" + index);
            }
        }

    }

    public static boolean check(int[] array, int index) {
        if (index == 0) {
            return array[index] < array[index + 1];
        }
        if (index == array.length - 1) {
            return array[index] < array[index - 1];
        }
        return array[index - 1] > array[index] && array[index + 1] > array[index];
    }

    public static int findLocalMinimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int length = arr.length;

        if (length == 1) {
            return 0;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[length - 1] < arr[length - 2]) {
            return length - 1;
        }

        int left = 0;
        int right = length - 1;
        int mid = -1;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid - 1] > arr[mid] && arr[mid + 1] > arr[mid]) {
                return mid;
            }
            if (arr[mid - 1] < arr[mid]) {
                right = mid;
            } else if (arr[mid + 1] < arr[mid]) {
                left = mid;
            }
        }
        return mid;
    }

}
