package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树序列化与反序列化
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code12_BinaryTreeSerialization {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        System.out.println(preSerial(tree));

        TreeNode node = reSerial(preSerial(tree));
        Class01_PrintBinaryTree.preOrder(node);
    }

    /**
     * 先序序列化
     *
     * @param tree
     * @return
     */
    public static Queue<String> preSerial(TreeNode tree) {
        Queue<String> result = new LinkedList<>();
        process(tree, result);
        return result;
    }

    public static void process(TreeNode treeNode, Queue<String> queue) {
        if (treeNode == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(treeNode.value));
            process(treeNode.left, queue);
            process(treeNode.right, queue);
        }
    }

    /**
     * 反序列化
     *
     * @param queue
     * @return
     */
    public static TreeNode reSerial(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return process2(queue);
    }

    public static TreeNode process2(Queue<String> queue) {
        String poll = queue.poll();
        if (poll == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(poll));
        node.left = process2(queue);
        node.right = process2(queue);
        return node;
    }
}
