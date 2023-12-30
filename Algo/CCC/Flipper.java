import java.util.Collections;
import java.util.Scanner;

public class Flipper { 

    static int[][] swap(int[][] grid, int oneRow, int oneCol, int twoRow, int twoCol) { 
        int temp = grid[oneRow][oneCol]; 
        grid[oneRow][oneCol] = grid[twoRow][twoCol]; 
        grid[twoRow][twoCol] = temp; 
        return grid; 
    }

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String operations = in.next(); 

        int[][] grid = new int[][] {{1, 2}, {3, 4}}; 

        for (int i = 0; i < operations.length(); i++) { 
            char operation = operations.charAt(i); 
            if (operation == 'H') { 
                grid = swap(grid, 0, 0, 1, 0); 
                grid = swap(grid, 0, 1, 1, 1); 
            }
            else if (operation == 'V') { 
                grid = swap(grid, 0, 0, 0, 1); 
                grid = swap(grid, 1, 0, 1, 1); 
            }
        }

        System.out.print(grid[0][0] + " " + grid[0][1] + "\n" 
                        + grid[1][0] + " " + grid[1][1]); 
    }
}
