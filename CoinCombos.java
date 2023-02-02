import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class CoinCombos { 

    public static void main(String[] args) throws IOException { 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer initValues = new StringTokenizer(in.readLine()); 
        int N = Integer.parseInt(initValues.nextToken()); 
        int X = Integer.parseInt(initValues.nextToken()); 

        StringTokenizer coinValues = new StringTokenizer(in.readLine()); 

        // Transfer input coin values into array
        int[] coins = new int[N]; 
        for (int i = 0; i < N; i++) { 
           coins[i] = Integer.parseInt(coinValues.nextToken()); 
        }

        // For storing coin sums up to the desired sum
        int[] dp = new int[X+1]; 
        dp[0] = 1; 


        // If we loop through i (coins) first, we create ordered distinct pairs
        // If sum is first, then coins, then all permutations 
        for (int i = 0; i < N; i++) {

        for (int sum = 1; sum <= X; sum++) {
             
            //for (int i = 0; i < N; i++) { 
                
                
                // A sum can only be made with coins smaller than it
                if (coins[i] <= sum) { 
                    dp[sum] += dp[sum-coins[i]];  
                } 
            
                }
            }
       // }
        

        System.out.println(dp[X] /*% 1e9+7*/);

    }
}