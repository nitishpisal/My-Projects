#include <iostream>
#include "linkedListHeader.h"

using namespace std;

void reverse(node * &head){
	node* current =  head;
	node* result = NULL;
	node* nextNode = NULL;
	while(current != NULL){
		nextNode = current->next;
		current->next = result;
		result = current;
		current = nextNode;
	}
	head = result;
	showList(head);	

}

char menu(){
	char choice;
	cout << "1. Add List"<< endl;
	cout << "2. Reverse"<< endl;
	cout << "3. Exit"<< endl;
	cin >>  choice;
	return choice;
}

int main(){
node* head = NULL;
node* tail = NULL;
int data;
char choice;
do{
	choice = menu();
	switch(choice){
	case '1':
		cout<< "Enter a number : ";
		cin>> data;
		insert(head, tail, data);
		break;
	case '2':
		reverse(head);
		break;
	case '3':
		return 0;
	}
}while(choice !='3');

return 0;
}
