// Online C++ compiler to run C++ program online
#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> array;

void init(vector<vector<int>> arr) { 
    array = arr;
}


int query(int a, int b, int c, int d) {
    vector<vector<int>> range;
    vector<vector<int>> newRange;
    if (b-a == 0) range = {array[a]};
    else range = {array.begin() + a, array.begin()+b+1};
    if (d-c == 0) newRange = {range[c]};
    else newRange = {range.begin() + c, range.begin() + d+1};
    
    for (int i = 0; i<newRange.size(); i++) { 
        for (int j = 0; j<newRange[0].size(); j++) {
        cout << newRange[i][j];
        }
    }
    
    return 0;
}

int main() {
    init({{1, 2}, {3, 4}});
    int q = query(0, 0, 1, 1);
    return 0;
}