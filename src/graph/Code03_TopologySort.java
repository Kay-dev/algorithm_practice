package graph;

import graph.Entity.Graph;
import graph.Entity.GraphNode;

import java.util.*;

/**
 * 图的拓扑排序
 * <p>
 * 在图上找到in值为0的节点作为起点。然后将这个节点从图中删掉。
 * 继续如上步骤，当图中所有节点都被删除后，依次输出的顺序就是拓扑序。
 * <p>
 * 前提：图中无环
 * 应用场景：事件安排、程序编译时的依赖关系
 *
 * @author weizheng
 * @date 2022/03/05
 */
public class Code03_TopologySort {

    /**
     * 方法一：利用队列实现
     * @param graph
     * @return
     */
    public static List<GraphNode> topologySort(Graph graph) {
        List<GraphNode> result = new ArrayList<>();
        // 存储各节点与in值的映射
        Map<GraphNode, Integer> map = new HashMap<>();
        // 存储in值为0的节点
        Queue<GraphNode> queue = new LinkedList<>();
        // 遍历一遍所有节点，获取初始数据
        graph.getNodes().forEach((k, v) -> {
            map.put(v, v.getIn());
            if (v.getIn() == 0) {
                queue.add(v);
            }
        });

        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            result.add(cur);
            cur.getNext().forEach(i-> {
                map.compute(i, (k, m) -> m - 1);
                if (map.get(i) == 0) {
                    queue.add(i);
                }
            });
        }
        return result;
    }

    /**
     * 方法二，利用堆实现
     * @param graph
     * @return
     */
    public static List<GraphNode> topologySortWithHeap(Graph graph) {
        List<GraphNode> result = new ArrayList<>();
        Queue<GraphNode> heap = new PriorityQueue<>(Comparator.comparing(i -> i.getIn()));
        graph.getNodes().forEach((k,v)->heap.add(v));
        while (!heap.isEmpty()) {
            GraphNode cur = heap.poll();
            cur.getNext().forEach(i->i.setIn(i.getIn()-1));
            result.add(cur);
        }
        return result;
    }

    public static void main(String[] args) {
        Graph graph = Graph.newInstance(new Integer[][]{
                {1, 0, 1}, {2, 0, 2}, {3, 0, 3}, {1, 1, 2}, {2, 2, 3}, {4, 2, 4}, {1, 1, 4}, {2, 4, 3}
        });
        List<GraphNode> graphNodes = topologySort(graph);
        graphNodes.stream().map(i->i.getIndex()).forEach(System.out::println);
        System.out.println("========================");
        topologySortWithHeap(graph).stream().map(i->i.getIndex()).forEach(System.out::println);
    }
}
