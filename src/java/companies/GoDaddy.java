// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GoDaddy.java

package java.companies;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoDaddy
{

    public GoDaddy()
    {
    }

    public static void main(String args[])
    {
        GoDaddy d = new GoDaddy();
        int n = 1;
        String domain = "abc";
        d.passwordCheckRegExp("aaAZ0");
    }

    boolean passwordCheckRegExp(String inputString)
    {
        class _cls1Helper
        {

            boolean find(String pattern, String input)
            {
                return Pattern.compile(pattern).matcher(input).find();
            }

            final GoDaddy this$0;

            _cls1Helper()
            {
            	super();
                this$0 = GoDaddy.this;
            }
        }

        _cls1Helper h = new _cls1Helper();
        System.out.println((new StringBuilder("\n")).append(h.find("[1-9]", inputString)).append("\n").append(h.find("[A-Z]", inputString)).append("\n").append(h.find("[a-z]", inputString)).toString());
        return false;
    }

    int typosquatting(int n, String orig)
    {
        HashSet domains = new HashSet();
        domains = getSingleTypos(orig, orig);
        System.out.println(domains.size());
        if(domains.size() > n)
            return 0;
        if(domains.size() == 0)
            return -1;
        int k = 1;
        int prev = domains.size();
        while(domains.size() <= n) 
        {
            System.out.println((new StringBuilder("k ")).append(k).append(" size ").append(domains.size()).toString());
            HashSet temp = new HashSet();
            for(Iterator iterator = domains.iterator(); iterator.hasNext(); System.out.println((new StringBuilder("temp ")).append(temp.size()).toString()))
            {
                String s = (String)iterator.next();
                HashSet oneMoreTypo = getSingleTypos(s, orig);
                temp.addAll(oneMoreTypo);
            }

            if(temp.size() > 0)
                domains.addAll(temp);
            if(domains.size() == prev)
                return -1;
            if(domains.size() > n)
                break;
            prev = domains.size();
            k++;
        }
        return k;
    }

    HashSet getSingleTypos(String str, String original)
    {
        HashSet result = new HashSet();
        char arr[] = str.toCharArray();
        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i] != '.' && arr[i + 1] != '.')
            {
                char temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                String newSwap = new String(arr);
                arr[i + 1] = arr[i];
                arr[i] = temp;
                if(!result.contains(newSwap))
                    result.add(newSwap);
            }

        result.remove(original);
        return result;
    }
}
