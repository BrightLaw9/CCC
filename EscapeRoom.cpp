#include <iostream>
#include <map>
#include <vector>  
#include <queue>
#include <utility>

using namespace std; 

map<pair<int, int>, vector<pair<int, int>>> map_graph; 

void addEdge (pair<int, int> u, pair<int, int> v) { 
    map_graph[u].push_back(v);  
} 

int m, n; 
vector<vector<int>> grid; 
int x, y, num, prev_r, prev_c; 
queue<pair<int, int>> qu; 
map<pair<int, int>, bool> visited; 

bool check_visited(pair<int, int> pos) { 
    return visited[pos];  
}

void add_to_graph() { 
    pair<int, int> cell = make_pair(x, y); 
    if (!check_visited(cell)) { 
        addEdge(make_pair(prev_r, prev_c), cell);  
        visited[cell] = true; 
        qu.push(cell);   
    }
}

void search(int val) { 
    for (int i = 0; i < m; i++) { 
        for (int a = 0; a < n; a++) { 
            if (grid[i][a] == val) { 
                x = i+1; 
                y = a+1; 
                add_to_graph();  
            }
        }
    }
}  

int main() { 

    cin >> m >> n; 
    for (int i = 0; i < m; i++) { 
         vector<int> vec; 
         for (int a = 0; a < n; a++) { 
             int b; 
             cin >> b; 
             vec.push_back(b); 
        }
        grid.push_back(vec); 
     }  

    pair<int, int> start = make_pair(m, n); 
    qu.push(start); 
    prev_r = m, prev_c = n; 
    num = m * n; 
    visited[start] = true; 
    search(num); 

    while (!qu.empty()) { 

        prev_r = qu.front().first; 
        prev_c = qu.front().second; 
        qu.pop(); 
        num = prev_r * prev_c;
        
        if (num == 1) {  
            break; 
        }
        
        search(num);  
    }

    if (num == 1) {  
        cout << "yes"; 
    }
    
    else { 
        cout << "no"; 
    }

    }



     
