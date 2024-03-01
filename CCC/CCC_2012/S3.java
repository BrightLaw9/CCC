package CCC_2012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class S3 {
    
    public static void main(String[] args) { 
        System.out.println(computeDifference()); 
    }

    public static int computeDifference() {

        Scanner in = new Scanner(System.in);

        int numSensors = Integer.parseInt(in.nextLine());

        int[] numReadings = new int[1000+1]; 
        Arrays.fill(numReadings, 0);

        int maxFreq = 0; 

       // int maxReadings = Integer.MAX_VALUE; 
        ArrayList<ArrayList<Integer>> readingWithFreq = new ArrayList<ArrayList<Integer>>(); 

        // for (int i = 0; i < maxReadings; i++) { 
        //     readingWithFreq.add(new ArrayList<Integer>()); 
        // }

        readingWithFreq.add(new ArrayList<Integer>()); // Zeroth reading
        for (int i = 0; i < numSensors; i++) { 
            int reading = Integer.parseInt(in.nextLine());
            numReadings[reading]++; 
            maxFreq = Math.max(numReadings[reading], maxFreq); 
            
            if (numReadings[reading] == readingWithFreq.size()) { 
                readingWithFreq.add(new ArrayList<Integer>()); 
            }
            readingWithFreq.get(numReadings[reading]).add(reading); 
            if (numReadings[reading] > 1) { 
                readingWithFreq.get(numReadings[reading] - 1).remove((Integer) reading); 
            }
        }
        
        // Max reading
        if (readingWithFreq.get(readingWithFreq.size() - 1).size() == 2) { 
            ArrayList<Integer> elements = readingWithFreq.get(readingWithFreq.size() - 1); 
            return Math.abs(elements.get(0) - elements.get(1)); 
        }
        else if (readingWithFreq.get(readingWithFreq.size() - 1).size() > 2) { 
            ArrayList<Integer> elements = readingWithFreq.get(readingWithFreq.size() - 1); 
            int min = Integer.MAX_VALUE; 
            int max = -1; 
            for (int element : elements) { 
                min = Math.min(min, element); 
                max = Math.max(max, element); 
            }
            return Math.abs(max - min); 
        }

        else {
        //if (readingWithFreq.get(readingWithFreq.size() - 1).size() == 1) { 
            // Find second largest frequency
            int secondLargestReading = readingWithFreq.size() - 2; 

            while (readingWithFreq.get(secondLargestReading).size() == 0) { 
                secondLargestReading -= 1; 
            }

            if (readingWithFreq.get(secondLargestReading).size() > 1) { 
                int min = Integer.MAX_VALUE; 
                int max = -1; 
                for (int element : readingWithFreq.get(secondLargestReading)) { 
                    min = Math.min(min, element); 
                    max = Math.max(max, element); 
                }
                int maxFreqReading = readingWithFreq.get(readingWithFreq.size() - 1).get(0);  
                return Math.max(Math.abs(maxFreqReading - min), Math.abs(max - maxFreqReading));  
            }

                return Math.abs(readingWithFreq.get(readingWithFreq.size() - 1).get(0) 
                                    - readingWithFreq.get(secondLargestReading).get(0)); 
        }
    }
}
