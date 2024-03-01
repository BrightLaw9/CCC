package Mock1;

import java.util.Scanner;

public class CCC2023S2 {
    
    public static void main(String[] args) 
    { 

        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next());
        int[] mount = new int[n]; 
        
        for (int i = 0; i < n; i++) { 
            mount[i] = Integer.parseInt(in.next()); 
        } 

        int[][] dp = new int[n][n]; 

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0; 
        }  
        System.out.print(0 + " ");  

        int min = Integer.MAX_VALUE; 

        // if (n >= 2) { 
        //     // 2 item crop
        //     for (int i = 0; i < n - 1; i++) { 
        //         dp[i][i+1] = Math.abs(mount[i+1] - mount[i]); 
        //         min = Math.min(min, dp[i][i+1]); 
        //     }

        //     System.out.print(min + " "); 
        // }

        // 2 item or more crop
        for (int r = 2; r <= n; r++) { 
            min = Integer.MAX_VALUE; 
            for (int i = 0; i <= n - r; i++) { 
                dp[i][i+r-1] = Math.abs(mount[i] - mount[i+r-1]) + dp[i+1][i+r-2]; // When 2, dp[i+1][i+r-2] will be zero as we don't know what that range is
                min = Math.min(min, dp[i][i+r-1]); 
            }
            System.out.print(min + " ");
        }
    }
}
