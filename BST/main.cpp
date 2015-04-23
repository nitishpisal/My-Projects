#include <iostream>
#include "bst.h"

using namespace std;

int main()
{
    BstNode* root = NULL;
/*
    insert(root, 22);
    insert(root, 20);
    insert(root, 19);
    insert(root, 21);
    insert(root, 32);
    insert(root, 30);
    insert(root, 40);
  //  insert(root, 24);
  //  insert(root, 28);
  //  insert(root, 29);
  */

  int arr[] = {19,20,21,22,24,28,29,30,32,40};
  int sizeA = sizeof(arr)/sizeof(arr[0]);
  root = makeTreeFromSortedArray(arr, 0, sizeA-1);

    bool value = search(root, 24);
    cout << value <<endl;

    cout<< findMin(root)<<endl;
    cout<< findMax(root)<<endl;
    cout<<"Height of tree is "<<findHeight(root)<<endl;
    cout<< "Level Order :"; levelOrder(root); cout<<endl;
    cout<< "Pre Order Stack :" ;preOrderStack(root); cout<<endl;
    cout<< "Pre Order Recursion :" ;preOrder(root); cout<<endl;
    cout<< "In Order Recursion :" ;inOrder(root); cout<<endl;
   // deleteNode(root, 28);
    cout<< "In Order Stack :" ;inOrder2(root); cout<<endl;
    cout<< "Post Order Recursion :" ;postOrder(root); cout<<endl;
    if(isBinarySearchTree(root))
        cout<<"True "<<endl;
    else
        cout<<"False "<<endl;
    if(isBalanced(root))
        cout<<"Tree is balanced "<<endl;
    else
        cout<<"Tree is not balanced "<<endl;

    cout<<"First Ancestor of 19 and 21 "<<findFirstAncestor(root, 21, 20) <<endl;
    BstNode* root2 = mirrorTree(root);
    cout<< "In Order After mirror:" ;inOrder(root2); cout<<endl;


    return 0;
}
