package Graph.AStar;

public class Edge {
    
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
