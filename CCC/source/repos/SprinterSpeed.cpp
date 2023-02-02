// Online C++ compiler to run C++ program online
#include <iostream>
#include <map> 
#include <cmath> 

using namespace std; 

int main() {
    // Write C++ code here
    int n; 
	cin >> n; 
	map<int, int> m; 

	for (int i = 0; i < n; i++) {
		int t, x; 
		cin >> t >> x; 
		m[t] = x; 
	}

	double high_s; 
	int pre_t = 0; 
	int pre_d = 0; 

	for (auto x : m) {
	    if (x.first == 0) { 
	        pre_d = x.second; 
	        continue; 
	    } 
		double s = (abs(1.0*(x.second - pre_d))) / (x.first - pre_t); 
		if (s > high_s) {
			high_s = s; 
		}
		pre_t = x.first;
		pre_d = x.second;
	}
	cout << high_s; 

    return 0;
}