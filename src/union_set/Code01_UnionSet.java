package union_set;

import java.util.*;

/**
 * 并查集
 *
 * @author weizheng
 * @date 2022/03/04
 */
public class Code01_UnionSet<T> {

    private Map<T, Node<T>> nodeMap;

    private Map<Node<T>, Node<T>> parentMap;

    private Map<Node<T>, Integer> sizeMap;

    public Code01_UnionSet(Collection<T> list) {
        this.nodeMap = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        for (T t : list) {
            Node<T> node = new Node<>(t);
            nodeMap.put(t, node);
            // 每个值最开始都指向自己
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 判断a，b是否在一个集合中。只需要判断两个对象的根节点是否是同一个即可
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSameSet(T a, T b) {
        if (notContainsAorB(a, b)) {
            return false;
        }
        return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
    }

    /**
     * 合并两个并查集，即将a相关联的元素，和b相关联的所有元素合并到一个父节点下
     *
     * @param a
     * @param b
     */
    public void union(T a, T b) {
        if (notContainsAorB(a, b)) {
            return;
        }
        Node<T> aFather = findFather(nodeMap.get(a));
        Node<T> bFather = findFather(nodeMap.get(b));
        // 若两个本来就在一个集合中，结束
        if (aFather == bFather) {
            return;
        }
        Integer aSize = sizeMap.get(aFather);
        Integer bSize = sizeMap.get(bFather);
        // 将小集合的父节点指针指向大集合的父节点，并删除小集合的相关数据
        Node<T> small = aSize > bSize ? bFather : aFather;
        Node<T> large = small == aFather ? bFather : aFather;
        parentMap.put(small, large);
        sizeMap.put(large, aSize + bSize);
        sizeMap.remove(small);
    }

    /**
     * 返回并查集中的集合数量
     * @return
     */
    public int getSize() {
        return sizeMap.size();
    }


    private boolean notContainsAorB(T a, T b) {
        return !nodeMap.containsKey(a) || !nodeMap.containsKey(b);
    }

    private Node<T> findFather(Node<T> node) {
        Node<T> cur = node;
        List<Node<T>> list = new ArrayList<>();
        // 一直找到最顶层的父节点
        while (!cur.equals(parentMap.get(cur))) {
            list.add(cur);
            cur = parentMap.get(cur);
        }
        // 将每个经过的节点的指针，直接指向根节点
        for (Node<T> tNode : list) {
            parentMap.put(tNode, cur);
        }
        return cur;
    }


    private static class Node<T> {
        T value;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

}
