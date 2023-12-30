package Graph;

public class Node { 
        
    private int weight;
    private int val;  

    public Node(int val, int weight) { 
        this.weight = weight; 
        this.val = val; 
    }

    public int getWeight() { return this.weight; }
    public int getValue() { return this.val; }
    
}
