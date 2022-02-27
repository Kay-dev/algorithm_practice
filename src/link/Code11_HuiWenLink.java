package link;

import link.entity.Node;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 判断是否是回文链表
 * @author weizheng
 * @date 2022/02/26
 */
public class Code11_HuiWenLink {

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1).add(2).add(3).add(4).add(2).add(1);
        System.out.println(isHuiWenLink1(head));
        System.out.println(isHuiWenLink2(head));
        System.out.println(isHuiWenLink3(head));
    }


    /**
     * 版本1，使用栈实现，额外空间为N
     * 将链表全部压入一个栈中，再从栈中依次弹出，并与原链表每个节点进行比较
     * @param head
     * @return
     */
    public static boolean isHuiWenLink1(Node<Integer> head) {

        if (head == null || head.next == null) {
            return true;
        }
        // 将原链表压入栈中
        Deque<Integer> stack = new ArrayDeque<>();
        Node<Integer> current = head;
        while (current != null) {
            stack.push(current.value);
            current = current.next;
        }

        // 从栈中依次弹出，并与原链表比较，看是否相同
        Node<Integer> cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != cur.value) {
                return false;
            }else {
                cur = cur.next;
            }
        }
        return true;
    }

    /**
     * 版本2，使用栈实现，额外空间N/2，
     * 先用快慢指针找到链表中间位置，然后将一半的链表节点压入栈中，再从栈中弹出并与原链表每个节点进行比较
     * @param head
     * @return
     */
    public static boolean isHuiWenLink2(Node<Integer> head) {
        Node<Integer> midNode = getMidNode(head);
        Deque<Integer> stack = new ArrayDeque<>();
        while (midNode.next != null) {
            stack.push(midNode.next.value);
            midNode = midNode.next;
        }

        while (!stack.isEmpty()) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 版本3，直接改变原链表，空间复杂度O(1)
     * 通过快慢指针找到中点，从中点开始，将后半段链表反转，然后头和尾同时向下遍历比较节点是否相等。
     * @param head
     * @return
     */
    public static boolean isHuiWenLink3(Node<Integer> head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node<Integer> midNode = getMidNode(head);
        // 反转后半段链表
        Node<Integer> pre = midNode;
        Node<Integer> cur = midNode.next;
        Node<Integer> next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        midNode.next = null;
        // 记录下最后一个节点，用于恢复后半段链表
        Node<Integer> lastNode = pre;
        // 头 尾 同时向下遍历，进行比较是否相同
        boolean res = true;
        while (head != null && pre != null) {
            if (head.value != pre.value) {
                res = false;
                break;
            }
            head = head.next;
            pre = pre.next;
        }
        // 最后，将链表恢复原样
        pre = null;
        cur = lastNode;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return res;
    }

    /**
     * 找到链表中间节点
     * @param head
     * @return
     */
    private static Node<Integer> getMidNode(Node<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        Node<Integer> slow = head.next;
        Node<Integer> fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }




}
