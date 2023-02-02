import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeTop { 

    public static void scanRowTreesVisible(int rowLength, int maxHeight, int treesViewed) { 
        for (int cell = 0; cell < rowLength; cell++) { 
            int currCellHeight = treeMatrix.get(i).get(cell);            
            if (currCellHeight > maxHeight) {
                maxHeight =  currCellHeight; 
                treesViewed++; 
            }
        }
    }
    

    public static void main(String args[]) { 
        //InputStreamReader streamReader =  
        Scanner in = new Scanner(System.in);

        //TODO: Figure out why BufferedReader does not work
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  

        ArrayList<ArrayList<Integer>> treeMatrix = new ArrayList<ArrayList<Integer>>(); 

        String inLine = in.nextLine(); 
        Character character;
        int rowNum = 0; 

        // Read input into matrix
        while (!inLine.equals("end")) {

            // For each row added, columns will be formed
            treeMatrix.add(new ArrayList<Integer>()); 
        
            for (int i = 0; i < inLine.length(); i++) { 
                character = inLine.charAt(i);
                treeMatrix.get(rowNum).add(Character.getNumericValue(character)); 
            }

            inLine = in.nextLine(); 
            rowNum++; 
        }

        //Account for all trees on the outside
        int treesViewed = 0; 
        int rowLength = treeMatrix.get(0).size(); 
        int colLength = treeMatrix.size(); 

        // Minus 4 to remove corner overlap
        treesViewed += (rowLength * 2) + (colLength * 2) - 4; 
        

        //Start from the one side to the other, for top, down, left, right
        //Continue searching adjacent cells, adding to a max value, any greater tree++

        // Top to down 
        int maxHeight = 0; 

        // Start and end 1 less as not including outer rows
        for (int r = 1; r < treeMatrix.size()-1; r++) {  
            scanRowTreesVisible(rowLength, maxHeight, treesViewed);
        }


        

        


  
        // Start DFS
    }
}
