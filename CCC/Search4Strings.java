import java.util.Scanner;
import java.util.Arrays;

public class Search4Strings { 

    // O(n log n) -- b/c of sort otherwise O(n)

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String needle = in.nextLine(); 
        String haystack = in.nextLine(); 

        // Sorted sliding window method 
        // For every "window" in haystack char array, see if the that sorted array is equal to needle array

        char[] haystackChars = haystack.toCharArray(); 

        char[] sortedNeedle = needle.toCharArray(); 
        Arrays.sort(sortedNeedle);
        System.out.println(sortedNeedle); 

        int foundPermutations = 0; 

        // Only need to check until the needle window reaches end
        for (int i = 0; i <= haystack.length()-needle.length(); i++) { 
            // Get & Sort window
            char[] window = Arrays.copyOfRange(haystackChars, i, i+needle.length()); 
            Arrays.sort(window); 
            System.out.println(window); 

            // Need to use valueOf() in order to get array's contents, not memory location
            if (String.valueOf(window).equals((String.valueOf(sortedNeedle)))) { 
                foundPermutations++; 
            }
        }
        
        System.out.println(foundPermutations); 
        in.close(); 
    }
}