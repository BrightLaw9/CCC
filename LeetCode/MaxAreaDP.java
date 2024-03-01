package LeetCode;

public class MaxAreaDP {

    // Passed
    class Solution {
        public int maximalSquare(char[][] matrix) {
            
            int[][] dp = new int[matrix.length+1][matrix[0].length+1]; 
    
            for (int i = 1; i < matrix.length; i++) { 
                for (int j = 1; j < matrix[0].length; j++) { 
                    dp[i][j] = 0;
                }
            }
    
            int maxSide = 0; 
    
            for (int i = 0; i < matrix.length; i++) { 
                dp[i][0] = 0; // ((int) matrix[i][0]) - 48;
               // maxSide = Math.max(maxSide, dp[i][0]);
            }
    
            for (int j = 0; j < matrix[0].length; j++) { 
                dp[0][j] = 0; //((int) matrix[0][j]) - 48;
               // maxSide = Math.max(maxSide, dp[0][j]);
            }
    
            for (int r = 0; r < matrix.length; r++) { 
                for (int c = 0; c < matrix[0].length; c++) { 
                    if (matrix[r][c] == '1') { 
                        dp[r+1][c+1] = Math.min(dp[r][c], Math.min(dp[r+1][c], dp[r][c+1])) + 1; 
                        maxSide = Math.max(dp[r+1][c+1], maxSide);
                    }
                }
            }
    
            return maxSide * maxSide; 
        }
    }
}
