import java.util.List; 
import java.util.Scanner; 

// Passed on CCC Grader

public class Good4and5 { 


    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in); 
        String in = input.nextLine();
        int inNum = Integer.parseInt(in); 
        int amtSubtract = 4; 
        int arrangements = 0; 
        
        if (inNum % 5 == 0) { 
            arrangements++; 
        }
        if (inNum % 4 == 0) { 
            arrangements++; 
        }

        while (inNum-amtSubtract > 0) { 
            inNum -= amtSubtract;
            if (inNum % 5 == 0 && amtSubtract == 4) { 
                arrangements++;
                amtSubtract = 4; 
                inNum -= amtSubtract;
            }
            if (inNum % 4 == 0 && amtSubtract == 5) { 
                arrangements++; 
                amtSubtract = 5;
                inNum -= amtSubtract;
            }
        }

        
        System.out.println(arrangements); 
        
    }
}