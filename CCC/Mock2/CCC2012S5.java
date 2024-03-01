package Mock2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC2012S5 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok = new StringTokenizer(br.readLine()); 

        int r = Integer.parseInt(tok.nextToken()); 
        int c = Integer.parseInt(tok.nextToken());

        int numCats = Integer.parseInt(br.readLine());
        boolean[][] hasCat = new boolean[r+1][c+1]; 
        for (int i = 0; i < numCats; i++) { 
            StringTokenizer tok1 = new StringTokenizer(br.readLine());
            int rowCat = Integer.parseInt(tok1.nextToken());
            int colCat = Integer.parseInt(tok1.nextToken());

            hasCat[rowCat][colCat] = true; 
        }

        int[][] dp = new int[r+1][c+1]; 
        boolean seenCat = false; 
        for (int i = 1; i <= c; i++) { 
            if (hasCat[1][i]) seenCat = true; 
            
            if (!seenCat) dp[1][i] = 1; 
            else dp[1][i] = 0; 
        }
        seenCat = false; 
        for (int i = 1; i <= r; i++) { 
            if (hasCat[i][1]) seenCat = true; 
            
            if (!seenCat) dp[i][1] = 1; 
            else dp[i][1] = 0; 
        }

        for (int i = 2; i <= r; i++) { 
            for (int j = 2; j <= c; j++) { 
                if (!hasCat[i][j]) { 
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
                    //System.out.println("r" + i + "c" + j+ "= " + dp[i][j]); 
                }
                // A path has to come from left and up (you can only move right and down)

                else dp[i][j] = 0; 
            }
        }

        System.out.println(dp[r][c]); 
    }
}
