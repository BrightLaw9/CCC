package Mock1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CCC2023S4 {

    public static class Edge { 
        int src; int dest; int w; 
        public Edge(int src, int dest, int w) { 
            this.src = src; 
            this.dest = dest; 
            this.w = w; 
        }
    }

    public static class Subset { 
        int parent;
        public Subset(int parent) { 
            this.parent = parent; 
        }
    }

    public static int findRoot(Subset[] subsets, int num) { 
        if (subsets[num].parent == num) return num; 
        subsets[num].parent = findRoot(subsets, subsets[num].parent); 
        return subsets[num].parent; 
    }

    public static void union(Subset[] subsets, int one, int two) { 
        subsets[two].parent = one; 
    }
    
    public static void onlyMST(int numIntersect, Subset[] subsets, ArrayList<Edge> edges) { 
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge one, Edge two) { 
                return one.w - two.w; 
            }
        });

        int numDone = 0; 
        int i = 0; 
        long cost = 0; 
        while (numDone < numIntersect - 1 && i < edges.size()) { 
            Edge next = edges.get(i);
            int srcRoot = findRoot(subsets, next.src);
            int destRoot = findRoot(subsets, next.dest); 
            if (srcRoot != destRoot) { 
                union(subsets, srcRoot, destRoot);
                numDone++;
                cost += next.w; 
            }
            i++; 
        }
        System.out.println(cost); 
    }

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int numIntersect = Integer.parseInt(in.next());
        int numRoads = Integer.parseInt(in.next());

        Subset[] subsets = new Subset[numIntersect+1]; 
        for (int i = 1; i <= numIntersect; i++) { 
            subsets[i] = new Subset(i);
        }

        ArrayList<Edge> edges = new ArrayList<Edge>(numIntersect); 
        for (int i = 0; i < numRoads; i++) { 
            // Only store costs
            int src = in.nextInt(); 
            int dest = in.nextInt();
            int l = in.nextInt();
            int c = in.nextInt(); 
            edges.add(new Edge(src, dest, c)); 
        }
        onlyMST(numIntersect, subsets, edges);
    }
}
