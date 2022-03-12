package dynamic_programming;

import java.util.Arrays;

/**
 * 基于业务的尝试模型
 * <p>
 * 给定一个数组，代表每个人喝完咖啡准备洗杯子的时间点
 * 只有一台机器，每次只能洗一个杯子，耗时a，洗完才能洗下一个杯子
 * 每个咖啡杯也可以自己挥发干净，耗时b，多个杯子可以同时挥发
 * 要求：返回所有咖啡杯干净的最短时间？
 *
 * @author weizheng
 * @date 2022/03/12
 */
public class Code09_TryModel4_WashCup {

    /**
     * 暴力递归
     *
     * @param times 所有需要洗杯子的时间点
     * @param a     机洗一个杯子的耗时
     * @param b     挥发干净的耗时
     * @return 所有杯子变干净的最短时间点
     */
    public static int minTime(int[] times, int a, int b) {
        if (times.length == 0 || a <= 0 || b <= 0) {
            return 0;
        }

        return process(times, a, b, 0, 0);

    }

    /**
     * 递归函数
     *
     * @param times    所有需要洗杯子的时间点
     * @param a        机洗一个杯子的耗时
     * @param b        挥发干净的耗时
     * @param index    当前杯子在times中的下标
     * @param washTime 机器可用的下一时间点
     * @return 所有杯子变干净的最短时间点
     */
    private static int process(int[] times, int a, int b, int index, int washTime) {
        // 终止条件: 已经到最后一个杯子了
        if (index == times.length - 1) {
            // 若机洗,计算出最终洗完的时间点
            int machine = Math.max(times[index], washTime) + a;
            // 若挥发，计算出最终的时间点
            int nomachine = times[index] + b;
            // 取耗时较少的方案
            return Math.min(machine, nomachine);
        }

        // 当前杯子选择机洗,计算出它洗完的时间点
        int nextTime1 = Math.max(times[index], washTime) + a;
        // 计算出剩余杯子洗完的时间点
        int tmp1 = process(times, a, b, index + 1, nextTime1);
        // 两者取最晚的时间点，保证所有杯子都洗完
        int result1 = Math.max(nextTime1, tmp1);

        // 当前杯子选择挥发，计算出挥发干净的时间点
        int nextTime2 = times[index] + b;
        // 计算出剩余杯子全部洗干净的时间点
        int tmp2 = process(times, a, b, index + 1, washTime);
        // 两者取最晚的时间点，保证所有杯子都洗完
        int result2 = Math.max(nextTime2, tmp2);

        // 两个方案中，选择耗时最短的那种方案，并返回最终的时间点
        return Math.min(result1, result2);
    }


    /**
     * 动态规划：这里的二维表的边界要根据业务参数进行确定
     *
     * @param times 所有需要洗杯子的时间点
     * @param a     机洗一个杯子的耗时
     * @param b     挥发干净的耗时
     * @return 所有杯子变干净的最短时间点
     */
    public static int dp(int[] times, int a, int b) {
        int l = times.length;
        if (l == 0 || a <= 0 || b <= 0) {
            return 0;
        }
        // 根据暴力递归中的方案，可知变量有两个：index，washTime
        // index的边界为[0,times.length-1], washTime的边界需要根据业务参数进行计算才能确定
        // washTime最大值出现在什么时候？当所有的杯子都选择机洗的时候，最终的washTime最大
        int washTime = 0;
        for (int time : times) {
            washTime += Math.max(time, washTime) + a;
        }
        // 准备一个二维表
        int[][] arr = new int[l][washTime + 1];
        // 初始化第N-1行的值，跟暴力递归的终止条件同理
        for (int i = 0; i < washTime; i++) {
            int machine = Math.max(times[l - 1], i) + a;
            // 若挥发，计算出最终的时间点
            int nomachine = times[l - 1] + b;
            // 取耗时较少的方案
            arr[l - 1][i] = Math.min(machine, nomachine);
        }
        // 填充二维表中的所有值
        for (int i = l - 2; i >= 0; i--) {
            for (int j = 0; j <= washTime; j++) {
                // 当前杯子选择机洗,计算出它洗完的时间点
                int nextTime1 = Math.max(times[i], j) + a;
                int tmp1 = Integer.MAX_VALUE;
                // 不越界才有意义
                if (nextTime1 <= washTime) {
                    tmp1 = arr[i + 1][nextTime1];
                }
                // 两者取最晚的时间点，保证所有杯子都洗完
                int result1 = Math.max(nextTime1, tmp1);

                // 当前杯子选择挥发，计算出挥发干净的时间点
                int nextTime2 = times[i] + b;
                // 计算出剩余杯子全部洗干净的时间点
                int tmp2 = arr[i + 1][j];
                // 两者取最晚的时间点，保证所有杯子都洗完
                int result2 = Math.max(nextTime2, tmp2);

                // 两个方案中，选择耗时最短的那种方案，并返回最终的时间点
                arr[i][j] = Math.min(result1, result2);
            }
        }
        return arr[0][0];

    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a = 3;
        int b = 10;

        System.out.println(minTime(arr, a, b));
        System.out.println(dp(arr, a, b));

    }

}
