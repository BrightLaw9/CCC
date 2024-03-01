package Mock1;

import java.util.Arrays;
import java.util.Scanner;

public class CCC2016S4 {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        int[] sum = new int[n]; // Efficient way of compute sum of range

        int[] riceballs = new int[n]; 

        int total = 0; 

        for (int i = 0; i < n; i++) { 
            riceballs[i] = Integer.parseInt(in.next()); 
            total += riceballs[i];
            sum[i] = total; 
        }

        // Loop through all starting pos for all ranges to check combinable
        // Go from outside in to sum blocks in seeing if whole range can be combined / large part

        boolean[][] combinable = new boolean[n][n];

        for (int i = 0; i < n; i++) { 
            Arrays.fill(combinable[i], false); 
        }

        int maxBall = -1; 

        for (int i = 0; i < n; i++) { 
            combinable[i][i] = true;
            maxBall = Math.max(maxBall, riceballs[i]);  
        }
        
        // Range is actually range+1 (both indexes inclusive)
        for (int range = 1; range < n; range++) { 
            for (int start = 0; start < n-range; start++) { 
                int end = start + range; 
                int frontSum = riceballs[start]; int endSum = riceballs[end]; 
                int s = start; int e = end; // Make a copy so it don't change start counter
                while (s < e) {
                   // System.out.println("s: " + s + "e: " + e); 
                    // The first two conditions ask if the sum we created can actually be combined (as per sub recursion smaller ranges)
                    if (combinable[start][s] && combinable[e][end] && frontSum == endSum && (e - s <= 2 || combinable[s+1][e-1])) { 
                        combinable[start][end] = true; 
                        maxBall = Math.max(maxBall, sum[end] - sum[start] + riceballs[start]); 
                        break; 
                    }
                    if (frontSum < endSum) { 
                        s++; 
                        frontSum += riceballs[s];
                    }
                    else if (endSum <= frontSum) { 
                        e--; 
                        endSum += riceballs[e]; 
                    }
                }
            }
        }
        System.out.println(maxBall); 
    }
}
