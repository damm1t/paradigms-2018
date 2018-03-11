package queue;

//INV: size >= 0 && elements[0..size-1] != NULL
public class ArrayQueueModule {
    private static int back, front, size;
    private static Object[] elements = new Object[2];

    private static int next(int x) {
        return (x + 1) % elements.length;
    }

    private static int prev(int x) {
        return (x - 1 + elements.length) % elements.length;
    }

    //PRE: true
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = (size == 0)
    public static boolean isEmpty() {
        return (size == 0);
    }

    //PRE: true
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = size
    public static int size() {
        return size;
    }

    //PRE : true
    //POST : elements.length >= capacity && elements[i] = elements[i]', ∀ i: [0..size)
    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        int EXT = 2;
        Object[] newElements = new Object[EXT * capacity];
        for (int i = front, j = 0;  j < size; i = next(i), j++) {
            newElements[j] = elements[i];
        }
        back = size;
        front = 0;
        elements = newElements;
    }

    //PRE: element != NULL
    //POST: size = size' + 1 && elements[size - 1] = element && elements[i] = elements[i]', ∀ i: [0..size')
    public static void enqueue(Object element) {
        assert (element != null) : "Element is null"; // don't forget about -ea
        ensureCapacity(size + 1);
        elements[back] = element;
        back = next(back);
        size++;
    } // insert element in the end of elements

    //PRE: size > 0
    //POST: size = size' - 1 && R = elements[0]' && elements[i] = elements[i+1]', ∀ i: [0..size)
    public static Object dequeue() {
        assert (size > 0) : "Queue is empty";
        int x = front;
        size--;
        front = next(front);
        return elements[x];
    } // delete element in the begin of elements

    //PRE: size > 0
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = elements[0]
    public static Object element() {
        assert (size > 0) : "Queue is empty";
        return elements[front];
    } //return element from the begin of elements

    //PRE: element != NULL
    //POST: size = size' + 1 && elements[0] = element && elements[i] = elements[i-1]', ∀ i: [0..size')
    public static void push(Object element) {
        assert (element != null) : "Element is null";

        ensureCapacity(size + 1);
        front = prev(front);
        elements[front] = element;
        size++;
    } // insert element in the begin of elements

    //PRE: size > 0
    //POST: size = size' - 1 && R = elements'[size] && elements[i] = elements[i]', ∀ i: [0..size)
    public static Object remove() {
        assert (size > 0) : "Queue is empty";
        back = prev(back);
        size--;
        return elements[back];
    } // delete element in the end of elements

    //PRE: size > 0
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = elements[size - 1]
    public static Object peek() {
        assert (size > 0) : "Queue is empty";
        return elements[prev(back)];
    } //return element from the end of elements

    //PRE: true
    //POST: size = 0
    public static void clear()
    {
        size = front = back = 0;
        elements = new Object[2];
    }
}
