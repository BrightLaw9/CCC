public class TowerOfHanoi {

    public static void towerOfHanoi(int diskNum, char from_rod, char to_rod, char aux_rod) { 

        if (diskNum == 0) return; 

        towerOfHanoi(diskNum - 1, from_rod, aux_rod, to_rod); 
        System.out.println("Move disk " + diskNum + " from rod "
                           + from_rod + " to rod "
                           + to_rod); 
        towerOfHanoi(diskNum - 1, aux_rod, to_rod, from_rod);
    }
    
    public static void main(String[] args) { 
        int N = 3; 
        towerOfHanoi(N, 'B', 'C', 'A');
    }
}
