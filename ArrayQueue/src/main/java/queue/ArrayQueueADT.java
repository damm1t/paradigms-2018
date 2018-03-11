package queue;

//INV: size >= 0 && elements[0..size-1] != NULL
public class ArrayQueueADT {
    private int back = 0, front = 0, size = 0;
    private Object[] elements = new Object[2];

    private static int next(ArrayQueueADT queue, int x) {
        return (x + 1) % queue.elements.length;
    }

    private static int prev(ArrayQueueADT queue, int x) {
        return (x - 1 + queue.elements.length) % queue.elements.length;
    }

    //PRE: queue != NULL
    //POST: queue.size = queue.size'
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size) && R = (queue.size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return (queue.size == 0);
    }

    //PRE: queue != NULL
    //POST: queue.size = queue.size'
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size) && R = queue.size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    //PRE : queue != NULL
    //POST : queue.elements.length >= capacity
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size)
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        int EXT = 2;
        Object[] newElements = new Object[EXT * capacity];
        for (int i = queue.front, j = 0;  j < queue.size; i = next(queue, i), j++) {
            newElements[j] = queue.elements[i];
        }
        queue.front = 0;
        queue.back = queue.size;
        queue.elements = newElements;
    }

    //PRE: element != NULL && queue != NULL
    //POST: queue.size = queue.size' + 1 && queue.elements[size - 1] = element
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size')
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert (element != null) : "Element is null";
        ensureCapacity(queue,queue.size + 1);
        queue.elements[queue.back] = element;
        queue.back = next(queue, queue.back);
        queue.size++;
    } // insert element in the end of elements

    //PRE: queue != NULL && queue.size > 0
    //POST: queue.size = queue.size' - 1 && R = queue.elements[0]'
    // && queue.elements[i] = queue.elements[i+1]', ∀ i: [0..queue.size)
    public static Object dequeue(ArrayQueueADT queue) {
        assert (queue.size > 0) : "Queue is empty";
        int x = queue.front;
        queue.front = next(queue, queue.front);
        queue.size--;
        return queue.elements[x];
    } // delete element in the begin of elements

    //PRE: queue.size > 0 && queue != NULL
    //POST: queue.size = queue.size'
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size) && R = queue.elements[0]
    public static Object element(ArrayQueueADT queue) {
        assert (queue.size > 0) : "Queue is empty";
        return queue.elements[queue.front];
    } //return element from the begin of elements

    //PRE: element != NULL && queue != NULL
    //POST: queue.size = queue.size' + 1
    // && queue.elements[0] = element && queue.elements[i] = queue.elements[i-1]', ∀ i: [0..queue.size')
    public static void push(ArrayQueueADT queue, Object element) {
        assert (element != null) : "Element is null";

        ensureCapacity(queue, queue.size + 1);
        queue.front = prev(queue, queue.front);
        queue.elements[queue.front] = element;
        queue.size++;
    } // insert element in the begin of elements

    //PRE: queue.size > 0 && queue != NULL
    //POST: queue.size = queue.size' - 1 && R = queue.elements'[size]
    // && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size)
    public static Object remove(ArrayQueueADT queue) {
        assert (queue.size > 0) : "Queue is empty";
        queue.back = prev(queue, queue.back);
        queue.size--;
        return queue.elements[queue.back];
    } // delete element in the end of elements

    //PRE: queue.size > 0 && queue != NULL
    //POST: queue.size = queue.size' && queue.elements[i] = queue.elements[i]', ∀ i: [0..queue.size)
    // && R = queue.elements[size - 1]
    public static Object peek(ArrayQueueADT queue) {
        assert (queue.size > 0) : "Queue is empty";
        return queue.elements[prev(queue, queue.back)];
    } //return element from the end of elements

    //PRE: queue != NULL
    //POST: queue.size = 0
    public static void clear(ArrayQueueADT queue)
    {
        queue.size = queue.front = queue.back = 0;
        queue.elements = new Object[2];
    }
}
