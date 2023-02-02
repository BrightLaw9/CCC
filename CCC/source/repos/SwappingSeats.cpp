#include <iostream>
#include <map>
#include <string> 
#include <vector>  
#include <typeinfo>

using namespace std; 

string n; 
map<char, map<char, int>> best; 
map<char, map<char, int>> new_best; 
map<char, int> table; 
int size; 

int count = 0; 
int divide = 0;
int it; 

void make_section(char letters, char fin_sect) { 
    for (char x : n.substr(divide, letters)) {
        table[x]++;
    }
    best[fin_sect] = table;
    table.clear(); 
    divide += letters; 
}
int cou; 
void adjust_section(int letter_count, char let_add, char let_sub) { 
    it += letter_count; 
    if (it >= size) { 
        it -= size; 
    }
    cout << "hi";  

    best[let_add][n[it]]++; 
    best[let_sub][n[it]]--;

    if (best[let_add][let_add] > new_best[let_add][let_add]) { 
       cou++;  
    }
}

int main() { 
    cin >> n; 
    size = n.length(); 
    int a, b, c; 

    for (char l : n) { 
        table[l]++; 
    }

    a = table['A']; 
    b = table['B'];
    c = table['C'];
    table.clear(); 

    make_section(a, 'A'); 
    make_section(b, 'B'); 
    if (c != 0) { 
        make_section(c, 'C');
    }
    
    // A = 0; B = 1; C = 2
    for (int i = 0; i < 3; i++) { 
        it = i; 
        adjust_section(a, 'A', 'B'); 
        adjust_section(b, 'B', 'C'); 
        adjust_section(c, 'C', 'A'); 
        if (cou > 1) { 
            new_best = best; 
        }
        cou = 0; 
         for (int v = 65; v < 68; v++) { 
    cout << "a:" << new_best[v]['A'] << "\n" << "b:" << new_best[v]['B'] << "\n" << "c:" << new_best[v]['C'] << "\n"; 
    }
    }
    divide = 0; 
    make_section(c, 'C'); 
    make_section(b, 'B'); 
    if (c != 0) { 
        make_section(a, 'A');
    }
    for (int j = 0; j < 3; j++) { 
        it = j; 
        adjust_section(c, 'C', 'B'); 
        adjust_section(b, 'B', 'A'); 
        adjust_section(a, 'A', 'C'); 
        if (cou > 1) { 
            new_best = best; 
        }
        cou = 0; 
        for (int v = 65; v < 68; v++) { 
    cout << "a:" << new_best[v]['A'] << "\n" << "b:" << new_best[v]['B'] << "\n" << "c:" << new_best[v]['C'] << "\n"; 
    } 
    }
    

    /*
    for (int v = 0; v < 3; v++) { 
    cout << "a:" << best[v]['A'] << "\n" << "b:" << best[v]['B'] << "\n" << "c:" << best[v]['C'] << "\n"; 
    }
    */


    
}