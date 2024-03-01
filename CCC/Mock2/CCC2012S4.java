package Mock2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC2012S4 {
    
    // NOT A Tower of Hanoi problem!! In Tower of Hanoi, can move to any rod. in this, only adjacent
    // Thus, brute force by computing all combinations of valid moves to see if valid config is formed

    public static int findCoinAtPos(int coinPos, int[] coins) { 
        // the smallest value of coin will be at top, return first occurence of coin 
        for (int i = 1; i < coins.length; i++) { 
            if (coins[i] == coinPos) return i; 
        }
        return -1; 
    }

    public static int toInt(int[] coins) { 
        String ans = ""; 
        for (int i : coins) { 
            ans += i; 
        }
        return Integer.parseInt(ans); 
    }
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        while (true) { 
            int n = Integer.parseInt(br.readLine()); 
            if (n == 0) break; 

            StringTokenizer tok = new StringTokenizer(br.readLine());

            int[] posCoins = new int[n+1]; 
            int[] correct = new int[n+1]; 

            for (int i = 1; i <= n; i++) { 
                correct[i] = i; 
                posCoins[Integer.parseInt(tok.nextToken())] = i;   
            }

            HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();

            Queue<int[]> queue = new LinkedList<int[]>();
            
            HashMap<Integer, Integer> numMoves = new HashMap<Integer, Integer>();

            queue.add(posCoins); 
            visited.put(toInt(posCoins), true);  
            numMoves.put(toInt(posCoins), 0); 
            boolean possible = false; 
            while (!queue.isEmpty()) { 
                // Perform BFS on all possibilities for coin moving
                int[] curArrange = queue.poll(); 
                if (Arrays.equals(curArrange, correct)) { 
                    //for (int i : curArrange) System.out.print(i + " "); 
                    System.out.println(numMoves.get(toInt(curArrange))); 
                    possible = true; 
                    break; 
                }

                boolean[] curVisited = new boolean[9]; // To prevent accessing coins that would be below a coin that has been moved
                Arrays.fill(curVisited, false);
                for (int i = 1; i <= n; i++) { 
                    // Rearranging, if possible, each coin number
                    // Find where it is
                    int curCoinPos = curArrange[i]; 
                    if (curVisited[curCoinPos]) continue; 
                    curVisited[curCoinPos] = true; 

                    // Find what coins are to the left and right of it, if this coin is smaller than, or if that spot is empty, then move
                    if (curCoinPos > 1) { 
                        int leftCoin = findCoinAtPos(curCoinPos-1, curArrange); 
                        if (leftCoin == -1 || i < leftCoin) { 
                            int[] newArrange = curArrange.clone(); 
                            newArrange[i] = curCoinPos - 1; 
                            if (!visited.getOrDefault(toInt(newArrange), false)) { 
                                queue.add(newArrange); 
                                visited.put(toInt(newArrange), true); 
                                numMoves.put(toInt(newArrange), numMoves.get(toInt(curArrange)) + 1); 
                            }
                        }
                    }
                    if (curCoinPos < n) { 
                        int rightCoin = findCoinAtPos(curCoinPos+1, curArrange); 
                        if (rightCoin == -1 || i < rightCoin) { 
                            int[] newArrange = curArrange.clone(); 
                            newArrange[i] = curCoinPos + 1; 
                            if (!visited.getOrDefault(toInt(newArrange), false)) { 
                                queue.add(newArrange); 
                                visited.put(toInt(newArrange), true); 
                                numMoves.put(toInt(newArrange), numMoves.get(toInt(curArrange)) + 1); 
                            }
                        }
                    }
                }
            }
            if (!possible) System.out.println("IMPOSSIBLE"); 
        }
    }
}
