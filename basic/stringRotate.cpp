#include <iostream>
#include <string>

using namespace std;

bool rotationCheck(string s1, string s2){
s1 = s1+s1;
if(s1.find(s2) != string::npos)
	return true;
else{
	return false;	
	}
}

int main(){

bool yes = rotationCheck("waterbot", "botwater");

cout<< " String is "<< yes<< endl;

return 0;
}
