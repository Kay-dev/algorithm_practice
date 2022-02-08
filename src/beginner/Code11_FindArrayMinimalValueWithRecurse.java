package beginner;

/**
 * @author weizheng
 * @Description 递归实现查找数组中最小值
 * @date 2022/02/08
 */
public class Code11_FindArrayMinimalValueWithRecurse {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 5, 1};
        System.out.println(findMinimal(array));
    }


    public static int findMinimal(int[] array) {
        if (array == null) {
            throw new RuntimeException("array can't be null!");
        }
        if (array.length < 2) {
            return array[0];
        }
        return process(array, 0, array.length - 1);

    }

    private static int process(int[] array, int L, int R) {
        if (L == R) {
            return array[L];
        }
        int M = L + (R - L) / 2;
        return Math.min(process(array, L, M), process(array, M + 1, R));
    }


}
