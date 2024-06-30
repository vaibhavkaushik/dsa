package DP;

public class DP {

    //Leetcode 509
    public int fibTopDown(int n) {
        int[] dp = new int[n+1];
        return fibHelper(n,dp);
    }

    public int fibHelper(int n, int[] dp){
        if(n <= 1){
            dp[n] = n;
            return n;
        }

        dp[n] = fibHelper(n-1,dp) + fibHelper(n-2,dp);

        return dp[n];
    }

    //Leetcode 509
    public int fib(int n) {

        if(n <= 1){
            return n;
        }

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }

    //Leetcode 70
    public int climbStairs(int n) {

        int[] dp =  new int[n+1];
        Arrays.fill(dp,-1);
        return climbStairsHelper(n,dp);
    }

    public int climbStairsHelper(int n, int[] dp) {

        if(n == 0){
            return 1;
        }

        if(dp[n]!=-1){
            return dp[n];
        }

        int oneStep = (n-1) < 0 ? 0 : climbStairsHelper(n-1,dp);
        int twoStep = (n-2) < 0 ? 0 : climbStairsHelper(n-2,dp);

        dp[n] = oneStep + twoStep;

        return dp[n];
    }
}
