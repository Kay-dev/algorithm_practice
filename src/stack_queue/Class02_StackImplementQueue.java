package stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author weizheng
 * @Description 如何用栈实现队列？
 * @date 2022/02/07
 */
public class Class02_StackImplementQueue {

    // 向队列add元素时，加入到这个栈中
    private Deque<Integer> pushStack = new ArrayDeque<>();
    // 队列poll的时候从这个栈中弹出元素
    private Deque<Integer> popStack = new ArrayDeque<>();

    public void add(Integer num) {
        moveData();
        pushStack.push(num);
    }

    public Integer poll() {
        checkEmpty();
        moveData();
        return popStack.pop();
    }

    public Integer peek() {
        checkEmpty();
        moveData();
        return popStack.peek();
    }

    // 将push栈中的所有元素依次弹出，并压入到pop栈中
    private void moveData() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    private void checkEmpty() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }
    }

    public static void main(String[] args) {
        Class02_StackImplementQueue queue = new Class02_StackImplementQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
