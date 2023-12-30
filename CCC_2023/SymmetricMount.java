import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SymmetricMount {
    
    public static int calculateVal(int[] range) { 
        int l = 0; 
        int r = range.length - 1; 
        int totalVal = 0; 

        while (l <= r) { 
            totalVal += Math.abs(range[l] - range[r]); 
            l++; 
            r--; 
        }

        return totalVal; 
    }

    public static boolean equals(int[] one, int[] two) { 
        for (int i = 0; i < one.length; i++) { 
            if (one[i] != two[i]) return false; 
        }
        return true; 
    }

    public static void main(String[] args) throws IOException { 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int numMountains = Integer.parseInt(in.readLine()); 

        int[] mountains = new int[numMountains]; 

        String mountainsString = in.readLine(); 

        StringTokenizer mountTokenizer = new StringTokenizer(mountainsString); 

        for (int i = 0; i < numMountains; i++) { 
            mountains[i] = Integer.parseInt(mountTokenizer.nextToken()); 
        }

        // Every length one pic will be 0 assymetric val
        System.out.print(0 + " "); 


        int[] copy = Arrays.copyOfRange(mountains, 0, numMountains); 
        Arrays.sort(copy); 

        if (equals(copy, mountains)) { 
            for (int i = 2; i <= numMountains; i++) {
            int l = 0; 
            int r = mountains.length - 1; 

            //Instead, go from middle outwards

            while (l+i <= r) { 
                if (Math.abs(mountains[l] - mountains[l+1]) >= Math.abs(mountains[r] - mountains[r-1])) { 
                    l++; 
                }
                else { 
                    r--; 
                }
            }
            int[] range = Arrays.copyOfRange(mountains, l, r+1); 
            System.out.print(calculateVal(range) + " "); 
            }
        }

        else {
        for (int length = 2; length <= numMountains; length++) { 
            int minValue = Integer.MAX_VALUE;
            
            for (int i = 0; i <= numMountains-length; i++) { 
                if (minValue == 0) break; 
                
                int[] range = Arrays.copyOfRange(mountains, i, i+length); 
                minValue = Math.min(calculateVal(range), minValue); 
            }
            System.out.print(minValue + " "); 
        }
    }
    }
}
