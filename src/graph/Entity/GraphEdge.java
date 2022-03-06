package graph.Entity;

/**
 * @author weizheng
 * @date 2022/03/05
 */
public class GraphEdge {

    /**
     * 权重
     */
    private int weight;

    /**
     * 出发点
     */
    private GraphNode from;

    /**
     * 终止点
     */
    private GraphNode to;


    public GraphEdge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GraphNode getFrom() {
        return from;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public GraphNode getTo() {
        return to;
    }

    public void setTo(GraphNode to) {
        this.to = to;
    }

}
