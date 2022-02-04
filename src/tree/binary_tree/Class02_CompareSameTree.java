package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * @author weizheng
 * @Description 给定两个二叉树，用一个函数判断两个树是否结构相同。
 * @date 2022/02/04
 */
public class Class02_CompareSameTree {


    public static boolean isSameTree(TreeNode tree1, TreeNode tree2) {
        // 如果两个树中，一个节点为null，另一个节点不为null，则返回false
        if (tree1 == null ^ tree2 == null) {
            return false;
        }
        // 两个树都为空，直接返回true
        if (tree1 == null) {
            return true;
        }
        // 头结点相等，并且左节点和右节点也相等，返回true
        return tree1.value == tree2.value && isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);

    }


}
