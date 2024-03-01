package CCC_2011;

import java.util.Scanner;

public class S2 {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.nextLine());

        String[] studentAns = new String[n]; 

        for (int i = 0; i < n; i++) {
            studentAns[i] = in.nextLine(); 
        }

        int correctAns = 0; 

        for (int i = 0; i < n; i++) { 
            if (studentAns[i].equals(in.nextLine())) { 
                correctAns++; 
            }
        }
        System.out.println(correctAns); 
    }
}
