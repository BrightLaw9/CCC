import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Labyrinth {
    
    public static void main(String[] args) throws IOException{ 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 

        String line = in.readLine(); 

        int height = Integer.parseInt(line.substring(0, 1)); 
        int width = Integer.parseInt(line.substring(1)); 

        char[][] map = new char[height][width]; 

        for (int h = 0; h < height; h++) { 
            line = in.readLine(); 
           for (int w = 0; w < width; w++) { 
                map[h][w] = line.charAt(w); 
           }
        }

        System.out.println(map[2][3]);

        
        
    }
}
