package dynamic_programming;

/**
 * 范围尝试模型
 * <p>
 * 给定一个int[],代表不同的纸牌，玩家A和B依次拿走一张牌。
 * 规定A先拿，每次只能从数组左边或右边拿，两位玩家都会选择对己方最有利，且对对方最不利的取牌方案
 * 问：最后获胜者的分数？
 *
 * @author weizheng
 * @date 2022/03/07
 */
public class Code05_TryModel2_CardsInLine {

    /**
     * 递归解法
     *
     * @param arr 牌堆
     * @return 最高的总分
     */
    public static int maxScore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 取先手方和后手方中总分最高的返回
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    /**
     * 先手
     *
     * @param arr   纸牌数组
     * @param left  左边界
     * @param right 右边界
     * @return 总分
     */
    public static int first(int[] arr, int left, int right) {
        // 终止条件：牌堆里只有最后一张牌了
        if (left == right) {
            return arr[left];
        }
        // 若取左边的牌,然后看后手方选完后，给什么牌
        int leftScore = arr[left] + second(arr, left + 1, right);
        // 若取右边的牌,然后看后手方选完后，给什么牌
        int rightScore = arr[right] + second(arr, left, right - 1);
        // 先手方取总分较大的方案
        return Math.max(leftScore, rightScore);
    }

    /**
     * 后手
     *
     * @param arr   纸牌数组
     * @param left  左边界
     * @param right 右边界
     * @return 返回给先手方的牌分数
     */
    private static int second(int[] arr, int left, int right) {
        // 终止条件：牌堆中只有一张牌，那这张牌必然属于先手方
        if (left == right) {
            return 0;
        }
        // 后手方，选左边
        int leftScore = first(arr, left + 1, right);
        // 后手方，选右边
        int rightScore = first(arr, left, right - 1);
        // 后手方自己留较大的牌，将较小的牌留给先手方下次选
        return Math.min(leftScore, rightScore);
    }


    /**
     * 动态规划解法, 从对角线开始依次填充
     * @param arr
     * @return
     */
    public static int dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        int[][] first = new int[length][length];
        int[][] second = new int[length][length];
        // 初始化对角线位置
        for (int i = 0; i < length; i++) {
            first[i][i] = arr[i];
        }

        for (int i = 1; i <length; i++) {
            int row = 0;
            int col = i;
            // 依次填充每条对角线
            while (row < length && col < length) {
                int leftScore = arr[row] + second[row + 1][col];
                int rightScore = arr[col] + second[i][col - 1];
                first[row][col] = Math.max(leftScore, rightScore);

                int lScore = first[row + 1][col];
                int rScore = first[row][col - 1];
                second[row][col] = Math.min(lScore, rScore);
                row++;
                col++;
            }
        }
        return Math.max(first[0][length - 1], second[0][length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 100, 3};
        System.out.println(maxScore(arr));
        System.out.println(dp(arr));
    }
}
