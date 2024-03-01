package CCC_2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4BFS {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        StringTokenizer tok = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(tok.nextToken()); 
        int m = Integer.parseInt(tok.nextToken()); 

        ArrayList<ArrayList<Integer>> tallerToShort = new ArrayList<ArrayList<Integer>>(n+1);
        
        for (int i = 0; i <= n; i++) { 
            tallerToShort.add(new ArrayList<Integer>()); 
        }

        for (int i = 0; i < m; i++) { 
            StringTokenizer tok1 = new StringTokenizer(br.readLine()); 
            tallerToShort.get(Integer.parseInt(tok1.nextToken())).add(Integer.parseInt(tok1.nextToken())); 
        }

        StringTokenizer tok2 = new StringTokenizer(br.readLine());
        int personP = Integer.parseInt(tok2.nextToken()); 
        int personQ = Integer.parseInt(tok2.nextToken());

        Queue<Integer> queue = new LinkedList<Integer>(); 
        boolean[] visited = new boolean[n+1]; 
        Arrays.fill(visited, false);
        
        queue.add(personP); 
        visited[personP] = true; 
        
        while (!queue.isEmpty()) { 
            int shorterPerson = queue.poll(); 

            if (shorterPerson == personQ) { 
                System.out.println("yes"); 
                System.exit(0); 
            }

            for (int connected : tallerToShort.get(shorterPerson)) { 
                if (!visited[connected]) { 
                    queue.add(connected); 
                    visited[connected] = true; 
                }
            }
        }

        // Reset and try to see if Q is known taller than P, thus, P is shorter than Q

        Arrays.fill(visited, false);
        queue.add(personQ); 

        while (!queue.isEmpty()) { 
            int shorterPerson = queue.poll(); 

            if (shorterPerson == personP) { 
                System.out.println("no"); 
                System.exit(0); 
            }

            for (int connected : tallerToShort.get(shorterPerson)) { 
                if (!visited[connected]) { 
                    queue.add(connected); 
                    visited[connected] = true; 
                }
            }
        }

        System.out.println("unknown"); 
    }
}
