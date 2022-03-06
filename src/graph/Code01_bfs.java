package graph;

import graph.Entity.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 *
 * @author weizheng
 * @date 2022/03/05
 */
public class Code01_bfs {

    /**
     * 宽度优先遍历
     *
     * @param graphNode 起始点
     */
    public static void bfs(GraphNode graphNode) {
        if (graphNode == null) {
            return;
        }
        // 队列，用于控制节点的进出
        Queue<GraphNode> queue = new LinkedList<>();
        // 记录已经打印过的节点
        Set<GraphNode> set = new HashSet<>();
        queue.add(graphNode);
        set.add(graphNode);
        System.out.print(graphNode.getIndex() + " ");
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            System.out.print(cur.getIndex() + " ");
            for (GraphNode next : cur.getNext()) {
                if (!set.contains(next)) {
                    queue.add(next);
                    // 记录一下，这个节点已经输出过了
                    set.add(next);
                }
            }

        }
    }

}
