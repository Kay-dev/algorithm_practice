package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * @author weizheng
 * @Description 能否组成路径和
 *
 * eg：
 *     5
 *    / \
 *   4   8
 *  /   / \
 * 11  13  4
 *
 * 输入：17
 * 输出：true，因为5+8+4=19,路径存在
 *
 * @date 2022/02/05
 */
public class Class08_BinaryTreePathSum {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(4);
        tree.right = new TreeNode(8);
        tree.left.left = new TreeNode(11);
        tree.right.left = new TreeNode(13);
        tree.right.right = new TreeNode(4);

        System.out.println(hasPathSum(tree,17));
    }

    public static boolean hasPathSum(TreeNode tree, int sum) {
        if (tree == null) {
            return false;
        }
        return process(tree, sum);

    }

    private static boolean process(TreeNode tree, int sum) {
        if (tree.left == null && tree.right == null) {
            return tree.value == sum;
        }
        // 计算左节点
        boolean left = tree.left != null && process(tree.left, sum - tree.value);
        // 计算右节点
        boolean right = tree.right != null && process(tree.right, sum - tree.value);
        return left || right;
    }

}
