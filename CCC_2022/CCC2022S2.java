package CCC_2022;

import java.util.HashMap;
import java.util.Scanner;

public class CCC2022S2 {

    static class Subset { 

        public int parent; 
        public int rank; 
        
        public Subset(int parent, int rank) { 
            this.parent = parent; 
            this.rank = rank; 
        }
    }

    static int findRoot(Subset[] subsets, int x) { 
        if (subsets[x].parent == x) return x; 
        subsets[x].parent = findRoot(subsets, subsets[x].parent); 
        return subsets[x].parent; 
    }

    static void union(Subset[] subsets, int x, int y) { 
        int xRoot = findRoot(subsets, x); 
        int yRoot = findRoot(subsets, y); 

        if (subsets[xRoot].rank > subsets[yRoot].rank) { 
            subsets[yRoot].parent = xRoot; 
        }
        else if (subsets[xRoot].rank < subsets[yRoot].rank) { 
            subsets[xRoot].parent = yRoot; 
        }
        else { 
            subsets[xRoot].parent = yRoot; 
            subsets[yRoot].rank++; 
        }
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in); 

        // Map to assign each name a unique number
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        int x = Integer.parseInt(in.nextLine()); 
        int count = 0; 
        String[][] samePeople = new String[x][2]; 
        for (int i = 0; i < x; i++) { 
            String[] names = in.nextLine().split(" "); 
            samePeople[i] = names; 
            if (map.get(names[0]) == null) map.put(names[0], count++); 
            if (map.get(names[1]) == null) map.put(names[1], count++); 
        }
        int y = Integer.parseInt(in.nextLine());
        String[][] differentPeople = new String[y][2]; 
        for (int j = 0; j < y; j++) { 
            String[] names2 = in.nextLine().split(" "); 
            differentPeople[j] = names2; 
            if (map.get(names2[0]) == null) map.put(names2[0], count++); 
            if (map.get(names2[1]) == null) map.put(names2[1], count++); 
        }

        // Perform Disjoint Set Union to know who is together in which groups
        // After, when checking the conditions, the people who should be in the same group who have different parent is penalty 
        // and different group with same parent is also penalty

        int MAX = 300000 + 1; 
        
        Subset[] subsets = new Subset[MAX]; 
        for (int s = 0; s < MAX; s++) { 
            subsets[s] = new Subset(s, 0); 
        }

        int g = Integer.parseInt(in.nextLine());
        for (int k = 0; k < g; k++) { 
            String[] names3 = in.nextLine().split(" "); 
            if (map.get(names3[0]) == null) map.put(names3[0], count++); 
            if (map.get(names3[1]) == null) map.put(names3[1], count++); 
            if (map.get(names3[2]) == null) map.put(names3[2], count++); 

            union(subsets, map.get(names3[0]), map.get(names3[1]));
            union(subsets, map.get(names3[1]), map.get(names3[2]));
        }

        int violations = 0; 

        // Check for violations
        for (String[] samePairs : samePeople) { 
            int f = findRoot(subsets, map.get(samePairs[0]));
            int s = findRoot(subsets, map.get(samePairs[1]));
            if (f != s) violations++; 
        }

        for (String[] diffPairs : differentPeople) { 
            int f = findRoot(subsets, map.get(diffPairs[0]));
            int s = findRoot(subsets, map.get(diffPairs[1]));
            if (f == s) violations++; 
        }
        System.out.println(violations);
    }
}