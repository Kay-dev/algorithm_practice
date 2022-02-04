package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

/**
 * @author weizheng
 * @Description 返回树的最大高度
 * @date 2022/02/04
 */
public class Class04_MaxDepthOfTree {


    public static Integer maxDepth(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(maxDepth(tree.left), maxDepth(tree.right)) + 1;
    }

}
