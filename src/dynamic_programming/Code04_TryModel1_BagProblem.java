package dynamic_programming;

import java.util.Arrays;

/**
 * 背包问题
 * <p>
 * 给定两个数组weights和values,其中weights[i]和values[i]分别代表一个商品的重量和价格，
 * 现给定一个正数N，表示一个最大承重为N的袋子。
 * 问：在不超重的前提下，返回承装的商品价格总和最高是多少？
 *
 * @author weizheng
 * @date 2022/03/07
 */
public class Code04_TryModel1_BagProblem {


    /**
     * 暴力递归
     *
     * @param weights 重量数组
     * @param values  价格数组
     * @param N       最大承重
     * @return 能够装载的商品最高总价
     */
    public static int calMaxPrice(int[] weights, int[] values, int N) {
        if (weights.length == 0 || values.length == 0 || N <= 0) {
            return -1;
        }
        return process(weights, values, 0, N);
    }

    /**
     * @param weights 重量数组
     * @param values  价格数组
     * @param index   当前所在位置的下标
     * @param rest    袋子剩余承重量
     * @return 总价
     */
    private static int process(int[] weights, int[] values, int index, int rest) {
        // 终止条件:超重了
        if (rest < 0) {
            return -1;
        }
        // 终止条件：没有商品可选了
        if (weights.length == index) {
            return 0;
        }
        // 加入当前商品的情况
        int pick = process(weights, values, index + 1, rest - weights[index]);
        // 若此方案成立,则加上当前商品的价格
        if (pick != -1) {
            pick += values[index];
        }
        // 不加入当前商品的情况,总价是多少
        int noPick = process(weights, values, index + 1, rest);
        // 从两种方案中取出总价最高的返回
        return Math.max(noPick, pick);
    }


    /**
     * 动态规划
     *
     * @param weights   重量数组
     * @param values    价格数组
     * @param maxWeight 最大承重
     * @return 能够装载的商品最高总价
     */
    public static int dp(int[] weights, int[] values, int maxWeight) {
        if (weights.length == 0 || values.length == 0 || maxWeight <= 0) {
            return -1;
        }

        // 存储函数 fun(当前位置，剩余承重) 的结果
        int[][] dp = new int[weights.length][maxWeight + 1];
        for (int i = 0; i < dp.length - 1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = maxWeight; j >= 0; j--) {
                // 进行越界判断
                if (j - weights[i] >= 0) {
                    // 选择加入当前商品的情况
                    int pick = dp[i + 1][j - weights[i]];
                    // 若此方案成立,则加上当前商品的价格
                    if (pick != -1) {
                        pick += values[i];
                    }
                    // 不选择加入当前商品的情况,总价是多少
                    int noPick = dp[i + 1][j];
                    // 从两种方案中取出总价最高的作为结果填入
                    dp[i][j] = Math.max(noPick, pick);
                }
            }
        }

        return dp[0][maxWeight];

    }


    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5};
        int[] values = {10, 5, 3, 22, 13};

        System.out.println(calMaxPrice(weights, values, 6));
        System.out.println(dp(weights, values, 6));
    }


}
