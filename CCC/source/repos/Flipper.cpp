#include <iostream> 
#include <array> 
#include <vector> 
#include <string> 
#include <utility>

using namespace std; 

int main() { 
    
    string flipOperations;  
    cin >> flipOperations; 

    int h = 0, v = 0; 

    for (int i = 0; i < flipOperations.length(); i++) {  
        if (flipOperations[i] == 'H') { 
            h++; 
        }
        else { 
            v++; 
        }
    }

    int hFlips = h % 2;  
    int vFlips = v % 2; 

    vector<vector<int>> table = {
        {1, 2},
        {3, 4}
    }; 

    if (hFlips == 1) { 
        table[0].swap(table[1]); 
    }

    if (vFlips == 1) { 
        swap(table[0][0], table[0][1]);
        swap(table[1][0], table[1][1]); 
    }

    cout << table[0][0] << " " << table[0][1] << "\n"; 
    cout << table[1][0] << " " << table[1][1];  

}