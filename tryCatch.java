public class tryCatch {
    

    public static void main(String args[]) { 
        try { 
            int nums[] = {1, 2, 3}; 
            
            System.out.println(nums[3]); 
        }
        catch(IndexOutOfBoundsException e) { 
            System.out.println("Index out of bounds");
        }
        
        // Program continues after try/catch statement finishes execution
        System.out.println("Hi"); 
    }
}
