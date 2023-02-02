/*
#include <iostream>
#include <vector> 
#include <utility>

using namespace std; 

/*
class Graph { 
    int V; 

    public: 
    Graph(int V) { 
    this->V = V; 
    }
    
    vector<pair<int, int>> adj; 
    void addEdge(pair<int, int> u, pair<int, int> v)
{
    adj[u].push_back(v);
    adj[v].push_back(u);
}


void printGraph()
{
    for (int v = 0; v < 5; ++v)
    {
        cout << "\n Adjacency list of vertex "
             << v << "\n head ";
        for (auto x : adj[v])
           cout << "-> " << x;
        printf("\n");
    }
}
// };




int main() { 
    //addEdge(2, 4);  
    //addEdge(3, 4);  
    //printGraph(); 
}
*/


// Online C++ compiler to run C++ program online
#include <iostream>
#include <map>
#include <vector> 
#include <utility>

using namespace std; 

map<pair<int, int>, vector<pair<int, int>>> m; 

void addEdge (pair<int, int> u, pair<int, int> v) { 
    //vector<pair<int, int>> v{  make_pair(2, 3), make_pair(4, 5), make_pair(1, 2) }; 
    m[u].push_back(v);
    m[v].push_back(u);   
} 

void printGraph() { 
    
    for(auto x : m) { 
        cout << x.first.first << ", " << x.first.second << " -> "; 
        for (int i = 0; i < x.second.size(); i++) { 
            cout << x.second[i].first << ", " << x.second[i].second << "|"; 
        }
        cout << "\n"; 
    }
    } 

int main() {
    // Write C++ code here
    addEdge(make_pair(2, 3), make_pair(4, 3)); 
    addEdge(make_pair(2, 3), make_pair(1, 5)); 
    printGraph(); 
    
    return 0; 
}