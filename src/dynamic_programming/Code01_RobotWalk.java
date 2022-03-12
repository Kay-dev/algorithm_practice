package dynamic_programming;

/**
 * 动态规划之 记忆化搜索
 *
 * 给定一int[N],值从1-N，N>2
 * 有一个机器人开始从M(M在1-N之间)出发，每次走一步，
 * 若机器人到达了1位置，则下一步只能向右走，来到2位置，
 * 若机器人到达了N位置，则下一步只能向左走，来到N-1位置
 * <p>
 * 现要求机器人从M开始必须走K步最终停在P位置。
 * 问：可能的路径有多少种？
 *
 * @author weizheng
 * @date 2022/03/08
 */
public class Code01_RobotWalk {

    /**
     *
     * @param N 右边界
     * @param M 起始位置
     * @param K 步数
     * @param P 终止位置
     * @return 有多少种可行的方案
     */
    public static int calPlans(int N, int M, int K, int P) {
        boolean incorrectParams = N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N;
        if (incorrectParams) {
            return 0;
        }

        return process(N, M, K, P);

    }

    /**
     * 暴力递归
     *
     * @param limit      右边界
     * @param location   当前位置
     * @param remainStep 剩余的步数
     * @param target     目标位置
     * @return 总共有多少种路径方案
     */
    private static int process(int limit, int location, int remainStep, int target) {
        // 终止条件:步数用完了
        if (remainStep == 0) {
            // 若结束时，当前位置就是目标位，则此方案有效
            return location == target ? 1 : 0;
        }

        // 如果走到了最左边
        if (location == 1) {
            return process(limit, location + 1, remainStep - 1, target);
        }
        // 如果走到了最右边
        if (location == limit) {
            return process(limit, location - 1, remainStep - 1, target);
        }
        // 如果在中间，则统计向左 和 向右两种方案的情况
        return process(limit, location - 1, remainStep - 1, target)
                + process(limit, location + 1, remainStep - 1, target);
    }


    /**
     * 动态规划 之 记忆化搜索
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int dp(int N, int M, int K, int P) {
        boolean incorrectParams = N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N;
        if (incorrectParams) {
            return 0;
        }

        // 缓存，缓存cache[N][K]的值存储 函数func(当前位置，剩余步数)的计算结果，这样当递归时，可以减少对相同函数的重复调用，提高速度。
        int[][] cache = new int[N + 1][K + 1];
        // 初始化缓存：全部用-1填充
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                cache[i][j] = -1;
            }
        }

        return process2(N, M, K, P, cache);

    }

    /**
     * 动态规划的递归函数
     *
     * @param limit      右边界
     * @param location   当前位置
     * @param remainStep 剩余的步数
     * @param target     目标位置
     * @param cache      缓存已经计算过的process()结果
     * @return 总共有多少种路径方案
     */
    private static int process2(int limit, int location, int remainStep, int target, int[][] cache) {
        // 先尝试从缓存中获取结果,如果缓存中不存在，才进行计算
        int cacheValue = cache[location][remainStep];
        if (cacheValue != -1) {
            return cacheValue;
        }

        // 终止条件:步数用完了
        if (remainStep == 0) {
            // 若结束时，当前位置就是目标位，则此方案有效
            int i = location == target ? 1 : 0;
            // 存入缓存
            cache[location][remainStep] = i;
            return i;
        }

        // 如果走到了最左边
        if (location == 1) {
            // 存入缓存
            cache[location][remainStep] = process2(limit, location + 1, remainStep - 1, target, cache);
            return cache[location][remainStep];
        }
        // 如果走到了最右边
        if (location == limit) {
            // 存入缓存
            cache[location][remainStep] = process2(limit, location - 1, remainStep - 1, target, cache);
            return cache[location][remainStep];
        }
        // 如果在中间，则统计向左 和 向右两种方案的情况
        // 存入缓存
        cache[location][remainStep] = process2(limit, location - 1, remainStep - 1, target, cache)
                + process2(limit, location + 1, remainStep - 1, target, cache);
        return cache[location][remainStep];
    }


    public static void main(String[] args) {
        System.out.println(calPlans(7, 3, 3, 2));
        System.out.println(dp(7, 3, 3, 2));
    }
}
