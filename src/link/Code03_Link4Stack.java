package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description 链表实现栈
 * @date 2022/01/26
 */
public class Code03_Link4Stack {
}


class MyStack<T> {

    private Node<T> head;
    private Integer size;

    public MyStack() {
        head = null;
        size = 0;
    }

    public Integer size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        head = node;
        if (!isEmpty()) {
            node.next = head;
        }
        size++;
    }

    public T pop() {
        T result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
            size--;
        }
        return result;
    }

}
