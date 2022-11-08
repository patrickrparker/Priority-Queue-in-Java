import java.util.Comparator;
import java.util.Random;

import junit.framework.TestCase;

/**
 * Test for MyQueue.
 * @author Patrick Parker
 * @version Nov 11, 2016
 *
 */
public class MyQueueTest extends TestCase 
{
    /**
     * Tests the queue.
     */
    public void testQueue()
    {
        MyQueue<Integer> queue = new MyQueue<Integer>(new Comparator<Integer>() 
        {
            public int compare(Integer a, Integer b)
            {
                                        
                return b - a;
            }
        });
        
        assertNull(queue.remove());
        
        for (int i = 1; i <= 10; i++)
        {
            queue.add(i);
        }
        
        for (int i = 10; i > 0; i--)
        {
            assertEquals((Integer) i, queue.remove());
        }
        Random rand = new Random();
        
        for (int i = 0; i < 100; i++)
        {
            queue.add(rand.nextInt(99) + 1);
        }
        
        while (!queue.isEmpty())
        {
            Integer a = queue.remove();            
            if (!queue.isEmpty())
            {
                Integer b = queue.remove();
                assertTrue(a >= b);
            }
        }
        
    }
}
