package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.*;

/**
 * 返回树的最大高度
 *
 * @author weizheng
 * @date 2022/02/04
 */
public class Class04_MaxDepthOfTree {

    /**
     * 方法一：递归
     *
     * @param tree
     * @return
     */
    public static Integer maxDepth(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(maxDepth(tree.left), maxDepth(tree.right)) + 1;
    }


    /**
     * 方法二：层序遍历（非递归）
     * 最大深度，其实就是二叉树的层数
     * @param tree
     * @return
     */
    public static Integer dfs(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        int depth = 0;
        while (!queue.isEmpty()) {
            boolean hasNode = false;
            int size = queue.size();
            // 遍历每一层的节点
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    hasNode = true;
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
            // 如果当前层有节点，则深度++
            depth = hasNode ? depth + 1 : depth;
        }
        return depth;
    }

    /**
     * 方法三：暴力递归
     *
     * @param tree
     * @return
     */
    public static Integer forceRecursive(TreeNode tree) {
        // 记录没条路径的深度
        List<Integer> depthList = new ArrayList<>();
        // 记录完整的路径
        List<TreeNode> path = new ArrayList<>();
        // 将所有可能的路径找出来，记录每条路径的深度，并放到集合中
        process(tree, depthList, path);
        // 从depthList中取出最大的值，即为最深的一条路径经过的层数
        return depthList.stream().max(Integer::compareTo).orElse(0);

    }

    private static void process(TreeNode tree, List<Integer> depthList, List<TreeNode> path) {
        if (tree == null) {
            depthList.add(path.size());
            return;
        }

        path.add(tree);
        process(tree.left, depthList, path);
        process(tree.right, depthList, path);
        path.remove(tree);
    }

    // ======================================== test ============================================
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        System.out.println(dfs(tree));
        System.out.println(forceRecursive(tree));
    }


}
