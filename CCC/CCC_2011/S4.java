package CCC_2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class S4 {

    // 100 of 100 on DMOJ 
    // Study Maximum Flow Problem (Dinic's Algo)

    public static final int nodes = 18; // 8 blood types, 8 patients, source + sink
    public static int[] level = new int[nodes]; 

    public static class Edge { 
        public int v; // Dest node
        public int flow; // How much is flowed in edge 
        public int C; // Max Capacity (aka weight)
        public int rev; // The index of the reverse edge where stored in adjList

        public Edge(int v, int flow, int C, int rev) { 
            this.v = v; 
            this.flow = flow; 
            this.C = C; 
            this.rev = rev; 
        }
    }

    public static void addEdge(ArrayList<ArrayList<Edge>> adj, int u, int v, int C) { 
        Edge forward = new Edge(v, 0, C, adj.get(v).size()); 

        Edge backward = new Edge(u, 0, 0, adj.get(u).size()); 
        
        adj.get(u).add(forward); 
        adj.get(v).add(backward); 
    }

    // See if more flow can be sent from source to tap
    public static boolean bfs(ArrayList<ArrayList<Edge>> adj, int s, int t) { 
        Arrays.fill(level, -1);

        level[s] = 0; // level array acts as visited array as well
        
        Queue<Integer> q = new LinkedList<Integer>(); 
        q.add(s); 

        while (!q.isEmpty()) { 
            int src = q.poll(); 

            for (Edge edge : adj.get(src)) { 
                if (level[edge.v] < 0 && edge.flow < edge.C) { // Level is less than 0 means not visited
                    level[edge.v] = level[src] + 1; 
                    q.add(edge.v); 
                }
            }
        }
        return level[t] < 0 ? false : true; // There is a path if t is assigned a level 
    }

    // Start array allows for storage of where to send flow next on recursion
    // Tracks how many of the adj connections to a vertex has been done

    public static int sendFlow(ArrayList<ArrayList<Edge>> adj, int cur, int flow, int tap, int[] start) { 
        if (cur == tap) { 
            return flow; 
        }

        for (; start[cur] < adj.get(cur).size(); start[cur]++) { 
            Edge edge = adj.get(cur).get(start[cur]); 

            if (level[edge.v] == level[cur] + 1 && edge.flow < edge.C) { 
                int cur_flow = Math.min(flow, edge.C - edge.flow); // If the capacity - flow is less than requested flow, then can't satisfy
                int temp_flow = sendFlow(adj, edge.v, cur_flow, tap, start); 

                if (temp_flow > 0) { 
                    edge.flow += temp_flow; 

                    // Subtract the reverse edge flow of curEdge
                    adj.get(edge.v).get(edge.rev).flow -= temp_flow; 
                    return temp_flow;  
                }
            }
        }
        return 0; 
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>(); 
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<Edge>()); 
        }
        
        // Connect blood units to source
        // The capacity is the amount of blood units that is available (that can leave)
        for (int i = 1; i <= 8; i++) { 
            addEdge(adjList, 0, i, Integer.parseInt(in.next()));
        }

        // O- is 1, O+ is 2, A- is 3, A+ is 4, B- is 5, B+ is 6, AB- is 7, AB+ is 8

        // Blood units to patients (infinty capacity)

        // Patients 
        // O- is 9, O+ is 10, A- is 11, A+ is 12, B- is 13, B+ is 14, AB- is 15, and AB+ is 16

        // O- can be distributed to any patient
        for (int i = 9; i <= 16; i++) { 
            addEdge(adjList, 1, i, Integer.MAX_VALUE); 
        }

        // O+ can be O+, A+, B+, or AB+
        addEdge(adjList, 2, 10, Integer.MAX_VALUE);
        addEdge(adjList, 2, 12, Integer.MAX_VALUE);
        addEdge(adjList, 2, 14, Integer.MAX_VALUE);
        addEdge(adjList, 2, 16, Integer.MAX_VALUE);

        // A- can be only given to A- and A+, AB- or AB+
        addEdge(adjList, 3, 11, Integer.MAX_VALUE);
        addEdge(adjList, 3, 12, Integer.MAX_VALUE);
        addEdge(adjList, 3, 15, Integer.MAX_VALUE);
        addEdge(adjList, 3, 16, Integer.MAX_VALUE);

        // A+ can be A+, AB+
        addEdge(adjList, 4, 12, Integer.MAX_VALUE);
        addEdge(adjList, 4, 16, Integer.MAX_VALUE);

        // B- can be B-, B+, AB-, AB+
        addEdge(adjList, 5, 13, Integer.MAX_VALUE);
        addEdge(adjList, 5, 14, Integer.MAX_VALUE);
        addEdge(adjList, 5, 15, Integer.MAX_VALUE);
        addEdge(adjList, 5, 16, Integer.MAX_VALUE);

        // B+ can be B+, AB+
        addEdge(adjList, 6, 14, Integer.MAX_VALUE);
        addEdge(adjList, 6, 16, Integer.MAX_VALUE);

        // AB- can be only AB-, AB+
        addEdge(adjList, 7, 15, Integer.MAX_VALUE);
        addEdge(adjList, 7, 16, Integer.MAX_VALUE);

        // AB+ is only AB+
        addEdge(adjList, 8, 16, Integer.MAX_VALUE);

        // Patients to tap (capacity being the number of patients that can receive)
        for (int i = 9; i <= 16; i++) { 
            addEdge(adjList, i, 17, Integer.parseInt(in.next())); 
        }
        

        int source = 0; 
        int tap = 17; 

        int total = 0; 
        while (bfs(adjList, source, tap)) { 
            int[] start = new int[nodes + 1]; 

            while (true) { 
                int flow = sendFlow(adjList, source, Integer.MAX_VALUE, tap, start);
                if (flow == 0) { 
                    break; 
                } 
                total += flow; 
            }
        }

        System.out.println(total); 
    }
}
