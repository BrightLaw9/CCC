import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Ragaman {
    
    public static void main(String... args) { 
        Scanner in = new Scanner(System.in); 
        
        String one = in.nextLine(); 
        String two = in.nextLine(); 

        char[] oneChars = one.toCharArray(); 
        char[] twoChars = two.toCharArray(); 

        Arrays.sort(oneChars);
        Arrays.sort(twoChars);

        char ch = twoChars[0];
        int count = 0; 

        while (ch == '*') { 
            // * will be smaller in ASCII value than letters
            // Count will act as the number of '*' and index in char array 
            count++; 
            if (count == twoChars.length) break;
            ch = twoChars[count]; 
        }

        twoChars = Arrays.copyOfRange(twoChars, count, twoChars.length);
        
        char result = 'A';

        HashMap<Character, Integer> numLetters = new HashMap<Character, Integer>();

        char twoIdx = 0;

        for (int i = 0; i < oneChars.length; i++) { 
            if (twoIdx == twoChars.length) break; // All wildcards will be used for rest

            if (oneChars[i] != twoChars[twoIdx]) { 
                if (count == 0) { 
                    result = 'N';
                    break;
                }
                // Use wildcard and move current letter right
                count--;
            }
            else twoIdx++; 
        }

        System.out.println(result);
        
    }
}
