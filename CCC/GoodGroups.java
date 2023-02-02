import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class GoodGroups {

    static HashMap<String, ArrayList<String>> sameMap; 

    static HashMap<String, ArrayList<String>> diffMap; 
    
    static int violated = 0; 

    public static void checkSame(String p1, String p2, String p3) { 
        // Person that is supposed to be together, is not
        if (sameMap.get(p1) != null) { 
        for (int i = 0; i < sameMap.get(p1).size(); i++) { 
            if (!sameMap.get(p1).get(i).equals(p2) && !sameMap.get(p1).get(i).equals(p3)) { 
                violated++;
            }
        }
        }
    }
    

    static void checkDifferent(String p1, String p2, String p3) { 
        // Person that is supposed to in different, ended up together (contained in map)
        if (diffMap.get(p1) != null) 
        {
            for (int i = 0; i < diffMap.get(p1).size(); i++) { 
                if (diffMap.get(p1).get(i).equals(p2)) { 
                    violated++;
                }
                if (diffMap.get(p1).get(i).equals(p3)) { 
                    violated++; 
                }
            }

        }   
    }
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        int numSame = Integer.parseInt(in.next());
        
        sameMap = new HashMap<String, ArrayList<String>>(numSame);
        
        // Store pairs in sameMap, with both ppl holding each other's connection
        // Must be together
        for (int i = 0; i < numSame; i++) { 
            String p1 = in.next(); 
            String p2 = in.next(); 

            if (sameMap.get(p1) == null) sameMap.put(p1, new ArrayList<String>()); 

            sameMap.get(p1).add(p2); 
            sameMap.put(p1, sameMap.get(p1)); 
        }

        int numDiff = Integer.parseInt(in.next());

        diffMap = new HashMap<String, ArrayList<String>>(numDiff);

        // Store must be in different group pairs
        for (int diff = 0; diff < numDiff; diff++) { 
            String p1 = in.next(); 
            String p2 = in.next(); 

            if (diffMap.get(p1) == null) diffMap.put(p1, new ArrayList<String>()); 

            diffMap.get(p1).add(p2); 
            diffMap.put(p1, diffMap.get(p1)); 
        }

        //See how many rules have been violated

        int numGroups = Integer.parseInt(in.next()); 

        for (int group = 0; group < numGroups; group++) { 
            String p1 = in.next(); 
            String p2 = in.next();
            String p3 = in.next(); 
            
            // The first person checking against other two for violations
            checkSame(p1, p2, p3);
            checkSame(p2, p1, p3); 
            checkSame(p3, p1, p2); 

            checkDifferent(p1, p2, p3);
            checkDifferent(p2, p1, p3);
            checkDifferent(p3, p1, p2); 
        }

        System.out.println(violated);

        in.close();
        
    }
}
