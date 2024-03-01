package CCC_2024; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S3 {

    static int[] arr2;
    static ArrayList<int[]> ans; 
    static HashMap<Integer, Boolean> visited;  

    static int toInt(int[] arrange) { 
        String ans = ""; 
        for (int i : arrange) { 
            ans += i; 
        }
        return Integer.parseInt(ans); 
    }

    public static boolean recurse(int[] arr, int numSteps, ArrayList<int[]> steps) { 

        Queue<int[]> queue = new LinkedList<int[]>(); 
        
        queue.add(arr); 
        visited.put(toInt(arr), true);
        Queue<ArrayList<int[]>> stepsQ = new LinkedList<ArrayList<int[]>>(); 
        stepsQ.add(steps);  
        while (!queue.isEmpty()) { 

            int[] curArr = queue.poll(); 
            ArrayList<int[]> curSteps = stepsQ.poll(); 

            if (Arrays.equals(curArr, arr2)) { 
                ans = curSteps; 
                return true; 
            }

            for (int i = 0; i < arr.length; i++) { 
                for (int j = 0; j < arr.length; j++) { 
                    if (i == j) continue; 
                    // boolean ans = false;
                    ArrayList<int[]> copy = new ArrayList<int[]>(curSteps);  
                    copy.add(new int[] {i, j}); 
                    int[] arrCopy = curArr.clone(); 
                    if (j > i) { 
                        for (int start = i; start <= j; start++) { 
                            arrCopy[start] = curArr[i]; 
                        }
                        // visited.put(toInt(arr), true); 
                        if (!visited.getOrDefault(toInt(arrCopy), false)) { 
                            queue.add(arrCopy); 
                            visited.put(toInt(arrCopy), true); 
                            stepsQ.add(copy); 
                        }
                         // ans = ans || recurse(arrCopy, numSteps + 1, copy); 
                    }
                    else if (j < i) { 
                        for (int start = i; start >= j; start--) { 
                            arrCopy[start] = curArr[i]; 
                        }
                        // visited.put(toInt(arr), true); 
                        if (!visited.getOrDefault(toInt(arrCopy), false)) { 
                            queue.add(arrCopy); 
                            visited.put(toInt(arrCopy), true); 
                            stepsQ.add(copy); 
                        }
                        // ans = ans || recurse(arrCopy, numSteps + 1, copy); 
                    }
                    // return ans; 
                }
            }

        }
            

        // if (!visited.getOrDefault(toInt(arr), false)) { 
        //     for (int i = 0; i < arr.length; i++) { 
        //         for (int j = 0; j < arr.length; j++) { 
        //             if (i == j) continue; 
        //             boolean ans = false;
        //             ArrayList<int[]> copy = new ArrayList<int[]>(steps);  
        //             steps.add(new int[] {i, j}); 
        //             int[] arrCopy = arr.clone(); 
        //             if (j > i) { 
        //                 for (int start = i; start <= j; start++) { 
        //                     arrCopy[start] = arr[i]; 
        //                 }
        //                 visited.put(toInt(arr), true); 
        //                 ans = ans || recurse(arrCopy, numSteps + 1, copy); 
        //             }
        //             else if (j < i) { 
        //                 for (int start = i; start >= j; start--) { 
        //                     arrCopy[start] = arr[i]; 
        //                 }
        //                 visited.put(toInt(arr), true); 
        //                 ans = ans || recurse(arrCopy, numSteps + 1, copy); 
        //             }
        //             return ans; 
        //         }
        //     }
        // }
        return false; 
    }

    // public static boolean bfs(int[] arr) { 
    //     Queue<int[]> queue = new LinkedList<int[]>(); 

    //     queue.add(arr); 

    //     if (Arrays.equals(arr, arr2)) { 
    //         ans = steps; 
    //         return true; 
    //     }
    // }
    
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine()); 

        StringTokenizer tok1 = new StringTokenizer(br.readLine()); 
        StringTokenizer tok2 = new StringTokenizer(br.readLine()); 

        int[] arr1 = new int[n]; 
        arr2 = new int[n]; 

        for (int i = 0; i < n; i++) { 
            arr1[i] = Integer.parseInt(tok1.nextToken());  
            arr2[i] = Integer.parseInt(tok2.nextToken());  
        }

        visited = new HashMap<Integer, Boolean>(); 
        //visited.put(toInt(arr1), true); 
        if (recurse(arr1, 0, new ArrayList<int[]>())) { 
            System.out.println("YES"); 
            System.out.println(ans.size()); 
            for (int i = 0; i < ans.size(); i++) { 
                int[] step = ans.get(i); 
                if (step[0] > step[1]) { 
                    System.out.println("L " + step[1] + " " + step[0]); 
                }
                else if (step[0] < step[1]) { 
                    System.out.println("R " + step[0] + " " + step[1]);
                }
            }
        } 
        else { 
            System.out.println("NO"); 
        }

        // Try pushing left and right from right



    }   
}
