import java.util.Scanner; 

public class SilentAuction {
    
    public static void main(String args[]) { 
        Scanner input = new Scanner(System.in); 

        int n = Integer.parseInt(input.nextLine()); 

        int winningBid = 0; 

        String winningName = ""; 

        for (int i = 0; i < n; i++) { 
            String name = input.nextLine(); 
            int bid = Integer.parseInt(input.nextLine()); 

            if (bid > winningBid) { 
                winningName = name; 
                winningBid = bid; 
            }
        }

        System.out.println(winningName); 
    }
}
