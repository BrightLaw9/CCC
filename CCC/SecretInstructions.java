import java.util.ArrayList;
import java.util.Scanner; 

public class SecretInstructions { 

    public static void main(String args[]) { 

        Scanner input = new Scanner(System.in); 

        String currentNum = input.nextLine(); 
        String pastValue = ""; 

        // To save solutions to end: 
        ArrayList<String> solutions = new ArrayList<String>(); 
        while (!currentNum.equals("99999")) { 
            String first = currentNum.substring(0, 1);
            String second = currentNum.substring(1, 2);
            int num = Integer.parseInt(first) + Integer.parseInt(second); 

            String solution = "";       
            if (num % 2 == 0 && num != 0) { 
                pastValue = "right";  
                solution += "right"; 
            }
            else if (num % 2 == 1) { 
                pastValue = "left";  
                solution += "left"; 
            }
            else if (num == 0) { 
                solution += pastValue; 
            }

            solution += " " + currentNum.substring(2, 5); 
            solutions.add(solution); 
            currentNum = input.nextLine(); 
        }

        for (String soln : solutions) { 
            System.out.println(soln);
        }
    }
}