import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J5 {

    static int row; static int col;

    public static String reverseWord(String word) { 
        char[] wordArr = word.toCharArray(); 
        char[] newWord = new char[wordArr.length];
        for (int i = 0; i < wordArr.length; i++) { 
            newWord[i] = wordArr[wordArr.length-1-i]; 
        }
        return String.valueOf(newWord); 
    }

    static boolean inRange(int r, int c) { 
        return r < row && r >= 0 && c < col && c >= 0; 
    }

    static boolean condition(String[][] grid, String word, int r, int c, int i) { 
        return inRange(r, c) && grid[r][c].charAt(0) == word.charAt(i); 
    }

    public static int findWords(String word, String[][] grid, int r, int c) {
        int words = 0; 
        // Right
        boolean fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r, c+i, i)) fail = true; 
        }
        if (!fail) words++; 
        // Left
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r, c-i, i)) fail = true; 
        }
        if (!fail) words++; 
        // Down
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r+i, c, i)) fail = true; 
        }
        if (!fail) words++; 
        // Up
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r-i, c, i)) fail = true; 
        }
        if (!fail) words++; 
        // Diagonal Top Left Bottom right
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r+i, c+i, i)) fail = true; 
        }
        if (!fail) words++; 
        // Diagonal Top Right Bottom Left
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r+i, c-i, i)) fail = true; 
        }
        if (!fail) words++; 
        // Diagonal BR TL
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r-i, c-i, i)) fail = true; 
        }
        if (!fail) words++; 
        // BL TR
        fail = false;
        for (int i = 1; i < word.length(); i++) { 
            if (!condition(grid, word, r-i, c+i, i)) fail = true; 
        }
        if (!fail) words++; 
       // Downwards 90 Right - All diff lengths
       for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j, c+i, i+j)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Downwards 90 Left
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j, c-i, i+j)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Upwards 90 Right
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r-start, c, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r-j, c+i, i+j)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Upwards 90 Left
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r-start, c, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r-j, c-i, i+j)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Right 90 Down
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r, c+start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+i, c+j, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Left 90 Down
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r, c-start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+i, c-j, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Right 90 Up
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r, c+start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r-i, c+j, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Left 90 Up
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r, c-start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r-i, c-j, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Diagonal TL BR 90 TR BL
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c+start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j+i, c+j-i, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Diagonal TR BL 90 TL BR
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c-start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j+i, c-j+i, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Diagonal TL BR 90 BL TR
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c+start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j-i, c+j+i, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }
        // Diagonal TR BL 90 BR TL
        for (int j = 1; j < word.length() - 1; j++) { 
            fail = false;
            for (int start = 1; start <= j; start++) { 
                if (!condition(grid, word, r+start, c-start, start)) fail = true; 
            }
            for (int i = 1; i < word.length() - j; i++) { 
                if (!condition(grid, word, r+j-i, c-j-i, j+i)) fail = true; 
            }
            if (!fail) words++; 
       }

       // Diagonal BL TR BR TL
       for (int j = 1; j < word.length() - 1; j++) { 
        fail = false;
        for (int start = 1; start <= j; start++) { 
            if (!condition(grid, word, r-start, c+start, start)) fail = true; 
        }
        for (int i = 1; i < word.length() - j; i++) { 
            if (!condition(grid, word, r-j-i, c+j-i, j+i)) fail = true; 
        }
        if (!fail) words++; 
   }
       // Diagonal BL TR TL BR
       for (int j = 1; j < word.length() - 1; j++) { 
        fail = false;
        for (int start = 1; start <= j; start++) { 
            if (!condition(grid, word, r-start, c+start, start)) fail = true; 
        }
        for (int i = 1; i < word.length() - j; i++) { 
            if (!condition(grid, word, r-j+i, c+j+i, j+i)) fail = true; 
        }
        if (!fail) words++; 
   }
       // Diagonal BR TL BL TR
       for (int j = 1; j < word.length() - 1; j++) { 
        fail = false;
        for (int start = 1; start <= j; start++) { 
            if (!condition(grid, word, r-start, c-start, start)) fail = true; 
        }
        for (int i = 1; i < word.length() - j; i++) { 
            if (!condition(grid, word, r-j-i, c-j+i, j+i)) fail = true; 
        }
        if (!fail) words++; 
   }
       // Diagonal BR TL TR BL
       for (int j = 1; j < word.length() - 1; j++) { 
        fail = false;
        for (int start = 1; start <= j; start++) { 
            if (!condition(grid, word, r-start, c-start, start)) fail = true; 
        }
        for (int i = 1; i < word.length() - j; i++) { 
            if (!condition(grid, word, r-j+i, c-j-i, j+i)) fail = true; 
        }
        if (!fail) words++; 
   }
        return words; 
    }

    // public static boolean isPalindrome(String word) { 
    //     int l = 0; 
    //     int r = word.length() - 1; 
    //     while (l <= r) { 
    //         if (word.charAt(r) != word.charAt(l)) return false; 
    //         l++; 
    //         r--; 
    //     }
    //     return true; 
    // }
   
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String word = br.readLine(); 
        row = Integer.parseInt(br.readLine());
        col = Integer.parseInt(br.readLine()); 

        String[][] grid = new String[row][col]; 

        for (int i = 0; i < row; i++) { 
            grid[i] = br.readLine().split(" "); 
        }
        int totalWords = 0; 
        for (int i = 0; i < row; i++) { 
            for (int j = 0; j < col; j++) { 
                if (grid[i][j].charAt(0) == word.charAt(0)) { 
                    totalWords += findWords(word, grid, i, j); 
                }
            }
        }

        // If palindrome, * 2
        // if (isPalindrome(word)) totalWords *= 2; 
        System.out.println(totalWords); 
    }
}
