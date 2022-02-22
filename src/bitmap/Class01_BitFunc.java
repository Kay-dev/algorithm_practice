package bitmap;

import java.util.Arrays;

/**
 * @author weizheng
 * @Description 异或运算.异或相当于两个数无进位相加
 * @date 2022/02/06
 */
public class Class01_BitFunc {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 6};
        // System.out.println(findOddNum(arr));
        System.out.println(Arrays.toString(findTwoOddNum(arr)));
    }


    // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这个数？
    public static int findOddNum(int[] array) {
        int num = 0;
        for (int i : array) {
            num ^= i;
        }
        return num;
    }


    // 一个数组中有两种数出现了奇数次，其余数出现了偶数次，怎么找到并打印这两种数？
    public static int[] findTwoOddNum(int[] array) {
        int a = 0;
        // 假设两个数是a和b，得到a^b的结果
        int oddNum = findOddNum(array);
        // 得到最后一位为1的结果，假设最后一位1出现的位置为1，那么就能知道a和b在第1位的值不同（一个为0，另一个为1）
        int pos = oddNum & (~oddNum + 1);
        // 将数组按第1位是0，还是1分成两组,则a，b被分开，对每组分别异或得到a和b的值
        for (int i : array) {
            // 如果i的第一位有1，则与a取异或
            if ((i & pos) != 0) {
                a ^= i;
            }
        }
        // 已经得到a，则b=(a^b)^a
        return new int[]{a, oddNum ^ a};
    }
}
