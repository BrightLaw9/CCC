import java.util.Scanner;

public class S2Improved {
    
    public static void main(String args[]) { 
        // Using prefix sum array on a smaller set
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        int[] heights = new int[n+1]; 

        for (int i = 1; i <= n; i++) { 
            heights[i] = Integer.parseInt(in.next());  
        }

        int[][] pre = new int[n+2][n+1]; 

        for (int i = 1; i <= n; i++) { 
            int l = 1; int r = i; int best = Integer.MAX_VALUE; 

            while (r <= n) { 
                pre[l][r] = Math.abs(heights[r] - heights[l]) + pre[l+1][r-1]; 
                
                best = Math.min(pre[l][r], best); 
                l++; r++; 
            }
            System.out.print(best + " "); 
        }

    }
}
