package link;

import link.entity.Node;

import java.util.stream.IntStream;

/**
 * @author weizheng
 * @Description 链表实现队列
 * @date 2022/01/26
 */
public class Code02_Link4Queue {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        IntStream.rangeClosed(1,10)
                .forEach(queue::offer);
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        queue.poll();
        System.out.println(queue.size());
        System.out.println(queue.peek());
    }


}

class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private Integer size;

    public MyQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public Integer size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void offer(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T poll() {
        T result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
            size--;
        }
        // 如果队列中此时已经没有数据，则将head和tail都指向null
        if (head == null) {
            tail = null;
        }
        return result;
    }

    public T peek() {
        if (head != null) {
            return head.value;
        }
        return null;
    }

}
