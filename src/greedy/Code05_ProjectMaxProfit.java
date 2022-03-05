package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 输入：int[] costs, int[] profits, int K, int M
 * costs[i] 表示i号项目的花费
 * profits[i] 表示i号项目的纯利润
 * K 表示最多只能做K个项目，且不能并行做项目
 * M 代表你的初始资金
 * <p>
 * 每做完一个项目，马上获得收益，可以支持你去做下一个项目
 * <p>
 * 问：怎么做项目才能让获得的利润最大？输出最后获得的钱
 *
 * @author weizheng
 * @date 2022/03/03
 */
public class Code05_ProjectMaxProfit {


    /**
     * 方法一：暴力递归
     *
     * @param costs
     * @param profits
     * @param K
     * @param M
     * @return
     */
    public static int forceRecursive(int[] costs, int[] profits, int K, int M) {
        // 封装成项目对象，存放在这个集合中
        List<Project> projects = new ArrayList<>();
        // 存放所有可能的收益结果
        List<Integer> result = new ArrayList<>();
        // 封装成对象，方便使用
        for (int i = 0; i < costs.length; i++) {
            projects.add(new Project(costs[i], profits[i]));
        }
        process(projects, K, M, result);
        return result.stream().max(Integer::compareTo).orElse(0);

    }

    private static void process(List<Project> projects, int times, int initial, List<Integer> result) {
        // 终结条件，将最终的钱收集进结果集中
        if (projects.isEmpty() || times == 0 || initial == 0) {
            result.add(initial);
            return;
        }
        for (Project project : projects) {
            if (project.cost <= initial) {
                // 将当前项目剔除后，继续递归
                List<Project> newList = projects.stream().filter(i -> i != project).collect(Collectors.toList());
                process(newList, times - 1, initial + project.profit, result);
            }
        }
    }

    /**
     * 贪心策略：使用堆实现
     * @param costs
     * @param profits
     * @param K
     * @param M
     * @return
     */
    public static int getMaxProfit(int[] costs, int[] profits, int K, int M) {
        // 按项目花费排序的小根堆
        PriorityQueue<Project> costQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        // 按利润排序的大根堆
        PriorityQueue<Project> profitQueue = new PriorityQueue<>((a,b)-> Integer.compare(b.profit, a.profit));
        // 把所有项目加入小根堆
        for (int i = 0; i < costs.length; i++) {
            costQueue.add(new Project(costs[i], profits[i]));
        }
        while (K > 0) {
            // 将当前能做的项目全部取出，放到大根堆中
            while (!costQueue.isEmpty() && costQueue.peek().cost <= M) {
                profitQueue.add(costQueue.poll());
            }
            // 从大根堆中取出利润最大的项目
            if (!profitQueue.isEmpty()) {
                M += profitQueue.poll().profit;
            }
            K--;
        }
        return M;
    }

    //==================================== test ============================================
    public static void main(String[] args) {
        System.out.println(forceRecursive(new int[]{1, 2, 3}, new int[]{3, 4, 5}, 2, 2));
        System.out.println(getMaxProfit(new int[]{1, 2, 3}, new int[]{3, 4, 5}, 2, 2));
    }


    static class Project {
        // 此项目的花费
        int cost;
        // 此项目的纯利润
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

}
