package CCC_2016;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Tandem {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);

        int question = Integer.parseInt(in.next()); 

        int n = Integer.parseInt(in.next()); 

        int[] dmojistan = new int[n]; 
        int[] pegland = new int[n];
        
        for (int i = 0; i < n; i++) { 
            dmojistan[i] = Integer.parseInt(in.next());
        }

        for (int i = 0; i < n; i++) { 
            pegland[i] = Integer.parseInt(in.next());
        }

        Arrays.sort(dmojistan); 
        Arrays.sort(pegland);

        int total = 0; 

        if (question == 1) { 
            // Minimum
            for (int i = 0; i < n; i++) { 
                total += Math.max(dmojistan[i], pegland[i]);
            }
        }
        else if (question == 2) { 
            // Maximum -- binary search pairing approach, the maximums will be from the top down of sorted
            for (int i = 0; i < n; i++) { 
                total += Math.max(dmojistan[i], pegland[n-1-i]);
            }
        }

        System.out.println(total);
    }
}
