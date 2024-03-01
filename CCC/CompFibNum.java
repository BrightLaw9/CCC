import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CompFibNum {

    // public static boolean isComposite(int num) { 
    //     // 0 and 1 are not composite
    //     for (int i = 2; i <= Math.sqrt(num); i++) { 
    //         if (num % i == 0) return true; 
    //     }
    //     return false; 
    // }

    public static boolean checkCompositeFib(boolean[] isFib, int num1, int num2) { 
        // System.out.println("num1" + num1 + "num2" + num2); 
        // System.out.println("num2 " + isFib[num2]); 
        // return (isFib[num1] && isComposite(num2)) || (isFib[num2] && isComposite(num1)); 
        return isFib[num1] && isFib[num2]; 
    }

    public static boolean solve(boolean[] isFib, String num) { 
        // System.out.println(num); 
        if (num.length() == 1) return isFib[Integer.parseInt(num)];  
        boolean res = false;
        for (int i = 1; i < num.length(); i++) { 
            if (isFib[Integer.parseInt(num.substring(0, i))]) { 
                res = res || solve(isFib, num.substring(i, num.length())); 
            }
        }
        return res; 
    }
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int tests = Integer.parseInt(br.readLine()); 
        int NUM_FIB = 10000; // Remove a zero
        boolean[] isFib = new boolean[NUM_FIB+1]; 
        int twoBack = 0; int oneBack = 1; 
        Arrays.fill(isFib, false); 
        isFib[0] = true; isFib[1] = true; 
        int cur = twoBack + oneBack; 

        while (cur <= NUM_FIB) { 
            isFib[cur] = true; 
            twoBack = oneBack; 
            oneBack = cur; 
            cur = oneBack + twoBack; 
        }
        
        for (int test = 0; test < tests; test++) { 
            String num = br.readLine(); 
            if (num.length() == 1) { 
                System.out.println("NO");
                continue; 
            }
            if (solve(isFib, num)) System.out.println("YES"); 
            else System.out.println("NO"); 
        }
    }
}
