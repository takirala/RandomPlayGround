package dp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * Created by takirala on 3/30/2016.
 */
public class DPJava {
    public static void main(String[] args) {

        Executor ex = new Executor() {
            @Override
            public void execute(Runnable command) {

            }


        };


        //lis();
        long t1 = System.currentTimeMillis();
        lcs();
        System.out.println(System.currentTimeMillis() - t1);
    }

    private static void lcs() {
        String str1 = "worldconfesshelloworld";
        String str2 = "WorldCongress";
        int[][] dp = new int[str1.length()][str2.length()];
        System.out.println(lcs(str1, str2, str1.length(), str2.length(), dp));

        for (int i = 0; i < str2.length(); i++) {
            System.out.print(str2.charAt(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            int[] row = dp[i];
            System.out.print(str1.charAt(i) + "\t");
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + "\t");
            }
            System.out.println();
        }

        for (int i = dp.length - 1; i >= 0; i++) {
            int row[] = dp[i];
            for (int j = row.length; j >= 0; j++) {

            }
        }
    }

    private static int lcs(String str1, String str2, int i1, int i2, int dp[][]) {

        if (i1 == 0 || i2 == 0) return 0;
        if (dp[i1 - 1][i2 - 1] > 0) return dp[i1 - 1][i2 - 1];
        int sol = 0;
        if (str1.charAt(i1 - 1) == str2.charAt(i2 - 1)) sol = 1 + lcs(str1, str2, i1 - 1, i2 - 1, dp);
        else sol = Math.max(lcs(str1, str2, i1 - 1, i2, dp), lcs(str1, str2, i1, i2 - 1, dp));
        dp[i1 - 1][i2 - 1] = sol;
        return sol;
    }

    private static void lis() {
        int[] input = {1, 3, 4, 5, 6, 2, 5, 2, 4, 5, 7, 7, 23, 4, 2, 3, 4, 2, 3, 5, 3, 4};


        int[] dp = new int[input.length];
        dp[0] = 1;

        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }
        int max = 1;
        for (int res : dp) {
            max = Math.max(max, res);
        }
        System.out.println(max + 1);
    }
}
