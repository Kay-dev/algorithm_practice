package utils;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author weizheng
 * @Description TODO
 * @date 2022/01/24
 */
public class CommonUtils {

    public static void testArraySort(int count,Consumer<int[]> consumer) {
        int[] array = createArray(count);
        printArray(array);
        consumer.accept(array);
        printArray(array);
    }

    /**
     * 创建一个数组
     * @return
     */
    public static int[] createArray(int count) {
        Random random = new Random();
        return IntStream.rangeClosed(1, count)
                .map(i -> random.nextInt(10))
                .toArray();
    }


    public static void swap(int[] arr, int a, int b) {
        int trans = arr[a];
        arr[a] = arr[b];
        arr[b] = trans;

    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
