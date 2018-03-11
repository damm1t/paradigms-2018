import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import queue.ArrayQueueADT;

public class ArrayQueueADTTest {
    ArrayQueueADT queue = new ArrayQueueADT();
    @Before
    public void clean() {
        ArrayQueueADT.clear(queue);
    }

    @Test
    public void testEnqueueDequeue() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,21);
        ArrayQueueADT.enqueue(queue,32);
        Assert.assertEquals(ArrayQueueADT.element(queue), 7);
        ArrayQueueADT.dequeue(queue);
        Assert.assertEquals(ArrayQueueADT.element(queue), 21);
        ArrayQueueADT.clear(queue);
    }

    @Test
    public void testDequeueEnqueue() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,21);
        ArrayQueueADT.dequeue(queue);
        ArrayQueueADT.enqueue(queue,32);
        Assert.assertEquals(ArrayQueueADT.dequeue(queue), 21);
        Assert.assertEquals(ArrayQueueADT.element(queue), 32);
        ArrayQueueADT.clear(queue);
    }

    @Test
    public void testSize() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,21);
        ArrayQueueADT.enqueue(queue,32);
        Assert.assertEquals(ArrayQueueADT.size(queue), 3);
        ArrayQueueADT.dequeue(queue);
        Assert.assertEquals(ArrayQueueADT.size(queue), 2);
    }

    @Test
    public void testIsEmpty() {
        ArrayQueueADT.enqueue(queue,7);
        Assert.assertEquals(ArrayQueueADT.isEmpty(queue), false);
        ArrayQueueADT.dequeue(queue);
        Assert.assertEquals(ArrayQueueADT.isEmpty(queue), true);
    }

    @Test
    public void testPush() {
        ArrayQueueADT.push(queue,7);
        ArrayQueueADT.push(queue,21);
        Assert.assertEquals(ArrayQueueADT.peek(queue), 7);
        ArrayQueueADT.dequeue(queue);
        Assert.assertEquals(ArrayQueueADT.peek(queue), 7);
    }

    @Test
    public void testEnsureCapacity() {
        int[] a = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            a[i] = i + i % 5;
            ArrayQueueADT.enqueue(queue, a[i]);
        }
        for (int i = 0; i < 1000; ++i) {
            Assert.assertEquals(ArrayQueueADT.element(queue), a[i]);
            ArrayQueueADT.dequeue(queue);
        }
    }

    @Test
    public void testRemove() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,21);
        ArrayQueueADT.enqueue(queue,32);
        Assert.assertEquals(ArrayQueueADT.remove(queue), 32);
        ArrayQueueADT.remove(queue);
        Assert.assertEquals(ArrayQueueADT.element(queue), 7);
    }

    @Test(expected = AssertionError.class)
    public void testDequeue() {
        ArrayQueueADT.enqueue(queue, 7);
        ArrayQueueADT.enqueue(queue, 21);
        ArrayQueueADT.enqueue(queue,32);
        ArrayQueueADT.dequeue(queue);
        Assert.assertEquals(ArrayQueueADT.element(queue), 21);
        ArrayQueueADT.dequeue(queue);
        ArrayQueueADT.dequeue(queue);
        ArrayQueueADT.dequeue(queue);
    }

    @Test(expected = AssertionError.class)
    public void testEnqueue() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,null);
    }

    @Test(expected = AssertionError.class)
    public void testClear() {
        ArrayQueueADT.enqueue(queue,7);
        ArrayQueueADT.enqueue(queue,32);
        ArrayQueueADT.clear(queue);
        Assert.assertEquals(ArrayQueueADT.element(queue), 7);
    }

}