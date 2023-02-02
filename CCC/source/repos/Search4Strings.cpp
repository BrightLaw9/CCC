#include <iostream> 
#include <algorithm>
#include <string> 
#include <vector> 

using namespace std; 

string n, h;

int main() {  
    
    vector<string> permutations; 
    cin >> n >> h; 

    int ned_len = n.length(); 
    int hay_len = h.length(); 
    
    if (hay_len < ned_len) { 
        cout << 0; 
        return 0; 
    }
    
    //Permutations of n
    sort(n.begin(), n.end());

    bool finished = false; 

    while (!finished) { 
        cout << n << "\n"; 
        permutations.push_back(n); 
        int t; 
        for (t = ned_len - 2; t >= 0; t--) { 
            if (n[t] < n[t + 1]) { 
                break; 
        }
        }
        //cout << t; 
        if (t == -1) { 
            finished = true; 
        }
        else { 
            int ceil = t + 1; 
            for (int i = t; i < ned_len; i++) { 
                if (n[i] > n[t] && n[i] < n[ceil]) { 
                    ceil = i; 
                }
            }
            
            
            swap(n[t], n[ceil]); 
            sort(n.begin()+t+1, n.end());
             
            }
        }

    
    // Finds possible groupings in h to check with n 
    int permu_found = 0; 

    for (auto x : permutations) { 
        for (int a = 0; a < h.length(); a++) { 
            if (h.substr(a, ned_len) == x) { 
                permu_found += 1; 
                break; 
            }
        }
        
    }
    cout << permu_found; 
} 
