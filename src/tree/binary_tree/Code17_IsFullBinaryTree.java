package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 判断是否是满二叉树
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code17_IsFullBinaryTree {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        // tree.right.right = new TreeNode(7);
        System.out.println(isBinaryTree(tree));
    }

    public static boolean isBinaryTree(TreeNode tree) {
        if (tree == null) {
            return false;
        }
        Info info = process(tree);
        // 因为满二叉树满足这个条件：节点数 = 2^层高 - 1
        return (Math.pow(2, info.height) - 1) == info.nodeCount;
    }

    private static Info process(TreeNode tree) {
        if (tree == null) {
            return new Info(0, 0);
        }

        Info left = process(tree.left);
        Info right = process(tree.right);

        int height = Math.max(left.height, right.height) + 1;
        int nodeCount = left.nodeCount + right.nodeCount + 1;

        return new Info(height,nodeCount);
    }


    static class Info {
        // 层高
        int height;
        // 节点数
        int nodeCount;

        public Info(int height, int nodeCount) {
            this.height = height;
            this.nodeCount = nodeCount;
        }
    }
}
