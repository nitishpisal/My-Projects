#include <iostream>
#include "stackUsingLL.h"

using namespace std;

void push(node* &top,int data){
	if(top == NULL){ // there is no element in the stack
		node* n = new node;
		n->data = data;
		n->next = NULL;
		top = n;
	}
	else{ // some element is already present
		node* n = new node;
		n->data = data;
		n->next = top;
		top = n;
	}
}

int pop(node* &top){
int result = 0;	
	if(top == NULL){
		cout<<"There is no element in the stack";
		return -1;
	}
	else{
		result = top->data;
		node * temp = top;
		top = top->next;
		delete temp;
	}
	return result;
}
