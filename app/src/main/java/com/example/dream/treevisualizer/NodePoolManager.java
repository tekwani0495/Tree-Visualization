package com.example.dream.treevisualizer;

/**
 * Created by shubh on 13-04-2018.
 */



public class NodePoolManager
{

    Node[  ] pool;
    boolean[  ] inUse;
    public NodePoolManager(int initialPoolSize)
    {
        pool = new Node[initialPoolSize];
        inUse = new boolean[initialPoolSize];
        for (int i = pool.length-1; i>=0; i--)
        {
//            pool[i] = new Node( );
            inUse[i] = false;
        }
    }

    public synchronized Node getNode(  )
    {
        for (int i = inUse.length-1; i >= 0; i--)
            if (!inUse[i])
            {
                inUse[i] = true;
                return pool[i];
            }

        //If we got here, then all the Nodes are in use. We will
        //increase the number in our pool by 10 (arbitrary value for
        //illustration purposes).
        boolean[  ] old_inUse = inUse;
        inUse = new boolean[old_inUse.length+10];
        System.arraycopy(old_inUse, 0, inUse, 0, old_inUse.length);

        Node[  ] old_pool = pool;
        pool = new Node[old_pool.length+10];
        System.arraycopy(old_pool, 0, pool, 0, old_pool.length);

        for (int i = old_pool.length; i < pool.length; i++)
        {
//            pool[i] = new Node(  );
            inUse[i] = false;
        }

        //and allocate the last Node
        inUse[pool.length-1] = true;
        return pool[pool.length-1];
    }

    public synchronized void returnNode(Node v)
    {
        for (int i = inUse.length-1; i >= 0; i--)
            if (pool[i] == v)
        {
            inUse[i] = false;
            //Can use clear(  ) for java.util.Collection objects
            //Note that setSize(  ) nulls out all elements
//            v.setSize(0);
            return;
        }
        throw new RuntimeException("Node was not obtained from the pool: " + v);
    }
}