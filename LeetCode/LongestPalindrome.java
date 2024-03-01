package LeetCode;


// Passed
public class LongestPalindrome {
    public static String longestPalindrome(String s) {

        if (s.length() <= 1) return s;
        
        boolean[][] dp = new boolean[s.length()][s.length()]; 

        int maxLen = 1; 
        int start = 0;
        int end = 0; 

        for (int i = 0; i < s.length(); i++) { 
            dp[i][i] = true; // single length strings are palindromes
            // Every time this loop runs, checks for palindromes up to i; so when the greater i is reached, all smaller combinations of j to i would be checked
            for (int j = 0; j < i; j++) {
                // Expand from outside in, the inside is a recursive case that should be covered before
                // j represent the start index, i is end, if equal, then expand out palindrome
                // If a 2 letter or less string with j and i equal, then palindrome
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j+1][i-1])) { 
                    dp[j][i] = true; 
                    if (i - j + 1 > maxLen) { 
                        // Difference in end to start is greater than maxLen (add 1 as string length start at 1, adjust for zero based index)
                        maxLen = i - j + 1; 
                        start = j; 
                        end = i; 
                    }
                }
            }
        }
        return s.substring(start, end + 1); // Exclusive of last so add 1
    }

    public static void main(String[] args) { 
        //System.out.println(longestPalindrome("abacaac"));
        System.out.println(longestPalindrome("cabacaac"));
    }
}
