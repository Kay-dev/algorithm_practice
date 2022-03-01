package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 返回二叉树中，最大搜索二叉树的头节点
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code15_FindMaxBSTNode {

    public static int findMaxBSTNode(TreeNode tree) {
        return process(tree).maxBSTSize;
    }

    private static Info process(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        Info left = process(tree.left);
        Info right = process(tree.right);
        int min = tree.value;
        int max = tree.value;
        int maxBSTSize = 0;
        // 获取当前节点下的最大值，最小值
        if (left != null) {
            min = Math.min(tree.value, left.min);
            max = Math.max(tree.value, left.max);
            maxBSTSize = Math.max(maxBSTSize, left.maxBSTSize);
        }
        if (right != null) {
            min = Math.min(tree.value, right.min);
            max = Math.max(tree.value, right.max);
            maxBSTSize = Math.max(maxBSTSize, right.maxBSTSize);
        }
        // 判断当前节点下是否是搜索树
        boolean isBST = false;
        // 左子树是否满足搜索树条件
        boolean leftCondition = left == null || (left.isBST && left.max < tree.value);
        // 右子树是否满足搜索树条件
        boolean rightCondition = right == null || (right.isBST && right.min > tree.value);
        if (leftCondition && rightCondition) {
            isBST = true;
            maxBSTSize = (left == null ? 0 : left.maxBSTSize) + (right == null ? 0 : right.maxBSTSize) + 1;
        }
        return new Info(isBST, maxBSTSize, min, max);
    }


    static class Info {
        // 是否是搜索二叉树
        public boolean isBST;
        // 最大搜索二叉树大小
        public int maxBSTSize;
        // 最小值
        public int min;
        // 最大值
        public int max;

        public Info(boolean isBST, int maxBSTSize, int min, int max) {
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = max;
        }
    }

}
