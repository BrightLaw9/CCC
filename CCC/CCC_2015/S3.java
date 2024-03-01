package CCC_2015;

import java.util.Arrays;
import java.util.Scanner;


// MESSED UP
public class S3 {

    static class Node {

        public int num;
        public Node left;
        public Node right;  

        public Node(int num, Node left, Node right) { 
            this.num = num; 
            this.left = left; 
            this.right = right;  
        }

    }

    // Binary Search Tree implementation - needed as a 
    
    public static void insert(Node tree, int num, Node prev, boolean isLeft) { 
        if (tree == null) { 
            if (isLeft) { 
                prev.left = new Node(num, null, null);
            }
            else { 
                prev.right = new Node(num, null, null); 
            }
        }
        else { 
            if (num < tree.num) { 
                insert(tree.left, num, tree, true);
            }
            else insert(tree.right, num, tree, false);
        } 
    }

    public static int findLargestAndRemove(Node tree, int topCap) { 
        if (tree == null) return -1; 

        Node track = tree; 
        
        if (track.num > topCap && tree.left == null) return -1; 

        if (track.left == null && track.right == null && tree.num <= topCap) {
            int num = tree.num; 
            tree.num = -1; 
            return num; 
        }

        if (track.right == null && tree.num <= topCap) { 
            int num = tree.num; 
            tree.num = tree.left.num; 
            tree.left = tree.left.left; 
            return num; 
        }
        
        if ((track.right.num > topCap && track.num <= topCap)) { 
            int num = tree.num; 
            tree.num = tree.right.num; 
            tree.right = tree.right.right; 
            return num; 
        }
        else if (track.right == null || track.num > topCap) {
            if (track.left.right != null && track.left.right.right == null) { 
                if (track.left.right != null && track.left.right.num < topCap) { 
                    int temp = track.left.right.num; 
                    track.left.right = null; 
                    return temp; 
                }
                else { 
                    int temp_num = track.left.num; 
                    track.left = track.left.left;
                    return temp_num; 
                }
            }
            else if (track.left.num <= topCap) { 
                int min = track.left.num; 
                track.left = track.left.left; 
                return min; 
            }
            track = tree.left; 
            if (track.right == null) { 
                int num = track.left.num; 
                track.left = track.left.left; 
                return num; 
            }
        }

        while (track.right.right != null) { 
            // if (track.right.num <= topCap && track.right.right) { 
            //     // Pop the track.right (parent to track.right.right as the largest before topCap)
            //     if (track.right.left == null) { 
            //         // Only one parent on right
            //         int tempRight = track.right.num; 
            //         track.right = track.right.right; 
            //         return tempRight; 
            //     }
            //     else { 
            //         // Two parent situation -- search right side for greatest as replacement
            //         Node track2 = track; 
            //         while (track2.right.right != null) { 
            //             track2 = track2.right; 
            //         }
            //         int minNode = track2.left.num; 
            //         track2.left = null; 
            //         track.right = new Node(minNode, track.right.left, track.right.right); 
            //         return minNode; 
            //     } 
            //}
            //else 
            if (track.right.right.num <= topCap) track = track.right; 
            else break; 
        }
        // if (track.left.left != null) { 
        //     int tempLeft = track.left.left.num; 
        //     track.left.left = null; 
        //     return tempLeft; 
        // }
       if (track.right.left == null) { 
                    // Only one parent on right
                    int tempRight = track.right.num; 
                    track.right = track.right.right; 
                    return tempRight; 
                }
                else { 
                    // Two parent situation -- search right side for greatest as replacement
                    Node track2 = track; 
                    while (track2.right.right != null) { 
                        track2 = track2.right; 
                    }
                    int minNode = track2.left.num; 
                    track2.left = null; 
                    track.right = new Node(minNode, track.right.left, track.right.right); 
                    return minNode; 
                } 
    }

    public static void buildBST(Node parent, int[] arr, boolean isLeft) { 
        if (arr.length == 1) { 
            if (isLeft) {
                insert(parent.left, arr[0], parent, isLeft);
                return; 
            }
            else { 
                insert(parent.right, arr[0], parent, isLeft);
                return; 
            }
        }
        else { 
            int mid = (int) arr.length / 2; 
            if (isLeft) insert(parent.left, mid, parent, true); 
            else insert(parent.right, mid, parent, false); 
            if (mid > 0) 
            buildBST(parent.left, Arrays.copyOfRange(arr, 0, mid), true);
            if (mid+1 < arr.length)
            buildBST(parent.right, Arrays.copyOfRange(arr, mid+1, arr.length), false);
        }
    }
         
    public static void main(String[] args) { 

        Scanner in = new Scanner(System.in); 

        int gates = Integer.parseInt(in.nextLine()); 
        int planes = Integer.parseInt(in.nextLine()); 

        int[] allGates = new int[gates+1];
        
        for (int i = 1; i <= gates; i++) { 
            allGates[i] = i; 
        }

        int mid = (int) ((gates / 2) + (gates % 2 == 0 ? 0 : 1)); 

        Node root = new Node(mid, null, null); 
        
        // buildBST(root, Arrays.copyOfRange(allGates, 0, mid), true); 
        // buildBST(root, Arrays.copyOfRange(allGates, mid+1, gates+1), false); 

        for (int i = mid-1; i > 0; i--) { 
            insert(root.left, i, root, true); 
        }

        for (int j = mid+1; j <= gates; j++) { 
            insert(root.right, j, root, false); 
        }

        //System.out.println(root.right); 


        // Must account for each plane as they arrive, therefore can't sort first
        // Use binary search to search for the free plane gates so you can efficiently find empty in O(P log G)

        int numPlanesLanded = 0; 

        for (int i = 0; i < planes; i++) { 
            int planeNum = Integer.parseInt(in.nextLine()); 
            //System.out.println(
            numPlanesLanded++; 
            if (findLargestAndRemove(root, planeNum) == -1) break; 
        }

        System.out.println(numPlanesLanded);

    }
}
