package tree.binary_tree;

import com.sun.scenario.effect.impl.prism.ps.PPStoPSWDisplacementMapPeer;
import tree.binary_tree.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 获取二叉树最大宽度
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code11_FindBinaryTreeMaxWith {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);

        System.out.println(findMaxWith(tree));
    }


    public static int findMaxWith(TreeNode node) {
        if (node == null) {
            return 0;
        }
        TreeNode curEnd = node; // 当前层最后一个节点
        TreeNode nextEnd = null; // 下一层最后一个节点
        int max = 0; // 最大宽度
        int currentNodes = 0; // 当前层节点数

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            currentNodes++;
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            // 若当前层节点已全部弹出
            if (poll == curEnd) {
                // 统计当前层的节点数
                max = Math.max(max, currentNodes);
                // 准备扫描下一层，节点数归零
                currentNodes = 0;
                // 指向下一层的最后一个节点
                curEnd = nextEnd;
            }
        }
        return max;
    }


}
