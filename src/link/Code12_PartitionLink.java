package link;

import link.entity.Node;

/**
 * 将链表按给定的值x改造成 < x 部分，= x 部分， > x 部分。
 *
 * @author weizheng
 * @date 2022/02/26
 */
public class Code12_PartitionLink {

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(4).add(7).add(2).add(3).add(8).add(4);
        System.out.println(partitionLink(head, 4));

    }


    public static Node<Integer> partitionLink(Node<Integer> head, int num) {
        Node<Integer> sh = null; // 小于部分的头节点
        Node<Integer> st = null; // 小于部分的尾节点
        Node<Integer> eh = null; // 等于部分的头节点
        Node<Integer> et = null; // 等于部分的尾节点
        Node<Integer> lh = null; // 大于部分的头节点
        Node<Integer> lt = null; // 大于部分的尾节点

        while (head != null) {
            if (head.value < num) {
                if (sh == null) {
                    sh = head;
                } else {
                    st.next = head;
                }
                st = head;
            }
            if (head.value == num) {
                if (eh == null) {
                    eh = head;
                } else {
                    et.next = head;
                }
                et = head;
            }
            if (head.value > num) {
                if (lh == null) {
                    lh = head;
                } else {
                    lt.next = head;
                }
                lt = head;
            }
            head = head.next;
        }
        // 将三个部分串起来,要考虑各个部分是否为空
        // 如果有小于部分
        if (st != null) {
            // 小于区与等于区连接
            st.next = eh;
            // 决定谁去跟大于区连接，如果有等于区，就用等于区连接，如果没有等于区，就用小于区连接
            et = et == null ? st : et;
        }
        // 如果有等于部分
        if (et != null) {
            et.next = lh;
        }
        lt.next = null;
        return sh != null ? sh : (eh != null ? eh : lh);
    }


}
