package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author weizheng
 * @Description
 * 找到满足“路径和等于某个值”的所有路径
 * eg；
 *     3
 *    / \
 *   2   3
 *  /   / \
 * 1   0   0
 *
 * 输入：6
 * 输出：[[3,2,1],[3,3,0],[3,3,0]]
 *
 * @date 2022/02/05
 */
public class Class09_FindAllBinaryTracePathSum {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.right = new TreeNode(3);
        tree.right.left = new TreeNode(0);
        tree.right.right = new TreeNode(0);
        System.out.println(findAllBinaryTracePath(tree,6));
    }

    public static List<List<Integer>> findAllBinaryTracePath(TreeNode tree,Integer sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (tree == null) {
            return resultList;
        }

        List<Integer> curPath = new ArrayList<>();
        return process(tree, 0, curPath, resultList, sum);

    }


    /**
     * 递归查找路径
     * @param tree 当前节点
     * @param preSum 当前路径已经过节点的和
     * @param curPath 当前路径已经过的节点集合
     * @param resultList 收集所有路径的集合
     * @param sum 目标路径和
     * @return 所有路径的集合
     */
    private static List<List<Integer>> process(TreeNode tree, int preSum, List<Integer> curPath, List<List<Integer>> resultList, Integer sum) {
        // 如果是叶子节点,那么判断整条路径和是否满足要求，如果满足则将路径添加到结果中
        if (tree.left == null && tree.right == null) {
            if (tree.value + preSum == sum) {
                List<Integer> fullPath = new ArrayList<>(curPath);
                fullPath.add(tree.value);
                resultList.add(fullPath);
            }
            return resultList;
        }
        // 如果是非叶子节点,则依次递归左树和右树
        preSum += tree.value;
        curPath.add(tree.value);
        if (tree.left != null) {
            process(tree.left, preSum, curPath, resultList, sum);
        }
        if (tree.right != null) {
            process(tree.right, preSum, curPath, resultList, sum);
        }
        curPath.remove(curPath.size() - 1);
        return resultList;
    }


}
