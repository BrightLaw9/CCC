import java.util.*; 

class UseOfPointers {
    public static void main(String[] args) {
       ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 4})); 
       ArrayList<Integer> two = one;  
       two.add(5); 
       for (int i : one) System.out.println(i); 
    }
}