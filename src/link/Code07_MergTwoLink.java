package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description 将两个有序链表合并成一个有序链表
 * @date 2022/01/29
 */
public class Code07_MergTwoLink {

    public static void main(String[] args) {

        Node<Integer> node1 = new Node<>(1);
        node1.add(2).add(4).add(5);

        Node<Integer> node2 = new Node<>(2);
        node2.add(3).add(4);
        System.out.println(merge(node1,node2));

    }

    public static Node<Integer> merge(Node<Integer> link1, Node<Integer> link2) {
        if (link1 == null) {
            return link2;
        }
        if (link2 == null) {
            return link1;
        }
        // 确定头节点在哪个链表上
        Node<Integer> head = link1.value < link2.value ? link1 : link2;
        // 链表一上的指针
        Node<Integer> cur1 = head.next;
        // 链表二上的指针
        Node<Integer> cur2 = head == link1 ? link2 : link1;
        // 记录当前位置的指针
        Node<Integer> pre = head;
        while (cur1!=null && cur2!=null) {
            if (cur1.value < cur2.value) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

}
