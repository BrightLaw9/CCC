package Graph.AStar;

import java.util.ArrayList;
import java.util.HashSet;

public class AStarGraph { 

    ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();

    HashSet<AStarNode> visitedSet; 

    private int numNodes; 

    public AStarGraph(int numNodes) { 
        this.numNodes = numNodes; 
        this.visitedSet = new HashSet<AStarNode>(this.numNodes);
        for (int i = 0; i < this.numNodes; i++) adjList.add(new ArrayList<Edge>()); 
    }

    public void addEdge(AStarNode start, AStarNode dest, int w) { 
        adjList.get(start.getNodeVal()).add(new Edge(start, dest, w)); 
        adjList.get(dest.getNodeVal()).add(new Edge(start, dest, w)); 
    }

    public ArrayList<Edge> getConnectedEdges(int node) { 
       return adjList.get(node); 
    }

    public void setVisitedNode(AStarNode node) { 
        this.visitedSet.add(node);
    }

    public HashSet<AStarNode> getVisitedSet() { 
        return this.visitedSet; 
    }

    public int getMinimumConnectedNode(int[] dist) { 
        // Get the minimum node weight that is currently available
        int minNode = -1; int maxWeight = Integer.MAX_VALUE; 

        for (int i = 0; i < this.numNodes; i++) { 
            if (dist[i] < maxWeight && !this.visitedSet.contains(i)) { 
                minNode = i; 
                maxWeight = dist[i]; 
            }
        }

        return minNode; 
    }

}
