import java.util.ArrayList;
import java.util.Scanner;

public class SumGame {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        ArrayList<Integer> swifts = new ArrayList<Integer>(); 

        ArrayList<Integer> semaphores = new ArrayList<Integer>(); 

        for (int i = 0; i < n; i++) { 
            swifts.add(Integer.parseInt(in.next())); 
        }

        for (int j = 0; j < n; j++) { 
            semaphores.add(Integer.parseInt(in.next())); 
        }

        int swiftTotal = 0; 
        int semaphoresTotal = 0; 

        int largestSameGame = 0; 

        for (int game = 0; game < n; game++) { 
            swiftTotal += swifts.get(game); 
            semaphoresTotal += semaphores.get(game); 

            if (swiftTotal == semaphoresTotal) largestSameGame = game+1; // 1-indexed 
        }

        System.out.println(largestSameGame);


    }
}
