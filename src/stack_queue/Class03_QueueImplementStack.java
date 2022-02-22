package stack_queue;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用队列实现栈
 * @author weizheng
 * @date 2022/02/22
 */
public class Class03_QueueImplementStack<T> {

    private Queue<T> queue;
    private int size;
    private T first;

    public Class03_QueueImplementStack() {
        queue = new LinkedList<>();
        size = 0;
        first = null;
    }

    public void push(T t) {
        queue.add(t);
        first = t;
        size++;
    }

    /**
     * 从头将元素放到队尾，直到最后一个元素成为队首，然后弹出
     *
     * @return
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            size--;
            first = null;
            return queue.poll();
        }

        for (int i = 1; i < size; i++) {
            T poll = queue.poll();
            // 将倒数第二个元素标记栈顶元素
            if (i == size - 1) {
                first = poll;
            }
            queue.add(poll);
        }
        size--;
        return queue.poll();
    }

    public T peek() {
        return first;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public static void main(String[] args) throws InterruptedException {

        int testTime = 1_000_000;
        Random random = new Random();
        Deque<Integer> stack = new ArrayDeque<>();
        Class03_QueueImplementStack<Integer> myStack = new Class03_QueueImplementStack<>();
        for (int i = 0; i < testTime; i++) {
            double num = random.nextDouble();
            if (num < 0.25) {
                int i1 = random.nextInt(10);
                stack.push(i1);
                myStack.push(i1);
            } else if (num < 0.5) {
                if (!stack.isEmpty() || !myStack.isEmpty()) {
                    Integer pop1 = stack.pop();
                    Integer pop2 = myStack.pop();
                    if (!Objects.equals(pop1, pop2)) {
                        System.err.println("pop() test fail");
                    }
                }
            } else if (num < 0.75) {
                Integer peek1 = stack.peek();
                Integer peek2 = myStack.peek();
                if (!Objects.equals(peek1, peek2)) {
                    System.err.println("peek() test fail");
                }
            } else if (num < 1) {
                if (stack.size() != myStack.size) {
                    System.err.println("size() test fail");
                }
            }

        }
    }
}
