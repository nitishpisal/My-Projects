#include <iostream>
#include <string>
#include "linkedListHeader.h"

using namespace std;

bool isEmpty(node* head){

	if(head ==  NULL)
		return true;
	else
		return false;
}

void insertAsFirstElement(node *&head, node* &tail, int data){

node* temp = new node;
temp->data = data;
temp->next = NULL;

head = temp;
tail = temp;
//size++;

}

void insert(node *&head, node* &tail, int data){
if(isEmpty(head)){
	insertAsFirstElement(head, tail, data);
	}
else{
	node* temp = new node;
	temp->data = data;
	temp->next = NULL;
	tail->next = temp;
	tail = tail-> next; // OR tail = temp
	//size++;
	}
}

//Remove from head

void remove(node *&head, node *&tail){
if(isEmpty(head)){
cout<< "The List already Empty";	
	}
else if(head == tail){ //There is only one element in the list
	delete head;	
	head = NULL;
	tail = NULL;
	}
else{	//delete the head element
	node* temp = head;
	head = head->next;
	delete temp;
	}

}

//Remove from End

void removeLast(node* &head, node* &tail){
if(isEmpty(head)){
cout<< "The List already Empty";	
	}
else if(head == tail){ //There is only one element in the list
	delete head;	
	head = NULL;
	tail = NULL;
	}
else{	
	node* temp = head;
	while(temp->next != tail){
	temp = temp->next;
	}
	temp->next = NULL;
	tail = temp;
	}
}

/*
Get Kth element from last

*/
int getKthElementFromLast(node* &head, int k){
if(isEmpty(head))
	cout<<"The List is empty";
node* p1 = head;
node* p2 = head;
for(int i=0; i<k-1; i++){
	if (p1 == NULL)
		break;
        p1 = p1-> next;
}
 while(p1-> next != NULL){
	p1 = p1-> next;
	p2 = p2-> next;
}
 return p2-> data;

}

/*
Partition the list around x
*/

void partitionListAroundX(node* current, int x){
	node* lh = NULL;
	node* rh = NULL;
	node *n , *tl, *tr;

	while(current != NULL){
	if(current->data < x){
		if(lh == NULL){
			n = new node;
			n->data = current-> data;
			tl = n;
			lh = n;
		}
		else{ 
			n =  new node;
			n->data = current->data;
			tl->next = n;
			tl = tl->next;		
		}
	current = current->next;
	}
	else{
		if(current->data == x){
		n = new node;
		n->data = current->data;
			if(rh->next != NULL){
				n->next = rh->next;
				rh->next = n;		
			}
			else{
			rh = n;
			tr = n;
			}
		}
		else{
			if(rh == NULL){
				n = new node;
				n->data = current-> data;
				tr = n;
				rh = n;
			}
			else{
				n =  new node;
				n->data = current->data;
				tr->next = n;
				tr = tr->next;		
			}
		}
	current = current->next;
	}
    }
 tl->next = rh;
 showList(lh);

}
//Show the list of elements

void showList(node *current){
if(isEmpty(current)){
	cout<<"The List is Empty";
}
else{
	cout<<"The List contains";
	while(current !=NULL){
	cout << current->data << endl;	
	current = current->next;
	}
}
}


