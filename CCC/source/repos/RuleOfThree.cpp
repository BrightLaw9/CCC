#include <iostream> 
#include <stack> 
#include <string> 
#include <map> 

using namespace std; 

map<string, string> rules;  

void search_rules(string starting) { 
    if(rules.find(starting) != rules.end()) { 
        stack.push(rules[starting]); 
    }
}

int main() { 


    // Insert rules
    for (int i = 0; i < 3; i++) { 
        string before, after; 
        cin >> before >> after; 
        rules[before] = after; 
    }

    stack<string> stack; 

    int steps, stepCount; 
    string begin, result; 

    cin >> steps >> begin >> result;  

    stack.push(begin); 

    while (stepCount < steps) { 
        search_rules(stack.top()); 
        stack.pop(); 
    } 

    while (stepCount == steps) { 
        string target_string = stack.top(); 
        search_rules(target_string);
        if (target_string == result) { 
            break; 
        } 
        stack.pop(); 
    }

    cout << "Found"; 





}