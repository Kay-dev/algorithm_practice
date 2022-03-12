package dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给定一个字符串，和一堆贴纸，每个贴纸由一个或多个字符组成，每个贴纸可以拆成多个字符使用。
 * 问：最少需要多少个贴纸才能拼成目标字符串？
 * 例如目标字符串为：“aabbcc”, 贴纸有["ae","bx","cd"], “ae”中可以拆出“a”，所以需要两个"ae"，同理，需要两个“bx”，两个“cd”，所以总共需要6个贴纸才能拼成目标字符串
 *
 * @author weizheng
 * @date 2022/03/11
 */
public class Code07_StickerToSpellWord {


    /**
     * @param rest 目标字符串
     * @param arr  字符串贴纸数组
     * @return 最少需要多少个贴纸才能拼成目标字符串
     */
    public static int getMinStringNum(String rest, String[] arr) {
        if ("".equals(rest) || arr.length == 0) {
            return 0;
        }
        Map<Character, Integer> srcMap = str2map(rest);
        return process(srcMap, arr);
    }


    /**
     * 递归方法
     *
     * @param restMap 剩余的字符串中每个字符各剩多少
     * @param arr     固定的贴纸字符串数组
     * @return 需要多少个贴纸才能拼完剩下的字符串
     */
    public static int process(Map<Character, Integer> restMap, String[] arr) {
        // 终止条件:所有的字符都拼接完成
        if (restMap.isEmpty()) {
            return 0;
        }
        Character character = restMap.keySet().stream()
                .findFirst()
                .orElse('0');
        int ans = -1;
        for (String str : arr) {
            // 只有当前字符串包含有剩余字符串的首字符时，才用当前str中的字符来拼剩余的字符
            if (str.contains(Character.toString(character))) {
                // 当前字符串中每个字符分别有多少个，封装成map
                Map<Character, Integer> curMap = str2map(str);
                // 从剩余字符串中减去当前字符串中包含的字符数
                int t = process(removeByMap(restMap, curMap), arr);
                if (t != -1) {
                    ans = ans == -1 ? t + 1 : Math.min(ans, 1 + t);
                }
            }
        }
        return ans;

    }

    private static Map<Character, Integer> str2map(String str) {
        Map<Character, Integer> srcMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            srcMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return srcMap;
    }

    private static Map<Character, Integer> removeByMap(Map<Character, Integer> src, Map<Character, Integer> target) {
        Map<Character, Integer> result = new HashMap<>();
        src.forEach((k, v) -> {
            if (target.containsKey(k)) {
                if (src.get(k) - target.get(k) > 0) {
                    result.put(k, src.get(k) - target.get(k));
                }
            } else {
                result.put(k, v);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMinStringNum("aabbcc", new String[]{"aa", "kcc", "bb"}));
    }

}
