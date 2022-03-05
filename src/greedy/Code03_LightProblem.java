package greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 传入一个字符串，字符串由“X” 和 “.”组成。“X”位置不能放灯，也无需照亮，“.”位置可以放灯，灯可以将左右位置照亮。
 * 问：要让字符串中的所有位置都照亮，最少需要放多少个灯？
 *
 * @author weizheng
 * @date 2022/03/02
 */
public class Code03_LightProblem {

    /**
     * 方法一：暴力递归
     *
     * @param str
     * @return
     */
    public static int forceRecursive(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 包含所有字符的集合
        char[] chars = str.toCharArray();
        int index = 0;
        Set<Integer> set = new HashSet<>();
        return process(chars, index, set);

    }

    /**
     * 递归收集所有结果
     *
     * @param chars 包含所有字符的集合
     * @param index 当前已经计算到哪个位置
     * @param set   已经放过灯的位置下标
     * @return 照亮所有地方需要的最小灯数
     */
    private static int process(char[] chars, int index, Set<Integer> set) {
        // 终止条件：当前是否已经到达字符串最后一个字符
        if (chars.length == index) {
            // 判断这个方案，是否能将字符串中所有的“.”照亮,如果不能，返回整型最大值
            for (int i = 0; i < chars.length; i++) {
                if ('X' != chars[i] && !set.contains(i - 1) && !set.contains(i) && !set.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            // 返回收集的灯数
            return set.size();
        } else {
            // 计算当前位置不放灯的结果
            int noLight = process(chars, index + 1, set);
            // 计算当前位置放灯的结果
            int light = Integer.MAX_VALUE;
            if ('.' == chars[index]) {
                set.add(index);
                light = process(chars, index + 1, set);
                set.remove(index);
            }
            // 在放灯和不放灯两种结果中取最小值
            return Math.min(noLight, light);
        }
    }

    /**
     * 贪心策略:每次都做出局部最佳的决定
     * @param str
     * @return
     */
    public static int getLightCount(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int light = 0;
        // 依次向后遍历，每次都做出局部最佳的决定
        while (index < chars.length) {
            // 当前字符是X，跳向下一个字符做判断
            if (chars[index] == 'X') {
                index++;
            } else {
                // 若当前字符是'.'，那么必有灯，剩下的只是考虑灯放置的位置
                light++;
                // 边界检查
                if (index + 1 == chars.length) {
                    break;
                }
                // “.X.”形式，直接向后跳2位
                if (chars[index + 1] == 'X') {
                    index = index + 2;
                    // “...”形式，直接向后跳3位
                } else {
                    index = index + 3;
                }
            }
        }
        return light;
    }


    public static void main(String[] args) {
        System.out.println(forceRecursive("X...X.X..X....X."));
        System.out.println(getLightCount("X...X.X..X....X."));

    }

}
