package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head = new Node(), tail = head;

    @Override
    protected void enqueueImpl(Object element) {
        Node temp = new Node(element, null, tail);
        tail.next = temp;
        tail = temp;
    }

    @Override
    protected void dequeueImpl() {
        Node temp = head;
        head = head.next;
        head.prev = null;
        temp = null;
    }

    @Override
    protected Object elementImpl() {
        return head.next.value;
    }

    @Override
    protected void clearImpl()
    {
        head = new Node();
        tail = head;
    }

    @Override
    public LinkedQueue makeCopy() {
        LinkedQueue copyQueue = new LinkedQueue();
        Node temp = head.next;
        while (temp != null) {
            copyQueue.enqueue(temp.value);
            temp = temp.next;
        }
        return copyQueue;
    }

    private class Node {
        private Object value;
        private Node next, prev;

        Node(Object value, Node next, Node prev) {

            this.value = value;
            this.next = next;
            this.prev = prev;
        }
        Node() {

            this.value = null;
            this.next = null;
            this.prev = null;
        }
    }
}
