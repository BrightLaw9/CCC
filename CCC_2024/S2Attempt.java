package CCC_2024; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S2Attempt {
    
    // Buggy
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok = new StringTokenizer(br.readLine()); 
        int numStrings = Integer.parseInt(tok.nextToken()); 
        int lengthString = Integer.parseInt(tok.nextToken()); 

        for (int string = 0; string < numStrings; string++) { 
            String data = br.readLine(); 
            HashMap<Character, Boolean> duplicate = new HashMap<Character, Boolean>();
            boolean passedEven = false;  
            boolean passedOdd = false; 
            boolean alternateEven = false; 
            boolean alternateOdd = false; 
            duplicate.put(data.charAt(0), true);
            duplicate.put(data.charAt(1), true); 
            if (lengthString > 2 && data.charAt(0) == data.charAt(2)) { 
                alternateEven = true; 
                passedEven = true;  
            }
            if (lengthString > 3 && data.charAt(1) == data.charAt(3)) { 
                alternateOdd = true;
                passedOdd = true; 
            }
            if (!alternateEven && !alternateOdd) { 
                System.out.println("F"); 
                continue; 
            }
            for (int i = 2; i < lengthString; i++) { 
                if (alternateEven) { 
                    if (i % 2 == 0 && !duplicate.getOrDefault(data.charAt(i), false)) {
                        passedEven = false;
                        //break; 
                    }
                    else if (i % 2 == 1 && duplicate.getOrDefault(data.charAt(i), false)) { 
                        passedEven = false; 
                        //break; 
                    }
                }
                if (alternateOdd) { 
                    if (i % 2 == 1 && !duplicate.getOrDefault(data.charAt(i), false)) {
                        passedOdd = false;
                        //break; 
                    } 
                    else if (i % 2 == 0 && duplicate.getOrDefault(data.charAt(i), false)) { 
                        passedOdd = false; 
                        //break; 
                    }
                }
                duplicate.put(data.charAt(i), true); 
                //if (!passedEven && !passedOdd) break; 
            }
            if ((passedEven && !passedOdd) || (passedOdd && !passedEven)) System.out.println("T"); 
            else System.out.println("F"); 
        }
    }
}
