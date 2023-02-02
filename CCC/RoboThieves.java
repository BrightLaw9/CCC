import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class RoboThieves {

    static char[][] grid; 
    static int width = 0, height = 0;

    static HashSet<String> visited = new HashSet<String>();

    static boolean firstCalculated = false; 

    static int calculateValidMin(int val, int curAns) { 
        if (val != -1) { 
            firstCalculated = true;
            return val; 
        }
        return Math.min(val, curAns);
    }

    static boolean visited(int rowCell, int colCell) { 
        return visited.contains(String.format("%s, %s", rowCell, colCell)); 
    }

    static void addVisited(int rowCell, int colCell) { 
        visited.add(String.format("%s, %s", rowCell, colCell)); 
    }

    static int[] findSymbol(char symbol) { 
        for (int c = 0; c < height; c++) { 
            for (int r = 0; r < width; r++) { 
                if (grid[c][r] == symbol) { 
                    return new int[] {r, c}; 
                }
            }
        }
        return new int[0]; 
    }

    static int dfs(int rowCell, int colCell, int steps, 
                    int targetRow, int targetCol) {
        
        if (grid[colCell][rowCell] != 'W') 
        {
            System.out.println(String.format("r: %s, c: %s, tr: %s, tc: %s", rowCell, colCell, targetRow, targetCol));
            
            if (rowCell == targetRow && colCell == targetCol) return steps;

            int ans = -1;
            System.out.println(firstCalculated);
        
            if (rowCell < width-1 && !visited(rowCell+1, colCell)) {
                addVisited(rowCell+1, colCell);
                return calculateValidMin(dfs(rowCell+1, colCell, steps+1, targetRow, targetCol), ans); 
            }
            if (rowCell > 0 && !visited(rowCell-1, colCell)) { 
                addVisited(rowCell-1, colCell);
                return calculateValidMin(dfs(rowCell-1, colCell, steps+1, targetRow, targetCol), ans); 
            }
            if (colCell < height- 1 && !visited(rowCell, colCell+1)) {
                addVisited(rowCell, colCell+1);
                return calculateValidMin(dfs(rowCell, colCell+1, steps+1, targetRow, targetCol), ans); 
            }
            if (colCell > 0 && !visited(rowCell, colCell-1)) { 
                addVisited(rowCell, colCell-1);
                return calculateValidMin(dfs(rowCell, colCell-1, steps+1, targetRow, targetCol), ans);  
            }
            return ans; 
        }
        return -1;
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        height = Integer.parseInt(in.next());  
        width = Integer.parseInt(in.next());

        grid = new char[height][width]; 

        for (int h = 0; h < height; h++) { 
            String row = in.next(); 
            for (int w = 0; w < width; w++) { 
                grid[h][w] = row.charAt(w);
            }
        }

        int[] robotPose = findSymbol('S'); 

        for (int r = 0; r < width; r++) { 
            for (int c = 0; c < height; c++) { 
                if (grid[c][r] == '.') {
                    System.out.println(
                        dfs(robotPose[0], robotPose[1], 0, r, c)
                    );
                    firstCalculated = false;
                    visited.clear();
                }
            }
        }
    
    }
}
