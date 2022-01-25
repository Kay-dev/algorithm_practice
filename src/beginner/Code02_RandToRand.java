package beginner;

import java.sql.Timestamp;

/**
 * @author weizheng
 * @Description 题目：假设有一个f(x)函数能等概率返回[1,5]之间的一个数，
 * 求：如何能够在不修改f(x)函数的前提下，得到另一个函数g(x)能等概率返回[1,x]之间的数。
 * @date 2022/01/25
 */
public class Code02_RandToRand {

    public static void main(String[] args) {
        int times = 1_000_000;

        int[] counts = new int[8];
        for (int i = 1; i < times; i++) {
            counts[g()]++;
        }
        for (int i = 1; i < counts.length; i++) {
            System.out.println(i + "出现了" + counts[i] + "次");
        }
    }


    /**
     * 在f2()的基础上,丢弃掉0，最终在[1,7]之间等概率返回
     * @return
     */
    public static int g() {
        int res;
        do {
            res = f2();
        } while (res == 0);
        return res;
    }


    /**
     * 在f1()的基础上，通过移位操作获得000-111之间的数，也即[0,7]之间的数。
     *
     * @return
     */
    public static int f2() {
        return (f1() << 2) + (f1() << 1) + f1();
    }

    /**
     * 在f()的基础上，丢弃掉3，转换为等概率返回0 或 1
     *
     * @return
     */
    public static int f1() {
        int res = 0;
        do {
            res = f();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    /**
     * 已知函数：返回[1,5]之间的一个随机数
     *
     * @return
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }
}
