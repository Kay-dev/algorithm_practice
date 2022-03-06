package graph;

import graph.Entity.GraphEdge;
import graph.Entity.Graph;
import graph.Entity.GraphNode;
import union_set.Code01_UnionSet;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 从图中找到最小生成树
 * 也就是说所有节点都能连通，不能有孤立的点存在，且要保证整体路径的总权重最小。
 *
 * @author weizheng
 * @date 2022/03/05
 */
public class Code04_MinTree {

    /**
     * 方法一：将边按权重组织成小根堆，依次弹出边，并检查这条边的两个节点是否已连通
     * @param graph
     * @return
     */
    public static Set<GraphEdge> kruskalMST(Graph graph) {
        ArrayList<GraphNode> graphNodeIndexList = new ArrayList<>(graph.getNodes().values());
        Code01_UnionSet<GraphNode> unionSet = new Code01_UnionSet<>(graphNodeIndexList);
        // 使用堆，将图中的边按权重有小到大组织
        Queue<GraphEdge> heap = new PriorityQueue<>(Comparator.comparing(GraphEdge::getWeight));
        heap.addAll(graph.getEdges());
        // 结果集，收集符合的边
        Set<GraphEdge> result = new HashSet<>();
        while (!heap.isEmpty()) {
            GraphEdge cur = heap.poll();
            // 如果两个节点还没有连通，则通过并查集合并到一起
            if (!unionSet.isSameSet(cur.getFrom(), cur.getTo())) {
                unionSet.union(cur.getFrom(),cur.getTo());
                result.add(cur);
            }
        }
        return result;
    }

    /**
     * 方法二：从任意节点开始，解锁与之关联的边，从解锁的边中，取出最小的边，从边的目标节点继续以上步骤，直到所有节点都连通。
     * @param graph
     * @return
     */
    public static Set<GraphEdge> primMST(Graph graph) {
        Set<GraphEdge> result = new HashSet<>();
        // 存放已解锁的边，按权重排序
        Queue<GraphEdge> heap = new PriorityQueue<>(Comparator.comparing(GraphEdge::getWeight));
        // 存放已经连通的节点
        Set<GraphNode> nodeSet = new HashSet<>();
        // 任意取一个节点作为起始节点
        GraphNode start = graph.getNode(0);
        // 保存初始节点和与之关联的边
        nodeSet.add(start);
        heap.addAll(start.getEdges());
        while (!heap.isEmpty()) {
            GraphEdge cur = heap.poll();
            GraphNode to = cur.getTo();
            // 若目标点未曾到达过，则解锁该节点，并解锁与之关联的边
            if (!nodeSet.contains(to)) {
                nodeSet.add(to);
                result.add(cur);
                // 将没有用过的边加入堆中
                heap.addAll(to.getEdges().stream().filter(i->!result.contains(i)).collect(Collectors.toList()));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Graph graph = Graph.newInstance(new Integer[][]{
                {1, 0, 1}, {2, 0, 2}, {3, 0, 3}, {1, 1, 2}, {2, 2, 3}, {4, 2, 4}, {1, 1, 4}, {2, 4, 3}
        });
        Consumer<Set<GraphEdge> > consumer = i-> i.stream().map(k->k.getWeight()).forEach(System.out::print);
        consumer.accept(kruskalMST(graph));
        System.out.println("\n=================================");
        consumer.accept(primMST(graph));
    }

}
