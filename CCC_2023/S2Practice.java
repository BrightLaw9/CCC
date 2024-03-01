import java.util.Scanner;

public class S2Practice {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); 

        int numMount = Integer.parseInt(in.nextLine());

        int[] heights = new int[numMount]; 

        for (int i = 0; i < numMount; i++) {
             heights[i] = Integer.parseInt(in.next());
        }

        // Use a dp array to store smaller subsets of crop, use 2 indices for left of range 
        // Right of range

        int[][] dp = new int[numMount+1][numMount]; 

        System.out.print(0 + " "); // A range of 1 will be the num subtracting itself, so 0

        for (int range = 2; range <= numMount; range++) { 
            int l = 0; int r = range - 1; int best = Integer.MAX_VALUE; 

            while (r < numMount) { 
                // Sliding window still in range
                dp[l][r] = Math.abs(heights[r] - heights[l]) + dp[l+1][r-1]; 

                best = Math.min(best, dp[l][r]);
                l++; r++; // Slide to right
            }
            System.out.print(best + " "); 
        }
    }
}
