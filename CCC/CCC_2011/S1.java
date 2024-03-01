package CCC_2011;

import java.util.Scanner;

public class S1 {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.nextLine());
        
        int sCount = 0; 
        int tCount = 0; 

        for (int i = 0; i < n; i++) { 
            String line = in.nextLine(); 
            for (int j = 0; j < line.length(); j++) { 
                if (line.charAt(j) == 's' || line.charAt(j) == 'S') { 
                    sCount++; 
                }
                else if (line.charAt(j) == 't' || line.charAt(j) == 'T') { 
                    tCount++; 
                }
            }
        }

        if (tCount > sCount) { 
            System.out.println("English");
        }
        else { 
            System.out.println("French"); 
        }
    }
}
