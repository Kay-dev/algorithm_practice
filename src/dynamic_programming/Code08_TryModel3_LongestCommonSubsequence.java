package dynamic_programming;

/**
 * 多样本位置全对应的尝试模型
 * <p>
 * 找出两个字符串中的最长公共子序列是多长？
 *
 * @author weizheng
 * @date 2022/03/12
 */
public class Code08_TryModel3_LongestCommonSubsequence {

    /**
     * 递归
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 两个字符串中最长公共子序列的长度
     */
    public static int lcselcs(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        return process(chars1, chars2, 0, 0);
    }

    /**
     *
     * @param chars1 字符数组1
     * @param chars2 字符数组2
     * @param i1 数组1当前下标
     * @param i2 数组2当前下标
     * @return 最长公共子序列的长度
     */
    private static int process(char[] chars1, char[] chars2, int i1, int i2) {
        // 终止条件
        if (i1 == chars1.length || i2 == chars2.length) {
            return 0;
        }
        int result = 0;
        // 遍历每一个字符
        for (int i = i1; i < chars1.length; i++) {
            int cur = 0;
            for (int j = i2; j < chars2.length; j++) {
                // 若找到了相同的字符，则公共子序列长度+1
                if (chars1[i] == chars2[j]) {
                    cur++;
                    // 继续递归
                    cur += process(chars1, chars2, i + 1, j + 1);
                    break;
                }
            }
            result = Math.max(result, cur);
        }
        return result;
    }


    /**
     * 动态规划
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长公共子序列的长度
     */
    public static int dp(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 准备一个i*j (i=str1.length,j=str2.length) 的二维表。其中每个位置上的值含义为：str1中0-i部分 和 str2中0-j部分的最长公共子序列的长度
        // 所以只需要根据规律，填充完这张二维表，dp[i][j]位置的值就是最终的结果。
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] dp = new int[l1][l2];
        // 填充[0,0]位置
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        // 填充第一列
        for (int i = 1; i < l1; i++) {
            if (chars1[i] == chars2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        // 填充第一行
        for (int i = 1; i < l2; i++) {
            if (chars1[0] == chars2[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        // 依次填充所有数据，有以下几种情况
        // 1. 若最长子序列结束于str1和str2最后一位，那么最长子序列的长度为：dp[i-1][j-1] + 1
        // 2. 若最长子序列结束于str1最后一位，而结束于str2中间某个字符，那么结果为：dp[i][j-1]
        // 3. 若最长子序列结束于str2最后一位，而结束于str1中间某个字符，那么结果为：dp[i-1][j]
        // 4. 若最长子序列结束于str1中间位置，同时也结束于str2中间位置，那么结果为：dp[i-1][j-1]
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                // 情况2和3中取出较大的值
                int max = Math.max(dp[i][j - 1], dp[i - 1][j]);
                // 只有两个字符串最后一位字符相同时，考虑情况1
                // 情况4无需单独考虑，因为情况2中的dp[i][j-1]和3中的dp[i-1][j]是由情况4的dp[i-1][j-1]值推出来的，dp[i-1][j-1]必不大于dp[i][j-1]或dp[i-1][j]
                if (chars1[i] == chars2[j]) {
                    max = Math.max(dp[i - 1][j - 1] + 1, max);
                }
                dp[i][j] = max;
            }
        }
        return dp[l1 - 1][l2 - 1];
    }


    public static void main(String[] args) {
        System.out.println(lcselcs("abcdef", "fghacebdf"));
        System.out.println(dp("abcdef", "fghacebdf"));
    }

}
