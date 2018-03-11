package queue;

import java.util.function.Predicate;
import java.util.function.Function;

public interface Queue {

    //PRE: element != null
    //POST: size = size' + 1 && elements[size - 1] = element && elements[i] = elements[i]', ∀ i: [0..size')
    public void enqueue(Object item);    // Puts item on end of queue

    //PRE: size > 0
    //POST: size = size' - 1 && R = elements[0]' && elements[i] = elements[i+1]', ∀ i: [0..size)
    public Object dequeue();    // Removes object from front of queue

    //PRE: true
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = (size == 0)
    public boolean isEmpty();    // Returns true if queue is empty

    //PRE: true
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = size
    public int size();    // Returns the number of elements in the queue

    //PRE: size > 0
    //POST: size = size' && elements[i] = elements[i]', ∀ i: [0..size) && R = elements[0]
    public Object element();    // Returns object from front of queue

    //PRE: true
    //POST: size = 0
    public void clear();    // Clears queue

    //PRE: predicate != null
    //POST: size <= size' && 0 <= i[0] < i[1] < ... < i[size - 1] < size' &&
    // && set = (i[0], i[1],..., i[size - 1])
    // && predicate(elements[j]) = true, j in set &&
    // && predicate(elements[j]) = false, j not in set, 0 <= j < size' &&
    // && R = {elements[i[0]], elements[i[1]],..., elements[i[size - 1]]}
    public Queue filter(Predicate<Object> predicate);


    //PRE: function != null
    //POST: size = size' && elements[i] = function(elements[i]'), 0 <= i < size' &&
    // && R = {elements[0], elements[1],..., elements[size - 1]}
    public Queue map(Function<Object, Object> function);
}
