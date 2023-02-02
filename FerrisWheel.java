import java.util.Arrays;
import java.util.Scanner;

public class FerrisWheel { 

    public static void main(String args[]) { 
        Scanner input = new Scanner(System.in); 

        int n = Integer.parseInt(input.next()); 
        int maxWeight = Integer.parseInt(input.next());
        
        int[] childrenWeights = new int[n]; 

        for (int i = 0; i < n; i++) { 
            childrenWeights[i] = Integer.parseInt(input.next()); 
        }

        Arrays.sort(childrenWeights);

        // Greedy method for putting children in carts

        int cartWeight = childrenWeights[0]; 
        int count = 0; 
        int numCarts = 1; 

        while (count < childrenWeights.length-1) {

        // Each iteration of loop will be seeing if the next passenger can fit
        while (cartWeight <= maxWeight) { 
            count++; 
            cartWeight += childrenWeights[count]; 
        }
            cartWeight = childrenWeights[count]; // Reset the cart to the next children
            numCarts++; 
    }
        System.out.println(numCarts);
    }
}