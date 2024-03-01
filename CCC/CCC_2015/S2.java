package CCC_2015;

import java.util.Scanner;

public class S2 {

    // Passed on DMOJ

    public static int convertSizeToInt(String jerseySize) { 
        if (jerseySize.equals("S")) return 0;
        else if (jerseySize.equals("M")) return 1;
        return 2; 
    }
    
    public static void main(String[] args) { 
        // Idea: if a jersey satisfies one, give it to them
        // If a large jersey exists for someone who requested med, give to them;
        // It does not matter if others after who requested large are not satisfied b/c there is only one of each jersey num

        Scanner in = new Scanner(System.in); 

        int numJerseys = Integer.parseInt(in.nextLine()); 
        int numAth = Integer.parseInt(in.nextLine()); 

        int[] jerseyStatus = new int[numJerseys+1]; 

        for (int j = 1; j <= numJerseys; j++) { 
            String jerseySize = in.nextLine(); 
            jerseyStatus[j] = convertSizeToInt(jerseySize);
        }

        int numSatisfied = 0; 
        for (int a = 1; a <= numAth; a++) { 
            String[] req = in.nextLine().split(" "); 
            int reqNum = Integer.parseInt(req[1]); 
            if (jerseyStatus[reqNum] >= convertSizeToInt(req[0])) { 
                // Jersey has to be greater than or equal to that of request
                numSatisfied++; 
                jerseyStatus[reqNum] = -1; 
            } 
        }
        System.out.println(numSatisfied);
    }
}
