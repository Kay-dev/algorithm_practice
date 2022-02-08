package link;

import link.entity.Node;

/**
 * @author weizheng
 * @Description Simple
 * 两个链表相加
 * 给定两个链表的头节点head1和head2，认为从左到右是某个数字从低位到高位，返回相加之后的链表
 * 例如 4->3->6 和 2->5->3 ，因为634+352=986, 所以返回6->8->9
 * @date 2022/01/29
 */
public class Code06_TwoLinkAdd {

    public static void main(String[] args) {
        Node<Integer> head1 = new Node<>(9);
        head1.add(9).add(9).add(9);
        Node<Integer> head2 = new Node<>(1);
        System.out.println(func1(head1, head2));
        System.out.println(func2(head1, head2));
    }

    /**
     * 方法一，额外分配一个链表存储结果
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node<Integer> func1(Node<Integer> head1, Node<Integer> head2) {
        Node<Integer> result = null;
        Node<Integer> head = null;
        // 存个位的值
        int g = 0;
        // 存进位的值
        int s = 0;
        while (head1 != null || head2 != null || g != 0) {
            int sum = 0;
            if (head1 != null) {
                sum += head1.value;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.value;
                head2 = head2.next;
            }
            if (g != 0) {
                sum += g;
            }
            s = sum % 10;
            g = sum / 10;
            if (result == null) {
                head = new Node<>(s);
                result = head;
            } else {
                result = result.add(s);
            }
        }
        return head;
    }


    /**
     * 方法二，复用原始长链表，无需额外空间
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node<Integer> func2(Node<Integer> head1, Node<Integer> head2) {
        Integer length1 = getLinkLength(head1);
        Integer length2 = getLinkLength(head2);
        Node<Integer> shortLink = length1 < length2 ? head1 : head2;
        Node<Integer> resultLink = shortLink == head1 ? head2 : head1;
        Node<Integer> longLink = resultLink;
        Integer carry = 0; // 存储进位信息
        Integer digit = 0; // 存储个位信息
        Node<Integer> lastNode = null; // 存储最后一个节点
        // 遍历短链表中的每个节点中的值
        while (shortLink != null) {
            int sum = shortLink.value + longLink.value + carry;
            carry = sum / 10;
            digit = sum % 10;
            longLink.value = digit;
            lastNode = longLink;
            shortLink = shortLink.next;
            longLink = longLink.next;
        }
        // 遍历长链表中剩余的节点值
        while (longLink != null) {
            int sum = longLink.value + carry;
            carry = sum / 10;
            digit = sum % 10;
            longLink.value = digit;
            lastNode = longLink;
            longLink = longLink.next;
        }
        // 判断最后一个节点是否需要进位
        if (carry != 0) {
            lastNode.add(carry);
        }
        return resultLink;
    }

    public static Integer getLinkLength(Node<Integer> node) {
        Integer length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

}
