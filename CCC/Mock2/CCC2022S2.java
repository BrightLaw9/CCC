package Mock2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CCC2022S2 {

    public static int findRoot(int[] subsets, int num) {
        if (subsets[num] == num) return num; 

        subsets[num] = findRoot(subsets, subsets[num]); 
        return subsets[num]; 
    }

    public static void union(int[] subsets, int one, int two) { 
        subsets[two] = one; 
    }
    
    public static void main(String[] args) throws IOException { 
        // Buffered reader much faster!!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int numSame = Integer.parseInt(br.readLine()); 

        HashMap<String, Integer> map = new HashMap<String, Integer>(); 

        int[][] sameStudents = new int[numSame][2]; 
        int count = 0; 
        for (int i = 0; i < numSame; i++) { 
            StringTokenizer tok = new StringTokenizer(br.readLine()); 
            String one = tok.nextToken(); 
            String two = tok.nextToken(); 
            
            if (!map.containsKey(one)) map.put(one, count++); 
            if (!map.containsKey(two)) map.put(two, count++); 

            sameStudents[i] = new int[] {map.get(one), map.get(two)}; 
        }

        int numDiff = Integer.parseInt(br.readLine()); 

        int[][] diffStudents = new int[numDiff][2]; 
        for (int i = 0; i < numDiff; i++) { 
            StringTokenizer tok = new StringTokenizer(br.readLine()); 
            String one = tok.nextToken(); 
            String two = tok.nextToken(); 
            
            if (!map.containsKey(one)) map.put(one, count++); 
            if (!map.containsKey(two)) map.put(two, count++); 

            diffStudents[i] = new int[] {map.get(one), map.get(two)}; 
        }

        int numGroups = Integer.parseInt(br.readLine()); 

        int[] subsets = new int[500000+1]; // Each index stores the parent of that number index

        for (int i = 1; i <= 500000; i++) { 
            subsets[i] = i; 
        }

        for (int i = 0; i < numGroups; i++) { 
            StringTokenizer tok = new StringTokenizer(br.readLine()); 
            String one = tok.nextToken(); String two = tok.nextToken(); String three = tok.nextToken(); 

            if (!map.containsKey(one)) map.put(one, count++); 
            if (!map.containsKey(two)) map.put(two, count++); 
            if (!map.containsKey(three)) map.put(three, count++); 

            union(subsets, map.get(one), map.get(two));
            union(subsets, map.get(two), map.get(three));
        }

        // Check violations
        int violations = 0; 
        for (int[] samePairs : sameStudents) { 
            if (findRoot(subsets, samePairs[0]) != findRoot(subsets, samePairs[1])) 
                violations++; 
        }

        for (int[] diffPairs : diffStudents) { 
            if (findRoot(subsets, diffPairs[0]) == findRoot(subsets, diffPairs[1])) 
                violations++;  
        }

        System.out.println(violations); 
    }
}
