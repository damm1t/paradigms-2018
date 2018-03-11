package queue;

import java.util.function.Predicate;
import java.util.function.Function;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   public void enqueue(Object element) {
        assert (element != null) : "Element is null";
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert (size > 0) : "queue.Queue is empty";
        Object item = element();
        dequeueImpl();
        size--;
        return item;
    }

    protected abstract void dequeueImpl();

    public Object element() {
        assert (size > 0) : "queue.Queue is empty";
        return elementImpl();
    }

    protected abstract Object elementImpl();


    public void clear()
    {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    protected abstract AbstractQueue makeCopy();

    public AbstractQueue filter(Predicate<Object> predicate) {
        assert (predicate != null) : "Predicate is null";
        AbstractQueue newQueue = makeCopy();
        int newSize = newQueue.size();
        for (int i = 0; i < newSize; i++) {
            Object element = newQueue.dequeue();
            if (predicate.test(element)) {
                newQueue.enqueue(element);
            }
        }
        return newQueue;
    }

    public AbstractQueue map(Function<Object, Object> function) {
        assert (function != null) : "Predicate is null";
        AbstractQueue newQueue = makeCopy();
        int newSize = newQueue.size();
        for (int i = 0; i < newSize; i++) {
            Object element = newQueue.dequeue();
            newQueue.enqueue(function.apply(element));
        }
        return newQueue;
    }
}
