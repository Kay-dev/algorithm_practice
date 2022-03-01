package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 判断是否是完全二叉树
 * 完全二叉树定义：节点从上到下，从左到右依次填充的二叉树，如下
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * @author weizheng
 * @date 2022/03/01
 */
public class Code18_IsCompleteBinaryTree {

    public static boolean isCompleteTree(TreeNode tree) {
        if (tree == null) {
            return false;
        }
        return process(tree).isCBT;
    }

    private static Info process(TreeNode tree) {
        if (tree == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(tree.left);
        Info rightInfo = process(tree.right);

        int height = leftInfo.height + rightInfo.height + 1;
        boolean isFBT = leftInfo.isFBT && rightInfo.isFBT && (leftInfo.height == rightInfo.height);
        // 如果是满二叉树，那么一定是完全二叉树
        boolean isCBT = isFBT;
        // 如果不是满二叉树，则需要分类讨论，不过前提是左子树和右子树都是完全二叉树。
        if (!isCBT && leftInfo.isCBT && rightInfo.isCBT) {
            /**
             *这种形式的完全二叉树，左子树未满，右子树必为满二叉树，且树高差为1
             *     1
             *    / \
             *   2   3
             *  /
             * 4
             */
            if ( rightInfo.isFBT && (leftInfo.height - rightInfo.height == 1)) {
                isCBT = true;
            }
            /**
             *这种形式的完全二叉树，左子树已满，右子树必为满二叉树，且树高差为1
             *     1
             *    / \
             *   2   3
             *  / \
             * 4   5
             */
            if (leftInfo.isFBT && rightInfo.isFBT && (leftInfo.height - rightInfo.height == 1)) {
                isCBT = true;
            }
            /**
             *这种形式的完全二叉树，左子树已满，右子树未满，且树高相等
             *      1
             *    /   \
             *   2     3
             *  / \   /
             * 4   5 6
             */
            if (leftInfo.isFBT && (leftInfo.height == rightInfo.height)) {
                isCBT = true;
            }
        }
        return new Info(isCBT, isFBT, height);
    }


    static class Info {
        // 是否是完全二叉树
        boolean isCBT;
        // 是否是满二叉树
        boolean isFBT;
        // 树高
        int height;

        public Info(boolean isCBT, boolean isFBT, int height) {
            this.isCBT = isCBT;
            this.isFBT = isFBT;
            this.height = height;
        }
    }


}
