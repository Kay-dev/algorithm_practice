package dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 汉诺塔
 *
 * @author weizheng
 * @date 2022/03/06
 */
public class Code01_Hanoi {

    /**
     * 递归版本
     * @param N 层数
     * @param A 位置1
     * @param B 位置2
     * @param C 位置3
     */
    public static void fun(int N, String A, String B, String C) {
        if (N == 1) {
            System.out.printf("move %s from %s to %s%n",N, A, B);
        } else {
            fun(N - 1, A, C, B);
            System.out.printf("move %s from %s to %s%n",N, A, B);
            fun(N - 1, C, B, A);
        }
    }

    public static void main(String[] args) {
        fun(4,"A","B","C");
    }





}
