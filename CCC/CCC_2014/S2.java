package CCC_2014;

import java.util.HashMap;
import java.util.Scanner;

public class S2 {
    
    public static void main(String[] args) { 
        
        Scanner in = new Scanner(System.in); 

        int numPartners = Integer.parseInt(in.nextLine());

        String[] firstLine = in.nextLine().split(" ");
        String[] secondLine = in.nextLine().split(" "); 

        HashMap<String, String> pairing = new HashMap<String, String>(); 

        for (int i = 0; i < firstLine.length; i++) { 
            if (!pairing.containsKey(firstLine[i]) && !pairing.containsKey(secondLine[i])) { 
                // Only when both pairs don't all ready have a match can they be paired up
                pairing.put(firstLine[i], secondLine[i]); // Two sided relationship
                pairing.put(secondLine[i], firstLine[i]);
            }

            if (!pairing.get(firstLine[i]).equals(secondLine[i]) 
                || firstLine[i].equals(secondLine[i])) { 
                System.out.println("bad"); 
                System.exit(0); 
            }
        }
        System.out.println("good"); 
    }
}
