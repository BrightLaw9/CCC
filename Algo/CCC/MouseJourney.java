import java.util.Scanner;

public class MouseJourney { 

    static int totalPaths = 0; 

    static boolean[][] visited;

    public static boolean[][] clearVisited(boolean[][] visited) { 
        for (int r = 0; r < visited.length; r++) { 
            for (int c = 0; c < visited[0].length; c++) { 
                visited[r][c] = false;
            }
        }
        return visited; 
    }

    public static void findPaths(boolean[][] grid, int r, int c) { 
        if (r == grid.length-1 && c == grid[0].length-1) { 
            totalPaths += 1;  
            visited = clearVisited(visited); 
            return;
        }
        visited[r][c] = true; 
        if (!grid[r][c]) 
        {   
            // Can't move left or up
            if (r < grid.length-1 && !visited[r+1][c]) findPaths(grid, r+1, c); 
            if (c < grid[0].length-1 && !visited[r][c+1]) findPaths(grid, r, c+1);
        }
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int r = Integer.parseInt(in.next()); 
        int c = Integer.parseInt(in.next()); 

        boolean[][] containsCats = new boolean[r][c]; 

        int numCats = Integer.parseInt(in.next());

        for (int i = 0; i < numCats; i++) { 
            int row = Integer.parseInt(in.next()) - 1; 
            int col = Integer.parseInt(in.next()) - 1; 

            containsCats[row][col] = true; 
        }
        
        visited = new boolean[r][c]; 
        // DFS to see different possible paths, adding a total number paths 
        findPaths(containsCats, 0, 0);

        System.out.println(totalPaths); 
    }  
}