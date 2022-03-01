package tree.binary_tree;

/**
 * 一张纸条对折，会生成一条折痕，再次对折，又会生成两条折痕。
 * 要求实现一个函数，打印出对折N次，纸条打开后上从上到下的折痕朝向。
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code13_PaperFold {

    public static void main(String[] args) {
        printFoldMarks(1);
        System.out.println();
        printFoldMarks(2);
        System.out.println();
        printFoldMarks(3);
        System.out.println();
        printFoldMarks(4);
    }

    /**
     * 可以看成二叉树问题，每次对折后，都会在原折痕的上方加一条凹折痕，在下方加一条凸折痕
     *
     * @param times
     */
    public static void printFoldMarks(int times) {
        process(1, times, false);
    }

    /**
     * @param i     当前是第几层
     * @param times 总对折次数
     * @param face  折痕朝向(true: up , false: down)
     */
    private static void process(int i, int times, boolean face) {
        if (i > times) {
            return;
        }
        process(i + 1, times, false);
        System.out.print(face ? "up " : "down ");
        process(i + 1, times, true);
    }

}
