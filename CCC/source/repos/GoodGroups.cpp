#include <iostream> 
#include <string> 
#include <map> 
#include <vector> 

using namespace std; 

int violated = 0;  
map<string, vector<string>> together;
map<string, vector<string>> diff; 

void searchPersonsTogether(string namePerson, string p2, string p3) { 
    for (int person = 0; person < together[namePerson].size(); person++) { 
        vector<string> withPersonPeoples = together[namePerson];  
        cout << withPersonPeoples[person];
        if (withPersonPeoples[person] != p2 && withPersonPeoples[person] != p3) {   
            violated++;  
        } 
}
}

void searchPersonsDifferent(string d1, string d2, string d3) { 
    for (int d = 0; d < diff[d1].size(); d++) { 
        vector<string> notWithPersonPeoples = diff[d1];  
        if (notWithPersonPeoples[d] == d2 || notWithPersonPeoples[d] == d3) {  
            violated++;  
        } 
}
}

int main() { 
    int same, different; 
    cin >> same; 

    for (int i = 0; i < same; i++) { 
        string name1, name2; 
        cin >> name1 >> name2; 
        if (together.find(name1) != diff.end()) { 
            together[name1].push_back(name2); 
        }
        else { 
            together[name2].push_back(name1); 
        }
    }
     
    cin >> different; 

    for (int a = 0; a < different; a++) { 
        string diff1, diff2; 
        cin >> diff1 >> diff2;
        if (diff.find(diff1) != diff.end()) { 
            diff[diff1].push_back(diff2); 
        }
        else { 
            diff[diff2].push_back(diff1); 
        } 
    }

    int g; 

    cin >> g; 

    for (int e = 0; e < g; e++) { 
        string m1, m2, m3; 
        cin >> m1 >> m2 >> m3; 
        searchPersonsTogether(m1, m2, m3); 
        searchPersonsTogether(m2, m3, m1); 
        searchPersonsTogether(m3, m1, m2); 

        searchPersonsDifferent(m1, m2, m3); 
        searchPersonsDifferent(m2, m3, m1); 
        searchPersonsDifferent(m3, m1, m2); 
        
        
    }

    cout << violated; 



    



}
