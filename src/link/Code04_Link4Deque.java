package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description 链表实现双端队列
 * @date 2022/01/26
 */
public class Code04_Link4Deque {


}

class MyDeque<T> {

    private Node<T> head;
    private Node<T> tail;
    private Integer size;

    public MyDeque() {
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

    public void pushFirst(T value) {
        Node<T> node = new Node<>(value);
        if (head != null) {
            head.last = node;
            node.next = head;
            head = node;
        } else {
            head = node;
            tail = node;
        }
        size++;
    }

    public void pushLast(T value) {
        Node<T> node = new Node<>(value);
        if (tail != null) {
            tail.next = node;
            node.last = tail;
            tail = node;
        } else {
            head = node;
            tail = node;
        }
        size++;
    }

    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;
        }
        size--;
        return result;
    }

    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T result = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            tail.next = null;
        }
        size--;
        return result;
    }

    public T peekFirst() {
        return isEmpty() ? null : head.value;

    }

    public T peekLast() {
        return isEmpty() ? null : tail.value;
    }

}
