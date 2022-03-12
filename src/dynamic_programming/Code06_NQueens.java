package dynamic_programming;

import java.sql.SQLOutput;

/**
 * N皇后问题
 * <p>
 * N*N的棋盘上，放置N个皇后，任意两个皇后都不能干涉（同行||同列||同对角线）
 * 问：有多少种放法？
 *
 * @author weizheng
 * @date 2022/03/07
 */
public class Code06_NQueens {

    public static int calPlacePlan(int N) {
        if (N < 1) {
            return 0;
        }
        return process(new int[N], 0, N);

    }

    /**
     * @param array 存放已经放置的皇后的位置，如array[0]=3,表示第0行的皇后放在第三列
     * @param row   当前进行到第几行
     * @param i     总共多少行
     * @return 方案数量
     */
    private static int process(int[] array, int i, int row) {
        // 终止条件：已经到最后一行了
        if (i == row) {
            return 1;
        }
        // 遍历第i行的每一列
        int result = 0;
        for (int j = 0; j < row; j++) {
            if (isValid(array, i, j)) {
                // 将皇后的位置记录下来
                array[i] = j;
                result += process(array, i + 1, row);
            }
        }
        return result;
    }

    /**
     * 判断若第i行第j列放置皇后，是否会同已放置过的皇后冲突
     *
     * @param array 已存在的皇后
     * @param i     行
     * @param j     列
     * @return 是否能在第i行第j列放置皇后
     */
    private static boolean isValid(int[] array, int i, int j) {
        for (int k = 0; k < i; k++) {
            int col = array[k];
            // 若同列，或同对角线
            if (col == j || Math.abs(col - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(calPlacePlan(7));
    }
}
