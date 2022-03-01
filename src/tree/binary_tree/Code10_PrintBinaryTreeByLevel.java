package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 按层遍历二叉树
 * @author weizheng
 * @date 2022/02/27
 */
public class Code10_PrintBinaryTreeByLevel {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);

        printByLevel(tree);
    }


    public static void printByLevel(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.value);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        System.out.println(list);

    }

}
