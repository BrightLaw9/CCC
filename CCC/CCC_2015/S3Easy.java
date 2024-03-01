package CCC_2015;

import java.util.Scanner;
import java.util.TreeSet;

public class S3Easy {

    // Passed on DMOJ
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        TreeSet<Integer> openGates = new TreeSet<Integer>(); 

        int gates = Integer.parseInt(in.nextLine()); 
        int planes = Integer.parseInt(in.nextLine()); 

        for (int i = 1; i <= gates; i++) { 
            openGates.add(i); 
        }

        int planesLanded = 0; 
        for (int p = 0; p < planes; p++) { 
            int planeNum = Integer.parseInt(in.nextLine()); 
            if (openGates.first() > planeNum) {
                System.out.println(planesLanded); 
                System.exit(0);
            }  

            // returns greatest element less than planeNum - that is the most optimal landing
            int planeGate = openGates.floor(planeNum);
            openGates.remove(planeGate); 
            planesLanded++; 
        }

        System.out.println(planesLanded); 
    
    }
}
