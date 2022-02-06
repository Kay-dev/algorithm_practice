package tree.binary_tree;

import tree.binary_tree.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author weizheng
 * @Description 由下到上，依次打印二叉树每一层的数据
 *    3
 *   / \
 *  9  20
 *     / \
 *    15  7
 * <p>
 * 输出：[[15,7],[9,20],[3]]
 * @date 2022/02/05
 */
public class Class06_PrintBinaryTreeEveryLevel {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        System.out.println(printLevel(tree));
    }

    /**
     * 思路：准备一个队列，执行下面的步骤
     *
     * 1. 从上到下，将每一层的节点加入到队列中
     * 2. 根据队列中节点的数量n，循环n次：将队列中的原数据保存下来，将下一层数据添加进队列
     *
     * @param tree
     * @return
     */
    public static List<List<Integer>> printLevel(TreeNode tree) {
        // 因为要求的结果是倒序输出，LinkedList的头部插入效率更高。
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(tree);
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                levelList.add(currentNode.value);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(0, levelList);
        }
        return result;
    }

}
