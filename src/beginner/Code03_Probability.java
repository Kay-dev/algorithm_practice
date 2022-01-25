package beginner;

/**
 * @author weizheng
 * @Description 有一个函数f()能以概率p1返回0，以概率p2返回1，其中p1！=p2。如何能在不修改f()的基础上得到一个g()能等概率返回0，1
 * @date 2022/01/25
 */
public class Code03_Probability {

    public static void main(String[] args) {
        int times = 1_000_000;
        int count = 0;
        for (int i = 0; i < times; i++) {
            if (f() == 0) {
                count++;
            }
        }
        System.out.println("0出现的概率是："+(double)count/times);

    }


    /**
     * 如何等概率返回0，1？
     * 考虑到分别执行两次，各组合出现的概率：
     *     00：1/p1 * 1/p1
     *     01: 1/p1 * 1/p2
     *     10: 1/p2 * 1/p1
     *     11: 1/p2 * 1/p2
     * 其中发现，0和1的不等概率体现在00 和 11 上面。排除掉这两种情况，剩下的01和10的概率就各为1/2
     * @return
     */
    public static int g() {
        int res = 0;
        do {
            res = f();
            // 若两次的结果一样，则表示出现了00或11的情况，则丢弃，然后重新计算。
        } while (res == f());
        return res;
    }


    /**
     * 已知函数，以0.7的概率返回0，以0.3的概率返回1
     * @return
     */
    public static int f() {
        return Math.random() < 0.7 ? 0 : 1;
    }

}
