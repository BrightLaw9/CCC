package Mock2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC2016S3 {

    static int vertexA; 
    static int numRoads; 
    static int diameter = 0; 

    public static boolean findDiameter(int start, int prev, ArrayList<ArrayList<Integer>> adjList, 
                                            boolean[] isPho, boolean findStage, int depth) { 
        // No need for visited, just don't visit prev
        
        // DFS -- see if road / the road end is a pho res, and thus all roads are needed
        // Or remove unneeded roads up to where a pho res occurs

        boolean connectedToPho = false;

        for (int connected : adjList.get(start)) {
            if (connected != prev) {
                if (findDiameter(connected, start, adjList, isPho, findStage, depth + 1)) { 
                    connectedToPho = true;
                }
            }
        }

        // Not connected to any other except prev, a leaf
        if (isPho[start]) connectedToPho = true; 
        if (!connectedToPho && findStage) { 
            numRoads--; 
        }
        else if (connectedToPho) { 
            if (depth > diameter) { 
                diameter = depth;
                vertexA = start; 
            } 
        }
        return connectedToPho; 
    }
    
    public static void main(String[] args) throws IOException {
        // Property of trees is that a road to get somewhere has to be the road to take getting back
        // Hence, a branch/road needs to be travelled twice
        // To minimize, don't travel back on the longest road (diameter of tree)
        // Remove roads that connect to only non-pho res. Indirectly connected pho should be contained
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok = new StringTokenizer(br.readLine()); 
        int n = Integer.parseInt(tok.nextToken());
        int m = Integer.parseInt(tok.nextToken()); 
        numRoads = n-1; 

        boolean[] isPho = new boolean[n]; 

        String[] data = br.readLine().split(" ");
        int phoRes = -1; 
        for (int i = 0; i < m; i++) { 
            phoRes = Integer.parseInt(data[i]); 
            isPho[phoRes] = true;  
        } 

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(n); 
        for (int i = 0; i < n; i++) { 
            adjList.add(new ArrayList<Integer>()); 
        }
        for (int i = 0; i < n-1; i++) { 
            StringTokenizer tok1 = new StringTokenizer(br.readLine()); 
            int resOne = Integer.parseInt(tok1.nextToken()); 
            int resTwo = Integer.parseInt(tok1.nextToken());

            adjList.get(resOne).add(resTwo);
            adjList.get(resTwo).add(resOne); 
        }

        findDiameter(phoRes, -1, adjList, isPho, true, 0);

        findDiameter(vertexA, -1, adjList, isPho, false, 0); 

        System.out.println(2 * numRoads - diameter);
    }
}
