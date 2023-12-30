package Graph.AStar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearchGrid {
    public static class AStarNode implements Comparable<AStarNode> {

        private int xVal; 
        private int yVal; 
        private double heur; 
    
        private double fVal;
        private double gVal; 

        private int gValTraceBack; 
        private AStarNode parentNode; 

        public AStarNode(int xVal, int yVal) { 
            this.xVal = xVal; 
            this.yVal = yVal; 
            this.gVal = 0;
            this.gValTraceBack = Integer.MAX_VALUE; 
        }

        @Override
        public int compareTo(AStarNode other) { 
            return Double.compare(this.getFVal(), other.getFVal()); 
        }

        
        public boolean equals(AStarNode other) { 
            if (this.xVal != other.getNodeXVal()) return false; 
            if (this.yVal != other.getNodeYVal()) return false; 
            return true;  
        }
        
        public int getNodeXVal() { return this.xVal; }
        public int getNodeYVal() { return this.yVal; }
        
        public void setGVal(double newGVal) { 
            // if (newGVal < this.gVal || this.gVal == 0) {
                this.gVal = newGVal; 
                this.fVal = this.gVal + this.heur;
            // }  
        }

        public double getFVal() { 
            return this.gVal + this.heur; 
        }

        public double getGVal() { return this.gVal; }
        public int getGValTraceBack() { return this.gValTraceBack; }

        public void calculateHeurValue(AStarNode goal) { 
            // 8 - directional heurisitic
            int dx = Math.abs(this.getNodeXVal() - goal.getNodeXVal()); 
            int dy = Math.abs(this.getNodeYVal() - goal.getNodeYVal()); 

            int straightDistance = 1; 
            double diagonalDistance = Math.sqrt(2); 
            this.heur = straightDistance * (dx + dy) 
                            + (diagonalDistance - 2 * straightDistance) * Math.min(dx, dy); 
        } 
        public AStarNode getParentNode()  { return this.parentNode; }
        public void setParentNode(AStarNode node) { 
            this.parentNode = node; 
        }
    }

    public static class Edge {
    
        private double edgeWeight;
        private AStarNode startNode;
        private AStarNode endNode; 
    
    
        public Edge(AStarNode startNode, AStarNode endNode, double edgeWeight) { 
            this.edgeWeight = edgeWeight; 
            this.startNode = startNode;
            this.endNode = endNode;  
        }
    
    
        public double getWeight() { return this.edgeWeight; }
        public AStarNode getStartNode() { return this.startNode; }
        public AStarNode getDestNode() { return this.endNode; }
        
    }

    public static class AStarGrid { 
    
        private boolean[][] grid; // false is not occupied
        private HashSet<AStarNode> visitedSet; 
    
        private int numNodes; 
    
        public AStarGrid(boolean[][] grid) { 
            this.grid = grid; 
            this.visitedSet = new HashSet<AStarNode>(); 
        }

        public boolean checkValid(int x, int y) { 
            if (x < 0 || x >= grid[0].length 
                    || y < 0 || y >= grid.length) return false; 
            if (grid[y][x]) return false; 
            return true; 
        }
    
        public ArrayList<Edge> getConnectedEdges(AStarNode node) { 
           ArrayList<Edge> connectedEdges = new ArrayList<Edge>(); 

           int x = node.getNodeXVal(); 
           int y = node.getNodeYVal(); 
           
           if (checkValid(x+1, y)) connectedEdges.add(new Edge(node, new AStarNode(x+1, y), 1));  
           if (checkValid(x-1, y)) connectedEdges.add(new Edge(node, new AStarNode(x-1, y), 1));  
           if (checkValid(x, y+1)) connectedEdges.add(new Edge(node, new AStarNode(x, y+1), 1));  
           if (checkValid(x, y-1)) connectedEdges.add(new Edge(node, new AStarNode(x, y-1), 1));  

           //DIAGONALS
           if (checkValid(x+1, y+1)) connectedEdges.add(new Edge(node, new AStarNode(x+1, y+1), Math.sqrt(2)));  
           if (checkValid(x-1, y-1)) connectedEdges.add(new Edge(node, new AStarNode(x-1, y-1), Math.sqrt(2)));  
           if (checkValid(x+1, y-1)) connectedEdges.add(new Edge(node, new AStarNode(x+1, y-1), Math.sqrt(2)));  
           if (checkValid(x-1, y+1)) connectedEdges.add(new Edge(node, new AStarNode(x-1, y+1), Math.sqrt(2)));  
        
           return connectedEdges; 
        }
    
        public void setVisitedNode(AStarNode node) { 
            this.visitedSet.add(node); 
        }
    
        public HashSet<AStarNode> getVisitedSet() { 
            return this.visitedSet; 
        }

        public void clearVisitedSet() { 
            this.visitedSet.clear(); 
        }
    
    }

    public static boolean checkInsertableQueue(Collection<AStarNode> set, AStarNode curNode) { 
        for (AStarNode node : set) { 
            if (node.getNodeXVal() == curNode.getNodeXVal() && 
                    node.getNodeYVal() == curNode.getNodeYVal() &&
                    node.getFVal() < curNode.getFVal())  
                return false; 
        }
        return true; 
    }

    public AStarSearchGrid() { 

    }
    

    public static ArrayList<AStarNode> tracePath(AStarGrid graph, AStarNode destNode, AStarNode startNode) { 
        ArrayList<AStarNode> path = new ArrayList<AStarNode>(); 

        AStarNode curNode = destNode; 
        path.add(destNode); 

        while (!curNode.equals(startNode)) { 
            AStarNode parent = curNode.getParentNode(); 
            path.add(parent);
            curNode = parent;  
        } 

        Collections.reverse(path);
        return path; 
    }

    public static ArrayList<AStarNode> aStarSearch(AStarGrid grid, AStarNode startNode, AStarNode goalNode) { 

        boolean foundDest = false; 
        ArrayList<AStarNode> list = new ArrayList<AStarNode>(); 
        PriorityQueue<AStarNode> queue = new PriorityQueue<AStarNode>();

        AStarNode destNode = null; 

        if (!grid.checkValid(startNode.getNodeXVal(), startNode.getNodeYVal())) { 
            System.out.println("Invalid or occupied starting node position"); 
            return list;  
        } 
        if (!grid.checkValid(goalNode.getNodeXVal(), goalNode.getNodeYVal())) { 
            System.out.println("Invalid or occupied goal node position"); 
            return list; 
        }
        
        if (startNode.equals(goalNode)) { 
            System.out.println("All ready at destination"); 
            list.add(startNode); 
            return list; 
        }

        queue.add(startNode); 

        while (!foundDest) { 
            if (queue.isEmpty()) { 
                System.out.println("Path from start to finish not achievable"); 
                return list; 
            }

            AStarNode curNode = queue.poll(); 

            for (Edge successorEdge : grid.getConnectedEdges(curNode)) { 
                if (successorEdge.getDestNode().equals(goalNode)) { //Equals only comparing x and y values
                    foundDest = true; 
                    destNode = successorEdge.getDestNode(); 
                    destNode.setParentNode(curNode);
                }
                
                AStarNode successorNode = successorEdge.getDestNode(); 

                double curG = curNode.getGVal() + successorEdge.getWeight(); 
                if (curG < successorNode.getGVal() || successorNode.getGVal() == 0) {
                    successorNode.setGVal(curG);
                    successorNode.setParentNode(curNode); 
                    successorNode.calculateHeurValue(goalNode);
                }

                if (checkInsertableQueue(queue, successorNode) &&
                    checkInsertableQueue(grid.getVisitedSet(), successorNode)) { 
                        queue.add(successorNode); 
                    }

            }
            grid.setVisitedNode(curNode);
        }

        return tracePath(grid, destNode, startNode); 
        
        
    }

    public static void main(String[] args) { 

        boolean[][] grid = {
            {false, false, false}, 
            {true, false, false}, 
            {false, true, true}, 
            {false, false, false}
        };
        
        AStarGrid gridA = new AStarGrid(grid); 

        ArrayList<AStarNode> res = aStarSearch(gridA, new AStarNode(0, 0), new AStarNode(2, 3)); 

        String s = ""; 
        for (AStarNode node : res) { 
            s += String.format("(%s, %s) -> ", node.getNodeXVal(), node.getNodeYVal()); 
        }
        
        if (s.length() > 0) System.out.println(s.substring(0, s.length()-4)); 
    }
}
