package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * @author weizheng
 * @Description 判断一颗树是否是镜像对称的树
 * @date 2022/02/04
 */
public class Class03_SymmetricTree {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.left.right = new TreeNode(3);
        tree.right.left = new TreeNode(3);

        System.out.println(isMirrorTree(tree));
    }


    public static boolean isMirrorTree(TreeNode tree) {
        if (tree == null) {
            return true;
        }
        return compareLeftAndRight(tree.left, tree.right);

    }

    private static boolean compareLeftAndRight(TreeNode left, TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        // 两个树都为空，直接返回true
        if (left == null) {
            return true;
        }

        return left.value == right.value && compareLeftAndRight(left.left,right.right) && compareLeftAndRight(left.right,right.left);
    }


}
