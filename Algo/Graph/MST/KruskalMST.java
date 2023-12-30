package Graph.MST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {

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
            for (Subset subset : this.subsets) { 
                System.out.print(subset.parent + ", "); 
            }
            System.out.print("\n"); 
        }
    }


    static class Edge { 
        
        public int src; 
        public int dest; 
        public int weight; 

        public Edge(int src, int dest, int weight) { 
            this.src = src; 
            this.dest = dest;
            this.weight = weight;  
        }
    }
 
    
    public static void main(String[] args) { 

        int V = 9; 
        ArrayList<Edge> edges = new ArrayList<Edge>(
            List.of(new Edge(2, 3, 7), new Edge(7, 8, 7), new Edge(8, 2, 2), new Edge(7, 6, 1), new Edge(2, 5, 4), new Edge(6, 5, 2), new Edge(0, 1, 4), 
                        new Edge(8, 6, 6), new Edge(0, 7, 8), new Edge(3, 4, 9), new Edge(5, 4, 10), new Edge(1, 7, 11), new Edge(1, 2, 8), new Edge(3, 5, 14)));
        
        // edges.add(new Edge(0, 1, 1));  
        // edges.add(new Edge(0, 2, 3)); 
        // edges.add(new Edge(1, 2, 2)); 

        Subset[] subsets = new Subset[V]; 
        // Init subsets
        for (int i = 0; i < V; i++) { 
            subsets[i] = new Subset(i, 0); 
        }

        SetsHandler setsHandler = new SetsHandler(subsets); 

        kruskals(V, edges, setsHandler);
    }

    public static void kruskals(int V, ArrayList<Edge> edges, SetsHandler setsHandler) { 
        // Sort in non decreasing order
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) { 
                return edge1.weight - edge2.weight; 
            }
        });

        Edge[] results = new Edge[V-1]; 

        int i = 0; 
        int numEdgesDone = 0; 

        while (numEdgesDone < V - 1) { 
            Edge nextEdge = edges.get(i); 
            
            int srcRoot = setsHandler.findRoot(nextEdge.src);
            int destRoot = setsHandler.findRoot(nextEdge.dest); 

            if (srcRoot != destRoot) { 
                // No cycle is formed, add to MST, otherwise discard edge
                results[numEdgesDone] = nextEdge; 
                numEdgesDone++; 
                setsHandler.union(srcRoot, destRoot); // Connect the edge to higher up in MST
            }
            i++; 
        }

        System.out.println("Edges in MST: ");
        int cost = 0; 
        for (Edge edge : results) { 
            System.out.println(edge.src + " -> " + edge.dest); 
            cost += edge.weight; 
        }
        System.out.println("The minimum cost to traverse graph is " + cost);
    } 
}
