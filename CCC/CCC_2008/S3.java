package CCC_2008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S3 {

    static int r; static int c; 

    public static void add(boolean[][] visited, Queue<int[]> queue, int[] toAdd, int[] cur, int[][] dist, char[][] grid) { 
        if (toAdd[0] >= 0 && toAdd[0] < r && toAdd[1] >= 0 && toAdd[1] < c && 
                !visited[toAdd[0]][toAdd[1]] && grid[toAdd[0]][toAdd[1]] != '*') { 
            queue.add(toAdd);
            visited[toAdd[0]][toAdd[1]] = true;
            dist[toAdd[0]][toAdd[1]] = Math.min(dist[toAdd[0]][toAdd[1]], dist[cur[0]][cur[1]] + 1); 
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int t = Integer.parseInt(br.readLine()); 

        for (int test = 0; test < t; test++)  {
            r = Integer.parseInt(br.readLine());
            c = Integer.parseInt(br.readLine()); 

            char[][] grid = new char[r][c];

            for (int i = 0; i < r; i++) { 
                String data = br.readLine(); 
                for (int j = 0; j < c; j++) { 
                    grid[i][j] = data.charAt(j); 
                }
            }

            // Perform BFS to see if can reach from northwest to southeast
            Queue<int[]> queue = new LinkedList<int[]>(); 
            boolean[][] visited = new boolean[r][c]; 
            int[][] dist = new int[r][c]; 

            for (int i = 0; i < r; i++) { 
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < r; i++) { 
                Arrays.fill(visited[i], false);
            }

            // Start at 0, 0, end at r-1, c-1
            queue.add(new int[] {0, 0}); 
            visited[0][0] = true; 
            dist[0][0] = 1; 

            boolean possible = false;

            while (!queue.isEmpty()) { 
                int[] cell = queue.poll();
                char symbol = grid[cell[0]][cell[1]]; 

                if (cell[0] == r-1 && cell[1] == c-1) { 
                    System.out.println(dist[r-1][c-1]); 
                    possible = true; 
                }
                
                if (symbol == '+') { 
                    add(visited, queue, new int[] {cell[0] - 1, cell[1]}, cell, dist, grid);
                    add(visited, queue, new int[] {cell[0] + 1, cell[1]}, cell, dist, grid);
                    add(visited, queue, new int[] {cell[0], cell[1] + 1}, cell, dist, grid);
                    add(visited, queue, new int[] {cell[0], cell[1] - 1}, cell, dist, grid);
                }
                else if (symbol == '-') { 
                    add(visited, queue, new int[] {cell[0], cell[1] - 1}, cell, dist, grid);
                    add(visited, queue, new int[] {cell[0], cell[1] + 1}, cell, dist, grid);
                }
                else if (symbol == '|') { 
                    add(visited, queue, new int[] {cell[0] + 1, cell[1]}, cell, dist, grid);
                    add(visited, queue, new int[] {cell[0] - 1, cell[1]}, cell, dist, grid); 
                }
            }

            if (!possible) System.out.println(-1); 
        }
    }
}
