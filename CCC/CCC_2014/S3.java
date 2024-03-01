package CCC_2014;

import java.util.Scanner;
import java.util.Stack;

public class S3 {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int numTests = Integer.parseInt(in.next());
        
        for (int test = 0; test < numTests; test++) { 
            Stack<Integer> dropStack = new Stack<Integer>();
            int n = Integer.parseInt(in.next()); 
            
            for (int i = 0; i < n; i++) { 
                dropStack.push(Integer.parseInt(in.next())); 
            }

            Stack<Integer> branchStack = new Stack<Integer>(); 
            
            int expected = 1; 

            while (!dropStack.isEmpty() || !branchStack.isEmpty()) { 
                if (!dropStack.isEmpty() && expected == dropStack.peek()) { 
                    expected++; 
                    dropStack.pop(); 
                }
                else if (!branchStack.isEmpty() && expected == branchStack.peek()) { 
                    expected++; 
                    branchStack.pop(); 
                }
                else if (!dropStack.isEmpty()) { 
                    branchStack.push(dropStack.pop()); 
                }
                else { 
                    break; 
                }
            }

            if (expected == (n + 1)) { 
                System.out.println("Y");
            }
            else { 
                System.out.println("N");
            }
        }
   }
}