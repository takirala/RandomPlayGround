// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Coursera.java

package java.companies;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Coursera
{
    static class Com
        implements Comparable
    {

        public String toString()
        {
            return (new StringBuilder("(")).append(x).append(",").append(y).append(")").toString();
        }

        public int compareTo(Com o)
        {
            if(o.x > x && o.y > y)
                return 1;
            return o.x >= x || o.y >= y ? 0 : -1;
        }

        public int compareTo(Object obj)
        {
            return compareTo((Com)obj);
        }

        int x;
        int y;

        Com(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class P
    {

        static void pr()
        {
            System.out.println("P");
        }

        P()
        {
        }
    }

    static class S extends P
    {

        static void pr()
        {
            System.out.println("S");
        }

        S()
        {
        }
    }


    public Coursera()
    {
    }

    public static void main(String args[])
    {
        String a = "hello";
        String b = "h";
        b = (new StringBuilder(String.valueOf(b))).append("ello").toString();
        System.out.println(a == b);
        System.out.println(a == b);
    }

    private void run()
    {
        P p = new S();
        P.pr();
    }

    static int val(int i)
    {
        if(i <= 1)
            return i;
        else
            return val(i - 1) + val(i - 2);
    }

    public static void numberComp(String args[])
    {
        System.out.println("Philip ".compareTo("Philippe "));
        String s = "5\n3 2\n3 4\n6 9\n8 7\n7 8\n";
        Scanner sc = new Scanner(s);
        int count = sc.nextInt();
        sc.nextLine();
        Com entries[] = new Com[count];
        int i = 0;
        for(; sc.hasNext(); sc.nextLine())
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            entries[i++] = new Com(x, y);
        }

        Arrays.sort(entries);
        int sol = 0;
        if(entries[0].compareTo(entries[1]) == 0)
            sol++;
        for(int j = 1; j < entries.length; j++)
        {
            if(entries[j].compareTo(entries[j - 1]) != 0)
                break;
            sol++;
            System.out.println(entries[j]);
        }

        System.out.println(sol != 0 ? sol : 1);
        System.out.println(Arrays.toString(entries));
    }
}
