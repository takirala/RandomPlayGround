// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PureStorage.java

package companies;

import java.io.PrintStream;
import java.util.*;
import commercehub.*;


// Referenced classes of package codesnippets.java.companies:
//            TNode

class AirWatch
{

    AirWatch()
    {
    }

    public static void main(String args[])
    {
        printBoundaryNodes();
    }

    static void printBoundaryNodes()
    {
        TNode t = new TNode(28);
        t.left = new TNode(4);
        t.right = new TNode(69);
        t.left.right = new TNode(8);
        t.left.right.left = new TNode(7);
        t.left.right.left.left = new TNode(5);
        t.left.right.left.left.right = new TNode(6);
        t.left.right.right = new TNode(12);
        t.left.right.right.right = new TNode(13);
        t.left.right.right.right.left = new TNode(11);
        t.right.left = new TNode(56);
        t.right.left.left = new TNode(34);
        t.right.left.left.left = new TNode(2);
        t.right.left.left.left.left = new TNode(10);
        t.right.left.right = new TNode(27);
        t.right.left.right.left = new TNode(3);
        t.right.left.right.left.left = new TNode(9);
        t.right.left.right.right = new TNode(39);
        List res = new ArrayList();
        printBoundaryNodes(t, res);
        System.out.println(res.toString());
    }

    static void printBoundaryNodes(TNode t, List res)
    {
        printLeft(t, 0, 0, res);
        addLeaves(t, res);
        HashMap map = new HashMap();
        printRight(t.right, 0, map);
        for(int i = map.size() - 1; i >= 0; i--)
            if(map.containsKey(Integer.valueOf(i)))
                res.add(map.get(Integer.valueOf(i)));

    }

    static void addLeaves(TNode t, List res)
    {
        if(t == null)
            return;
        if(t.left == null && t.right == null)
            res.add(Integer.valueOf(t.val));
        addLeaves(t.left, res);
        addLeaves(t.right, res);
    }

    static int printLeft(TNode t, int ctr, int max, List res)
    {
        if(t == null)
            return max;
        if(t.left == null && t.right == null)
            return max;
        if(++ctr > max)
        {
            max = ctr;
            res.add(Integer.valueOf(t.val));
        }
        max = printLeft(t.left, ctr, max, res);
        int j = printLeft(t.right, ctr, max, res);
        return Math.max(max, j);
    }

    static void printRight(TNode t, int ctr, HashMap res)
    {
        if(t == null)
            return;
        if(t.left == null && t.right == null)
            return;
        ctr++;
        if(!res.containsKey(Integer.valueOf(ctr)))
            res.put(Integer.valueOf(ctr), Integer.valueOf(t.val));
        printRight(t.right, ctr, res);
        printRight(t.left, ctr, res);
    }
}
