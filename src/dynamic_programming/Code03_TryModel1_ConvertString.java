package dynamic_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 从左到右的尝试模型
 * <p>
 * 已知A与1对应，B与2对应，C与3对应，...Z与26对应
 * <p>
 * 若给定一个字符串“111”，它可以转换成"AK","KA","AAA"
 * 问：传入任意数字字符串，求出可能的字母组合有多少种？
 *
 * @author weizheng
 * @date 2022/03/06
 */
public class Code03_TryModel1_ConvertString {

    /**
     * 暴力递归
     *
     * @param string 要处理的字符串
     */
    public static List<String> calTransResult(String string) {
        if (string == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        process(string.toCharArray(), 0, "", result);
        return result;
    }

    /**
     * 递归函数
     *
     * @param chars  字符数组
     * @param i      当前下标
     * @param s      当前已经转换过的字符串结果
     * @param result 存储所有拼接完成的字符串结果
     */
    private static void process(char[] chars, int i, String s, List<String> result) {
        if (i == chars.length) {
            result.add(s);
        } else {
            // 由于题目中条件只有从A到Z的映射，对应的ASCII范围为[65,90],所以最多一次取两位，所以k只能取1或2。
            for (int k = 1; i < chars.length && k < 3; k++) {
                // 从i位置开始，获取k个字符，并将之转换数字，再根据转换关系转成char
                char cur = getLeftChar(chars, i, k);
                // 若char是大写字符，证明当前这种字符串取法可以按题目中转换关系转换成功。
                if (Character.isUpperCase(cur)) {
                    // 下一层递归，从i后第k个字符开始
                    process(chars, i + k, s + cur, result);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 从i位置开始向后取k个字符，组成数字字符串，然后转成char返回
     *
     * @param chars 源数字字符数组，形如：['1','2','3']
     * @param i     从哪个位置开始
     * @param k     从i位置向后取多少个
     * @return 若i=0,k=1,则取出'1',按规则转成'A'然后返回
     */
    private static char getLeftChar(char[] chars, int i, int k) {
        String str = IntStream.range(i, Math.min(k + i, chars.length))
                .boxed()
                .map(l -> chars[l])
                .map(String::valueOf)
                .collect(Collectors.joining());
        return (char) (Integer.parseInt(str) + 64);
    }


    public static List<String> dp(String string) {
        return new ArrayList<>();


    }


    public static void main(String[] args) {
        System.out.println(calTransResult("111"));

    }

}
