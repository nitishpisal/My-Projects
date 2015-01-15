#ifndef STACKUSINGLL
#define STACKUSINGLL

//Implementing stack using LinkedList
struct node{
int data;
node* next;
};

void push(node* &top,int data);

int pop(node* &top);

#endif
