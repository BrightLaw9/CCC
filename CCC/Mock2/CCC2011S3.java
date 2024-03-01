package Mock2;

import java.util.Scanner;

public class CCC2011S3 {

    public static boolean examine(int m, int startX, int startY, int xCell, int yCell) {
        if (m == 0) return false; 
        // Present on bottom row
        if (startX + Math.pow(5, m-1) <= xCell && xCell < startX + 4 * Math.pow(5, m-1) 
                && yCell >= startY && yCell < startY + Math.pow(5, m-1)) { 
            return true; 
        }
        // On top
        if (startX + 2 * Math.pow(5, m-1) <= xCell && xCell < startX + 3 * Math.pow(5, m-1)
            && yCell >= startY + Math.pow(5, m-1) && yCell < startY + 2 * Math.pow(5, m-1)) { 
                return true; 
        }
        return examine(m-1, (int) Math.pow(5, m-1), (int) Math.pow(5, m-1), xCell, yCell) || 
                examine(m-1, (int) (2 * Math.pow(5, m-1)), (int) (2 * Math.pow(5, m-1)), xCell, yCell) || 
                examine(m-1, (int) (3 * Math.pow(5, m-1)), (int) (Math.pow(5, m-1)), xCell, yCell); 
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int n = in.nextInt(); 
        
        for (int i = 0; i < n; i++) { 
            if (examine(in.nextInt(), 0, 0, in.nextInt(), in.nextInt())) { 
                System.out.println("crystal");
            }
            else { 
                System.out.println("empty"); 
            }
        }
    }
}
