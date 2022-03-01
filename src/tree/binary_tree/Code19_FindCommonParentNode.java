package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 找到二叉树上两个节点的最小祖先节点
 *
 * @author weizheng
 * @date 2022/03/01
 */
public class Code19_FindCommonParentNode {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        tree.left.left = node4;
        TreeNode node5 = new TreeNode(5);
        tree.left.right = node5;
        TreeNode node6 = new TreeNode(6);
        tree.right.left = node6;
        tree.right.right = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);


        System.out.println(findParentNode1(tree, node4, node8));
        System.out.println(findParentNode2(tree, node4, node8));
    }


    /**
     * 方法一，采用遍历的方式
     * @param root 根节点
     * @param node1 目标节点1
     * @param node2 目标节点2
     * @return 节点1和节点2的最小祖先节点
     */
    public static TreeNode findParentNode1(TreeNode root, TreeNode node1, TreeNode node2) {
        // 遍历二叉树，将所有节点和父节点的关系存储进HashMap中
        if (root == null) {
            return null;
        }
        Map<TreeNode, TreeNode> map = new HashMap<>();
        process1(root, map);
        // 从node1开始，沿着父节点到根节点，将所有节点放入HashSet
        TreeNode cur1 = node1;
        Set<TreeNode> set = new HashSet<>();
        do {
            set.add(cur1);
            cur1 = map.get(cur1);
        } while (cur1 != null);

        // 从node2开始，沿着父节点依次判断:节点是否在HashSet中，第一次找到的set中存在的节点，即为最小祖先节点
        TreeNode cur2 = node2;
        do {
            if (set.contains(cur2)) {
                return cur2;
            }
            cur2 = map.get(cur2);
        } while (cur2 != null);

        return null;
    }

    private static void process1(TreeNode root, Map<TreeNode, TreeNode> map) {
        if (root.left != null) {
            map.put(root.left, root);
            process1(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right, root);
            process1(root.right, map);
        }
    }

    /**
     * 方法二，递归
     * @param root 根节点
     * @param node1 目标节点1
     * @param node2 目标节点2
     * @return 节点1和节点2的最小祖先节点
     */
    public static TreeNode findParentNode2(TreeNode root, TreeNode node1, TreeNode node2) {
        return process2(root,node1,node2).ancestor;
    }

    private static Info process2(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return new Info(null, false, false);
        }
        // 拿到左树和右树的信息
        Info leftInfo = process2(root.left, node1, node2);
        Info rightInfo = process2(root.right, node1, node2);
        // 如果当前节点就是node1，或者左树上找到了node1,或右树上找到了node1,就代表node1找到了
        boolean findO1 = root == node1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = root == node2 || leftInfo.findO2 || rightInfo.findO2;
        // 最小祖先节点
        TreeNode ancestor = null;
        // 如果最小祖先节点在左树上，则取左树上找到的的最小祖先节点
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
            // 如果最小祖先节点在右树上，则取右树上找到的的最小祖先节点
        } else if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
            // 如果node1，node2存在于当前节点树下，且左树，右树上都没有相交点，那么相交点就是当前节点。
        } else if (findO1 && findO2) {
            ancestor = root;
        }
        return new Info(ancestor, findO1, findO2);
    }

    static class Info{
        // o1 和 o2 的最初交汇节点，如果没有则为null
        TreeNode ancestor;
        // 是否找到了 o1 节点
        boolean findO1;
        // 是否找到了 o2 节点
        boolean findO2;

        public Info(TreeNode ancestor, boolean findO1, boolean findO2) {
            this.ancestor = ancestor;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }
}
