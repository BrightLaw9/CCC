package CCC_2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class S3 {

    // Passed on DMOJ

    public static ArrayList<ArrayList<Integer>> adjList; 
    public static int vertexA; 
    public static boolean[] isPho; 
    public static int numRoads; 
    public static int diameter = 0; 

    public static boolean findDiameter(int current, int previous, int depth, boolean toCut) { 

        boolean connectedToPho = false; 

        for (int connected : adjList.get(current)) { 
            // To prevent searching backward -- the path is non-directed so the next will point back
            if (connected != previous) { 
                if (findDiameter(connected, current, depth + 1, toCut)) { 
                    // If this is true, then when the path ends, there should be a pho res
                    // Say if the most farthest out is not, but second farthest is, then one road will be subtracted, 
                    //but on return, it will check the status and true will then be sent back
                    connectedToPho = true; 
                }
            }
        }

        if (isPho[current]) connectedToPho = true; 

        if (!connectedToPho) { 
            if (toCut) numRoads--; 
            return false; 
        }

        // Valid to account as a furthest point in calc (in pho network)
        if (depth > diameter) { 
            diameter = depth; 
            if (toCut) vertexA = current; 
        }

        return true; // ConnectedToPho if false should have returned
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 
        int m = Integer.parseInt(in.next()); 

        numRoads = n - 1; 

        adjList = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++) { 
            adjList.add(new ArrayList<Integer>()); 
        }
        
        isPho = new boolean[n]; 
        Arrays.fill(isPho, false);

        int phoRestaurant = -1; 
        for (int i = 0; i < m; i++) { 
            phoRestaurant = Integer.parseInt(in.next()); 
            isPho[phoRestaurant] = true;  
        }

        for (int i = 0; i < numRoads; i++) { 
            int oneResta = Integer.parseInt(in.next());
            int twoResta = Integer.parseInt(in.next()); 
            
            adjList.get(oneResta).add(twoResta);
            adjList.get(twoResta).add(oneResta); 
        }

        findDiameter(phoRestaurant, -1, 0, true); // Finding a vertex to perform the diameter search from
        // Also cutting unnecessary roads that don't lead to pho res 
        findDiameter(vertexA, -1, 0, false); // Finding the diameter  

        System.out.println(2 * numRoads - diameter); // All the roads the critic must walk there and back (no loop / two ways to get there)
                                                        // The longest road is not walked back and forth to minimize
    }
}
