package LeetCode;

public class MaxArea {
    // Fully passed
    static class Solution {

        static public int maximalSquare(char[][] matrix) {
            int largest = 0; 
            // If one square is found, no need to start in bottom row, if 4 is made, no need for second row
            for (int i = 0; i < matrix.length - Math.sqrt(largest); i++) { 
                for (int j = 0; j < matrix[i].length - Math.sqrt(largest); j++) { 
                    if (matrix[i][j] == '1') { 
                        largest = Math.max(1, largest);
                        int count = 1; 
    
                        while (i+count < matrix.length && j+count < matrix[i].length) { 
                            //int down = matrix[i+count][j]; int right = matrix[i][j+count]; int diagonal = matrix[i+count][j+count]; 
                            boolean toContinue = true; 
                            for (int r = 0; r <= count; r++) { 
                                if (matrix[i+count][j + r] == '0' || matrix[i+r][j+count] == '0') { 
                                    toContinue = false; 
                                    break; 
                                }
                            }
                            if (toContinue) { 
                                int largeCopy = count + 1;
                                largest = Math.max(largest, (int) Math.pow(largeCopy, 2));
                                count++;
                            }
                            else break; 
                        }
                    }
                }
            }
            return largest;
        }
    }

    public static void main(String[] args) { 
        System.out.println(Solution.maximalSquare(new char[][] {new char[] {'1', '1', '1', '1'}, 
                                                            new char[] {'1', '1', '1', '1'}, 
                                                            new char[] {'0', '0', '0', '0'}, 
                                                            new char[] {'1', '1', '1', '1'}})); 
    }
}
