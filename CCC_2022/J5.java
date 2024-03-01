package CCC_2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class J5 {

    static class Tree {
        int r; int c;  
        public Tree(int r, int c) { 
            this.r = r; this.c = c; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine());
        int numTrees = Integer.parseInt(br.readLine());

        Tree[] trees = new Tree[numTrees+2];

        trees[0] = new Tree(0, 0);
        trees[1] = new Tree(n+1, n+1); // Prevent going out of bounds both top bottom, left and right

        for (int t = 2; t < numTrees+2; t++) {  
            StringTokenizer tok = new StringTokenizer(br.readLine());  
            trees[t] = new Tree(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));
        }

        // Sort trees from top down
        // For one tree in the sorted tree, this represents the top boundary of a 'rectangle'
            // Start a horizontal array
            // For another tree greater than 1 position from the top
                // Compute the height - note: exclude the indexes containing the tree
                // Form horizontal boundaries (vertical lines) - knowing the trees that have occurred in the previous loops
                // Range will only be limited to trees seen - the boundaries get bigger (lower bounds shifts down) as you go
                // For each horizontal boundary segment, 
                    //compute the width
                // Form the biggest possible square, min of width and height
                // Add the col to the horizontal components so when boundary expands, can account
        

        Arrays.sort(trees, new Comparator<Tree>() {
            @Override
            public int compare(Tree tree1, Tree tree2) { 
                return tree1.r - tree2.r; 
            }
        }); 
        // For square
        int biggestSide = 0; 

        for (int i = 0; i < trees.length; i++) { 
            ArrayList<Integer> horizontal = new ArrayList<Integer>();
            horizontal.add(0); horizontal.add(n+1); 
            for (int j = i+1; j < trees.length; j++) { 
                int width = 0; int height = trees[j].r - trees[i].r - 1; // Normally +1 inclusive of both indexes, so -1 exclusive both
                horizontal.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer one, Integer two) { 
                        return one - two; 
                    }
                });

                for (int k = 1; k < horizontal.size(); k++) { 
                    width = Math.max(width, horizontal.get(k) - horizontal.get(k-1) - 1); // Exclusive both
                }
                biggestSide = Math.max(biggestSide, Math.min(width, height)); 
                horizontal.add(trees[j].c); 
            }
        }

        System.out.println(biggestSide); 
    }
}
