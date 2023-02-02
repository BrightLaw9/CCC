import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

// Passed on CCC Grader

public class CrazyFencing { 

    public static int toInt(String num) { 
        return Integer.parseInt(num); 
    }

    public static void main(String args[]) { 

        Scanner input = new Scanner(System.in); 
        
        int numPieces = toInt(input.nextLine()); 

        // Process heights of fences into list
        String heights = input.nextLine(); 
        String[] individualHeights = heights.split(" "); 

        List<Integer> intHeights = new ArrayList<Integer>(); 

        for (String height : individualHeights) { 
            intHeights.add(toInt(height)); 
        }

        // Process widths into individual pieces
        String widths = input.nextLine(); 
        String[] individualWidths = widths.split(" "); 

        double totalArea = 0; 
        int count = 0; 

        for (String width : individualWidths) { 
            totalArea += Double.parseDouble(width) * (intHeights.get(count) + intHeights.get(count+1)) / 2; 
            count++; 
        }

        System.out.println(totalArea); 


        
        
    }
}