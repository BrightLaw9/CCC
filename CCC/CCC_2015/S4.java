package CCC_2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class S4 {

    // Passed 15/15 on DMOJ
    public static class Node { 

        int val; 
        int weightToGet;
        int damage; 

        public Node(int val, int weightToGet, int damage) { 
            this.val = val; 
            this.weightToGet = weightToGet; 
            this.damage = damage; 
        }
    }

    public static void addEdge(ArrayList<ArrayList<Node>> adjList, int start, 
                            int dest, int weight, int damage) { 
        adjList.get(start).add(new Node(dest, weight, damage)); 
        adjList.get(dest).add(new Node(start, weight, damage)); 
    }

    public static int getMin(int[] damageDist) { 
        // Get the min of a particular node (recurse through all damages)
        int min = Integer.MAX_VALUE;  
        for (int weight : damageDist) { 
            min = Math.min(weight, min);
        }
        return min; 
    }

    public static void main(String[] args) { 

        // Create adjacency list to represent graph
        // Use Dijsktra to find the shortest path (time) for reaching start to dest
        // Use another dimension in dist to keep track of hull damage

        Scanner in = new Scanner(System.in); 

        ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(); 

        // Strictly less than
        String[] read = in.nextLine().split(" "); 
        int maxHullDamage = Integer.parseInt(read[0]); 
        int numIslands = Integer.parseInt(read[1]); 
        int numRoutes = Integer.parseInt(read[2]); 

        for (int r = 0; r <= numIslands; r++) { 
            adjList.add(new ArrayList<>()); 
        }

        for (int i = 0; i < numRoutes; i++) { 
            String[] data = in.nextLine().split(" ");
            addEdge(adjList, Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
                    Integer.parseInt(data[2]), Integer.parseInt(data[3])
                    );  
        }

        // System.out.println(adjList.get(1).get(0).val); 
        // System.out.println(adjList.get(3).get(0).val); 

        // For each travelling to a island (first index), the second index holds the hull damage for that island
        int[][] dist = new int[numIslands+1][maxHullDamage];
        for (int f = 0; f <= numIslands; f++) {
            Arrays.fill(dist[f], Integer.MAX_VALUE);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(numRoutes, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) { 
                if (node1.weightToGet - node2.weightToGet == 0) return node1.damage - node2.damage;
                return node1.weightToGet - node2.weightToGet; 
            } 
        }); 

        String[] initial = in.nextLine().split(" ");
        int src = Integer.parseInt(initial[0]); 
        int dest = Integer.parseInt(initial[1]); 

        pq.add(new Node(src, 0, 0));

        for (int s = 0; s < maxHullDamage; s++) { 
            dist[src][s] = 0; 
        }

        boolean[][] visited = new boolean[numIslands+1][numIslands+1]; 

        for (int v = 1; v <= numIslands; v++) { 
            Arrays.fill(visited[v], false); 
        }
        
        while (!pq.isEmpty()) {
            //System.out.println("Damage " + pq.peek().damage); 
            Node curNode = pq.poll(); 
            int num = curNode.val; 
            int damage = curNode.damage; 
            
            // if (num == dest) { 
            //     System.out.println(curNode.weightToGet); 
            //     System.exit(0);
            // }

            // Check all edges connected to this island;
            //if the previously store distance to get there is more than this route plus new node, change to this one

            for (Node node : adjList.get(num)) { 
                if (node.damage + damage < maxHullDamage && dist[node.val][node.damage + damage] > curNode.weightToGet + node.weightToGet) { 
                    //for (int j = 0; j < (maxHullDamage - node.damage); j++) {
                        //if (dist[num][damage] != Integer.MAX_VALUE) {
                            dist[node.val][node.damage + damage] = curNode.weightToGet + node.weightToGet;  
                        //}
                    //}
                    // if (node.damage + damage < maxHullDamage 
                    // && 
                    //if (!visited[node.val][num]) {
                    // { // opposite visited - checking if loop
                        pq.add(new Node(node.val, dist[node.val][node.damage + damage], node.damage + damage));
                        //visited[node.val][num] = true;
                    //}
                    //}
                }
            }
        }

        //Access dist at the dest node, and get the smallest value less than maxHullDamage
        int min = Integer.MAX_VALUE; 
        for (int d = 0; d < maxHullDamage; d++) { 
            min = Math.min(dist[dest][d], min);
        }

        if (min == Integer.MAX_VALUE)
        System.out.println(-1); 
        else System.out.println(min);
    }
}