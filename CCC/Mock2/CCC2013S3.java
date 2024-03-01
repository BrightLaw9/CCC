package Mock2; 

import java.util.Arrays;
import java.util.Scanner;

public class CCC2013S3 {

    // Passed!
    static int favTeam; 
    static int count; 
    static int numWins; 
    static int numPlayed; 

    static void markedPlayed(boolean[][] played, int one, int two) { 
        played[one][two] = true;
        played[two][one] = true; 
    }

    static void calcPoints(int[] points, int teamOne, int teamTwo, int scoreOne, int scoreTwo) { 
        if (scoreOne > scoreTwo) points[teamOne] += 3; 
        else if (scoreTwo > scoreOne) points[teamTwo] += 3; 
        else { 
            points[teamOne] += 1; 
            points[teamTwo] += 1; 
        }
    }

    static boolean[][] copyPlayed(boolean[][] played) { 
        boolean[][] copyPlayed = new boolean[played.length][played[0].length]; 
        for (int i = 0; i < played.length; i++) { 
            copyPlayed[i] = played[i].clone(); 
        }
        return copyPlayed; 
    }

    static boolean findNonPlayed(boolean[][] played) { 
        boolean nonPlayed = false; 
        for (int i = 1; i < played.length-1; i++) { 
            for (int j = i+1; j < played[0].length; j++) { 
                if (!played[i][j]) nonPlayed = true; 
            }
        }
        return nonPlayed; 
    }

    static void permute(boolean[][] played, int[] points, int layerNum, boolean first) { 
        boolean noPlay = true; 
        layerNum++; 
        for (int i = 1; i < played.length-1; i++) { 
            for (int j = i+1; j < played[0].length; j++) { 
                // System.out.println("i" + i + "j" + j); 
                if (!played[i][j]) {
                    noPlay = false; 
                    boolean[][] playedCopy = copyPlayed(played); 
                    int[] pointsCopyOne = points.clone(); // W/L
                    int[] pointsCopyTwo = points.clone(); // L/W
                    int[] pointsCopyThree = points.clone(); //T/T

                    markedPlayed(playedCopy, i, j);
                    calcPoints(pointsCopyOne, i, j, 3, 2); // Arbitrary points
                    calcPoints(pointsCopyTwo, i, j, 2, 3);
                    calcPoints(pointsCopyThree, i, j, 1, 1);

                    permute(playedCopy, pointsCopyOne, layerNum, false);
                    permute(playedCopy, pointsCopyTwo, layerNum, false);
                    permute(playedCopy, pointsCopyThree, layerNum, false);
                    break; 
                }
            }
            if (!noPlay) break; 
        }
        if (noPlay && !findNonPlayed(played) && !first) { 
            int maxPoint = -1; 
            for (int i = 1; i <= 4; i++) { 
                if (i == favTeam) continue; 
                maxPoint = Math.max(maxPoint, points[i]); 
            }
            if (points[favTeam] > maxPoint) numWins++; 
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        favTeam = in.nextInt(); 
        numPlayed = in.nextInt(); 

        int[] points = new int[4+1]; // points for a team scored at index
        boolean[][] played = new boolean[4+1][4+1]; 

        Arrays.fill(points, 0); 
        for (int i = 0; i <= 4; i++) { 
            Arrays.fill(played[i], false); 
        }

        for (int i = 0; i < numPlayed; i++) { 
            int teamA = in.nextInt(); 
            int teamB = in.nextInt(); 
            int scoreA = in.nextInt(); 
            int scoreB = in.nextInt(); 

            markedPlayed(played, teamA, teamB);
            calcPoints(points, teamA, teamB, scoreA, scoreB);
        }
        permute(played, points, 0, true);

        System.out.println(numWins); 
    }
}