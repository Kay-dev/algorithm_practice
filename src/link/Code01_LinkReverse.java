package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description 单链表反转
 * @date 2022/01/26
 */
public class Code01_LinkReverse {


    public static void main(String[] args) {

        Node<Integer> node = new Node<>(1);
        node.add(2).add(3);
        System.out.println(node);
        Node<Integer> reverse = reverse(node);
        System.out.println(reverse);

    }


    public static <T> Node<T> reverse(Node<T> node) {
        if (node.next == null) {
            return node;
        }

        Node<T> head = node;
        Node<T> pre = null;
        Node<T> next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
}
