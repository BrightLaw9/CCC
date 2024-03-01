package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;

// Passed on LeetCode
public class MinCost2Points {

    public static class Subset { 
        int parent; int rank;

        public Subset(int parent, int rank) { 
            this.parent = parent; 
            this.rank = rank;
        }
    }

    public static class Edge { 

        int src; int dest; int weight; 
        public Edge(int src, int dest, int weight) { 
            this.src = src; 
            this.dest = dest; 
            this.weight = weight; 
        }
    }

    public static int calculateWeight(int[] point1, int[] point2) { 
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    public static int findRoot(Subset[] subsets, int nodeIndex) { 
        if (subsets[nodeIndex].parent == nodeIndex) { 
            return nodeIndex; 
        }

        subsets[nodeIndex].parent = findRoot(subsets, subsets[nodeIndex].parent); 
        return subsets[nodeIndex].parent; 
    }

    public static void union(Subset[] subsets, int nodeIdx1, int nodeIdx2) { 

        int node1Root = findRoot(subsets, nodeIdx1); 
        int node2Root = findRoot(subsets, nodeIdx2); 

        if (subsets[node1Root].rank > subsets[node2Root].rank) { 
            // Put node2 under node1
            subsets[node2Root].parent = node1Root;
        }
        else if (subsets[node1Root].rank < subsets[node2Root].rank) { 
            subsets[node1Root].parent = node2Root; 
        }
        else { 
            subsets[node1Root].parent = node2Root; 
            subsets[node2Root].rank++; 
        }
    }

    public static int minCostConnectPoints(int[][] points) {
        
        // MST
        ArrayList<Edge> edges = new ArrayList<Edge>(points.length); 

        for (int i = 0; i < points.length; i++) { 
            for (int j = 0; j < points.length; j++) { 
                if (j == i) continue;

                // Use indexes as a reference to the point in the points array
                // an integer will allow for subsets reference
                edges.add(new Edge(i, j, calculateWeight(points[i], points[j])));
            }
        }

        edges.sort(new Comparator<Edge>() { 
            @Override
            public int compare(Edge one, Edge two) { 
                return one.weight - two.weight; 
            }
        });

        Subset[] subsets = new Subset[points.length];

        for (int i = 0; i < points.length; i++) { 
            subsets[i] = new Subset(i, 0); 
        } 

        int i = 0;
        int minCost = 0; 
        int numDone = 0; 

        while (numDone < points.length - 1) { 
            Edge nextEdge = edges.get(i);

            // Find roots to not form loop if roots are different

            int srcRoot = findRoot(subsets, nextEdge.src);
            int destRoot = findRoot(subsets, nextEdge.dest); 

            if (srcRoot != destRoot) { 
                // Add to MST
                union(subsets, srcRoot, destRoot); 
                numDone++; 
                minCost += nextEdge.weight; 
            }
            i++; 
        }
        return minCost; 
    }

    public static void main(String[] args) {
        System.out.println(minCostConnectPoints(new int[][] {new int[] {0,0}, new int[] {2, 2}, new int[] {3, 10}, new int[] {5, 2}, new int[] {7, 0}})); 
    }
}

