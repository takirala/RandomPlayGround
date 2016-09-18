// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CBInsight.java

package java.companies;

import java.io.PrintStream;

public class CBInsight
{

    public CBInsight()
    {
    }

    public static void main(String args[])
    {
        String command = "GLLR";
        System.out.println(maxStep(2, 3));
    }

    static int maxStep(int n, int k)
    {
        int step = 0;
        for(int i = 1; i <= n; i++)
            if(step + i == k)
            {
                if((step + i) - 1 > 0)
                    step = (step + i) - 1;
            } else
            {
                step += i;
            }

        return step;
    }

    static boolean isCircle(String command)
    {
        if(command == null)
            return false;
        if(command.length() == 1)
            return command.charAt(0) != 'G';
        int temp[] = new int[3];
        int i;
        switch(command.charAt(0))
        {
        case 71: // 'G'
            temp[0] = 1;
            temp[1] = 0;
            temp[2] = 1;
            // fall through

        case 76: // 'L'
            temp[0] = 4;
            temp[1] = -1;
            temp[2] = 0;
            // fall through

        case 82: // 'R'
            temp[0] = 2;
            temp[1] = 1;
            temp[2] = 0;
            // fall through

        default:
            i = 1;
            break;
        }
        for(; i < command.length(); i++)
            temp = getNextCord(temp[0], command.charAt(i), temp[1], temp[2]);

        for(i = 0; i < command.length(); i++)
            temp = getNextCord(temp[0], command.charAt(i), temp[1], temp[2]);

        for(i = 0; i < command.length(); i++)
            temp = getNextCord(temp[0], command.charAt(i), temp[1], temp[2]);

        for(i = 0; i < command.length(); i++)
            temp = getNextCord(temp[0], command.charAt(i), temp[1], temp[2]);

        return temp[1] == 0 && temp[2] == 0;
    }

    static double distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2D) + Math.pow(y1 - y2, 2D));
    }

    static int[] getNextCord(int dir, char cmd, int x, int y)
    {
        int sol[] = new int[3];
label0:
        switch(dir)
        {
        default:
            break;

        case 1: // '\001'
            switch(cmd)
            {
            case 71: // 'G'
                sol[0] = 1;
                sol[1] = x;
                sol[2] = y + 1;
                break label0;

            case 76: // 'L'
                sol[0] = 4;
                sol[1] = x - 1;
                sol[2] = y;
                break label0;

            case 82: // 'R'
                sol[0] = 2;
                sol[1] = x + 1;
                sol[2] = y;
                break label0;
            }
            // fall through

        case 2: // '\002'
            switch(cmd)
            {
            case 71: // 'G'
                sol[0] = 2;
                sol[1] = x + 1;
                sol[2] = y;
                break label0;

            case 76: // 'L'
                sol[0] = 1;
                sol[1] = x;
                sol[2] = y + 1;
                break label0;

            case 82: // 'R'
                sol[0] = 3;
                sol[1] = x;
                sol[2] = y - 1;
                break label0;
            }
            // fall through

        case 3: // '\003'
            switch(cmd)
            {
            case 71: // 'G'
                sol[0] = 3;
                sol[1] = x;
                sol[2] = y - 1;
                break label0;

            case 76: // 'L'
                sol[0] = 2;
                sol[1] = x + 1;
                sol[2] = y;
                break label0;

            case 82: // 'R'
                sol[0] = 4;
                sol[1] = x - 1;
                sol[2] = y;
                break label0;
            }
            // fall through

        case 4: // '\004'
            switch(cmd)
            {
            case 71: // 'G'
                sol[0] = 4;
                sol[1] = x - 1;
                sol[2] = y;
                break;

            case 76: // 'L'
                sol[0] = 3;
                sol[1] = x;
                sol[2] = y - 1;
                break;

            case 82: // 'R'
                sol[0] = 1;
                sol[1] = x;
                sol[2] = y + 1;
                break;
            }
            break;
        }
        return sol;
    }
}
