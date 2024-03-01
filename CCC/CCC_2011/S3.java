package CCC_2011;

import java.util.Scanner;

public class S3 {

    // Passed 5 of 5 on DMOJ

    public static int computeShift(int m) { 
        return (int) (Math.pow(5, m-2));  
    }

    public static boolean check(int m, int curLocX, int curLocY, int targetX, int targetY) { 
        if (m == 0) return false; 

        // Fit in lower level blocks
        if (targetX >= curLocX && targetX <= (curLocX + 2 * Math.pow(5, m-1)) 
            && targetY >= curLocY && targetY < (curLocY + Math.pow(5, m-1))) { 
                //System.out.println("in here"); 
            return true; 
        }  
        // Upper middle block
        else if (targetX >= (curLocX + Math.pow(5, m-1)) && targetX < (curLocX + 2 * Math.pow(5, m-1)) 
                    && targetY >= (curLocY + Math.pow(5, m-1)) && targetY < (curLocY + 2 * Math.pow(5, m-1))) { 
            return true; 
        }
        
        return check(m-1, curLocX + computeShift(m), (int) (curLocY + Math.pow(5, m-1)), targetX, targetY) || // Left
                check(m-1, (int) (curLocX + Math.pow(5, m-1) + computeShift(m)), (int) (curLocY + 2 * Math.pow(5, m-1)), targetX, targetY) || // Upper center
                check(m-1, (int) (curLocX + 2 * Math.pow(5, m-1) + computeShift(m)), (int) (curLocY + Math.pow(5, m-1)), targetX, targetY); // Right
    }   

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int testcases = Integer.parseInt(in.nextLine()); 
 
        for (int i = 0; i < testcases; i++) { 
            int m = Integer.parseInt(in.next()); 
            int x = Integer.parseInt(in.next());
            int y = Integer.parseInt(in.next());
            if (check(m, (int) Math.pow(5, m-1), 0, x, y)) { 
                System.out.println("crystal"); 
            }
            else {
                System.out.println("empty"); 
            }
        }
    }
}
