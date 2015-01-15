#include <iostream>
#include <string>
#include "linkedListHeader.h"
using namespace std;

char menu(){
	char choice;
	cout << "1. Add Item "<< endl;
	cout << "2. Remove Item "<< endl;
	cout << "3. Remove Last Item "<< endl;
	cout << "4. Show the list "<< endl;
	cout << "5. Get Kth Element "<< endl;
	cout << "6. Partiton Around X "<< endl;
	cout << "7. Exit "<< endl;

	cin >>  choice;
	return choice;
}

int main(){

node* head = NULL;
node* tail = NULL;
cout<<"head "<<head<<endl;
cout<<"&  head "<<&head<<endl;
char choice;
int data;
string ip;
int ele2, k=0;

do{
	choice = menu();
	switch(choice){
	case '1':
		cout<< "Enter a number : ";
		cin>> data;
		insert(head, tail, data);
		break;
	case '2':
		remove(head, tail);
		break;
	case '3':
		removeLast(head, tail);
		break;
	case '4':
		showList(head);
		break;
	case '5':
		
		cout<< "Enter the value for K : ";
		//getline(cin, ip);
		//k = stoi(ip);
		cin>>k;
		ele2 = getKthElementFromLast(head, k);
		cout << "The Kth element from last is : "<<ele2<<endl;
		break;
	
	case '6':
		cout<< "Enter the value for partition : ";
		cin >> k;
		partitionListAroundX(head, k);
		break;

        case '7':
		return 0;
	default :
		cout<<"Invalid choice, Please try again";
		break;
	}

} while(choice !='7');

return 0;
}
