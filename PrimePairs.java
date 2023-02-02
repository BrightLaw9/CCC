import java.util.BitSet;
import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List; 

// Passed on CCC Grader

public class PrimePairs { 

    public static BitSet isPrime = new BitSet(1999999);

    public static List<Integer> primes = new ArrayList<Integer>(); 


    public static void findPrimes(BitSet isPrime, List<Integer> primes) { 

        isPrime.set(2, 1999999, true); // Initally start off with all numbers considered a prime  
    
        
        primes.add(2); 
        
    
        // Sieve of Eratosthenes
        for (int j = 4; j < 1999999; j += 2) { 
            isPrime.set(j, false); 
        } 
    
        // Consider only odd numbers from 3
        // Need up to 1 999 999 since max size is 1 000 000 and is doubled, prime starts at 2
        for (int currNum = 3; currNum < Math.sqrt(1999999); currNum += 2) { 
            if (isPrime.get(currNum)) { 
                primes.add(currNum); 
                for (int i = 2; i <= 1999999/currNum; i++) { 
                    // Set all the multiples of the prime to false
                    isPrime.set(i * currNum, false); 
                }
            }
        }
    }

    public static void solve(int n, List<Integer> primes) { 

        int sum = 2 * n; 

        for (int p : primes) { 
            if (isPrime.get(sum-p)) { 
                System.out.println(p + " " + (sum-p)); 
                return; 
            }
        }

    }

    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int testCases = in.nextInt();  

        findPrimes(isPrime, primes); 
        
        for (int i = 0; i < testCases; i++) { 
            int next = in.nextInt(); 
            solve(next, primes); 
        }

        in.close(); 
    }
}  
