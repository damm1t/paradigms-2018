package queue;

public class ArrayQueue extends AbstractQueue {
    private int back = 0, front = 0;
    private Object[] elements = new Object[2];

    private int next(int x) {
        return (x + 1) % elements.length;
    }

    private int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        int EXT = 2;
        Object[] newElements = new Object[EXT * capacity];
        if (back <= front && size != 0) {
            System.arraycopy(elements, front, newElements, 0, elements.length - front);
            System.arraycopy(elements, 0, newElements, elements.length - front, back);
        } else {
            System.arraycopy(elements, front, newElements,0,back - front);
        }
        elements = newElements;
        front = 0;
        back = size;
    }

    @Override
    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[back % elements.length] = element;
        back = next(back);
    }

    @Override
    protected void dequeueImpl() {
        front = next(front);
    }

    @Override
    protected Object elementImpl() {
        return elements[front];
    }

    @Override
    protected void clearImpl() {
        front = back = 0;
        elements = new Object[2];
    }

    @Override
    protected ArrayQueue makeCopy(){
        ArrayQueue newQueue = new ArrayQueue();
        newQueue.elements = new Object[size];
        newQueue.size = size;
        newQueue.back = size;
        newQueue.front = 0;
        if (back <= front && size != 0) {
            System.arraycopy(elements, front, newQueue.elements, 0, elements.length - front);
            System.arraycopy(elements, 0, newQueue.elements, elements.length - front, back);
        } else {
            System.arraycopy(elements, front, newQueue.elements,0,back - front);
        }
        return newQueue;
    }
}
