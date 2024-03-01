import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// Passed on DMOJ

public class CCC2023S4 { 

    // MST minimum cost to travel to each
    public static class Edge implements Comparable<Edge> { 

        int src; int dest; int length; int cost;

        public Edge(int src, int dest, int length, int cost) { 
            this.src = src; 
            this.dest = dest; 
            this.length = length; 
            this.cost = cost; 
        }

        @Override
        public int compareTo(Edge other) { 
            // Sorting in decreasing order
            return -(this.cost - other.cost);  
            
        }
    }

    // Used for Dijkstra minimum distance calc
    public static class PathToNode extends Object { 
        int val; long weight; 

        public PathToNode(int val, long weight) { 
            this.val = val; 
            this.weight = weight; 
        }
        
        @Override
        public boolean equals(Object other2) { 
            try {
                PathToNode other = (PathToNode) other2; 
                return this.val == other.val && this.weight == other.weight;
            }
            catch (Exception e) {
                return false; 
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        String[] read = in.nextLine().split(" ");

        int numIntersect = Integer.parseInt(read[0]); 
        int numRoads = Integer.parseInt(read[1]);

        Edge[] roads = new Edge[numRoads]; 

        ArrayList<ArrayList<PathToNode>> adjList = new ArrayList<ArrayList<PathToNode>>(numIntersect+1);

        for (int i = 0; i <= numIntersect; i++) { 
            adjList.add(new ArrayList<PathToNode>());
        }

        for (int r = 0; r < numRoads; r++) { 
            String[] data = in.nextLine().split(" ");

            int src = Integer.parseInt(data[0]);
            int dest = Integer.parseInt(data[1]);
            int length = Integer.parseInt(data[2]);
            int cost = Integer.parseInt(data[3]);
            
            roads[r] = new Edge(src, dest, length, cost); 

            adjList.get(src).add(new PathToNode(dest, length));
            adjList.get(dest).add(new PathToNode(src, length));
        }

        long cost = 0; 

        Arrays.sort(roads);

        // Test most expensive first on Dijstkra to see if this edge makes a overall better to the dest 
        //(with the given edge being removed)
        // This way, dist and cost is minimized
        long[] dist = new long[numIntersect+1]; 

        PriorityQueue<PathToNode> pq = new PriorityQueue<>(new Comparator<PathToNode>() {
            @Override
            public int compare(PathToNode current, PathToNode other) { 
                if (current.weight - other.weight == 0) return 0;
                return (current.weight - other.weight) > 0 ? 1 : -1; 
            }
        });

        for (Edge road : roads) {

            Arrays.fill(dist, Long.MAX_VALUE);

            PathToNode srcToDest = new PathToNode(road.dest, road.length);
            PathToNode destToSrc = new PathToNode(road.src, road.length);

            // Delete forward edge
            //System.out.println(adjList.get(road.src).indexOf(srcToDest)); 
            adjList.get(road.src).remove(adjList.get(road.src).indexOf(srcToDest));

            // Delete backward edge
            adjList.get(road.dest).remove(adjList.get(road.dest).indexOf(destToSrc));

            // Perform dijkstra's with the one removed
            pq.add(new PathToNode(road.src, 0)); // init the src as the starting 
            dist[road.src] = 0;  
             
            while (!pq.isEmpty()) { 
                PathToNode curNode = pq.poll(); 

                for (PathToNode node : adjList.get(curNode.val)) { 
                    if (dist[node.val] > node.weight + curNode.weight) { 
                        dist[node.val] = node.weight + curNode.weight; 
                        pq.add(new PathToNode(node.val, node.weight + curNode.weight)); 
                    }
                }
            }

            if (dist[road.dest] > road.length) { 
                // This road is actually necessary, add it back to graph
                adjList.get(road.src).add(srcToDest);
                adjList.get(road.dest).add(destToSrc);
                cost += road.cost; 
            }
        }

        System.out.println(cost); 
        

    }
}