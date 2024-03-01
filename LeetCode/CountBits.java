package LeetCode; 

public class CountBits {
    class Solution {
        public int[] countBits(int n) {
            int[] dp = new int[n+1]; 
    
            dp[0] = 0;
            if (n >= 1) dp[1] = 1; 
    
            int i = 2; 
            for (int exp = 1; exp <= Math.log(n)/Math.log(2); exp++) { 
                int power = (int) Math.pow(2, exp);  
                dp[power] = 1; 
                i++; 
                while (i <= n && i < power * 2) { 
                    dp[i] = dp[power] + dp[i % power];
                    i++; 
                }
            }
    
            return dp;
        }
    }
}
