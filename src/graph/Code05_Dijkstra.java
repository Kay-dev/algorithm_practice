package graph;

import graph.Entity.Graph;
import graph.Entity.GraphEdge;
import graph.Entity.GraphNode;

import java.util.*;

/**
 * 给定一个出发点，求这个点到达其他各节点的路径中权重最小分别是多少？
 * 前提：权重不能为负数
 * <p>
 * 如：
 * [
 * [1,0,1],
 * [7,0,2],
 * [6,0,3],
 * [2,1,2],
 * [70,1,4],
 * [2,2,3],
 * [23,2,4],
 * [4,3,4],
 * ]
 * 在这个图中,假设出发点为0，那么0到其他各点路径的最小权重依次为：
 * 0-0: 0
 * 0-1: 1
 * 0-2: 3
 * 0-3: 5
 * 0-4: 9
 *
 * @author weizheng
 * @date 2022/03/05
 */
public class Code05_Dijkstra {


    public static Map<GraphNode, Integer> dijkstra(GraphNode start) {
        Map<GraphNode, Integer> result = new TreeMap<>(Comparator.comparing(GraphNode::getIndex));
        result.put(start, 0);
        // 用于存放已经冻结的节点
        Set<GraphNode> used = new HashSet<>();
        // 从起始点开始
        GraphNode cur = start;
        while (cur!=null) {
            // 根据当前节点更新关联的节点权重
            calNodeWeight(cur, used, result);
            // 这个节点上的所有边遍历完成，起始点到达这个点的最小权重路径已经找到，冻结这个节点
            used.add(cur);
            // 从剩余未冻结的节点中取出权重最小的作为当前节点
            cur = getMinNode(result, used);
        }
        return result;
    }

    private static void calNodeWeight(GraphNode cur, Set<GraphNode> used, Map<GraphNode, Integer> result) {
        for (GraphEdge edge : cur.getEdges()) {
            GraphNode to = edge.getTo();
            // 若这条边的目标节点还没被冻结
            if (!used.contains(to)) {
                // （这条边的目标节点记录的权重值）跟（当前边的权重和当前节点的权重之和）比较，哪条路径的权重小记录谁
                int distance = result.getOrDefault(cur, 0) + edge.getWeight();
                result.put(to, Math.min(distance,result.getOrDefault(to, Integer.MAX_VALUE)) );
            }
        }
    }

    private static GraphNode getMinNode(Map<GraphNode, Integer> result, Set<GraphNode> used) {
        return result.entrySet().stream()
                .filter(e -> !used.contains(e.getKey()))
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // ==================================== test =========================================

    public static void main(String[] args) {
        Graph graph = Graph.newInstance(new Integer[][]{
                {1, 0, 1},
                {7, 0, 2},
                {6, 0, 3},
                {2, 1, 2},
                {70, 1, 4},
                {2, 2, 3},
                {23, 2, 4},
                {4, 3, 4}
        });
        Map<GraphNode, Integer> map = dijkstra(graph.getNode(0));
        map.forEach((k, v) -> {
            System.out.println(k.getIndex() + ":" + v);
        });
    }
}
