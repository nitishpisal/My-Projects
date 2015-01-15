#ifndef LINKEDLISTHEADER
#define LINKEDLISTHEADER


struct node{
int data;
node* next;
};

bool isEmpty(node* head);
void insertAsFirstElement(node *&head, node* &tail, int data);
void insert(node *&head, node* &tail, int data);
void remove(node *&head, node *&tail);
void showList(node *current);
void removeLast(node* &head, node* &tail);
void partitionListAroundX(node* current, int x);
int getKthElementFromLast(node* &head, int k);


#endif
