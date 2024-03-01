package CCC_2024; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1 {
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); 

        int[] sittingPeople = new int[n/2]; 

        for (int i = 0; i < n/2; i++) { 
            sittingPeople[i] = Integer.parseInt(br.readLine()); 
        }

        int numMatch = 0; 
        for (int i = 0; i < n/2; i++) { 
            // Check if current read in is equivalent to i
            if (sittingPeople[i] == Integer.parseInt(br.readLine())) { 
                numMatch++; 
            }
        }

        System.out.println(numMatch  * 2); 

    }
}
