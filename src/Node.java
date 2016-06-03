/**
 * Created by radhikadesai on 17/03/2016.
 */
public class Node {
    Event event;
    Color color;
    Node left;
    Node right;
    Node parent;

    public Node(Event event,Node left, Node right) {
        this.event = event;
        this.left=left;
        this.right=right;
    }

    public Node(Event event, Color color, Node left, Node right,Node parent) {
        this.event = event;
        this.color = color;
        this.left = left;
        this.right = right;
        this.parent=parent;
    }
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
