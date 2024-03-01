package CCC_2022;

import java.util.Scanner;

public class CCC2022S4 {
    
    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in);

        int numPoints = Integer.parseInt(in.next()); 
        int circum = Integer.parseInt(in.next());

        int[] countAtLocation = new int[circum]; 

        for (int i = 0; i < numPoints; i++) { 
            int location = Integer.parseInt(in.next()); 

            countAtLocation[location]++; 
        }

        
    }
}
