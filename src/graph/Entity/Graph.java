package graph.Entity;

import java.util.*;

/**
 * 图的封装类
 * @author weizheng
 * @date 2022/03/05
 */
public class Graph {
    /**
     * 将数组转成图对象
     * 每个数组代表一条边的信息
     * [
     *     [weight1,from1,to1],
     *     [weight2,from2,to2],
     *     ...
     * ]
     *
     * @param matrix
     * @return
     */
    public static Graph newInstance(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int fromIndex = matrix[i][1];
            int toIndex = matrix[i][2];
            // 将点存入图中
            graph.addNode(fromIndex, new GraphNode(fromIndex));
            graph.addNode(toIndex, new GraphNode(toIndex));
            // 将边存入图中
            GraphNode from = graph.getNode(fromIndex);
            GraphNode to = graph.getNode(toIndex);
            GraphEdge edge = new GraphEdge(weight, from, to);
            graph.addEdge(edge);
            // 更新点的数据
            from.increaseOut();
            from.addNext(to);
            from.addEdge(edge);
            to.increaseIn();
        }
        return graph;
    }

    /**
     * 下标和点的对应关系
     */
    private Map<Integer, GraphNode> nodes;

    /**
     * 边的集合
     */
    private Set<GraphEdge> graphEdges;

    public Graph() {
        nodes = new HashMap<>();
        graphEdges = new HashSet<>();
    }

    public boolean containsNode(Integer index) {
        return nodes.containsKey(index);
    }

    public boolean containsEdge(GraphEdge graphEdge) {
        return graphEdges.contains(graphEdge);
    }

    public void addNode(Integer index, GraphNode graphNode) {
        nodes.putIfAbsent(index, graphNode);
    }

    public void addEdge(GraphEdge graphEdge) {
        graphEdges.add(graphEdge);
    }

    public GraphNode getNode(Integer index) {
        return nodes.get(index);
    }

    public Map<Integer, GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, GraphNode> nodes) {
        this.nodes = nodes;
    }

    public Set<GraphEdge> getEdges() {
        return graphEdges;
    }

    public void setEdges(Set<GraphEdge> graphEdges) {
        this.graphEdges = graphEdges;
    }
}
