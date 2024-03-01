import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileInputStream; 
import java.io.File;

public class ModernArt { 

    // Passed solution

    public static int toInt(String num) { 
        return Integer.parseInt(num); 
    }

    public static void checkIfCanceled(Map<Integer, Integer> mapVals) { 
        Set<Integer> allKeys = mapVals.keySet(); 
        Set<Integer> toRemove = new HashSet<Integer>(); 
        for (int a : allKeys) { 
            int mappedVal = mapVals.get(a); 
            if (mappedVal % 2 == 0) { 
                toRemove.add(a); 
            }
        }
        mapVals.keySet().removeAll(toRemove); 
    }
    
public static void main(String args[]) { 
    
    //System.out.println(new File(".").getAbsolutePath());
    Scanner reader = new Scanner(System.in); //new FileInputStream("Good4and5.java"/*"C:/Users/lawre/Downloads/all_data (1)/all_data/junior_data/j5/junior_data/j5/j5.12.in"*/)); 
    int m = toInt(reader.nextLine());
    int n = toInt(reader.nextLine()); 
    int k = toInt(reader.nextLine()); 
   

    Map<Integer, Integer> colouredRows = new HashMap<Integer, Integer>(); 
    Map<Integer, Integer> colouredCols = new HashMap<Integer, Integer>(); 

    int goldCells = 0; 

    for (int i = 0; i < k; i++) { 
        String placement = reader.nextLine(); 
        String[] place = placement.split(" "); 
        String rowCol = place[0]; 

        // Store rows/columns with applied paintbrush in list
        if (rowCol.equals("R")) { 
            colouredRows.put(toInt(place[1]), colouredRows.getOrDefault(toInt(place[1]), 0)+1); 
        }
        else if (rowCol.equals("C")) { 
            colouredCols.put(toInt(place[1]), colouredCols.getOrDefault(toInt(place[1]), 0)+1);  
        }
    }

        // Check if golden row/column was canceled by reapplied brush (even numbers)
        checkIfCanceled(colouredRows); 
        checkIfCanceled(colouredCols);

        
        
        // Gold cells are number of rows * by number of columns 
        goldCells += colouredRows.size() * n; 

        // Check columns and compare to row if it has been not coloured
        // Minus number of all ready coloured cells in column and add uncoloured cells 
 
        int numColouredInRow = colouredRows.size(); 
        goldCells -= numColouredInRow * colouredCols.size();  
        goldCells += (m - numColouredInRow) * colouredCols.size(); 
        
        
        System.out.print(goldCells); 
}
}