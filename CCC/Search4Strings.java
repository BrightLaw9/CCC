import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet; 

public class Search4Strings { 

    // O(n log n) -- b/c of sort otherwise O(n)

    public static final int base = 122; // 122 ASCII max for alphabet
    public static final int mod = 101; 

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String needle = in.nextLine(); 
        String haystack = in.nextLine(); 

        int soln = 1; 

        // Sorted sliding window method 
        // For every "window" in haystack char array, see if the that sorted array is equal to needle array

        HashSet<String> distinctPermu = new HashSet<String>(); 

        if (soln == 0) { 

            char[] haystackChars = haystack.toCharArray(); 

            char[] sortedNeedle = needle.toCharArray(); 
            Arrays.sort(sortedNeedle);
            //System.out.println(sortedNeedle); 


            // Only need to check until the needle window reaches end
            for (int i = 0; i <= haystack.length()-needle.length(); i++) { 
                // Get & Sort window
                char[] window = Arrays.copyOfRange(haystackChars, i, i+needle.length()); 
                char[] unsortedWindow = Arrays.copyOf(window, window.length); 
                Arrays.sort(window); 
                //System.out.println(window); 

                // Need to use valueOf() in order to get array's contents, not memory location
                if (String.valueOf(window).equals((String.valueOf(sortedNeedle)))) { 
                    distinctPermu.add(String.valueOf(unsortedWindow)); 
                }
            }
            System.out.println(distinctPermu.size()); 
        }
        else { 
            // Faster than above but TLE still
            // Rabin-Karp pattern matching algorithm 

            int base_minus_one = (int) Math.pow(base, needle.length() - 1); 

            int patternHash = 0; 
            int rollingHash = 0; 

            // Calculate pattern hash
            char[] needleChars = needle.toCharArray(); 
            for (char needleChar : needleChars) { 
                //patternHash = (patternHash * base + (int) needleChar) % mod; // Change to ASCII
                patternHash += ((int) needleChar * base) % mod; 
            }
            //System.out.println("pattern hash: " + patternHash); 
            Arrays.sort(needleChars);
            // First window initial hash
            if (haystack.length() >= needle.length()) { 
            char[] firstWindow = haystack.substring(0, 0 + needle.length()).toCharArray(); 
            Arrays.sort(firstWindow); 
            for (char hayChar :  firstWindow) {
                //rollingHash = (rollingHash * base + (int) hayChar) % mod;
                rollingHash += ((int) hayChar * base) % mod;
            }
            // Slide over haystack string, checking if same hash for every window
            for (int s = 0; s <= haystack.length() - needle.length(); s++) { 
                if (rollingHash == patternHash) { 
                    // Check if characters actually match in permutation - could be false collision
                    // Hashing of the characters allows for any permutation, as the last char is just added to sum (commutative)
                    char[] haystackWindow = haystack.substring(s, s + needle.length()).toCharArray(); 
                    char[] unsortedWindow = Arrays.copyOf(haystackWindow, haystackWindow.length); 
                    Arrays.sort(haystackWindow);

                    if (String.valueOf(haystackWindow).equals(String.valueOf(needleChars))) distinctPermu.add(String.valueOf(unsortedWindow));
                }
                if (s < haystack.length() - needle.length()) { 
                    // Remove leading of string window; add trailing to remove forward
                    //rollingHash = (((rollingHash - (int) haystack.charAt(s)) * base) + (int) haystack.charAt(s + needle.length())) % mod; 
                    rollingHash -= ((int) haystack.charAt(s) * base) % mod; 
                    rollingHash += ((int) haystack.charAt(s + needle.length()) * base) % mod;  
                    //System.out.println(rollingHash); 
                    if (rollingHash < 0) rollingHash += mod; // Prevent negative modulo!
                }
            }
            // for (String x : distinctPermu) { 
            //     System.out.println(x);
            // }
        }
        System.out.println(distinctPermu.size()); 
    }
        in.close(); 
    }
}