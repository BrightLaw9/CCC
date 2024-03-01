package Mock1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC2023S4Full {

    static class Edge implements Comparable<Edge> { 
        int src; int dest; int c; int d;  
        public Edge(int src, int dest, int d, int c) { 
            this.src = src; 
            this.dest = dest; 
            this.d = d; 
            this.c = c; 
        }

        @Override
        public int compareTo(Edge other) { 
            return -(this.c - other.c); // Sort in decreasing order
            // Choose most costly first to test removal
        }
    }

    static class Node extends Object { 
        int val; long d; 
        public Node(int val, long d) { 
            this.val = val; 
            this.d = d; 
        }

        @Override
        public boolean equals(Object other)  {
            try { 
                Node n = (Node) other; 
                return n.d == this.d && n.val == this.val; 
            }
            catch (Exception e) { 
                return false; 
            }
        }
    }
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok = new StringTokenizer(br.readLine()); 
        int n = Integer.parseInt(tok.nextToken());
        int m = Integer.parseInt(tok.nextToken()); 

        ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(n+1); 
        Edge[] roads = new Edge[m]; 

        for (int i = 0; i <= n; i++) { 
            adjList.add(new ArrayList<Node>()); 
        }

        for (int i = 0; i < m; i++) { 
            StringTokenizer tok1 = new StringTokenizer(br.readLine()); 
            int src = Integer.parseInt(tok1.nextToken()); 
            int dest = Integer.parseInt(tok1.nextToken()); 
            int length = Integer.parseInt(tok1.nextToken()); 
            int cost = Integer.parseInt(tok1.nextToken()); 

            roads[i] = new Edge(src, dest, length, cost); 
            adjList.get(src).add(new Node(dest, length)); 
            adjList.get(dest).add(new Node(src, length)); 
        }

        Arrays.sort(roads);
        long[] dist = new long[n+1]; 
        PriorityQueue<Node> pq = new PriorityQueue<Node>(
            new Comparator<Node>() {
                @Override
                public int compare(Node one, Node two) { 
                    return one.d == two.d ? 0 : one.d > two.d ? 1 : -1; 
                }
            }
        ); 

        long cost = 0; 

        for (Edge road : roads) {
            // Testing and removing most expensive first
            Arrays.fill(dist, Long.MAX_VALUE);

            // Remove current road to see if needed
            adjList.get(road.src).remove(new Node(road.dest, road.d));
            adjList.get(road.dest).remove(new Node(road.src, road.d));

            pq.add(new Node(road.src, 0));
            dist[road.src] = 0; 

            while (!pq.isEmpty()) { 
                Node curNode = pq.poll(); 

                for (Node connected : adjList.get(curNode.val)) { 
                    if (dist[connected.val] > connected.d + curNode.d) { 
                        dist[connected.val] = connected.d + curNode.d; 
                        pq.add(new Node(connected.val, dist[connected.val])); 
                    }
                }
            }

            if (dist[road.dest] > road.d) { 
                // Road is needed
                adjList.get(road.src).add(new Node(road.dest, road.d)); 
                adjList.get(road.dest).add(new Node(road.src, road.d)); 
                cost += road.c; 
            }
        }
        System.out.println(cost); 
    }
}
