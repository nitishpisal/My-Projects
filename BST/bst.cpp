#include "bst.h"
#include <cstddef>
#include <iostream>
#include <queue>
#include <stack>
#include <limits.h>
#include <math.h>
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

int findMin(BstNode* current){
    if(current->left == NULL)
        return current->data;
    else
        return findMin(current->left);
}

BstNode* FindMin(BstNode* current){
    if(current->left == NULL)
        return current;
    else
        return FindMin(current->left);
}

int findMax(BstNode* current){
    if(current->right == NULL)
        return current->data;
    else
        return findMax(current->right);

}

int findHeight(BstNode* current){
    if(current == NULL)// leaf node
        return -1;
    return max(findHeight(current->left), findHeight(current->right)) +1;

}


void levelOrder(BstNode* root){
    if(root == NULL) //check if there is no node present
        return;
    queue<BstNode*> Q;
    Q.push(root);

    while(!Q.empty()){
        BstNode* current =  Q.front();
        cout<< current->data<< " ";
        if (current->left != NULL)
            Q.push(current->left);
        if (current->right != NULL)
            Q.push(current->right);
        Q.pop();
    }

}

void preOrderStack(BstNode* root){
    if(root == NULL)
        return;
    stack<BstNode*> S;
    S.push(root);

    while(!S.empty()){
        BstNode* current = S.top();
        S.pop();
        cout << current-> data <<" ";
        if(current->right != NULL)
            S.push(current->right);
        if(current->left != NULL)
            S.push(current->left);
    }

}

//Recursive approach

void preOrder(BstNode* root){
    if (root == NULL)
        return;
    cout << root->data << " ";
    preOrder(root->left);
    preOrder(root->right);


}

//Inorder recursive

void inOrder(BstNode* root){
    if(root == NULL)
        return;
    inOrder(root->left);
    cout<< root->data << " ";
    inOrder(root->right);
}


// How to do it without recursion?
void inOrder2(BstNode* root){
    if (root == NULL)
        return;
    BstNode* temp;
    BstNode* rightRoot;

    stack<BstNode*> s;
    s.push(root);

    while(root->left != NULL){
        s.push(root->left);
        root = root->left;

    }
    while(!s.empty()){
        cout << s.top()->data << " ";
        temp = s.top();
        rightRoot = temp->right;
        if(temp->right != NULL){
            s.pop();
            s.push(temp->right);
            while(rightRoot->left != NULL){
                s.push(rightRoot->left);
                    rightRoot = rightRoot->left;
                }
        }
        else{
            s.pop();
        }
    }
}


//Post Order Recursive

void postOrder(BstNode* root){
    if(root == NULL)
        return;
    postOrder(root->left);
    postOrder(root->right);
    cout<< root->data << " ";
}

//Binary search tree check utility

bool isBinarySearchTree(BstNode* root){ // just a small function definition for user exp
    return utilForBstCheck(root, INT_MIN, INT_MAX);

}

bool utilForBstCheck(BstNode* root, int mini, int maxi){

    if(root == NULL)
        return true;
    if(root->data > mini && root-> data <= maxi && utilForBstCheck(root->left, mini, root->data)
     && utilForBstCheck(root->right, root->data, maxi))
        return true;
    else
        return false;

}

/*
Delete a Node.
There will be three cases:
1. Node is the leaf node -- just delete it.
2. Node has only one child, just copy the new node to current erase the link of next node
3. Node has both the children-- find the min in the right subtree and copy this to current node repear the process for right subtree.

*/

BstNode* deleteNode(BstNode* root, int data){
    if(root == NULL)
        return root;
    else if(data < root->data)
        root->left = deleteNode(root->left, data);
    else if(data > root->data)
        root->right = deleteNode(root->right, data);
    //If data is equal, i.e the element to be deleted is found
    else {
    //the leaf to be deleted in leaf
        if(root->left == NULL && root->right == NULL){
             root = NULL;
             delete root;
        }
        //There is only one child
        //Left child
        else if(root->left != NULL){
            BstNode* temp = root;
            root = root->left;
            delete temp;
        }
        //right child
        else if(root->right != NULL){
            BstNode* temp = root;
            root = root->right;
            delete temp;
        }
        //Node has two children
        else{
            BstNode* temp = FindMin(root->right); // select the right minimum
            root->data = temp->data;
            root->right = deleteNode(root->right, temp->data);
        }

    }
    return root;

}
/*
check if the difference between the heigt of left and right subtree is not more than 1

*/
bool isBalanced(BstNode* root){
    int lh; int rh;
    if(root == NULL)
        return 1;
    lh = findHeight(root->left);
    rh = findHeight(root->right);
    if(abs(lh-rh) <=1 && isBalanced(root->left) && isBalanced(root->right))
        return 1;
    else
        return 0;

}

/*
Making a BST from a Inorder list

*/

BstNode* makeTreeFromSortedArray(int a[], int startA, int endA){
    if(startA > endA)
        return NULL;
    int mid = (endA + startA) /2;
    BstNode* n = new BstNode();
    n->data = a[mid];
    n->left = makeTreeFromSortedArray(a, startA, mid-1);
    n->right = makeTreeFromSortedArray(a, mid+1, endA);
    return n;

}

/*
Find first common Ancestor
*/

int findFirstAncestor(BstNode* root, int p, int q){
    if(root == NULL)
        return root->data;
    if(p<root->data && q<root->data){
        if (root->left->data != p && root->left->data != q)
            findFirstAncestor(root->left, p, q);
        else
            return root->data;
    }
    else if(p>root->data && q>root->data){
        if (root->right->data != p && root->right->data != q)
            findFirstAncestor(root->right, p, q);
        else
            return root->data;
    }
    else if((p<root->data && q > root->data)|| (p>root->data && q<root->data))
        return root->data;
  //  return root->data;
}

/*
Mirror of a binary tree
*/

BstNode* mirrorTree(BstNode* root){
    if (root == NULL)
        return NULL;

    BstNode* newNode = new BstNode();
    newNode->data = root->data;
    newNode->left = mirrorTree(root->right);
    newNode->right = mirrorTree(root->left);
    return newNode;

}
