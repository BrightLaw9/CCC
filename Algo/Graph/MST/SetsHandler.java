package Graph.MST;

import java.util.ArrayList;

public class SetsHandler {
    
    // Sets are useful checking if there are relationship between two objects further than there direct relation; 
    // Ex. 1 -> 2 and 2 -> 3, then {1, 2, 3} are in the same set - the parent of them are same 
    // Thus, in this way, in checking if there is a cycle in the graph 
    //(if another edge checked causes the two elements in the same set to be found, then a cycle is present). 

    public Subset[] subsets; // subset of a node (the index being their number) 

    public SetsHandler(Subset[] subsets) { 
        this.subsets = subsets; 
    }
    
    public int findRoot(int node) { 
        // Recursively finds the parent of each node; 
        if (this.subsets[node].parent == node) { 
            return node; 
        }
        // path compression - assign the root (highest up) of the subset to be its parent - shortening its path
        this.subsets[node].parent = findRoot(subsets[node].parent); 
        return this.subsets[node].parent; 
    } 

    public void union(int nodeOne, int nodeTwo) { 
        // Union by rank; move the smaller subset (tree structure) under the larger
        int nodeOneRoot = findRoot(nodeOne);
        int nodeTwoRoot = findRoot(nodeTwo);

        if (this.subsets[nodeOneRoot].rank > this.subsets[nodeTwoRoot].rank) { 
            // Smaller subset's parent becomes larger subset's
            this.subsets[nodeTwoRoot].parent = nodeOneRoot;
        }
        else if (this.subsets[nodeOneRoot].rank < this.subsets[nodeTwoRoot].rank) { 
            this.subsets[nodeOneRoot].parent = nodeTwoRoot;
        }
        else { 
            // Same size; no matter which one, rank has to increase for one that slides under
            this.subsets[nodeOneRoot].parent = nodeTwoRoot; 
            this.subsets[nodeTwoRoot].rank++; // nodeTwo is parent, so its now highest
        }
    }
    


}
