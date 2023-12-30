package Graph.MST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CCC2017S4 {

    static class Subset { 

        public int parent; 
        public int rank; 
        
        public Subset(int parent, int rank) { 
            this.parent = parent; 
            this.rank = rank; 
        }
    }

    static class SetsHandler {
        
        // Sets are useful checking if there are relationship between two objects further than there direct relation; 
        // Ex. 1 -> 2 and 2 -> 3, then {1, 2, 3} are in the same set - the parent of them are same 
        // Thus, in this way, in checking if there is a cycle in the graph 
        //(if another edge checked causes the two elements in the same set to be found, then a cycle is present). 

        public Subset[] subsets; // subset of a node (the index being their number) 

        public SetsHandler(Subset[] subsets) { 
            this.subsets = subsets; 
        }
        
        public int findRoot(int node) { 
            // Recursively finds the parent of each node; 
            if (this.subsets[node].parent == node) { 
                return node; 
            }
            // path compression - assign the root (highest up) of the subset to be its parent - shortening its path
            this.subsets[node].parent = findRoot(subsets[node].parent); 
            return this.subsets[node].parent; 
        } 

        public void union(int nodeOne, int nodeTwo) { 
            // Union by rank; move the smaller subset (tree structure) under the larger
            int nodeOneRoot = findRoot(nodeOne);
            int nodeTwoRoot = findRoot(nodeTwo);

            if (this.subsets[nodeOneRoot].rank > this.subsets[nodeTwoRoot].rank) { 
                // Smaller subset's parent becomes larger subset's
                this.subsets[nodeTwoRoot].parent = nodeOneRoot;
            }
            else if (this.subsets[nodeOneRoot].rank < this.subsets[nodeTwoRoot].rank) { 
                this.subsets[nodeOneRoot].parent = nodeTwoRoot;
            }
            else { 
                // Same size; no matter which one, rank has to increase for one that slides under
                this.subsets[nodeOneRoot].parent = nodeTwoRoot; 
                this.subsets[nodeTwoRoot].rank++; // nodeTwo is parent, so its now highest
            }
            // for (Subset subset : this.subsets) { 
            //     System.out.print(subset.parent + ", "); 
            // }
            // System.out.print("\n"); 
        }
    }


    static class Edge extends Object { 
        
        public int src; 
        public int dest; 
        public int weight; 

        public Edge(int src, int dest, int weight) { 
            this.src = src; 
            this.dest = dest;
            this.weight = weight;  
        }

        @Override
        public boolean equals(Object other) { 
            if (other instanceof Edge) {
                Edge edge = (Edge) other;
                return this.src == edge.src && this.dest == edge.dest && this.weight == edge.weight; 
            }
        
            return false; 
            
        }
    }
    
    //static int[][] lookup; 

    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in);
        
        String[] init = in.nextLine().split(" "); 
        
        int V = Integer.parseInt(init[0]); // num of buildings
        int M = Integer.parseInt(init[1]); // num of pipes
        int pipeEnhancer = Integer.parseInt(init[2]); 

        // int remainderPipes = M - (V - 1); 

        // lookup = new int[V-1][(int) Math.ceil(Math.log(V-1))];

        ArrayList<Edge> edges = new ArrayList<Edge>(); 

        for (int i = 0; i < M; i++) { 
            String[] read = in.nextLine().split(" "); 

            edges.add(new Edge(Integer.parseInt(read[0]), 
                                Integer.parseInt(read[1]), 
                                Integer.parseInt(read[2]))); 
        }
        
        // edges.add(new Edge(0, 1, 1));  
        // edges.add(new Edge(0, 2, 3)); 
        // edges.add(new Edge(1, 2, 2)); 

        Subset[] subsets = new Subset[V+1]; 
        // Init subsets
        for (int i = 1; i <= V; i++) { 
            subsets[i] = new Subset(i, 0); 
        }

        SetsHandler setsHandler = new SetsHandler(subsets); 

        kruskals(V, edges, setsHandler, pipeEnhancer);
    }

    // static void buildSparseTable(int arr[], int n) { 
    //     for (int i = 0; i < n; i++) { 
    //         lookup[i][0] = arr[i];
    //     }


    // }

    public static void kruskals(int V, ArrayList<Edge> edges, SetsHandler setsHandler, int pipeEnhancer) { 
        // Sort in non decreasing order

        ArrayList<Edge> unsortedEdges = new ArrayList<Edge>(edges.subList(0, V-1)); 
        //ArrayList<Edge> fullEdges = new ArrayList<Edge>(edges);
    
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) { 
                return edge1.weight - edge2.weight; 
            }
        });

        int i = 0; 
        int numEdgesDone = 0; 
        int numChanged = 0; 

        while (numEdgesDone < V - 1) { 
            Edge nextEdge = edges.get(i); 
            
            int srcRoot = setsHandler.findRoot(nextEdge.src);
            int destRoot = setsHandler.findRoot(nextEdge.dest); 

            if (srcRoot != destRoot) { 
                if (unsortedEdges.indexOf(nextEdge) < 0) { 
                    numChanged++; 
                } 
                numEdgesDone++;
                setsHandler.union(srcRoot, destRoot); // Connect the edge to higher up in MST
            }
            i++; 
        }

        //int minCost = 0; 

        System.out.println(numChanged); 

        // System.out.println("Edges in MST: ");
        // int cost = 0; 
        // for (Edge edge : results) { 
        //     System.out.println(edge.src + " -> " + edge.dest); 
        //     cost += edge.weight; 
        // }
        // System.out.println("The minimum cost to traverse graph is " + cost);
    } 

}
