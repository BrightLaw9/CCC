package CCC_2012;

import java.util.HashMap;
import java.util.Scanner;

public class S2 {

    public static HashMap<String, Integer> romanLookup; 

    public static int calculate(String num) { 
        return Integer.parseInt(String.valueOf(num.charAt(0))) * romanLookup.get(String.valueOf(num.charAt(1))); 
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String aromaticNum = in.nextLine(); 

        romanLookup = new HashMap<String, Integer>();

        romanLookup.put("I", 1);
        romanLookup.put("V", 5);
        romanLookup.put("X", 10);
        romanLookup.put("L", 50);
        romanLookup.put("C", 100);
        romanLookup.put("D", 500);
        romanLookup.put("M", 1000); 

        int ans = 0; 
        
        for (int i = 0; i < aromaticNum.length(); i += 2) { 
            int calc = calculate(aromaticNum.substring(i, i+2)); 
            if (i + 2 == aromaticNum.length()) { 
                // Add last value
                ans += calc;  
            }
            else if (romanLookup.get(String.valueOf(aromaticNum.charAt(i+3))) > romanLookup.get(String.valueOf(aromaticNum.charAt(i+1)))) { 
                ans -= calc; 
            }
            else {
                ans += calc; 
            }
        }

        System.out.println(ans); 
    }
}
