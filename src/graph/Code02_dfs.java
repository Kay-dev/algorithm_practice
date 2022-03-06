package graph;

import graph.Entity.GraphNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 图的深度优先遍历
 * @author weizheng
 * @date 2022/03/05
 */
public class Code02_dfs {

    public static void dfs(GraphNode graphNode) {
        if (graphNode == null) {
            return;
        }

        Deque<GraphNode> stack = new ArrayDeque<>();
        Set<GraphNode> set = new HashSet<>();
        stack.push(graphNode);
        set.add(graphNode);
        System.out.print(graphNode.getIndex() + " ");
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            for (GraphNode next : cur.getNext()) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.getIndex() + " ");
                    break;
                }
            }
        }
    }

}
