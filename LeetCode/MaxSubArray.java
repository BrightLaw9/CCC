package LeetCode;

public class MaxSubArray {
    class Solution {

        // DP, passed 200/210 -- MLE
        public int maxSubArray(int[] nums) {
            int[][] dp = new int[nums.length][nums.length]; 
             
            int max = Integer.MIN_VALUE; 
    
            for (int i = 0; i < nums.length; i++) { 
                dp[i][i] = nums[i];
                max = Math.max(max, dp[i][i]);
            }
    
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) { 
                    dp[j][i] = dp[j][i-1] + nums[i]; 
                    max = Math.max(max, dp[j][i]);
                }
            }
            return max; 
        }
    }

    class Solution2 {
        // O(n) time comp. O(1) space -- passed all
        public int maxSubArray(int[] nums) {
           // If a subarray causes a value to decrease, don't include it, reset the sum counter
           // Previous arrays sum will have been stored in a var
            // A subarray of not starting directly after the reset is not needed to be considered,
            // any positive increment in sum is worth considering, 
            //when negative, reset and consider another array
    
            int sum = 0; 
            int ans = nums[0]; 
            for (int i = 0; i < nums.length; i++) { 
                if (sum+nums[i] <= 0) { 
                    sum = 0; 
                    ans = Math.max(ans, nums[i]); // Consider only cur element in case if all negative
                }
                else { 
                    sum += nums[i];
                    ans = Math.max(sum, ans);
                }
            }
            return ans;
        }
    }
}
