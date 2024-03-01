package CCC_2011;

import java.util.Scanner;

public class S5 {

    public static String turnOff(String s) { 
        // If a row of 7, 6, 5 or 4 1's then turn all those off
        s = s.replace("1111111", "0000000"); 
        s = s.replace("111111", "000000");
        s = s.replace("11111", "00000"); 
        s = s.replace("1111", "0000"); 
        return s; 
    }
    
    public static void turnOn() { 

    }

    public static boolean done(String lights) { 
        return lights.indexOf("1") == -1; 
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int numLights = Integer.parseInt(in.nextLine());

        String[] lights = new String[numLights]; 

        for (int i = 0; i < numLights; i++) { 
            lights[i] = in.nextLine(); 
        }

        String lightString = String.valueOf(lights); 


    }
}
