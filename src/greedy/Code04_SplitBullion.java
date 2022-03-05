package greedy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 一块金条切成两半，是需要花费和长度一样的铜板的。
 * 比如20长的金条，不管怎么切都要花费20个铜板。
 * 例如：给定数组[10,20,30]代表三个人，整块金条长度为60，金条要分为10，20，30三部分。
 * 如果先把60分为10和50，花费60个铜板，再把50分为20和30，花费50个铜板，总共花费110个铜板；
 * 但如果吧60分为30和30，花费60个铜板，再把30分为10和20，花费30个铜板，总共只花费90个铜板
 * 问：一群人怎么分最省铜板？返回分割的最小代价。
 *
 * @author weizheng
 * @date 2022/03/03
 */
public class Code04_SplitBullion {

    /**
     * 方法一：暴力递归
     *
     * @param arr
     * @return
     */
    public static int forceRecursive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Set<Integer> resultSet = new HashSet<>();
        Deque<Integer> list = new ArrayDeque<>();
        process(arr, list, resultSet);
        return resultSet.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    /**
     * 暴力递归，求出所有可能
     *
     * @param arr       原数组
     * @param used      记录每一步消耗的铜片数
     * @param resultSet 收集每种方案所消耗的铜片数
     */
    private static void process(int[] arr, Deque<Integer> used, Set<Integer> resultSet) {
        if (arr.length == 0) {
            return;
        }
        // 终止条件,只剩最后一块的时候，不用再分了，统计这个方案消耗的所有铜片，放到结果集中
        if (arr.length == 1) {
            int sum = used.stream().mapToInt(Integer::intValue).sum();
            resultSet.add(sum);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int[] newArray = copyArray(arr, i);
            Arrays.stream(arr).forEach(used::push);
            process(newArray, used, resultSet);
            Arrays.stream(arr).forEach(k -> used.pop());
        }
    }

    public static int[] copyArray(int[] arr, int index) {
        return IntStream.range(0, arr.length)
                .boxed()
                .filter(i -> i != index)
                .mapToInt(i -> arr[i])
                .toArray();
    }

    /**
     * 贪心：用小根堆实现
     *
     * @param arr
     * @return
     */
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i : arr) {
            queue.add(i);
        }
        int sum = 0;
        int cur;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

    // ============================ test ====================================
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(forceRecursive(arr));
        System.out.println(lessMoney(arr));
    }

}
