package CCC_2013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class S3 {

    // Passed on DMOJ

    public static void markPlayed(boolean[][] played, int one, int two) { 
        played[one][two] = true;
        played[two][one] = true; 
    }

    public static ArrayList<Integer[]> findNonPlayed(boolean[][] played) {
        ArrayList<Integer[]> toPlay = new ArrayList<Integer[]>(); 
        for (int i = 1; i <= 4; i++) { 
            for (int j = i+1; j <= 4; j++) { 
                if (!played[i][j]) { 
                    toPlay.add(new Integer[] {i, j}); 
                }
            }
        }
        return toPlay; 
    }

    static int numWin;

    static int favTeam; 

    public static void eval(int[] points, boolean[][] copyPlayed, Integer[] pair) { 
        // 1st pair win, 2nd pair lose
        int[] pointsCopy = Arrays.copyOf(points, points.length); 
        pointsCopy[pair[0]] += 3; 
        find(pointsCopy, copyPlayed, false);

        // 1st pair lose, 2nd pair win
        int[] pointsCopy2 = Arrays.copyOf(points, points.length); 
        pointsCopy2[pair[1]] += 3; 
        find(pointsCopy2, copyPlayed, false);

        // Tie
        int[] pointsCopy3 = Arrays.copyOf(points, points.length); 
        pointsCopy3[pair[0]] += 1;
        pointsCopy3[pair[1]] += 1;  
        find(pointsCopy3, copyPlayed, false);
    }

    public static void find(int[] points, boolean[][] played, boolean first) { 
        // for (int i = 0; i <= 4; i++) { 
        //     for (int j = 0; j <= 4; j++) { 
        //         System.out.println(i + ", " + j + " :" + played[i][j]) ;
        //     }
        // }
        //for (Integer[] pair : findNonPlayed(played)) { // IMPORTANT!!! Complete search don't iterate starting pairs!!
        if (!findNonPlayed(played).isEmpty()) { 
        ArrayList<Integer[]> nonPlayed = findNonPlayed(played); 
            boolean[][] copyPlayed = new boolean[played.length][played[0].length]; 
            for (int i = 0; i < played.length; i++) { 
                copyPlayed[i] = played[i].clone(); 
            }
            markPlayed(copyPlayed, nonPlayed.get(0)[0], nonPlayed.get(0)[1]);
            eval(points, copyPlayed, nonPlayed.get(0));
        }
        //}

        else if (!first) // Ignore the initial call of this recursive function
        { 
            int max = -1; 
            for (int i = 1; i <= 4; i++) { 
                if (i == favTeam) continue; 
                if (points[i] > max) max = points[i];
            }

            if (max < points[favTeam]) numWin += 1; 
        }
    }
    
    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in); 

        favTeam = Integer.parseInt(in.nextLine());

        int numPlayed = Integer.parseInt(in.nextLine());

        int[] points = new int[4+1]; 

        Arrays.fill(points, 0);

        boolean[][] played = new boolean[4+1][4+1]; 

        for (int i = 0; i <= 4; i++) { 
            for (int j = 0; j <= 4; j++) {
                if (i == j) played[i][j] = true; 
                else played[i][j] = false;  
            }
        }

        for (int g = 0; g < numPlayed; g++) { 
            String[] data = in.nextLine().split(" "); 
            int teamOne = Integer.parseInt(data[0]);
            int teamTwo = Integer.parseInt(data[1]); 
            int scoreOne = Integer.parseInt(data[2]); 
            int scoreTwo = Integer.parseInt(data[3]); 

            markPlayed(played, teamOne, teamTwo);

            if (scoreOne > scoreTwo) points[teamOne] += 3; 
            else if (scoreTwo > scoreOne) points[teamTwo] += 3; 
            else {
                points[teamOne] += 1; 
                points[teamTwo] += 1; 
            }
        }

        // Recursively determine all possibilities

        numWin = 0; 

        find(Arrays.copyOfRange(points, 0, points.length), Arrays.copyOf(played, played.length), true);
        
        System.out.println(numWin); 

    }
}
