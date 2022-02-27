package link;

import link.entity.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个单链表（可能有环，可能无环），找到这两个链表的相交节点
 * <p>
 * 分情况讨论：
 * 情况1： 两个无环链表相交
 * 情况2： 两个有环链表相交
 * 2.1：两个有环链表在环外相交
 * 2.2：两个有环链表在环上相交
 * 注：一个有环和一个无环链表是不可能相交的，故无需讨论
 *
 * @author weizheng
 * @date 2022/02/27
 */
public class Code13_FindFirstIntersectNode {

    public static void main(String[] args) {
        // 环形链表1
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;

        // 环形链表2
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node6.next = node7;
        node7.next = node5;

        System.out.println(findIntersectNode(node1, node6).value);


    }

    public static Node findIntersectNode(Node link1, Node link2) {
        if (link1 == null || link2 == null) {
            return null;
        }
        // 判断两个链表是否有环
        Node loopNode1 = getLoopNode(link1);
        Node loopNode2 = getLoopNode(link2);
        // 情况一：两个无环链表查找相交
        if (loopNode1 == null && loopNode2 == null) {
            return findNoLoopIntersectNode2(link1, link2);
        }
        // 情况二：两个有环链表查找相交
        if (loopNode1 != null && loopNode2 != null) {
            return getLoopIntersectNode(link1, link2, loopNode1, loopNode2);
        }
        return null;
    }

    /**
     * 查找无环链表相交点，方法一
     * 将一个链表中的节点添加到HashSet中，然后遍历第二个链表，看节点是否在HashSet中，如果在，那么就找到了相交节点。
     *
     * @param link1
     * @param link2
     * @return
     */
    public static Node findNoLoopIntersectNode1(Node link1, Node link2) {
        if (link1 == null || link2 == null) {
            return null;
        }

        Set<Node> set = new HashSet<>();
        while (link1 != null) {
            set.add(link1);
            link1 = link1.next;
        }

        while (link2 != null) {
            if (set.contains(link2)) {
                return link2;
            }
            link2 = link2.next;
        }
        return null;
    }

    /**
     * 查找无环链表相交点，方法二
     * 不使用额外空间。
     * 1. 判断两个链表是否相交：先遍历两个链表，记录下两个链表的长度，并比较两个链表末尾的节点是否相同，如果相同则代表两个链表有相交。
     * 2. 找出交点：若链表1的长度为10，链表2的长度为8，则链表1指针先移动2个节点，然后两个指针同时向下移动，当两个指针相遇时，即为交点位置
     *
     * @param link1
     * @param link2
     * @return
     */
    public static Node findNoLoopIntersectNode2(Node link1, Node link2) {
        if (link1 == null || link2 == null) {
            return null;
        }
        // 两个链表长度的差值
        int diff = 0;
        Node cur1 = link1;
        while (cur1.next != null) {
            cur1 = cur1.next;
            diff++;
        }
        Node cur2 = link2;
        while (cur2.next != null) {
            cur2 = cur2.next;
            diff--;
        }
        // 若两个链表尾节点不相同，则表示两链表没有相交
        if (cur1 != cur2) {
            return null;
        }
        // 若相交，找出交点
        // 哪个链表更长，cur1指向谁
        cur1 = diff > 0 ? link1 : link2;
        cur2 = cur1 == link1 ? link2 : link1;
        diff = Math.abs(diff);
        // 长链表的指针先移动diff个节点
        while (diff != 0) {
            cur1 = cur1.next;
            diff--;
        }
        // 两个节点同时向下走，直到相遇
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    /**
     * 找到链表上的入环节点，如果链表无环，则返回null
     * 用快慢指针，快指针每次移动2，慢指针每次移动1，如果有环，则快慢指针会在某一时刻相遇。
     * 两相遇时，慢指针不动，快指针回到链表头部，速度变为1，两个指针同时移动，当两指针第二次相遇时，此位置即为入环节点。
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        // 长度小于3,不可能有环
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // 快指针移到链表开头，步长变为1，两个指针同时移动，直到相遇
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 找到两个有环链表的交点
     *
     * @param link1     链表1
     * @param link2     链表2
     * @param loopNode1 链表1的入环节点
     * @param loopNode2 链表2的入环节点
     * @return 交点
     */
    public static Node getLoopIntersectNode(Node link1, Node link2, Node loopNode1, Node loopNode2) {
        // 如果两个有环链表的入环节点相同，说明两个链表的相交点在环外,问题就变成了无环链表求交点
        // 找出相交点
        if (loopNode1 == loopNode2) {
            // 两个链表长度的差值
            int diff = 0;
            Node cur1 = link1;
            while (cur1.next != loopNode1) {
                cur1 = cur1.next;
                diff++;
            }
            Node cur2 = link2;
            while (cur2.next != loopNode2) {
                cur2 = cur2.next;
                diff--;
            }
            // 若两个链表尾节点不相同，则表示两链表没有相交
            if (cur1 != cur2) {
                return null;
            }
            // 若相交，找出交点
            // 哪个链表更长，cur1指向谁
            cur1 = diff > 0 ? link1 : link2;
            cur2 = cur1 == link1 ? link2 : link1;
            diff = Math.abs(diff);
            // 长链表的指针先移动diff个节点
            while (diff != 0) {
                cur1 = cur1.next;
                diff--;
            }
            // 两个节点同时向下走，直到相遇
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 如果两个有环链表的入环节点不同
            Node cur = loopNode1.next;
            // 从链表1的入环节点开始，绕着环走一圈，看是否能碰到链表2的入环节点，如果能相遇，说明两个链表在环上相交了。否则说明两个链表未相交
            while (cur != loopNode1) {
                cur = cur.next;
                if (cur == loopNode2) {
                    return cur;
                }
            }
        }
        return null;
    }
}
