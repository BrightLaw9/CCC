#include <iostream> 

using namespace std; 

int main() { 
    
    int r = 0, s = 0; 
    cin >> r >> s; 

    if (r != 0 && s != 0) { 
        cout << ((r * 8) + (s * 3)) % 28; 
    }
    
    else { 
        cout << 0; 
    }
     
}