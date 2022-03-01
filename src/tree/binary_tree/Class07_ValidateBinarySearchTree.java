package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 判断一个树是否是搜索二叉树。
 * 搜索二叉树：左树所有节点小于头节点，右树所有节点大于头节点。并且所有子树也必须是搜索二叉树
 * @author weizheng
 * @date 2022/02/05
 */
public class Class07_ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);
        tree1.left.left = new TreeNode(4);
        tree1.left.right = new TreeNode(5);
        tree1.right.left = new TreeNode(6);
        tree1.right.right = new TreeNode(7);
        System.out.println(isBinarySearchTree(tree1).isBST);

        TreeNode tree2 = new TreeNode(2);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(3);
        // tree2.left.left = new TreeNode(1);
        // tree2.left.right = new TreeNode(3);
        // tree2.right.left = new TreeNode(5);
        // tree2.right.right = new TreeNode(7);
        System.out.println(isBinarySearchTree(tree2).isBST);
    }

    public static Info isBinarySearchTree(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        Info leftTree = isBinarySearchTree(tree.left);
        Info rightTree = isBinarySearchTree(tree.right);
        // 获取当前节点下子树的最大值和最小值
        int min = tree.value;
        int max = tree.value;
        if (leftTree != null) {
            max = Math.max(leftTree.max, max);
            min = Math.min(leftTree.min, min);
        }
        if (rightTree != null) {
            max = Math.max(rightTree.max, max);
            min = Math.min(rightTree.min, min);
        }
        // 判断当前节点下子树是否是搜索二叉树
        // 1. 如果左树或右树有一个不是搜索二叉树，则整个树都不是搜索二叉树
        boolean leftIsBst = leftTree == null || leftTree.isBST;
        boolean rightIsBst = rightTree == null || rightTree.isBST;
        // 2. 如果左树的所有节点不小于头节点，或右树的所有节点不大于头节点，则不是搜索二叉树
        boolean leftLessThanHead = leftTree == null || leftTree.max < tree.value;
        boolean rightMoreThanHead = rightTree == null || rightTree.min > tree.value;
        // 同时满足以上条件，才称为搜索二叉树
        if (leftIsBst && rightIsBst && leftLessThanHead && rightMoreThanHead) {
            return new Info(true, min, max);
        }
        return new Info(false, min, max);
    }
}

/**
 * 对子树关键数据的封装类
 */
class Info {
    // 是否是二叉搜索树
    public boolean isBST;
    // 子树中的最小值
    public int min;
    // 子树中的最大值
    public int max;

    public Info(boolean isBST, int min, int max) {
        this.isBST = isBST;
        this.min = min;
        this.max = max;
    }
}