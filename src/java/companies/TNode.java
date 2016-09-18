// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PureStorage.java

package java.companies;


class TNode
{

    TNode(int val)
    {
        this.val = val;
        left = right = null;
    }

    TNode left;
    TNode right;
    int val;
}
