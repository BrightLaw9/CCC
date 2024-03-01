package CCC_2008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2 {
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        while (true) { 
            int numPennies = 0; 
            int radius = Integer.parseInt(br.readLine());
            
            if (radius == 0) break; 

            for (int x = 1; x < radius; x++) { 
                numPennies += (int) Math.sqrt(radius * radius - x * x); 
            }
            // 2x as this arrangement is reflected across y-axis
            numPennies *= 2; 

            // Add the middle line
            numPennies += radius; 

            numPennies *= 2; // Reflected x-axis
            // Add the y=0 part
            numPennies += 2 * radius + 1; 
            System.out.println(numPennies); 
        }
    }
}
