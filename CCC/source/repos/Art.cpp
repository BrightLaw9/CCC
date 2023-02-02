// Use vector and algorthim for sorting (insert iterators vector.begin(), vector.end())

#include <iostream> 
#include <string>
#include <sstream>
#include <math.h>

using namespace std; 

int main() { 
    
    int n; 
    cin >> n; 
    
    int greatestX = 0, leastX = 100, greatestY = 0, leastY = 100; 

    for(int i = 0; i < n; i++) { 
        
        string coordinate_pair; 
        
        cin >> coordinate_pair; 
        /*
        stringstream pair(coordinate_pair); 

        string storage; 
        */ 
        int x = stoi(coordinate_pair.substr(0, coordinate_pair.find(","))); 
        int y = stoi(coordinate_pair.substr(coordinate_pair.find(",")+1, (coordinate_pair.size()-1)));  

        //int count = 0; 

        // while (getline(pair, storage, ',')) { 

        
    
        
        
        if (x > greatestX) { 
            greatestX = x; 
        }

        if (x < leastX) { 
            leastX = x; 
        }

        if (y > greatestY) { 
            greatestY = y; 
        }

        if (y < leastY) { 
            leastY = y; 
        }
        

        
    }

    cout << leastX-1 << "," << leastY-1 << "\n"; 
    cout << greatestX+1 << "," << greatestY+1 << "\n"; 

} 
