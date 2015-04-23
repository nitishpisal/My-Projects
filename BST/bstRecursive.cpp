#include "bst.h"
#include <cstddef>
#include <iostream>
using namespace std;

bool search(BstNode* current, int data){
    if(current == NULL) //the tree is empty
        return false;
    else if(current->data == data) //root is having the data
        return true;
    else if(data < current->data) // go to left subtree
        return search(current->left, data);
    else
        return search(current->right, data);
}


void insert(BstNode* &root, int data){
    BstNode* n = new BstNode();
     n->data = data;
     n->left = NULL;
     n->right = NULL;
    if(root == NULL){
        root = n;
        }
    else{
        BstNode* current = root;
        while(current != NULL){
            if(data <= current->data){
                if(current->left == NULL){
                    current->left = n;
                    cout<<"Inserted "<< data <<" to left of" << current->data<<endl;
                    break;
                }
                else
                    current = current->left;
            }
            else{
                if(current->right == NULL){
                    current->right = n;
                    cout<<"Inserted "<< data <<" to right of" << current->data<<endl;
                    break;
                }
                else
                    current = current->right;
            }

        }
    }

}
