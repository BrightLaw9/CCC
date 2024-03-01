package LeetCode; 

public class JumpGame {
    class Solution {

        public int jump(int[] nums) {
            int n = nums.length; 
            int[] dp = new int[n]; 
            for (int i = 0; i < n; i++) { 
                dp[i] = Integer.MAX_VALUE;
            }
            dp[0] = 0;
            for (int i = 0; i < n-1; i++) {
                if (i + nums[i] >= nums.length - 1) { 
                        // Optimization - dp[i] will all ready store the shortest length to get there
                        // if a shorter path stored at dp[n-1] use that, otherwise, b/c i is the farthest away that hasn't been explored, use this (greatest jump)
                        dp[n-1] = Math.min(dp[n-1], dp[i] + 1);  
                        break; 
                } 
                for (int j = 1; j <= nums[i]; j++) { 
                    dp[i+j] = Math.min(dp[i+j], dp[i] + 1); 
                }
            }
            return dp[n - 1];
        }
    }
}
