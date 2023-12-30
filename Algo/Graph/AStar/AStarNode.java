package Graph.AStar;

public class AStarNode {

    private int val; 
    private int heur; 

    private int fVal;
    private int gVal; 

    public AStarNode(int val, int heur) { 
        this.val = val; 
        this.gVal = 0;
    }
    
    public int getNodeVal() { return this.val; }
    
    public void setGVal(int newGVal) { 
        if (newGVal < this.gVal) this.gVal = newGVal; 
    }

    public int getFVal() { 
        return this.gVal + this.heur; 
    }

    public int getGVal() { return this.gVal; }

    public int getHeurValue() { return this.heur; } 
}
