import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Heap-based priority queue.
 * @author Patrick Parker
 * @version Nov 11, 2016
 * @param <E> Class of objects to store.
 */
public class MyQueue<E> 
{
    private ArrayList<E> queue;
    private Comparator<E> comp;
    
    /**
     * Creates the priority queue.
     * @param c Comparator used to order elements.
     */
    public MyQueue(Comparator<E> c)
    {
        queue = new ArrayList<E>();
        comp = c;
    }
    
    /**
     * Returns head without removal.
     * @return Head of queue.
     */
    public E peek()
    {
        if (!queue.isEmpty())
        {
            return queue.get(0);
        }
        return null;
    }
    
    /**
     * Adds element to queue.
     * @param element Element to add.
     * 
     */
    public void add(E element)
    {
        queue.add(element);
        siftUp();
        
    }
    
    /**
     * Adds the contents if a list to the queue.
     * @param c List of objects to add.
     * 
     */
    public void addAll(List<E> c)
    {
        
        for (E element : c)
        {
            add(element);            
        }
    }
        
    
    /**
     * Removes and returns the head.
     * @return The head of the queue.
     */
    public E remove()
    {
        if (!queue.isEmpty())
        {
            E head = queue.get(0);
            if (queue.size() > 2)
            {
                siftDown();
                return head;
            }
            else
            {
                queue.remove(0);
                return head;
            }
        }
        return null;
    }
    
    /**
     * Checks if queue is empty.
     * @return True if empty, else false.
     */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    /**
     * Extracts new head.
     */
    private void siftDown() 
    {
        boolean swapMade;
        E last = queue.remove(queue.size() - 1);
        queue.set(0, last);
        int index = 0;
        do
        {
            swapMade = false;
            int l = 0;
            if (leftChildOf(index) < queue.size() - 1)
            {
                l = comp.compare(queue.get(index),
                        queue.get(leftChildOf(index)));
            }
            int r = 0;
            if (rightChildOf(index) < queue.size() - 1)
            {
                r = comp.compare(queue.get(index),
                        queue.get(rightChildOf(index)));
            }
            if (l > 0 && l > r)
            {
                swap(index, leftChildOf(index));
                swapMade = true;
                index = leftChildOf(index);
            }
            else if (r > 0 && r >= l)
            {
                swap(index, rightChildOf(index));
                swapMade = true;
                index = rightChildOf(index);
            }
            
        }
        while (swapMade && index != queue.size() - 1);
        
    }

    /**
     * Places new element in correct position.
     */
    private void siftUp() 
    {
        boolean swapMade;
        int index = queue.size() - 1;
        do
        {
            swapMade = false;
            int c = comp.compare(queue.get(index),
                queue.get(parentOf(index)));
            if (c < 0)
            {
                swap(index, parentOf(index));
                swapMade = true;
            }
            index = parentOf(index);
        }
        while (swapMade && index != 0);
        
    }
    
    /**
     * Swaps elements at indexes a and b.
     * @param a Index of element to swap.
     * @param b Index of element to swap.
     */
    private void swap(int a, int b) 
    {
        E temp = queue.get(a);
        queue.set(a, queue.get(b));
        queue.set(b, temp);        
    }

    /**
     * Finds index of left child.
     * @param parent Index of parent node.
     * @return Index of left child.
     */
    private int leftChildOf(int parent)
    {
        return 2 * parent + 1;
    }
    
    /**
     * Finds index of right child.
     * @param parent Index of parent node.
     * @return Index of right child.
     */
    private int rightChildOf(int parent)
    {
        return 2 * (parent + 1);
    }
    
    /**
     * Finds index of parent.
     * @param child Index of child node.
     * @return Index of parent.
     */
    private int parentOf(int child)
    {
        return (child - 1) / 2;
    }
}
