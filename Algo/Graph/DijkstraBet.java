package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraBet {

    // Passed on DMOJ Shortest path problem
    // Can solve negative edge if not terminated right when you reach dest -- let it search until queue is empty
    public static class Node { 
        
        public int val;
        public int weight;
        // You are keeping weights in nodes as you are picking next node to go to
        public Node(int val, int weight) { 
            this.val = val; 
            this.weight = weight; 
        }
    }
    
    public static void addEdge(ArrayList<ArrayList<Node>> adjList, int src, int dest, int weight) { 
        adjList.get(src).add(new Node(dest, weight));
    }

    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in); 

        String[] read = in.nextLine().split(" "); 

        int numVertices = Integer.parseInt(read[0]);
        int numEdges = Integer.parseInt(read[1]); 

        ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(numVertices + 1); 

        for (int a = 0; a <= numVertices; a++) { 
            adjList.add(new ArrayList<Node>()); 
        }

        for (int i = 0; i < numEdges; i++) { 
            String[] data = in.nextLine().split(" "); 
            addEdge(adjList, Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
                        Integer.parseInt(data[2])
            );
        }

        // Dijkstra

        int[] dist = new int[numVertices+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<Node>(numEdges, new Comparator<Node>() {  
            @Override
            public int compare(Node node1, Node node2) { 
                return node1.weight - node2.weight; 
            }
        }); 

        pq.add(new Node(1, 0)); 

        dist[1] = 0; 

        while (!pq.isEmpty()) { 
            Node curNode = pq.poll(); 

            // If statement can be unnecessary, but save stop rest of searching queue (slightly faster)
            if (curNode.val == numVertices) { 
                dist[curNode.val] = curNode.weight; 
                break; // Reached the dest node that was picked from the priority queue as the shortest weight to get there
            }

            for (Node connected : adjList.get(curNode.val)) { 
                if (dist[connected.val] > curNode.weight + connected.weight) { 
                    dist[connected.val] = curNode.weight + connected.weight; 

                    pq.add(new Node(connected.val, curNode.weight + connected.weight));
                }
             }
        }

        System.out.println(dist[numVertices]);

    }
}
