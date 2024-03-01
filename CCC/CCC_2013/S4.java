package CCC_2013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class S4 {

    // Passed 5/7 on DMOJ

    public static int findRoot(int[] subsets, int num) { 
        if (subsets[num] == num) return subsets[num]; 

        return findRoot(subsets, subsets[num]); // Cannot use path compression
    }
    
    static boolean[] visited; 

    public static void searchForNum(ArrayList<ArrayList<Integer>> adjList, int one, int two, int curNum, boolean firstFound) { 
        //System.out.println(curNum); 
        boolean firstFoundCopy = firstFound; 
        if (curNum == one) { 
            if (!firstFoundCopy) firstFoundCopy = true; 
            else {
                System.out.println("no"); // Smaller all ready found
                System.exit(0);
            }
        }
        if (curNum == two) { 
            if (!firstFoundCopy) firstFoundCopy = true; 
            else { 
                System.out.println("yes"); // Greater all ready found 
                System.exit(0);
            }
        }
        for (int num : adjList.get(curNum)) {
            if (!visited[num]) { 
                visited[num] = true;  
                searchForNum(adjList, one, two, num, firstFoundCopy); 
            }
        }
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int numStudents = Integer.parseInt(in.next()); 
        int numCompare = Integer.parseInt(in.next()); 

        int[] subsets = new int[numStudents+1]; // Representing parents of each node 
        // For checking cycle

        for (int i = 0; i <= numStudents; i++) { 
            subsets[i] = i; 
        }

        visited = new boolean[numStudents+1]; 
        Arrays.fill(visited, false);

        // Have a directed edge which shows large -> small

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(numStudents+1);
        
        for (int i = 0; i <= numStudents; i++) { 
            adjList.add(new ArrayList<Integer>()); 
        }
        int tallest = -1; 

        for (int i = 0; i < numCompare; i++) {
            int taller = Integer.parseInt(in.next());
            int small = Integer.parseInt(in.next()); 
            //subsets[small] = taller; // Parent

            adjList.get(taller).add(small); // Taller going to the shorter
            // The parent of from the subsets will be used to search
            // Two queries may not be the parent always
        }

        int one = Integer.parseInt(in.next());
        int two = Integer.parseInt(in.next()); 

        // int root = tallest; //findRoot(subsets, one);

        //if (root == findRoot(subsets, two) && subsets[one] != subsets[two]) { 
            // Forms a cycle and thus have bounds on either side to compare 
            searchForNum(adjList, one, two, one, false);
            visited[one] = true;
            Arrays.fill(visited, false);
            visited[two] = true;
            searchForNum(adjList, one, two, two, false);
        // }
        // else { 
            // Another check
            if (adjList.get(two).indexOf(one) >= 0) { 
                // Directly connected relationship - cannot have inverted edge
                System.out.println("no");
                System.exit(0);
            }
            System.out.println("unknown"); 
        // }
    }
}
