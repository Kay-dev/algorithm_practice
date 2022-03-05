package greedy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给定一组字符串，要求获得所有字符拼接后字典序最小的方案
 *
 * @author weizheng
 * @date 2022/03/01
 */
public class Code01_DepthFirst {


    /**
     * 方法一，采用深度优先的递归
     *
     * @param strings
     * @return
     */
    public static String findLowestString1(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        // 获取所有可能的字符串拼接结果
        process(strings, set, "", list);
        // 找出字典序最小的那一个，并返回
        return list.stream()
                .reduce((i, j) -> i.compareTo(j) < 0 ? i : j)
                .orElse("");

    }

    /**
     * 全排列, 深度优先获取所有可能拼接出的字符串
     *
     * @param strings 提供的字符串数组
     * @param used    已经使用过的字符串下标
     * @param path    已经使用过的字符串拼接而成的结果
     * @param all     收集所有拼接完成的字符串
     */
    public static void process(String[] strings, Set<Integer> used, String path, List<String> all) {
        // 所有字符串都已经使用过，则收集进all集合中
        if (used.size() == strings.length) {
            all.add(path);
        } else {
            // 对每一个字符串进行判断，是否使用过，未使用过，则递归
            for (int i = 0; i < strings.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    process(strings, used, path + strings[i], all);
                    used.remove(i);
                }
            }
        }
    }

    /**
     * 方法二，贪心算法，采用自定义比较器实现
     *
     * @param strings
     * @return
     */
    public static String findLowestString2(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        // 对字符串数组排序(排序规则为：[a,b] 若拼接结果ab<ba,则a在前，b在后),然后将由小到大排序好的字符串拼接在一起
        return Arrays.stream(strings)
                .sorted( (a, b) -> (a + b).compareTo(b + a))
                .collect(Collectors.joining());
    }

    // ================================= test =========================================

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String[] strings = generateStringArray(10, 5);
            String[] stringsCopy = new String[strings.length];
            System.arraycopy(strings,0,stringsCopy,0,strings.length);
            if (!Objects.equals(findLowestString1(strings), findLowestString2(stringsCopy))) {
                System.out.println("Failed");
            }
        }
        System.out.println("Finished");

    }

    /**
     * 生成容量随机，长度随机的字符串数组
     *
     * @param size
     * @param strLength
     * @return
     */
    public static String[] generateStringArray(int size, int strLength) {
        Random random = new Random();
        return IntStream.range(0, random.nextInt(size) + 1)
                .boxed()
                .map(i -> generateString(strLength))
                .toArray(String[]::new);
    }

    /**
     * 生成随机长度的字符串
     *
     * @param strLength
     * @return
     */
    public static String generateString(int strLength) {
        Random random = new Random();
        int length = random.nextInt(strLength) + 1;
        return IntStream.range(0, length)
                .boxed()
                .map(i -> (char) (random.nextInt(10) + 97))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
