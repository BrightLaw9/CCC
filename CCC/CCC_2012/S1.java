package CCC_2012;

import java.math.BigInteger;
import java.util.Scanner;

public class S1 {

    public static long factorial(int lower, int higher) { 
        //if (n == 0) return 0; 
        long ans = 1; 
        // Lower usually cancels out
        for (int i = lower+1; i <= higher; i++) { 
            ans *= i; 
        }
        return ans;
    }
    
    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in);
        
        int goalScorerNum = Integer.parseInt(in.next()); 

        // Note the person scoring the goal has to be part of combination

        // So only left with (n-1) options, with 3 choices
        // Combinatorics formula C(n, r) = n!/((n-r)!r!)
        // Simplified by only multiplying the n-r to n part
        // 3! = 6

        if (goalScorerNum < 4) { 
            System.out.println(0); 
            System.exit(0);
        }

        else if (goalScorerNum == 4) { 
            System.out.println(1); 
            System.exit(0); 
        }

        System.out.println((factorial(goalScorerNum - 1 - 3, goalScorerNum - 1) / 6)); 
    }

}
