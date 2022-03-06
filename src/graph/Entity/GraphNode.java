package graph.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizheng
 * @date 2022/03/05
 */
public class GraphNode {

    /**
     * 点的编号
     */
    private int index;

    /**
     * 有多少个点指向这个点
     */
    private int in;

    /**
     * 这个点指向了多少个点
     */
    private int out;

    /**
     * 从这个点指向的其他节点的集合
     */
    private List<GraphNode> next;

    /**
     * 以这个点为出发点的边的集合
     */
    private List<GraphEdge> graphEdges;

    public GraphNode(int index) {
        this.index = index;
        this.in = 0;
        this.out = 0;
        this.next = new ArrayList<>();
        this.graphEdges = new ArrayList<>();

    }

    public void increaseIn() {
        this.in++;
    }

    public void increaseOut() {
        this.out++;
    }

    public void addNext(GraphNode graphNode) {
        next.add(graphNode);
    }

    public void addEdge(GraphEdge graphEdge) {
        graphEdges.add(graphEdge);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public List<GraphNode> getNext() {
        return next;
    }

    public void setNext(List<GraphNode> next) {
        this.next = next;
    }

    public List<GraphEdge> getEdges() {
        return graphEdges;
    }

    public void setEdges(List<GraphEdge> graphEdges) {
        this.graphEdges = graphEdges;
    }

}
