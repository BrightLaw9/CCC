import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Traverse {

    static void createEdge(ArrayList<ArrayList<Integer>> adjList, int a, int b) { 
        adjList.get(a).add(b); 
    }

    public static HashSet<Integer> overallVisited = new HashSet<Integer>(); 

    public static HashSet<Integer> visited = new HashSet<Integer>(); 
    static ArrayList<Integer> foundRoots = new ArrayList<Integer>();

    static Integer findRootNode(ArrayList<ArrayList<Integer>> adjList, Integer node) { 
        for (int link : adjList.get(node)) 
        {
            //System.out.println("Node: " + node);
            if (!visited.contains(link)) { 
                visited.add(link);
                overallVisited.add(link);
                return findRootNode(adjList, link); 
            }
        } 

        foundRoots.add(node); 
        visited.clear(); 
        visited.addAll(foundRoots);

        return node;
    }

    static int traverse(ArrayList<ArrayList<Integer>> adjList, Integer startNode, int steps, HashSet<Integer> toVisit, int curSteps, int stepsOnPath) { 
        // DFS style
        // Overall visited will score the nodes of restraunt which have been visited
        // curSteps is the number of untracked steps
        while (overallVisited.size() < toVisit.size())
        {  
            curSteps += 1; 
            for (int link : adjList.get(startNode)) 
            {   
                //for (int i : adjList.get(startNode)) System.out.println(i);
                //System.out.println("Node: " + startNode);
                if (!visited.contains(link)) { 
                    visited.add(link);
                    if (toVisit.contains(link)) { 
                        overallVisited.add(link);
                        steps += curSteps; 
                        curSteps = 0;
                        stepsOnPath = steps;
                    } 
                    traverse(adjList, link, steps, toVisit, curSteps, stepsOnPath); 
                }
                steps += stepsOnPath;
                stepsOnPath = 0; 
            } 
        }   
        return steps; 
    }

    public static void main(String... args) { 
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        int numToVisit = Integer.parseInt(in.next());
        
        HashSet<Integer> toVisit = new HashSet<Integer>(numToVisit);

        for (int i = 0; i < numToVisit; i++) { 
            toVisit.add(Integer.parseInt(in.next())); 
        }

        // Adjacency list for connected nodes 
        // Use index and the element as the pair 
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(n);

        for (int i = 0; i < n; i++) adjList.add(new ArrayList<Integer>());

        for (int i = 0; i < n-1; i++) { 
            int a = Integer.parseInt(in.next()); 
            int b = Integer.parseInt(in.next());

            createEdge(adjList, a, b);
            createEdge(adjList, b, a);
        }

        // Start at edge node(s), if to visit is there, traverse 
        
        int steps = -1; 
        System.out.println(adjList.get(2));
        while (overallVisited.size() < n) 
        { 
            overallVisited.add(0);
            Integer rootNode = findRootNode(adjList, 0); // 0 is arbitrary 
            
            if (toVisit.contains(rootNode)) { 
                // Start journey there
                visited.clear(); 
                overallVisited.clear(); 
                overallVisited.add(rootNode);
                visited.add(rootNode);
                System.out.println("R: " + rootNode);
                steps = traverse(adjList, rootNode, steps+1, toVisit, 0, 0); 
                break; 
            }
        }

        // No optimal starting found, start anywhere
        if (steps == -1) { 
            visited.clear(); 
            overallVisited.clear(); 
            steps = traverse(adjList, 0, steps+1, toVisit, 0, 0); 
        }

        System.out.println(steps); 
    }
}
