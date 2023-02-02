import java.util.Arrays;
import java.util.Scanner;

public class VoroniVillages {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 
        
        int numVillages = Integer.parseInt(in.next()); 
        
        int[] villages = new int[numVillages]; 

        for (int i = 0; i < numVillages; i++) { 
            villages[i] = Integer.parseInt(in.next());
        }

        Arrays.sort(villages);

        double smallest = Integer.MAX_VALUE; 

        for (int v = 1; v < numVillages-1; v++) {
            // Distance = larger - smaller
            smallest = Math.min((villages[v] - villages[v-1]) / 2.0 + (villages[v+1] - villages[v]) / 2.0, smallest);  
        }

        System.out.println(String.format("%.1f", smallest));


    }
}
