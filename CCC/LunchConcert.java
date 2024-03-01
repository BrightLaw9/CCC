import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


// Passed on DMOJ

public class LunchConcert {

    static int[][] persons; 

    public static int toInt(String num) { 
        return Integer.parseInt(num); 
    }

    //Calculate how much each person has to walk for each concert location
    public static long getWalkTime(int pos) { 
        long out = 0; 

        for (int[] p : persons) { 
            long walkTime = Math.abs(pos-p[0])-p[2]; // Concert - person pos - hearing distance
            // Must be long, might have max hearing distance (p[2]) and be next to concert pos-p[0] = 0; so what would have been - overflowed to pos

            if (walkTime > 0) { 
                out += walkTime * p[1]; // Multiply by walk speed (if larger, then slower)
            }
        }
        return out; 
    }
    // V-shaped principle
    public static void main(String args[]) throws IOException { 

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 

        int n = toInt(input.readLine()); 

        persons = new int[n][3]; 

        int high = 0; 
        int low = Integer.MAX_VALUE;  

        // Loop to enter 
        for (int i = 0; i < n; i++) { 
            // Get position -- stored in 0
            StringTokenizer line = new StringTokenizer(input.readLine());
            persons[i][0] = toInt(line.nextToken()); 
            // Get walking speed -- stored in 1 (how fast can travel one metre)
            persons[i][1] = toInt(line.nextToken()); 
            // Get hearing distance -- stored in 2
            persons[i][2] = toInt(line.nextToken());
            
            // Determine highest and lowest positions in list through iterations
            if (persons[i][0] < low) { 
                low = persons[i][0]; 
            }
            if (persons[i][0] > high) { 
                high = persons[i][0];
            }
        }

        // Perform binary search 
        int mid = 0; 
        long time = 0; 
        //Determine best concert location
        
        while (low <= high) { 
    
        // Get how long each one will have to walk if concert in middle 
            mid = (int) Math.floor((low+high)/2);
            time = getWalkTime(mid); 
            
            // Compare left and right to see if this is the bottom
            long left = getWalkTime(mid-1); 
            long right = getWalkTime(mid+1); 

            if ((time <= left && time <= right)) { 
                break;
            }

            if (time > left) {
                // Best concert loc exists to the left 
                high = mid-1;  
            }
            else if (time > right) { 
                // Loc exists to the right
                low = mid+1; 
            }
        
        }

        // Output minimum walking time
        System.out.println(time); 

        input.close();
    }
}
