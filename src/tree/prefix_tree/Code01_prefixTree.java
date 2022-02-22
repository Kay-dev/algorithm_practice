package tree.prefix_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 前缀树
 *
 * @author weizheng
 * @date 2022/02/21
 */
public class Code01_prefixTree {
    // 起始位置
    private Node root;

    public Code01_prefixTree() {
        this.root = new Node();
    }

    /**
     * 将一字符串加入前缀树
     *
     * @param string
     */
    public void insert(String string) {
        if (string == null || string.length() == 0) {
            return;
        }

        char[] chars = string.toCharArray();
        Node node = root;
        // 根节点通过数加1
        node.pass++;
        for (int i = 0; i < chars.length; i++) {
            int ascii = chars[i];
            // 如果当前节点下还没有这个字符，就把这个字符添加进去
            if (!node.next.containsKey(ascii)) {
                node.next.put(ascii, new Node());
            }
            node = node.next.get(ascii);
            // 通过数+1
            node.pass++;
        }
        // 结束数+1
        node.end++;

    }

    /**
     * 删除树上的某一字符串
     *
     * @param string
     */
    public void delete(String string) {
        // 先判断前缀数中是否有这个字符串
        if (!contains(string)) {
            return;
        }
        char[] chars = string.toCharArray();
        Node node = root;
        // 根节点通过数-1
        node.pass--;
        for (int ascii : chars) {
            // 如果某一个字符节点的pass数已经为0，那么它后面的节点都可以丢弃了，然后直接return。
            if (--node.next.get(ascii).pass == 0) {
                node.next.remove(ascii);
                return;
            }
            node = node.next.get(ascii);
        }
        node.end--;
    }

    /**
     *
     * 统计某个字符串添加过几次
     *
     * @param string
     * @return
     */
    public int search(String string) {
        if (string == null) {
            return 0;
        }
        char[] chars = string.toCharArray();
        Node node = root;
        for (int ascii : chars) {
            if (node.next.containsKey(ascii)) {
                node = node.next.get(ascii);
            } else {
                return 0;
            }
        }
        return node.end;
    }

    /**
     * 当前前缀树中是否包含指定字符串
     *
     * @param string
     * @return
     */
    public boolean contains(String string) {
        return search(string) > 0;
    }

    /**
     * 对所有加入的字符串，统计以某一字符串开头的有多少个
     *
     * @param prefix
     * @return
     */
    public int prefixCount(String prefix) {
        if (prefix == null) {
            return 0;
        }
        char[] chars = prefix.toCharArray();
        Node node = root;
        for (int ascii : chars) {
            if (node.next.containsKey(ascii)) {
                node = node.next.get(ascii);
            } else {
                return 0;
            }
        }
        return node.pass;
    }


    // 测试
    public static void main(String[] args) {

        int TEST_TIME = 100_000;
        int ARRAY_LENGTH = 100;
        int STR_LENGTH = 5;
        Random random = new Random();
        for (int i = 0; i < TEST_TIME; i++) {
            String[] strings = generateRandomStrArray(STR_LENGTH, ARRAY_LENGTH);
            Code01_prefixTree tree = new Code01_prefixTree();
            RightPrefixTree rightTree = new RightPrefixTree();

            for (int j = 0; j < strings.length; j++) {
                String k = strings[j];
                double v = random.nextDouble();
                if (v < 0.25) {
                    tree.insert(k);
                    rightTree.insert(k);
                } else if (v < 0.5) {
                    tree.delete(k);
                    rightTree.delete(k);
                } else if (v < 0.75) {
                    int search1 = tree.search(k);
                    int search2 = rightTree.search(k);
                    if (search1 != search2) {
                        System.err.println("search() test fail");
                    }
                } else {
                    int count1 = tree.prefixCount(k);
                    int count2 = rightTree.prefixCount(k);
                    if (count1 != count2) {
                        System.err.println("prefixCount() test fail");
                        System.out.println("count1="+count1);
                        System.out.println("count2="+count2);
                    }
                }

            }
        }
        System.out.println("finished");
    }

    // 测试用，生成随机字符串数组
    public static String[] generateRandomStrArray(int stringLength, int arraySize) {
        Random random = new Random();
        String[] array = new String[random.nextInt(arraySize) + 1];
        for (int i = 0; i < array.length; i++) {
            String string = IntStream.rangeClosed(1, random.nextInt(stringLength) + 1)
                    .mapToObj(k -> (char) (random.nextInt(26) + 17 + '0'))
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            array[i] = string;
        }
        return array;
    }
}

class Node {
    // 字符通过数，表示经过这个字符节点的字符串数量
    public int pass;
    // 字符结束数，表示以此字符节点结束的字符串数量
    public int end;
    // <当前节点的ASCII值，下一级节点>
    public Map<Integer, Node> next;

    public Node() {
        this.pass = 0;
        this.end = 0;
        this.next = new HashMap<>();
    }
}

// 用于测试的绝对正确前缀树
class RightPrefixTree {
    private Map<String, Integer> map;

    public RightPrefixTree() {
        this.map = new HashMap<>();
    }

    public void insert(String string) {
        map.compute(string, (k, v) -> v == null ? 1 : v + 1);
    }

    public void delete(String string) {
        if (map.containsKey(string)) {
            if (map.get(string) == 1) {
                map.remove(string);
            } else {
                map.put(string, map.get(string) - 1);
            }
        }
    }

    public int search(String string) {
        return map.getOrDefault(string, 0);
    }

    public int prefixCount(String string) {
        return map.entrySet().parallelStream()
                .filter(i -> i.getKey().startsWith(string))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

}