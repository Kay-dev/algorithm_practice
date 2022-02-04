package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * @author weizheng
 * @Description 递归打印二叉树
 * @date 2022/01/31
 */
public class Class01_PrintBinaryTree {


    /**
     * 先序打印，即按 [头，左，右]的顺序
     *
     * @param head
     */
    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 中序打印，即【左，头，右】的顺序
     * @param head
     */
    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.value + " ");
        inOrder(head.right);
    }

    /**
     * 后序打印，即【左，右，头】的顺序
     *
     * @param head
     */
    public static void posOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        posOrder(head.left);
        posOrder(head.right);
        System.out.print(head.value + " ");
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);

        preOrder(tree);
        System.out.println();
        inOrder(tree);
        System.out.println();
        posOrder(tree);
    }


}
