#include <iostream>
#include "linkedListHeader.h"
#include <math.h>
using namespace std;

void addLists(node* current1, node* current2){
	
	int temp = 0;
	int count = 0;
	while(current1 != NULL && current2 != NULL){
		if(current1 == NULL){
			temp = temp + pow(10, count) * current2->data;
			count++;
			}
		if(current2 == NULL){
			temp = temp + pow(10, count) * current1->data;
			count++;
			}
		else{
			temp = temp + pow(10, count) * current1->data + pow(10, count) * current2->data;
			count++;
			}
	current1 = current1->next; current2 = current2->next;		
	}
cout<< "The output is : "<< temp;
	
}

char menu(){
	char choice;
	cout << "1. Add List 1 "<< endl;
	cout << "2. Add List 2 "<< endl;
	cout << "3. Add Lists"<< endl;
	cout << "4. Show the list "<< endl;
	cout << "5. Exit "<< endl;
	
	cin >>  choice;
	return choice;
}

int main(){

node* head = NULL;
node* tail = NULL;
node* head2 = NULL;
node* tail2 = NULL;
char choice; int k;
int data;
do{
	choice = menu();
	switch(choice){
	case '1':
		cout<< "Enter a number : ";
		cin>> data;
		insert(head, tail, data);
		break;
	case '2':
		cout<< "Enter a number : ";
		cin>> data;
		insert(head2, tail2, data);
		break;
	case '3':
		addLists(head, head2);
	case '4':
		cout<< "List number : ";
		cin >> k;		
		if (k == 1)
			showList(head);
		else
			showList(head2);
		break;
	case '5':
		return 0;
	}
} while(choice !='5');

return 0;
}
