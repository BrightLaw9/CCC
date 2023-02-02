import java.util.HashMap;
import java.util.Scanner;

public class SwapSeats { 

    public static int countChars(String substring, char searchingChar) { 
        int charsFound = 0; 
    
        for (int i = 0; i < substring.length(); i++) { 
            if (substring.charAt(i) == searchingChar) { 
                charsFound++; 
            }
        }
    
        return charsFound; 
    } 

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String curSeating = in.nextLine(); 

        HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
        letterCount.put('A', 0); 
        letterCount.put('B', 0); 

        //Traverse the curSeating once for letter count 

        for (Character seat : curSeating.toCharArray()) { 
            letterCount.put(seat, letterCount.get(seat) + 1); 
        }

        // Use sliding window
        // To get the minimum amount of swaps, see how many different character are in the window with most similar chars

        Integer numA = letterCount.get('A'); 
        int maxAFound = 0; 
        
        // Add numA chars to seating to check for circular 
        curSeating += curSeating.substring(0, numA); 
        for (int i = 0; i <= curSeating.length() - numA; i++) { 
            maxAFound = Math.max(countChars(curSeating.substring(i, i+numA), 'A'), maxAFound); 
        }

        // Check circular back around (end back to start)

        System.out.println(numA - maxAFound); 
    }
}