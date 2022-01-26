package beginner;

import java.util.Arrays;

import static utils.CommonUtils.createArray;

/**
 * @author weizheng
 * @Description 查找有序数组中 >= num 最左的位置
 * @date 2022/01/25
 */
public class Code10_1_BinaryNearLeft {


    public static void main(String[] args) {

        testManyTimes();

    }

    public static void testManyTimes() {
        int times = 1_000_000;

        for (int i = 0; i < times; i++) {
            int[] array = createArray(20);
            Arrays.sort(array);
            int num = array[(int) (Math.random() * array.length)];
            int index = mostLeftNoLessNumIndex(array, num);
            if (check(array, num) != index) {
                System.out.println("出现错误！");
                System.out.println("原数组：" + Arrays.toString(array));
                System.out.println("查询的目标值：" + num);
                System.out.println("二分查找结果，下标：" + index);
            }
        }
    }

    public static int check(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }


    public static int mostLeftNoLessNumIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int mark = -1;
        while ( left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= num) {
                mark = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return mark;

    }

}
