package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    
    static class Solution {
        public static List<Integer> getRow(int rowIndex) {
    
            List<Integer> dp = new ArrayList<Integer>(rowIndex + 1);  

            for (int i = 0; i <= rowIndex; i++) { 
                dp.add(i, 0); 
            }

            dp.set(0, 1);
            
            for (int round = 1; round <= rowIndex; round++) {
                dp.set(round, 1); 
                int prev2 = dp.get(0); 
                for (int i = 1; i < round; i++) { 
                    int prev = dp.get(i); 
                    dp.set(i, prev2 + dp.get(i));
                    prev2 = prev; 
                }
            }
    
            return dp; 
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.getRow(4));
    }

}
