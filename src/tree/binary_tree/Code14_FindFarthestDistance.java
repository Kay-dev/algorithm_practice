package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * 找出一个二叉树中，距离最远的两个节点的距离(即经过的节点数量最多)
 * 如：
 *   1
 *  / \
 * 2   3
 * <p>
 * 2 和 3 之间的距离为3，因为经过了3个节点
 *
 * @author weizheng
 * @date 2022/02/28
 */
public class Code14_FindFarthestDistance {

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

        System.out.println(findFarthestDistance(tree));
    }

    public static int findFarthestDistance(TreeNode tree) {
        return process(tree).farthestDistance;
    }

    private static Info process(TreeNode tree) {
        if (tree == null) {
            return new Info(0, 0);
        }
        // 分别获取左子树和右子树的信息
        Info leftInfo = process(tree.left);
        Info rightInfo = process(tree.right);
        // 计算当前节点下的树高
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 计算当前节点下的两个节点间的最长距离:
        // 1. 如果距离最远的两个节点会通过当前节点，则最远两个节点分别在左子树和右子树上，距离为（左子树高度+右子树高度+1）
        // 2. 如果距离最远的两个节点不通过当前节点，则最远两个节点全在 左子树 或 右子树上。
        int farthestDistance = Math.max(leftInfo.height + rightInfo.height + 1,
                Math.max(leftInfo.farthestDistance, rightInfo.farthestDistance));
        return new Info(height, farthestDistance);
    }


    static class Info {
        int height;
        int farthestDistance;

        public Info(int height, int farthestDistance) {
            this.height = height;
            this.farthestDistance = farthestDistance;
        }
    }


}
