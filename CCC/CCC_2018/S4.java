package CCC_2018;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class S4 {

    // MLE error

    public static HashMap<Integer, Long> trees;

    public static void solve(int weight) { 

        long totalTrees = 0; 

        int nextIndex = -1; 
        for (int end = weight; end >= 2; end = nextIndex) { 
            int nextSubTreeWeight = Math.floorDiv(weight, end);
            nextIndex = Math.floorDiv(weight, nextSubTreeWeight+1); 

            if (trees.get(nextSubTreeWeight) == null) { 
                // Need to solve
                solve(nextSubTreeWeight); 
            }

            totalTrees += trees.get(nextSubTreeWeight) * (end - nextIndex); 
        } 
        trees.put(weight, totalTrees); 
    }

    // DP approach as we are solving for many subtree weights
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); 

        int treeWeight = Integer.parseInt(in.next());

        trees = new HashMap<Integer, Long>(treeWeight+1); 

        // Single node is simply one instance
        trees.put(1, (long) 1); 

        if (treeWeight > 1) solve(treeWeight);

        // Floor the treeWeight / 3 result to get the number of trees that can undergo further subdivide
        // Ex. 2 -> only can be 1,1 (2 subtrees)

        // 3 branches -> can be 1,1,1 as well 
        // 4 -> 2,2 & 1,1,1 &  1,1,1,1

        // There will be all 1s filled down to 2 branches of higher numbers
        // BranchNum is the number of branches that spans from root


        System.out.println(trees.get(treeWeight));
    }
}
