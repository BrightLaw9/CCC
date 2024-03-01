package CCC_2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S2 {
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok = new StringTokenizer(br.readLine()); 
        int numStrings = Integer.parseInt(tok.nextToken()); 
        int lengthString = Integer.parseInt(tok.nextToken()); 

        for (int string = 0; string < numStrings; string++) { 
            String data = br.readLine(); 
            HashMap<Character, Integer> count = new HashMap<Character, Integer>(); 
            for (int i = 0; i < lengthString; i++) { 
                count.put(data.charAt(i), count.getOrDefault(data.charAt(i), 0) + 1); 
            }

            boolean alternateEven = count.get(data.charAt(0)) > 1; // heavy letters on the even indexes
            boolean passedEven = alternateEven;  
            boolean alternateOdd = count.get(data.charAt(1)) > 1;  // heavy letters on odd indexes
            boolean passedOdd = alternateOdd; 
            //System.out.println(passedOdd); 
            for (int i = 0; i < lengthString; i++) { 
                if (alternateEven) { 
                    if (i % 2 == 0 && count.get(data.charAt(i)) == 1) passedEven = false; 
                    else if (i % 2 == 1 && count.get(data.charAt(i)) > 1) passedEven = false; 
                }
                if (alternateOdd)  {
                    if (i % 2 == 1 && count.get(data.charAt(i)) == 1) passedOdd = false; 
                    else if (i % 2 == 0 && count.get(data.charAt(i)) > 1) passedOdd = false; 
                }
            }
            if (passedEven || passedOdd) System.out.println("T"); 
            else System.out.println("F"); 
        }
    }
}
