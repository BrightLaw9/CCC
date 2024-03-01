package LeetCode;

public class LongestFibSequence {

    // DP!! Passed
    class Solution {
        public int lenLongestFibSubseq(int[] arr) {
            int n = arr.length; 
            int[][] dp = new int[n][n];
    
            int max = 0; 
    
            for (int i = 2; i < n; i++) { 
                // Perform two sum for the elements that add up to arr[i]
                int l = 0; 
                int r = i-1; 
                while (l < r) { 
                    if (arr[l] + arr[r] < arr[i]) l++; 
                    else if (arr[l] + arr[r] > arr[i]) r--; 
                    else { 
                        dp[r][i] = dp[l][r] + 1; 
                        max = Math.max(dp[r][i], max); 
                        // Continue the check for possible sums that match in case the difference of inner make a larger fib seq
                        r--; 
                        l++; 
                    }
                }
            }
    
            return max == 0 ? 0 : max + 2; 
        }
    }
}
