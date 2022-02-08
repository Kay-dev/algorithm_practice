package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description 删除链表上等于给定值的节点
 * <p>
 * eg：
 * 1->2->3->3->4
 * <p>
 * input: 3
 * output: 1->2->4
 * @date 2022/02/06
 */
public class Code09_RemoveNodeFromLink {


    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        head.add(2).add(3).add(3).add(4);
        System.out.println(removeByValue(head,4));

    }

    public static Node<Integer> removeByValue(Node<Integer> head, int num) {
        if (head == null) {
            return null;
        }
        // 前一个节点
        Node<Integer> pre = null;
        // 当前节点
        Node<Integer> cur = head;
        while (cur!= null) {
            if (cur.value == num) {
                // 如果要删除的是头节点，头节点向下移动一位
                if (cur == head) {
                    head = cur.next;
                } else {
                    // 如果不是删除的头节点，当前节点删除后，pre指向下一个节点
                    pre.next = cur.next;
                }
            } else {
                // pre节点跟着向后移
                pre = cur;
            }
            // cur节点向后移
            cur = cur.next;
        }
        return head;
    }
}
