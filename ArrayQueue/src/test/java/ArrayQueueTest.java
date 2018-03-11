import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import queue.ArrayQueue;

public class ArrayQueueTest {

    private ArrayQueue q;

    @Before
    public void create() {
        q = new ArrayQueue();
    }

    @Test
    public void testEnqueueDequeue() {
        q.enqueue(7);
        q.enqueue(21);
        q.enqueue(32);
        Assert.assertEquals(q.element(), 7);
        q.dequeue();
        Assert.assertEquals(q.element(), 21);
    }

    @Test
    public void testDequeueEnqueue() {
        q.enqueue(7);
        q.enqueue(21);
        q.dequeue();
        q.enqueue(32);
        Assert.assertEquals(q.element(), 21);
        q.dequeue();
        Assert.assertEquals(q.element(), 32);
    }

    @Test
    public void testSize() {
        q.enqueue(7);
        q.enqueue(21);
        q.enqueue(32);
        Assert.assertEquals(q.size(), 3);
        q.dequeue();
        Assert.assertEquals(q.size(), 2);
    }

    @Test
    public void testIsEmpty() {
        q.enqueue(7);
        Assert.assertEquals(q.isEmpty(), false);
        q.dequeue();
        Assert.assertEquals(q.isEmpty(), true);
    }

    @Test
    public void testPush() {
        q.push(7);
        q.push(21);
        Assert.assertEquals(q.peek(), 7);
        q.dequeue();
        Assert.assertEquals(q.peek(), 7);
    }

    @Test
    public void testEnsureCapacity() {
        int[] a = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            a[i] = i + i % 5;
            q.enqueue(a[i]);
        }
        for (int i = 0; i < 1000; ++i) {
            Assert.assertEquals(q.element(), a[i]);
            q.dequeue();
        }
    }

    @Test
    public void testRemove() {
        q.enqueue(7);
        q.enqueue(21);
        q.enqueue(32);
        Assert.assertEquals(q.remove(), 32);
        q.remove();
        Assert.assertEquals(q.element(), 7);
    }

    @Test(expected = AssertionError.class)
    public void testDequeue() {
        q.enqueue(7);
        q.enqueue(21);
        q.enqueue(32);
        Assert.assertEquals(q.dequeue(), 7);
        Assert.assertEquals(q.element(), 21);
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }

    @Test(expected = AssertionError.class)
    public void testEnqueue() {
        q.enqueue(7);
        q.enqueue(null);
    }

    @Test(expected = AssertionError.class)
    public void testClear() {
        q.enqueue(7);
        q.enqueue(32);
        q.clear();
        Assert.assertEquals(q.element(), 7);
    }
}