package Mock1;

public class MaxAreaSquare {
    
    static int solve(char[][] matrix) { 
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1]; // Extra col and row on left and top to initialize base 0

        int maxSide = 0; 
        for (int r = 0; r < matrix.length; r++) { 
            for (int c = 0; c < matrix[0].length; c++) { 
                if (matrix[r][c] == '1') { 
                    dp[r+1][c+1] = Math.min(dp[r][c], Math.min(dp[r+1][c], dp[r][c+1])) + 1; 
                    maxSide = Math.max(maxSide, dp[r+1][c+1]); 
                }
            }
        }

        return maxSide * maxSide; 
    }

    public static void main(String[] args) { 

    }
}
