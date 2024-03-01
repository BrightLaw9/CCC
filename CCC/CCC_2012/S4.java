package CCC_2012;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S4 {

    public static Integer toInt(int[] coins) { 
        String ans = ""; 
        for (int coin : coins) { 
            ans += coin; 
        }
        return Integer.parseInt(ans); 
    }

    public static boolean equals(int[] one, int[] two) {
        if (one.length != two.length) return false; 
        for (int i = 0; i < one.length; i++) { 
            if (one[i] != two[i]) return false; 
        }
        return true; 
    }

    public static int find(int[] coins, int position) { 
        for (int i = 1; i < coins.length; i++) { 
            if (coins[i] == position) {
                 return i; 
            }
        }
        return -1; 
    }

    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        while (true) { 
            
            boolean possible = false; 
            
            int n = Integer.parseInt(in.next());

            if (n == 0) break;

            int[] coins = new int[n+1]; // Coins represent the POSITION that a coin number is at
            // Coin number -> coin position 
            int[] correct = new int[n+1]; 
            for (int i = 1; i <= n; i++) { 
                correct[i] += i; 
            }

            for (int i = 1; i <= n; i++) { 
                // Use one based indexing for positioning
                int coin = Integer.parseInt(in.next()); 
                coins[coin] = i; 
            }
            
            HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>(); 
            HashMap<Integer, Integer> numMoves = new HashMap<Integer, Integer>();

            Queue<int[]> queue = new LinkedList<int[]>(); 
            queue.add(coins); 
            visited.put(toInt(coins), true);
            numMoves.put(toInt(coins), 0);  
            
            while (!queue.isEmpty()) { 
                int[] curArrange = queue.poll(); 

                if (equals(curArrange, correct)) { 
                    System.out.println(numMoves.get(toInt(curArrange))); 
                    possible = true; 
                    break; 
                }

                boolean[] curVisited = new boolean[9]; // Note that once a coin has been placed on top of another, that spot is no longer accessible
                Arrays.fill(curVisited, false);
                // Go through all the coins numbers, and move the coin left or right if possible
                // Always n coins in the coins array
                for (int i = 1; i <= n; i++) { 
                    int curCoinPos = curArrange[i]; 

                    if (curVisited[curCoinPos]) continue; 
                    curVisited[curCoinPos] = true; 

                    if (curCoinPos > 1) { 
                        // Note 1 based index so only left available when > 1
                        
                        // Search from left to right in the coins array for coin at position cur -1
                        // Note: can never place a higher-value coin on top of lower coin, so coming first in the list must be on top

                        int coinAtPosition = find(curArrange, curCoinPos - 1); 
                        // If an empty slot or coin value is greater than there, stack
                        if (coinAtPosition == -1 || coinAtPosition > i) { 
                            int[] newArrange = curArrange.clone(); 
                            newArrange[i] = curCoinPos - 1; 
                            if (!visited.getOrDefault(toInt(newArrange), false)) { 
                                visited.put(toInt(newArrange), true); 
                                numMoves.put(toInt(newArrange), numMoves.get(toInt(curArrange)) + 1); 
                                queue.add(newArrange); 
                            }
                        }
                    }

                    // Moving current coin to the right if possible
                    if (curCoinPos < n) { 
                        // Possible coin slot at pos n
                        int coinRight = find(curArrange, curCoinPos + 1); 
                        if (coinRight == -1 || coinRight > i) { 
                            int[] newArrange = curArrange.clone(); 
                            newArrange[i] = curCoinPos + 1; 
                            if (!visited.getOrDefault(toInt(newArrange), false)) { 
                                visited.put(toInt(newArrange), true); 
                                numMoves.put(toInt(newArrange), numMoves.get(toInt(curArrange)) + 1); 
                                queue.add(newArrange); 
                            }
                        }
                    }
                }

            }
            if (!possible) System.out.println("IMPOSSIBLE"); 
        }
    }
}
