package beginner;

import java.util.Arrays;

/**
 * @author weizheng
 * @Description 二分
 * @date 2022/01/25
 */
public class Code10_0_BinarySearch {


    public static void main(String[] args) {

        testBinarySearch();
    }


    /**
     * 测试二分查找
     */
    public static void testBinarySearch() {
        int times = 100_000;
        int length = 10;
        int range = 20;

        for (int i = 0; i < times; i++) {
            int[] originArr = createSortedArr(length, range);
            int index = ((int) (Math.random() * originArr.length));
            int num = originArr[index];

            if (check(originArr, num) && binarySearch(originArr, num)) {
                break;
            } else {
                System.out.println("出错了！原数组：" + Arrays.toString(originArr) + "|搜寻的值是：" + num);
            }
        }
    }

    public static boolean check(int[] arr, int num) {
        for (int j : arr) {
            if (j == num) {
                return true;
            }
        }
        return false;

    }

    public static int[] createSortedArr(int length, int range) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }


    /**
     * 二分查找算法
     * @param arr
     * @param num
     * @return
     */
    public static boolean binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }


}
