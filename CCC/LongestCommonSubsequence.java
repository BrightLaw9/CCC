import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestCommonSubsequence {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        StringTokenizer tok1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tok1.nextToken()); 
        int m = Integer.parseInt(tok1.nextToken());

        StringTokenizer tok2 = new StringTokenizer(br.readLine());
        int[] first = new int[n]; 

        for (int i = 0; i < n; i++) {
            first[i] = Integer.parseInt(tok2.nextToken()); 
        }

        StringTokenizer tok3 = new StringTokenizer(br.readLine()); 
        int[] second = new int[m]; 

        for (int i = 0; i < m; i++) {
            second[i] = Integer.parseInt(tok3.nextToken()); 
        }

        int[][] dp = new int[n][m];

        int count = 0; 
        for (int j = 0; j < m; j++) { 
            if (second[j] == first[0])  {
                dp[0][j] = 1; 
                count = 1; 
            }
            else dp[0][j] = count; 
        }
        count = 0; 
        for (int i = 0; i < n; i++) { 
            if (first[i] == second[0]) { 
                dp[i][0] = 1; 
                count = 1; 
            }
            else dp[i][0] = count; 
        }

        for (int i = 1; i < n; i++) { 
            for (int j = 1; j < m; j++) { 
                if (first[i] == second[j]) dp[i][j] = 1 + dp[i-1][j-1]; 
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); 
            }
        }

        System.out.println(dp[n-1][m-1]); // 0 indexing
    }
}
