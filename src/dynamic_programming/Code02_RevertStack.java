package dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 将一个栈逆序
 * 要求：只能使用递归，不能使用额外的数据结构
 *
 * @author weizheng
 * @date 2022/03/06
 */
public class Code02_RevertStack {


    /**
     * 反转一个给定的栈
     *
     * @param stack
     */
    public static void revertStack(Deque<Integer> stack) {
        if (!stack.isEmpty()) {
            // 每次取栈底的值
            Integer last = getLast(stack);
            // 将剩下的栈反转
            revertStack(stack);
            // 将栈底的值压入到栈顶
            stack.push(last);
        }
    }

    /**
     * 获取栈底的值
     *
     * @param stack
     * @return
     */
    public static Integer getLast(Deque<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            Integer pop = stack.pop();
            Integer last = getLast(stack);
            stack.push(pop);
            return last;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        revertStack(stack);
        stack.forEach(System.out::println);
    }

}
