package link.entity;

/**
 * @author weizheng
 * @Description TODO
 * @date 2022/01/26
 */
public class Node<T> {

    public T value;
    public Node<T> last;
    public Node<T> next;

    public Node(T value) {
        this.last = this;
        this.value = value;
    }

    public Node<T> add(T t) {
        Node<T> node = new Node<>(t);
        last.next = node;
        last = last.next;
        return this;
    }

    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder("[") ;
        Node<T> node = this;
        do {
            sb.append(node.value.toString()).append("->");
            node = node.next;
        } while (node != null);
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
