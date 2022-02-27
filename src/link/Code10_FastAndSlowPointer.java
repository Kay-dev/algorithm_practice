package link;

import link.entity.Node;

/**
 * 快慢指针
 * @author weizheng
 * @date 2022/02/26
 */
public class Code10_FastAndSlowPointer {


    /**
     * 找出一个单链表中的中间节点（当链表节点数为奇数时）或中间偏上的节点（当链表节点数为偶数时）
     * @param head
     * @return
     */
    public static Node<Integer> midOrUpMid(Node<Integer> head) {
        // 当节点数小于3个，直接返回
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // 慢指针
        Node<Integer> slow = head.next;
        // 快指针
        Node<Integer> fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            // 慢指针每次移动一个节点，快指针每次移动两个节点，当快指针到达末尾，则慢指针刚好位于中点。
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


}
