package CCC_2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3 {
    
    public static int findNumWays(int[] boards, int height) { 
        // Perform two sum that add to height, boards is sorted
        int numWays = 0;
        // boolean[] used = new boolean[boards.length];
        // Arrays.fill(used, false);  

        // for (int i = 0; i < boards.length; i++) { 
        //     for (int j = i+1; j < boards.length; j++) {
        //         if (boards[i] + boards[j] == height && !used[i] && !used[j]) { 
        //             numWays++; 
        //             used[i] = true;
        //             used[j] = true; 
        //         }
        //     }
        // }
        int l = 0; int r = boards.length - 1; 
        while (l < r) { 
            if (boards[l] + boards[r] == height) { 
                numWays++;
                l++; 
                r--;  
            }
            else if (boards[r] + boards[l] < height) { 
                l++; 
            }
            else { 
                r--; 
            }
        }
        return numWays; 
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine()); 

        int[] boards = new int[n]; 
        StringTokenizer tok = new StringTokenizer(br.readLine()); 
        for (int i = 0; i < n; i++) { 
            boards[i] = Integer.parseInt(tok.nextToken()); 
        }

        // For every possible height of the fence, determine the amount of pairs that can make it
        // Store numPairs in array, and update maxLength for easy finding later

        Arrays.sort(boards);
        int maxLength = 0; 
        int[] numLength = new int[1000000/2+1]; // The number of heights for a certain length
        Arrays.fill(numLength, 0); 

        for (int height = 2; height <= 4000; height++) { 
            int numBoards = findNumWays(boards, height); 
            maxLength = Math.max(maxLength, numBoards); 
            numLength[numBoards]++; 
        }

        System.out.println(maxLength + " " + numLength[maxLength]); 

        
    }
}
