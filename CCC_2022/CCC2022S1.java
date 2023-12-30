package CCC_2022;

import java.util.Scanner;

public class CCC2022S1 {

    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.nextLine()); 

        int numWays = 0; 

        if (n % 5 == 0) numWays++; 
        if (n % 4 == 0) numWays++; 

        for (int i = 1; i < (Math.floor(n/5) + (n % 5 >= 3 ? 1 : 0)); i++) { 
            if ((n - (5 * i)) % 4 == 0) { 
                numWays++; 
            }
        }

        System.out.println(numWays);
    }
}
