#include <iostream> 

using namespace std; 

int main() { 
    
    int n; 

    cin >> n; 

    int playersOver40 = 0; 
    bool goldTeam = true; 

    for (int i = 0; i < n; i++) { 
        
        int points, fouls, score; 
        cin >> points >> fouls; 

        score = (points * 5) - (fouls * 3); 

        if (score > 40) { 
            playersOver40++; 
        } 

        if (score <= 40) { 
            goldTeam = false; 
        }
    }

    cout << playersOver40; 

    if (goldTeam) { 
        cout << "+"; 
    }

}