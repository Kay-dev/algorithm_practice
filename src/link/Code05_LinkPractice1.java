package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description
 * Hard
 * 给定一个单链表，要求按k个为一组，组内进行反转，不满k个的不做操作。
 * 如 1->2->3->4->5->6->7 ，当k=3时，结果为 (3->2->1)->(6->5->4)->7
 * @date 2022/01/29
 */
public class Code05_LinkPractice1 {

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        node.append(2).append(3).append(4).append(5).append(6).append(7);
        System.out.println(mainLogic(node,3));
    }


    /**
     * 主逻辑
     * @param head
     * @param k
     * @return
     */
    public static Node<Integer> mainLogic(Node<Integer> head, Integer k) {
        Node<Integer> start = head;
        Node<Integer> end = getGroupEnd(head, k);
        // 如果k>链表节点数，则一组都凑不齐，无需排序，直接返回
        if (end == null) {
            return start;
        }
        head = end;
        reverse(start, k);
        // 上一组的结尾节点
        Node<Integer> lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getGroupEnd(start, k);
            // 如果不够一组了，不做逆序操作，直接结束。
            if (end == null) {
                return head;
            }
            reverse(start, k);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    /**
     * 反转局部链表
     * @param head
     * @param k
     * @return
     */
    public static Node<Integer> reverse(Node<Integer> head, Integer k) {
        Node<Integer> cur = head;
        Node<Integer> pre = null;
        Node<Integer> next = null;
        while (k-- != 0 && cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = next;
        return pre;
    }

    /**
     * k个节点为一组，获取组中的最后一个节点
     *
     * @param start
     * @param k
     * @return
     */
    public static Node<Integer> getGroupEnd(Node<Integer> start, Integer k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }
}
