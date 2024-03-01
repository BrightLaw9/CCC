package CCC_2014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class S4 {

    static class Line { 
        
        public long x1; // when calculating area, need to multiply x and y, safer to keep as long multiplication
        public long y1; 
        public long y2; 
        public long t;
        
        public Line(long x1, long y1, long y2, long t) { 
            this.x1 = x1; 
            this.y1 = y1; 
            this.y2 = y2; 
            this.t = t; 
        }
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int numGlass = Integer.parseInt(in.next()); 
        
        int requiredTint = Integer.parseInt(in.next()); 

        // Divide into segments seperated by vertical ranges (y1 and y2)

        // For every x1, the whole segment will be temporarily tinted until the x2 is reached
        // Check for when the vertical segment remains above the requiredTint

        // The verticalLines will be sorted from top to bottom based on x

        ArrayList<Line> verticalLines = new ArrayList<Line>();

        Set<Long> tempHorSegments = new TreeSet<Long>(); 

        for (int i = 0; i < numGlass; i++) { 
            long x1 = Long.parseLong(in.next()); 
            long y1 = Long.parseLong(in.next()); 
            long x2 = Long.parseLong(in.next()); 
            long y2 = Long.parseLong(in.next()); 
            long t = Long.parseLong(in.next()); 

            verticalLines.add(new Line(x1, y1, y2, t)); 
            verticalLines.add(new Line(x2, y1, y2, -t)); // Negative tint to undo the applying of such strip

            tempHorSegments.add(y1); 
            tempHorSegments.add(y2); 
        }

        Object[] horizontalSegments = tempHorSegments.toArray(); 

        verticalLines.sort(new Comparator<Line>() {
            @Override
            public int compare(Line one, Line two) {
                // long[] s = new long[4]; 
                // long[] t = new long[4]; 

                // s[0] = one.x1; 
                // s[1] = one.y1; 
                // s[2] = one.y2; 
                // s[3] = one.t; 
                // t[0] = two.x1; 
                // t[1] = two.y1; 
                // t[2] = two.y2; 
                // t[3] = two.t;         
                
                // return Arrays.compare(s, t);
                // Lexiographical comparison
                if (one.x1 != two.x1) return Long.compare(one.x1, two.x1); 
                else if (one.y1 != two.y1) return Long.compare(one.y1, two.y1); 
                else if (one.y2 != two.y2) return Long.compare(one.y2, two.y2); 
                return Long.compare(one.t, two.t); 
            }
        });

        // for (Line line : verticalLines) { 
        //     System.out.println(line.x1 + " " + line.y1  + " " + line.y2 + " " + line.t);   
        // }
        // Assigining lines from top to bottom with a number rank
        // The yCoordinate will not surpass 32 bit integer
        HashMap<Long, Integer> yCoordToLineNum = new HashMap<Long, Integer>(); 

        for (int i = 0; i < horizontalSegments.length; i++) { 
            yCoordToLineNum.put((long) horizontalSegments[i], i); 
        }

        // yBlocked stores the tint factor currently for a current block of y Range (y1 to y2)
        Long[] yBlocked = new Long[horizontalSegments.length - 1]; 
        Arrays.fill(yBlocked, (long) 0);
        
        long area = 0; 

        for (int i = 0; i < verticalLines.size(); i++) { 

            for (int j = yCoordToLineNum.get(verticalLines.get(i).y1); j < yCoordToLineNum.get(verticalLines.get(i).y2); j++) { 
                yBlocked[j] += verticalLines.get(i).t; 
            }

            // Add to the area by looking at each vertical block and seeing how this addition of x1 to x2 has affected

            for (int j = 0; j < yBlocked.length; j++) { 
                if (yBlocked[j] >= requiredTint) { 
                    // System.out.println((long) horizontalSegments[j] - (long) horizontalSegments[j+1]); 
                    area += Math.abs((long) horizontalSegments[j] - (long) horizontalSegments[j+1]) * 
                            Math.abs(verticalLines.get(i).x1 - verticalLines.get(i+1).x1); // Next vertical line can have a different tint
                }
            }
        }
        System.out.println(area);
    }
}
