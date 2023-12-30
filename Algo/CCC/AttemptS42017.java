import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AttemptS42017 {

    public static class Edge { 

        private int start, dest, weight; 

        public Edge(int start, int dest, int weight) { 
            this.start = start; 
            this.dest = dest; 
            this.weight = weight; 
        }

        public int getStart() { 
            return this.start; 
        }

        public int getDest() { 
            return this.dest; 
        }

        public int getWeight() { 
            return this.weight; 
        }
    }

    public static ArrayList<Boolean> setAllFalse(ArrayList<Boolean> visited, int numPipes) { 
        for (int i = 0; i < numPipes; i++) { 
            visited.add(i, false);
        }
        return visited; 
    }
    public static ArrayList<Edge> sortEdges(ArrayList<Edge> edges) { 
        ArrayList<Edge> sortedEdges = new ArrayList<Edge>(); 

        sortedEdges.add(edges.get(0));

        for (int i = 1; i < edges.size(); i++) { 
            for (int j = 0; j < sortedEdges.size(); j++) { 
                Edge curEdge = edges.get(i); 
                Edge sortEdge = sortedEdges.get(j); 
                
                if (curEdge.getWeight() < sortEdge.getWeight()) {
                    sortedEdges.add(j, curEdge);
                    break; 
                }
                else if (curEdge.getWeight() > sortedEdges.get(sortedEdges.size()-1).getWeight()) { 
                    sortedEdges.add(curEdge); 
                }
            }
            
        }

        return sortedEdges; 
    }

    public static boolean checkFormLoop(ArrayList<ArrayList<Integer>> adjList, int start, ArrayList<Boolean> visited) { 
        if (visited.get(start)) { 
            return true; 
        }
        
        visited.add(start, true); 

        for (Integer connectedNodes : adjList.get(start)) { 
            return checkFormLoop(adjList, connectedNodes, visited); 
        }

        return false; 
    }

    public static ArrayList<Edge> mst(ArrayList<ArrayList<Integer>> adjList, ArrayList<Edge> edgeList, ArrayList<Boolean> visited, int numPipes) { 

        ArrayList<Edge> finalEdgeList = new ArrayList<Edge>(); 

        // MST algo
        for (Edge edge : edgeList) { 
            if (!checkFormLoop(adjList, edge.getStart(), visited)) { 
                adjList = addAdjList(adjList, edge.getStart(), edge.getDest()); 
                adjList = addAdjList(adjList, edge.getDest(), edge.getStart()); 
                finalEdgeList.add(edge); 
                visited = setAllFalse(visited, numPipes); 
            }
        }
        return finalEdgeList; 
    }

    public static ArrayList<ArrayList<Integer>> addAdjList(ArrayList<ArrayList<Integer>> adjList, int parent, int child) { 
        adjList.get(parent).add(child); 
        adjList.add(parent, adjList.get(child));

        return adjList; 
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int n = Integer.parseInt(in.next()); 

        int numPipes = Integer.parseInt(in.next());

        int pipeEnhancer = Integer.parseInt(in.next());

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(numPipes); 

        for (int i = 0; i < numPipes; i++) { 
            adjList.add(i, new ArrayList<Integer>());
        }

        ArrayList<Edge> edgeList = new ArrayList<Edge>(); 

        
        for (int i = 0; i < numPipes; i++) { 
            int a = Integer.parseInt(in.next());
            int b = Integer.parseInt(in.next());
            int cost = Integer.parseInt(in.next());

            edgeList.add(new Edge(a, b, cost)); 
        }

        ArrayList<Boolean> visited = new ArrayList<Boolean>(numPipes); 
        
        visited = setAllFalse(visited, numPipes); 

        //Minimum spanning tree (MST) following the least weighted path

        edgeList = sortEdges(edgeList); 
        
        ArrayList<Edge> finalEdgeList = mst(adjList, edgeList, visited, numPipes); 

        int constructSteps = 0; 

        for (int i = 0; i < edgeList.size(); i++) { 
            if (i == finalEdgeList.size() && (!(edgeList.get(i).getDest() == 1 || edgeList.get(i).getStart() == 1))) { 
                constructSteps++;
                break;  
            }

            if (!edgeList.get(i).equals(finalEdgeList.get(i))) { 
                //if (!(finalEdgeList.get(i).getDest() == 1 || finalEdgeList.get(i).getStart() == 1)) { 
                    constructSteps++; 
                    edgeList.remove(i); 
                    i -= 1; 
               // }
           } 
        }

        System.out.println(constructSteps);
    }
}
