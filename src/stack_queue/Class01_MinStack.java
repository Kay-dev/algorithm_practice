package stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author weizheng
 * @Description 为栈提供一个getMin()方法，返回栈中当前最小值。要求时间复杂度O(1)。
 * <p>
 * 思路：
 * 添加一个额外的栈用于存储最小值，每次push元素时，将元素和最小栈栈顶的元素比较，谁小，将谁压入最小栈。pop弹出时，两个栈同时弹出。
 * @date 2022/02/07
 */
public class Class01_MinStack {

    // 存储全部数据的栈
    public Deque<Integer> data = new ArrayDeque<>();
    // 存储最小值的栈
    public Deque<Integer> minData = new ArrayDeque<>();


    public void push(Integer num) {
        if (minData.isEmpty() || minData.peek() > num) {
            minData.push(num);
        } else {
            minData.push(minData.peek());
        }
        data.push(num);
    }

    public Integer pop() {
        checkSize();
        minData.pop();
        return data.pop();
    }

    public Integer getMin() {
        checkSize();
        return minData.peek();
    }

    private void checkSize() {
        if (data.size() == 0) {
            throw new RuntimeException("stack is empty!");
        }
    }

    public static void main(String[] args) {
        Class01_MinStack minStack = new Class01_MinStack();
        minStack.push(3);
        minStack.push(2);
        minStack.push(2);
        minStack.push(1);
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMin());
    }
}
