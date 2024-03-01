package CCC_2015;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class S1 {
    
    static class Stack { 

        ArrayList<Integer> stack;
        
        public Stack() {
            this.stack = new ArrayList<Integer>(); 
        }

        public void push(int num) { 
            this.stack.add(num); 
        }

        public void pop() { 
            if (this.stack.size() > 0) this.stack.remove(this.stack.size()-1); 
        }

        public int sum() { 
            int out = 0; 
            for (int num : this.stack) { 
                out += num; 
            }
            return out; 
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 

        int k = Integer.parseInt(in.nextLine());

        Stack numStack = new Stack(); 

        for (int i = 0; i < k; i++) { 
            int curNum = Integer.parseInt(in.nextLine()); 
            if (curNum == 0) numStack.pop();
            else { 
                numStack.push(curNum);
            }
        }

        System.out.println(numStack.sum());

    }
}
