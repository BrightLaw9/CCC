import java.util.Scanner;

public class Palindrome { 

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int row = Integer.parseInt(in.next()); 
        int col = Integer.parseInt(in.next()); 

        int palinR = Integer.parseInt(in.next()); 
        int palinC = Integer.parseInt(in.next()); 

        char[] letters = new char[] {'a', 'b', 'c', 'd', 'e'}; 

        char[][] res = new char[row][col]; 

        if ((row - palinR == 0 && col-palinC == 1) || (row - palinR == 1 && col-palinC == 0)) { 
            System.out.println("IMPOSSIBLE"); 
        }
        else { 
            for (int r = 0; r < palinR; r++) { 
                for (int c = 0; c < col; c++) { 
                res[r][c] = letters[1]; 
                }
            }

            for (int c = 0; c < palinC; c++) { 
                for (int r = 0; r < row; r++) { 
                    res[r][c] = letters[1]; 
                }
            }

            //Fill empty spaces
            for (int r = palinR; r < row; r++) { 
                for (int c = palinC; c < col; c++) { 
                    res[r][c] = letters[2]; 
                }
            }

            for (int r = 0; r < row; r++) { 
                for (int c = 0; c < col; c++) {
                    System.out.print(res[r][c]);
                }
                System.out.println();
            }
        }
    }
}