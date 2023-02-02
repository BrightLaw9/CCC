#include <iostream> 
#include <string> 

using namespace std; 

int main() { 
    
    string instructions; 

    cin >> instructions; 

    int l = 0;   

    while (l < instructions.size()) { 
        string::size_type nextPos; 
        if (instructions.at(l) == '+') { 
            cout << " " << "tighten" << " "; 
            l++; 
            int turns = stoi(instructions.substr(l, instructions.size()), &nextPos);  
            cout << nextPos; 
            cout << turns << "\n"; 
            int turnsLength = to_string(turns).length();  
            int newPos = nextPos + turnsLength; 
            if (nextPos < instructions.size()) { 
            instructions = instructions.substr(newPos); 
            l = 0; 
            }
            else { 
                break; 
            }
        }
        else if (instructions[l] == '-') { 
            cout << " " << "loosen" << " "; 
            l++; 
            int turns = stoi(instructions.substr(l, instructions.size()), &nextPos); 
            cout << turns << "\n"; 
            int turnsLength = to_string(turns).length();  
            int newPos2 = nextPos + turnsLength; 
            if (nextPos < instructions.size()) { 
            instructions = instructions.substr(newPos2+1); 
            l = 0; 
        }
            else { 
                break; 
            }
        }
        else { 
            cout << instructions[l]; 
        }
    l++; 
    }
    }

