import java.util.Arrays;
import java.util.Scanner;

public class CCC2023S3 {

    public static void fill(char[][] poster, char letter) { 
        for (int r = 0; r < poster.length; r++) { 
            for (int c = 0; c < poster[0].length; c++) { 
                poster[r][c] = letter; 
            }
        }
    }

    public static char intToLetter(int num) { 
        return (char) (num + 97);
    }

    public static int letterToNum(char letter) { 
        return ((int) letter) - 97; 
    }
    
    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in);

        String[] data = in.nextLine().split(" ");

        int numRows = Integer.parseInt(data[0]);
        int numCol = Integer.parseInt(data[1]);

        int palinR = Integer.parseInt(data[2]);
        int palinC = Integer.parseInt(data[3]);

        char[][] poster = new char[numRows][numCol];

        // Regular case or if whole grid is palindromic
        if ((palinR == numRows && palinC == numCol) || (palinR != numRows && palinR != 0 && palinC != numCol && palinC != 0)) { 
          
            fill(poster, 'a');
            
            // Fill rows with all the same letter to ensure palindrome
            for (int r = 0; r < palinR; r++) { 
                for (int c = 0; c < numCol; c++) { 
                    poster[r][c] = 'b';
                }
            }
             
            // Fill remaining columns with same letter to ensure palidrome there
            for (int r = palinR; r < numRows; r++) { 
                for (int c = 0; c < palinC; c++) { // continuing from above
                    poster[r][c] = 'b';
                }
            }
        }
        // Both 0, randomly fill grid
        else if (palinR == 0 && palinC == 0) { 
            int letter = 0; 
            for (int r = 0; r < numRows; r++) { 
                for (int c = 0; c < numCol; c++) { 
                    poster[r][c] = intToLetter(letter); 
                    letter++; 
                    letter %= 26; 
                }
                // Adjust slightly so palindromic columns not happen
                letter += (r + 1) * 3; 
                letter %= 26;  
            }
        }
        // Cannot have palindromic rows, but palindromic columns occur
        else if (palinR == 0) { 

            fill(poster, 'a');
            // Fill last column preventing palin Rows
            for (int r = 0; r < numRows; r++) { 
                poster[r][numCol - 1] = 'b'; 
            }

            // On bottom row,
            // Increment all letters after the needed palindromic columns so those don't be palin
            for (int c = palinC; c < numCol; c++) { 
                poster[numRows - 1][c] = intToLetter(letterToNum(poster[numRows - 1][c]) + 1); 
            }
        }

        // All rows are palindromic
        else if (palinR == numRows) {
                        
            fill(poster, 'a');
            // reduce number of palinC by having two pointers from outside in eliminating palins
            // when modulos equal, the divide and conquer will leave the cols in the right amount
            if (numCol % 2 == palinC % 2) { 
                int curPalinCols = numCol; 
                int l = 0; int r = numCol -1; 

                while (curPalinCols > palinC) { 
                    poster[0][l] = 'b';
                    poster[0][r] = 'b';
                    l++; r--; curPalinCols -= 2; 
                }
            }
            else if (numCol % 2 == 1) { 
                // cols are odd, palin cols are even
                // Subtract using the same approach above but stop one before the needed palins
                // Last alteration goes in the center

                int curPalinCols = numCol; 
                int l = 0; int r = numCol -1; 

                while (curPalinCols > palinC + 1) { 
                    poster[0][l] = 'b';
                    poster[0][r] = 'b';
                    l++; r--; curPalinCols -= 2; 
                }

                int mid = (l + r) / 2; 
                poster[0][mid] = 'b'; 
            }
            else { 
                System.out.println("IMPOSSIBLE"); 
                System.exit(0);
            }
        }

        // No palindromic columns, but palindromic rows happen
        else if (palinC == 0) { 
            fill(poster, 'a');

            for (int c = 0; c < numCol; c++) { 
                poster[numRows - 1][c] = 'b'; 
            }

            for (int r = palinR; r < numRows; r++) { 
                poster[r][numCol - 1] = intToLetter(letterToNum(poster[r][numCol - 1]) + 1); 
            }
        }
        // All palindromic columns
        else if (palinC == numCol) {
            // Same logic as before, but rows and columns are swapped
            fill(poster, 'a');

            if (numRows % 2 == palinR % 2) { 
                int curPalinRows = numRows; 
                int l = 0; int r = numRows - 1; 

                while (curPalinRows > palinR) { 
                    poster[l][0] = 'b';
                    poster[r][0] = 'b';
                    l++; r--; curPalinRows -= 2; 
                }
            }
            else if (numRows % 2 == 1) { 

                int curPalinRows = numRows; 
                int l = 0; int r = numRows -1; 

                while (curPalinRows > palinR + 1) { 
                    poster[l][0] = 'b';
                    poster[r][0] = 'b';
                    l++; r--; curPalinRows -= 2; 
                }

                int mid = (l + r) / 2; 
                poster[mid][0] = 'b'; 
            }
            else { 
                System.out.println("IMPOSSIBLE"); 
                System.exit(0);
            }
        }

        // Print poster 
        for (int r = 0; r < numRows; r++) { 
            for (int c = 0; c < numCol; c++) { 
                System.out.print(poster[r][c]); 
            }
            System.out.print("\n"); 
        }
    }
}
