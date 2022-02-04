package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weizheng
 * @Description 根据一个无重复元素的先序遍历数组和一个无重复元素的中序遍历数组生成对应的二叉树
 * @date 2022/02/04
 */
public class Class05_ConstructBinaryTreeFromPreAndInTraversal {


    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        // 将中序数组的数值和下标关系缓存起来，方便使用
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return build(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode build(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map) {
        // 如果一个树没有左分支，只有右分支，则返回null
        if (l1 > r1) {
            return null;
        }
        // 如果数组中只有一个数了，递归结束，直接返回节点
        TreeNode node = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return node;
        }

        // 根据先序数组中的头节点，找到它在中序数组中的位置
        int find = map.get(pre[l1]);
        // 构建左树
        node.left = build(pre, l1 + 1, l1 + find - l2, in, l2 + 1, find - 1, map);
        // 构建右树
        node.right = build(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, map);
        return node;
    }

}
