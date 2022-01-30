package link;

import link.entity.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author weizheng
 * @Description 合并三条有序链表
 * @date 2022/01/30
 */
public class Code08_MergThreeLink {


    public static void main(String[] args) {
        ArrayList<Node<Integer>> list = new ArrayList<>();
        Node<Integer> node1 = new Node<>(3);
        node1.add(4).add(7).add(9);
        Node<Integer> node2 = new Node<>(0);
        node2.add(2).add(5);
        Node<Integer> node3 = new Node<>(1);
        node3.add(2).add(6);
        list.add(node1);
        list.add(node2);
        list.add(node3);

        // System.out.println(merge(list));
        System.out.println(func2(list));

    }


    /**
     * 方法一：会占用额外空间
     *
     * @param lists
     * @return
     */
    public static Node<Integer> merge(List<Node<Integer>> lists) {
        PriorityQueue<Node<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.value));
        for (Node<Integer> list : lists) {
            Node<Integer> current = list;
            while (current != null) {
                queue.add(current);
                current = current.next;
            }
        }
        Node<Integer> result = null;
        Node<Integer> next = null;
        while (queue.size() > 0) {
            Node<Integer> node = queue.poll();
            if (result == null) {
                result = node;
                next = result;
            } else {
                next.next = node;
                next = next.next;
            }
        }
        return result;

    }

    /**
     * 方法二：额外空间占用更小
     * 优先级队列大小等于链表个数，每个链表从头开始，放入自己的节点进优先级队列中
     * @param lists
     * @return
     */
    public static Node<Integer> func2(List<Node<Integer>> lists) {
        PriorityQueue<Node<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.value));
        queue.addAll(lists);
        Node<Integer> head = null;
        Node<Integer> current = null;
        while (queue.size() > 0) {
            Node<Integer> node = queue.poll();
            if (head == null) {
                head = node;
                current = head;
            } else {
                current.next = node;
                current = current.next;
            }
            Node<Integer> next = node.next;
            if (next != null) {
                queue.add(next);
            }
        }
        return head;
    }

}
