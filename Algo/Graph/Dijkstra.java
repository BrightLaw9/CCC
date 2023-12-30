package Graph;
import java.util.ArrayList;
import java.util.HashSet;

public class Dijkstra { 

    public static class Node { 
        
        private int weight;
        private int val;  

        public Node(int val, int weight) { 
            this.weight = weight; 
            this.val = val; 
        }

        public int getWeight() { return this.weight; }
        public int getValue() { return this.val; }
        
    }

    public static class Graph { 

        ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(9);

        public Graph() { 
            for (int i = 0; i < 9; i++) adjList.add(new ArrayList<Node>()); 
        }

        public void addEdge(int u, int v, int w) { 
            adjList.get(u).add(new Node(v, w)); 
            adjList.get(v).add(new Node(u, w)); 
        }

        public ArrayList<Node> getConnectedNodes(int node) { 
            return adjList.get(node); 
        }

        public int getMinimumConnectedNode(int[] dist, HashSet<Integer> treeSet) { 
            // Get the minimum node weight that is currently available
            int minNode = -1; int maxWeight = Integer.MAX_VALUE; 

            for (int i = 0; i < 9; i++) { 
                if (dist[i] < maxWeight && !treeSet.contains(i)) { 
                    minNode = i; 
                    maxWeight = dist[i]; 
                }
            }

            return minNode; 
        }

    }


    public static int[] dijkstra(Graph g, int src) { 
        // Returns the shortest distance from src to i

        int[] dist = new int[9]; 
        HashSet<Integer> treeSet = new HashSet<Integer>(9);  

        for (int i = 0; i < 9; i++) { 
            dist[i] = Integer.MAX_VALUE; 
        }

        dist[src] = 0; 

        while (treeSet.size() < 6) { 
            
            int minNode = g.getMinimumConnectedNode(dist, treeSet); 
            
            treeSet.add(minNode); 

            // Update edges for the newly picked node
            for (Node node : g.getConnectedNodes(minNode)) { 
                int newWeight = dist[minNode] + node.getWeight(); 

                if (!treeSet.contains(node.getValue()) 
                    //&& dist[node.getValue()] != Integer.MAX_VALUE 
                    && newWeight < dist[node.getValue()]) 
                    { 
                        dist[node.getValue()] = newWeight; 
                    }
            } 
                
        }
        
        return dist; 
    }

    public static void printSoln(int[] dist) { 
        System.out.println("Node, distance from src"); 
        for (int i = 0; i < dist.length; i++) {
            System.out.println(String.format("%s -> %s", i, dist[i])); 
        }
    }

    public static void main(String[] args) {  

        Graph g = new Graph(); 

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 7, 11); 
        g.addEdge(7, 8, 7);
        g.addEdge(7, 6, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(8, 2, 2);
        g.addEdge(1, 2, 8); 
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, 9);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 3, 14);
        g.addEdge(5, 2, 4);
        g.addEdge(5, 6, 2);
        

        int[] res = dijkstra(g, 0); 

        printSoln(res);
    }
}