import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import queue.ArrayQueueModule;

public class ArrayQueueModuleTest {

    @Before
    public void clean() {
        ArrayQueueModule.clear();
    }

    @Test
    public void testEnqueueDequeue() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(21);
        ArrayQueueModule.enqueue(32);
        Assert.assertEquals(ArrayQueueModule.element(), 7);
        ArrayQueueModule.dequeue();
        Assert.assertEquals(ArrayQueueModule.element(), 21);
    }

    @Test
    public void testDequeueEnqueue() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(21);
        ArrayQueueModule.dequeue();
        ArrayQueueModule.enqueue(32);
        Assert.assertEquals(ArrayQueueModule.dequeue(), 21);
        Assert.assertEquals(ArrayQueueModule.element(), 32);
    }

    @Test
    public void testSize() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(21);
        ArrayQueueModule.enqueue(32);
        Assert.assertEquals(ArrayQueueModule.size(), 3);
        ArrayQueueModule.dequeue();
        Assert.assertEquals(ArrayQueueModule.size(), 2);
    }

    @Test
    public void testIsEmpty() {
        ArrayQueueModule.enqueue(7);
        Assert.assertEquals(ArrayQueueModule.isEmpty(), false);
        ArrayQueueModule.dequeue();
        Assert.assertEquals(ArrayQueueModule.isEmpty(), true);
    }

    @Test
    public void testPush() {
        ArrayQueueModule.push(7);
        ArrayQueueModule.push(21);
        Assert.assertEquals(ArrayQueueModule.peek(), 7);
        ArrayQueueModule.dequeue();
        Assert.assertEquals(ArrayQueueModule.peek(), 7);
    }

    @Test
    public void testEnsureCapacity() {
        int[] a = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            a[i] = i + i % 5;
            ArrayQueueModule.enqueue(a[i]);
        }
        for (int i = 0; i < 1000; ++i) {
            Assert.assertEquals(ArrayQueueModule.element(), a[i]);
            ArrayQueueModule.dequeue();
        }
    }

    @Test
    public void testRemove() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(21);
        ArrayQueueModule.enqueue(32);
        Assert.assertEquals(ArrayQueueModule.remove(), 32);
        ArrayQueueModule.remove();
        Assert.assertEquals(ArrayQueueModule.element(), 7);
    }

    @Test(expected = AssertionError.class)
    public void testDequeue() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(21);
        ArrayQueueModule.enqueue(32);
        ArrayQueueModule.dequeue();
        Assert.assertEquals(ArrayQueueModule.element(), 21);
        ArrayQueueModule.dequeue();
        ArrayQueueModule.dequeue();
        ArrayQueueModule.dequeue();
    }

    @Test(expected = AssertionError.class)
    public void testEnqueue() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(null);
    }

    @Test(expected = AssertionError.class)
    public void testClear() {
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(32);
        ArrayQueueModule.clear();
        Assert.assertEquals(ArrayQueueModule.element(), 7);
    }
}