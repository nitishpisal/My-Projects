#include <iostream>
#include <string>
using namespace std;

struct node{
int data;
node* next;
};

bool isEmpty(node* head);
char menu();
void insertAsFirstElement(node *&head, node* &tail, int data);
void insert(node *&head, node* &tail, int data);
void remove(node *&head, node *&tail);
void showList(node *current);

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
