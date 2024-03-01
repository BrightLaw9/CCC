package CCC_2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S2EscapeRoom {

    // Only passed 5 of 7

    static int rows; static int cols; 

    public static boolean withinBounds(int one, int two) { 
        return (one < rows && two < cols); 
    }

    public static void addToQueue(Queue<int[]> queue, int[] toAdd, boolean[][] visited) { 
        if (withinBounds(toAdd[0] - 1, toAdd[1] -1) && !visited[toAdd[0] - 1][toAdd[1] - 1]) queue.add(toAdd); 
    }

    public static void computeMultipliedFactors(ArrayList<Integer> factors, Queue<int[]> queue, int total, boolean[][] visited) { 
        for (int i = 1; i < factors.size() - 1; i++) { 
            if (factors.get(i) == factors.get(i-1)) continue; // No same factor combo repeated 
            int totCopy = total / factors.get(i-1);
            int acc = factors.get(i-1); 
           // Starting point 
            for (int j = i; j < factors.size() - 2 + i - 1; j++) { 
                if (j >= factors.size()) break; 
                // Window size
                // if (factors.get(j) != factors.get(j-1)) { 
                    // Eliminate two same multiple which was all ready covered
                    acc *= factors.get(j);
                    totCopy /= factors.get(j);  
                    addToQueue(queue, new int[] {acc, totCopy}, visited);
                    addToQueue(queue, new int[] {totCopy, acc}, visited);    
                // }
            }
        }
    }

    public static void computePairs(int n, Queue<int[]> queue, boolean[][] visited) { 
        ArrayList<Integer> factors = new ArrayList<Integer>(); 
        int ans = n; 
        int fac = 1; 
        while (n % 2 == 0) {
            factors.add(2);  
            n /= 2; 
            fac *= 2; 
            addToQueue(queue, new int[] {n, fac}, visited);   
            addToQueue(queue, new int[] {fac, n}, visited);   
        }
        
        // N now must be odd, += to only check for odd numbers
        for (int i = 3; i <= Math.sqrt(ans); i += 2) { 
            int temp = ans; 
            fac = 1; 
            while (n % i == 0) { 
                factors.add(i);
                n /= i; 
                temp /= i; 
                fac *= i; 
                addToQueue(queue, new int[] {fac, temp}, visited);   
                addToQueue(queue, new int[] {temp, fac}, visited);  
            }
        }

        // If n is prime greater than two, then not checked by above conditions (sqrt prevents)
        // if (n > 2) { 
            addToQueue(queue, new int[] {1, ans}, visited);   
            addToQueue(queue, new int[] {ans, 1}, visited);   
            
            computeMultipliedFactors(factors, queue, ans, visited);
        // }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        rows = Integer.parseInt(in.next()); 
        cols = Integer.parseInt(in.next()); 

        int[][] gridNums = new int[rows][cols]; 

        for (int i = 0; i < rows; i++) { 
            for (int j = 0; j < cols; j++) {
                gridNums[i][j] = Integer.parseInt(in.next()); 
            }
        }

        Queue<int[]> queue = new LinkedList<int[]>(); 
        
        boolean[][] visited = new boolean[rows][cols]; 
        visited[0][0] = true; 
        computePairs(gridNums[0][0], queue, visited);

        while (!queue.isEmpty()) { 
            int[] cell = queue.poll(); 

            if (cell[0] == rows && cell[1] == cols) { 
                System.out.println("yes"); 
                System.exit(0); 
            }

            visited[cell[0] - 1][cell[1] - 1] = true; 
            computePairs(gridNums[cell[0] - 1][cell[1] - 1], queue, visited);
        }

        System.out.println("no"); 
        // for (int[] q : queue) { 
        //     System.out.println(q[0] + ", " + q[1]); 
        // } 
        
    }
}
