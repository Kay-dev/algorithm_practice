package beginner;

/**
 * @author weizheng
 * @Description 打印出整型的32位信息
 * @date 2022/01/24
 */
public class Code06_PrintB {


    public static void main(String[] args) {
        int i = Integer.MAX_VALUE;
        print(i);
        System.out.println();
        System.out.println(Integer.toBinaryString(i));

        //取反
        int j = ~i;
        print(j);

        newblock();
        // 对一个整数取反 等价于 二进制取反 + 1
        int a = 5;
        int b = ~a + 1;
        print(a);
        print(b);

        newblock();
        // 对整型最小值取反，等于它自身
        print(Integer.MIN_VALUE);
        print(~Integer.MIN_VALUE + 1);

    }

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();

    }

    public static void newblock() {
        System.out.println("===================");
    }

}
