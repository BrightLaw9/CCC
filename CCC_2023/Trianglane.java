import java.util.Scanner;

public class Trianglane { 

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 
        
        int numColumns = Integer.parseInt(in.next()); 

        boolean[][] tileWet = new boolean[2][numColumns]; 

        for (int count = 0; count < 2; count++) { 
            for (int i = 0; i < numColumns; i++) { 
                int num = Integer.parseInt(in.next()); 
                if (num == 1) tileWet[count][i] = true;
                else tileWet[count][i] = false;
            }
        }

        int totalTape = 0; 

        for (int count = 0; count < 2; count++) { 
            for (int i = 0; i < numColumns; i++) { 
                if (tileWet[count][i]) { 
                    totalTape += 3; 
                    if (numColumns-1 > i && tileWet[count][i+1]) totalTape -= 1; 
                
                    if (i > 0 && tileWet[count][i-1]) totalTape -= 1; 

                    if (count == 0 && tileWet[count+1][i] && i % 2 == 0) totalTape -= 1; 
                    
                    if (count == 1 && tileWet[count-1][i] && i % 2 == 0) totalTape -= 1; 
                }
            }
        }

        System.out.println(totalTape); 
        
    }
}