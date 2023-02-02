import java.util.HashMap;
import java.util.Scanner;

public class NailedIt {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);
        
        int n = Integer.parseInt(in.next()); 

        // Key: length of board, value: number of that length
        HashMap<Integer, Integer> numBoardLengths = new HashMap<Integer, Integer>(); 

        int maxBoardLength = 0; 
        for (int i = 0; i < n; i++) { 
            int boardLength = Integer.parseInt(in.next()); 
            numBoardLengths.put(boardLength, 
                                numBoardLengths.getOrDefault(boardLength, 0) + 1); 
            maxBoardLength = Math.max(boardLength, maxBoardLength);
        }

        // Key: height of fence, value: number of fences that exist of height (making length)
        HashMap<Integer, Integer> numFenceLengths = new HashMap<Integer, Integer>(); 

        // Permutate all possibilites of two board combos
        for (int i = 0; i <= maxBoardLength; i++) { 
            for (int j = i; j <= maxBoardLength; j++) { 
                if (i == j) numFenceLengths.put(i + j, 
                                numFenceLengths.getOrDefault(i+j, 0) + (int) Math.floor(numBoardLengths.getOrDefault(i, 0) / 2));

                else { 
                    numFenceLengths.put(i + j, 
                            numFenceLengths.getOrDefault(i+j, 0) + Math.min(numBoardLengths.getOrDefault(i, 0), numBoardLengths.getOrDefault(j, 0)));
                }
            }
        }

        // Print
        int largestFenceLength = 0, numHeightsLongest = 0; 
        for (int count : numFenceLengths.values()) {
            if (count > largestFenceLength) { 
                largestFenceLength = count; 
                numHeightsLongest = 0; 
            } 
            if (count == largestFenceLength) numHeightsLongest++; 
        }

        System.out.println(largestFenceLength + " " + numHeightsLongest);
    }
}
