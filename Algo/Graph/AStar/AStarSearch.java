package Graph.AStar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch {


    public static class AStarNode implements Comparable<AStarNode> {

        private int val; 
        private int heur; 

        private int fVal;
        private int gVal; 

        private int gValTraceBack; 

        private AStarNode parentNode; 

        public AStarNode(int val, int heur) { 
            this.val = val; 
            this.gVal = 0;
            this.gValTraceBack = Integer.MAX_VALUE; 
            this.heur = heur; 
        }

        @Override
        public int compareTo(AStarNode other) { 
            return Integer.compare(this.getFVal(), other.getFVal()); 
        }
        
        public int getNodeVal() { return this.val; }
        
        public void setGVal(int newGVal) { 
                this.gVal = newGVal; 
                this.gValTraceBack = newGVal; 
                this.fVal = this.gVal + this.heur;
        }

        public int getFVal() { 
            return this.gVal + this.heur; 
        }

        public int getGVal() { return this.gVal; }
        public int getGValTraceBack() { return this.gValTraceBack; }

        public int getHeurValue() { return this.heur; } 
        public AStarNode getParentNode()  { return this.parentNode; }
        public void setParentNode(AStarNode node) { 
            this.parentNode = node; 
        }
    }


    public static class Edge {
    
        private int edgeWeight;
        private AStarNode startNode;
        private AStarNode endNode; 
    
    
        public Edge(AStarNode startNode, AStarNode endNode, int edgeWeight) { 
            this.edgeWeight = edgeWeight; 
            this.startNode = startNode;
            this.endNode = endNode;  
        }
    
    
        public int getWeight() { return this.edgeWeight; }
        public AStarNode getStartNode() { return this.startNode; }
        public AStarNode getDestNode() { return this.endNode; }
        
    }

    public static class AStarGraph { 

        private ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
    
        private HashSet<Integer> visitedSet; 
        private HashSet<AStarNode> visitedNodeSet; 
    
        private int numNodes; 
    
        public AStarGraph(int numNodes) { 
            this.numNodes = numNodes; 
            this.visitedSet = new HashSet<Integer>(this.numNodes);
            this.visitedNodeSet = new HashSet<AStarNode>(this.numNodes); 
            for (int i = 0; i < this.numNodes; i++) adjList.add(new ArrayList<Edge>()); 
        }
    
        public void addEdge(AStarNode start, AStarNode dest, int w) { 
            adjList.get(start.getNodeVal()).add(new Edge(start, dest, w)); 
            adjList.get(dest.getNodeVal()).add(new Edge(start, dest, w)); 
        }
    
        public ArrayList<Edge> getConnectedEdges(AStarNode node) { 
            ArrayList<Edge> connectedEdges = new ArrayList<Edge>(); 

            for (Edge edge : adjList.get(node.getNodeVal())) { 
                if (!this.visitedSet.contains(edge.getDestNode().getNodeVal())) connectedEdges.add(edge);
            }
           return connectedEdges; 
        }

        public ArrayList<Edge> getTraceBackConnectedEdges(AStarNode node) { 
            ArrayList<Edge> connectedEdges = new ArrayList<Edge>(); 

            for (Edge edge : adjList.get(node.getNodeVal())) { 
                if (!this.visitedSet.contains(edge.getStartNode().getNodeVal())) connectedEdges.add(edge);
            }
           return connectedEdges; 
        }
    
        public void setVisitedNode(AStarNode node) { 
            this.visitedSet.add(node.getNodeVal());
            this.visitedNodeSet.add(node); 
        }
    
        public HashSet<AStarNode> getVisitedSet() { 
            return this.visitedNodeSet; 
        }

        public void clearVisitedSet() { 
            this.visitedNodeSet.clear();
            this.visitedSet.clear(); 
        }
    
    }

    public static boolean checkInsertableQueue(Collection<AStarNode> set, AStarNode curNode) { 
        for (AStarNode node : set) { 
            if (node.getNodeVal() == curNode.getNodeVal() && 
                    node.getFVal() < curNode.getFVal())  
                return false; 
        }
        return true; 
    }

    public AStarSearch() { 

    }
    

    public static ArrayList<Integer> tracePath(AStarGraph graph, AStarNode destNode, AStarNode startNode) { 
        ArrayList<Integer> path = new ArrayList<Integer>(); 

        AStarNode curNode = destNode; 
        path.add(destNode.getNodeVal()); 

        while (curNode.getNodeVal() != (startNode.getNodeVal())) { 
                AStarNode parent = curNode.getParentNode(); 
                path.add(parent.getNodeVal());
                curNode = parent;  
        } 

        Collections.reverse(path);
        return path; 
    }

    public static ArrayList<Integer> aStarSearch(AStarGraph graph, AStarNode start) { 
        // Returns a list representing the shortest path from goal to start 

        boolean foundDest = false; 
        PriorityQueue<AStarNode> queuedNodes = new PriorityQueue<AStarNode>();
        
        queuedNodes.add(start);  

        AStarNode destNode = null; 

        while (!queuedNodes.isEmpty() && !foundDest)
        {
            AStarNode minNode = queuedNodes.poll(); 
            
            for (Edge edge : graph.getConnectedEdges(minNode)) { 
                if (edge.getDestNode().getHeurValue() == 0) {
                    destNode = edge.getDestNode();  
                    // foundDest = true; 
                    // break; 
                }
            
            AStarNode curNode = edge.getDestNode(); 
            //graph.setVisitedNode(curNode);
            int curG = minNode.getGVal() + edge.getWeight(); 
            if (curG < curNode.getGVal() || curNode.getGVal() == 0) {
                curNode.setGVal(curG);
                curNode.setParentNode(minNode); 
            }
            
            queuedNodes.add(curNode); 

            }
            graph.setVisitedNode(minNode);
        }

        return tracePath(graph, destNode, start); 
        
    }

    public static void main(String[] args) { 

        AStarGraph g = new AStarGraph(7); 

        AStarNode[] arr = new AStarNode[] { 
            new AStarNode(0, 5), 
            new AStarNode(1, 4), 
            new AStarNode(2, 2), 
            new AStarNode(3, 4), 
            new AStarNode(4, 0), 
            new AStarNode(5, 2), 
            new AStarNode(6, 5)
        }; 

        g.addEdge(arr[0], arr[1], 1);
        g.addEdge(arr[1], arr[2], 1);
        g.addEdge(arr[2], arr[4], 5);
        g.addEdge(arr[0], arr[6], 2); 
        g.addEdge(arr[6], arr[5], 3);
        g.addEdge(arr[1], arr[5], 4);
        g.addEdge(arr[5], arr[4], 3);
        g.addEdge(arr[2], arr[3], 1);
        g.addEdge(arr[3], arr[4], 1);
        

        // g.addEdge(new AStarNode(1, 10), new AStarNode(2, 6), 3);
        // g.addEdge(new AStarNode(2, 6), new AStarNode(0, 5), 1);
        // g.addEdge(new AStarNode(2, 6), new AStarNode(3, 3), 7);
        // g.addEdge(new AStarNode(3, 3), new AStarNode(4, 1), 1);
        // g.addEdge(new AStarNode(0, 5), new AStarNode(4, 1), 3);
        // g.addEdge(new AStarNode(4, 1), new AStarNode(5, 0), 3);

        AStarGraph g2 = new AStarGraph(6); 
        // g2.addEdge(new AStarNode(1, 1), new AStarNode(2, 1), 1);
        // g2.addEdge(new AStarNode(2, 1), new AStarNode(0, 1), 1);
        AStarNode zeroNode = new AStarNode(0, 3); 
        AStarNode firstNode = new AStarNode(1, 2); 
        AStarNode secondNode = new AStarNode(2, 1); 
        AStarNode thirdNode = new AStarNode(3, 0); 
        //g2.addEdge(zeroNode, thirdNode, 3);
        g2.addEdge(zeroNode, firstNode, 2);
        g2.addEdge(zeroNode, secondNode, 2); 
        g2.addEdge(secondNode, thirdNode, 3);
        g2.addEdge(firstNode, thirdNode, 2);
        
        ArrayList<Integer> res = aStarSearch(g, arr[0]); 
        
        System.out.println("Path from start to goal: "); 
        String s = ""; 
        for (int nodeVal : res)  { 
            s += String.format("%s -> ", nodeVal); 
        }
        System.out.print(s.substring(0, s.length()-4)); 
    }
}
