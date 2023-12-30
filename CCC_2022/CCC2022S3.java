package CCC_2022;

import java.util.Scanner;

public class CCC2022S3 {
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in); 

        String[] read = in.nextLine().split(" "); 
        int n = Integer.parseInt(read[0]); 
        int m = Integer.parseInt(read[1]); 
        int k = Integer.parseInt(read[2]); 

        int curGoodSamples = 0; 

        int[] res = new int[n]; 

        int checkpoint = 0; 

        for (int curIndex = 1; curIndex <= n; curIndex++) { 
            if ((curGoodSamples + curIndex) <= k && curIndex <= m && curIndex * (n-curIndex) <= k-curGoodSamples) { 
                res[curIndex-1] = curIndex;
                curGoodSamples += curIndex; 
            }
            else { 
                checkpoint = curIndex - 1;
                break; 
            }
        }

        //System.out.println("Checkpoint: " + checkpoint);

        curGoodSamples += n - checkpoint + 1;

        for (int index = checkpoint; index < n; index++) { 
            if (curGoodSamples < k) { 
                //for (int j = index-1; j >= 0; j--) {
                for (int j = m; j > 0; j--) { 
                    //if (j+1 < res.length && res[j+1] == res[j]) continue; 
                    if (k-curGoodSamples - j + 1 >= 0) { 
                        res[index] = res[index - j];
                        curGoodSamples += j - 1;
                        break; 
                    }
                }
            }
            else { 
                res[index] = res[index-1];
            }
        }

        //System.out.print(curGoodSamples); 

        if (curGoodSamples != k) { 
            System.out.print(-1); 
        }
        else {
            for (int num : res) { 
                System.out.print(num + " "); 
            }
        }
    }
}
