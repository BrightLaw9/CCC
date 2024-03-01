import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CompFibNum2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int t = 0; t < testCases; t++) {
            int num = scanner.nextInt();
            if (num < 10) { 
                System.out.println("NO");
                continue; 
            }
            if (isCompositeFib(num)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        scanner.close();
    }

    static int lastGen = 0; 
    static List<Integer> prevGen; 

    public static boolean isCompositeFib(int num) {
        if (num <= 0) return false; // Fibonacci numbers are positive
        List<Integer> fibNumbers = generateFibonacciNumbersUpTo(num);
        int n = fibNumbers.size();
        boolean[] dp = new boolean[num + 1];
        dp[0] = true;

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= fibNumbers.get(j)) {
                    dp[i] |= dp[i - fibNumbers.get(j)];
                }
            }
        }
        return dp[num];
    }

    public static List<Integer> generateFibonacciNumbersUpTo(int num) {

        if (lastGen == 0) { 
            prevGen = new ArrayList<Integer>(); 
            prevGen.add(0); prevGen.add(1); 
        }
        int a = prevGen.get(prevGen.size() - 2), b = prevGen.get(prevGen.size() - 1), c;
        while ((c = a + b) <= num) {
            prevGen.add(c);
            a = b;
            b = c;
        }
        return prevGen;
    }
}
