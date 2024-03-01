package CCC_2016;

import java.util.Arrays;
import java.util.Scanner;

public class S4 {
    
    public static void main(String[] args) { 
        
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        int[] sum = new int[n]; 

        int[] riceballs = new int[n]; 

        riceballs[0] = Integer.parseInt(in.next()); 
        sum[0] = riceballs[0]; 

        for (int i = 1; i < n; i++) { 
            riceballs[i] = Integer.parseInt(in.next()); 
            sum[i] = sum[i-1] + riceballs[i]; 
        }

        boolean[][] combinable = new boolean[n][n]; 

        for (int i = 0; i < n; i++) { 
            Arrays.fill(combinable[i], false); 
        }

        for (int i = 0; i < n; i++) { 
            combinable[i][i] = true; // A single range is combinable
        }

        // To access a particular segment from i to j, sum[j] - sum[i]
        for (int range = 1; range < n; range++) { 
            
            for (int start = 0; start < n - range; start++) { 
                int end = start + range; // end of end block

                int frontBlock = riceballs[start]; 
                int endBlock = riceballs[end]; 

                int frontEnd = start;  // This will increase
                int endBegin = end; // This will decrease later

                while (frontEnd < endBegin) { 
                    // Still valid ranges
                    // If front is combinable, no middle or middle is combinable, and end is combinable && the front block == end block
                    if (combinable[start][frontEnd] 
                            && (endBegin-1 < frontEnd + 1 || combinable[frontEnd + 1][endBegin - 1]) 
                            && combinable[endBegin][end] 
                            && frontBlock == endBlock) { 
                            combinable[start][end] = true;
                            break;
                    }
                    else if (frontBlock < endBlock) { 
                        // Increase the range of frontBlock to check if can combine and form a match with endBlock
                        frontEnd++; 
                        frontBlock += riceballs[frontEnd]; 
                    }
                    else { 
                        endBegin--; 
                        endBlock += riceballs[endBegin]; 
                    }
                }
            }
        }

        int largest = 0; 

        for (int i = 0; i < n; i++) { 
            // all possible starting
            for (int j = 0; j < n; j++) { 
                // all possible ranges

                if (combinable[i][j]) { 
                    largest = Math.max(largest, sum[j] - sum[i] + riceballs[i]); 
                }
            }
        }
        System.out.println(largest);
    }
}
