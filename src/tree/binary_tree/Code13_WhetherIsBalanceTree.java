package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 判断一个二叉树是否是平衡树
 * 平衡树：左子树和右子树的树高之差不大于1
 * @author weizheng
 * @date 2022/02/28
 */
public class Code13_WhetherIsBalanceTree {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        tree.right.right.left = new TreeNode(8);
        tree.right.right.left.left = new TreeNode(9);

        System.out.println(isBalanceTree(tree));

    }

    public static boolean isBalanceTree(TreeNode tree) {
        return process(tree).isBalance;
    }


    private static Info process(TreeNode tree) {
        if (tree == null) {
            return new Info(true, 0);
        }
        // 分别获取左子树和右子树的信息
        Info leftInfo = process(tree.left);
        Info rightInfo = process(tree.right);
        // 计算当前节点树的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = false;
        // 若左子树是平衡树，右子树是平衡树，且左右子树高度差不大于1，则当前节点为平衡树
        if (leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) <= 1) {
            isBalance = true;
        }
        return new Info(isBalance,height);
    }

    static class Info{
        // 是否是平衡树
        public boolean isBalance;
        // 树高
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}


