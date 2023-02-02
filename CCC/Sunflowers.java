import java.util.Scanner;

public class Sunflowers {
    

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);
        
        int n = Integer.parseInt(in.next());
        
        int[][] grid = new int[n][n]; 

        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < n; j++) { 
                grid[i][j] = Integer.parseInt(in.next()); 
            }
        }

        boolean horizontalIncrease = true; 
        boolean verticalIncrease = true; 

        if (grid[0][0] > grid[1][0]) verticalIncrease = false; 
        if (grid[0][0] > grid[0][1]) horizontalIncrease = false; 

        int[][] ans = new int[n][n];

        // Horizontal and vertical increase should be true

        if (!horizontalIncrease && verticalIncrease) { 
            //Rotate 90 counterclockwise 
            // Left to right, fill from bottom up
            for (int co = 0; co < n; co++) {
                for (int i = 0; i < n; i++) 
                ans[i][co] = grid[co][n-i-1];
            }
           
        }
        else if (!verticalIncrease && horizontalIncrease) { 
            //Rotate 90 clockwise
            for (int co = 0; co < n; co++) {
                for (int i = 0; i < n; i++) 
                ans[co][n-i-1] = grid[i][co];
            }
        }
        else if (!horizontalIncrease && !verticalIncrease) { 
            // Rotate 180
            for (int co = 0; co < n; co++) {
                for (int i = 0; i < n; i++) 
                ans[n-co-1][n-i-1] = grid[i][co];
            }
        }

        else if (horizontalIncrease && verticalIncrease) { 
            // Normal case
            for (int co = 0; co < n; co++) {
                for (int i = 0; i < n; i++) 
                ans[i][co] = grid[i][co];
            } 
        }

        for (int c = 0; c < n; c++) { 
            for (int r = 0; r < n; r++) { 
                System.out.print(ans[c][r] + " "); 
            }
            System.out.println(); 
        }
    }
}
