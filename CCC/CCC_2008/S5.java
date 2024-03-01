package CCC_2008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5 {

    // Logic : recursively determine if a particles are in favour for you by seeing if any of the ways you lower can get one of the particles below zero

    // However, if the opponent finds the winning position (by all the combos) before you, you lose

    // If there is one way Patrick can win, he can form the winning strategy first; if no possible ways, Roland wins (as he must form -- cards have to run out on his turn)

    static int[][][][] dp; 
    public static boolean findCombo(int a, int b, int c, int d) { 
        if (a < 0 || b < 0 || c < 0 || d < 0) { 
            // This means the previous combo / recursive call causes a losing position, therefore it is winning
            return true; 
        }

        if (dp[a][b][c][d] != 0) { 
            if (dp[a][b][c][d] == 1) return true; 
            return false; 
        }

        boolean winning = false; 
        // Negate as the opponent will discover this combo if it is a recursive call one up the stack
        // Winning or the call -- Patrick only needs one working solution
        winning = winning || !findCombo(a-2, b-1, c, d-2); 
        winning = winning || !findCombo(a-1, b-1, c-1, d-1); 
        winning = winning || !findCombo(a, b, c-2, d-1);
        winning = winning || !findCombo(a, b-3, c, d);
        winning = winning || !findCombo(a-1, b, c, d-1);

        if (winning) dp[a][b][c][d] = 1; 
        else dp[a][b][c][d] = -1; 
        return winning; 
    }
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine()); 

        for (int i = 0; i < n; i++) { 
            StringTokenizer tok = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tok.nextToken()); int b = Integer.parseInt(tok.nextToken());
            int c = Integer.parseInt(tok.nextToken()); int d = Integer.parseInt(tok.nextToken());

            dp = new int[a+1][b+1][c+1][d+1]; 

            if (findCombo(a, b, c, d)) { 
                System.out.println("Patrick"); 
            }
            else { 
                System.out.println("Roland"); 
            }

        }
    }
}
