// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Akuna.java

package java.companies;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Akuna
{

    public Akuna()
    {
    }

    public static void main(String args[])
    {
        String src = "Source";
        ArrayList list = new ArrayList();
        permute(src, new StringBuilder(), 0, list, new boolean[src.length()]);
        System.out.println(list.size());
        String str;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); System.out.println(str))
            str = (String)iterator.next();

    }

    static void permute(String src, StringBuilder sb, int index, ArrayList result, boolean used[])
    {
        if(index == src.length())
        {
            System.out.println(sb.toString());
            result.add(sb.toString());
            return;
        }
        for(int i = 0; i < src.length(); i++)
            if(!used[i])
            {
                used[i] = true;
                sb.append(src.charAt(i));
                permute(src, sb, index + 1, result, used);
                sb.setLength(sb.length() - 1);
                used[i] = false;
            }

    }
}
