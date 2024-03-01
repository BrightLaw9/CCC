package CCC_2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3Improved {
    
    static int[] woodLengthCount; 
    static int findNumWays(int height) {
        int numWays = 0;
        
        //boolean[] used = new boolean[height/2+1]; 
        // Only needed to check up to and including height/2
        for (int i = 1; i <= height/2; i++) { 
            
            if (height - i == i) { 
                numWays += (int) woodLengthCount[i] / 2; 
            }
            else { 
                // Not the same number combo
                numWays += Math.min(woodLengthCount[i], woodLengthCount[height-i]); 
            }
        }
        return numWays; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine()); 
        StringTokenizer tok = new StringTokenizer(br.readLine()); 

        woodLengthCount = new int[4000+1]; 
        Arrays.fill(woodLengthCount, 0); 

        for (int i = 0; i < n; i++) { 
            woodLengthCount[Integer.parseInt(tok.nextToken())]++; 
        }

         // For every possible height of the fence, determine the amount of pairs that can make it
        // Store numPairs in array, and update maxLength for easy finding later

        int maxLength = 0; 
        int[] numLength = new int[1000000/2+1]; // The number of heights for a certain length
        Arrays.fill(numLength, 0); 

        for (int height = 2; height <= 4000; height++) { 
            int numBoards = findNumWays(height); 
            maxLength = Math.max(maxLength, numBoards); 
            numLength[numBoards]++; 
        }

        System.out.println(maxLength + " " + numLength[maxLength]); 
    }
}
