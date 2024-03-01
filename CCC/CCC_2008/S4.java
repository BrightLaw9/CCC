package CCC_2008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class S4 {

    static int max = 0; 

    public static void solve(ArrayList<Integer> hand) { 
        // Permute through all possibilties of cards with operations

        if (hand.size() == 1) { 
            int ans = hand.get(0); 
            if (ans <= 24 && ans > max) max = ans; 
            return; 
        }

        for (int i = 0; i < hand.size(); i++) { 
            int card1 = hand.remove(i); 
            for (int j = i; j < hand.size(); j++) { 
                int card2 = hand.remove(j); // Really should be add 1, Minus one b/c card1 remove shifts left

                int add = card1 + card2; 
                ArrayList<Integer> copy = new ArrayList<Integer>(hand); 
                copy.add(add); 
                solve(copy); 

                int sub1 = card1 - card2; 
                ArrayList<Integer> copy2 = new ArrayList<Integer>(hand); 
                copy2.add(sub1); 
                solve(copy2); 

                int sub2 = card2 - card1; 
                ArrayList<Integer> copy3 = new ArrayList<Integer>(hand); 
                copy3.add(sub2); 
                solve(copy3); 

                int mult = card1 * card2; 
                ArrayList<Integer> copy4 = new ArrayList<Integer>(hand); 
                copy4.add(mult); 
                solve(copy4); 

                if (card2 != 0 && card1 % card2 == 0) { 
                    int div1 = card1 / card2; 
                    ArrayList<Integer> copy5 = new ArrayList<Integer>(hand); 
                    copy5.add(div1); 
                    solve(copy5); 
                }

                if (card1 != 0 && card2 % card1 == 0) { 
                    int div2 = card2 / card1; 
                    ArrayList<Integer> copy6 = new ArrayList<Integer>(hand); 
                    copy6.add(div2); 
                    solve(copy6); 
                }

                hand.add(j, card2);
            }
            hand.add(i, card1); 
        }
    }
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int numHands = Integer.parseInt(br.readLine());

        for (int hand = 0; hand < numHands; hand++) {

            ArrayList<Integer> curHand = new ArrayList<Integer>(4);
            
            for (int i = 0; i < 4; i++) {
                curHand.add(Integer.parseInt(br.readLine())); 
            }

            max = 0; 

            solve(curHand);

            System.out.println(max);
        }
    }
}
