#ifndef BST
#define BST

struct BstNode{
    int data;
    BstNode* left;
    BstNode* right;
};

void insert(BstNode* &root, int data);
bool search(BstNode* current, int data);
int findMin(BstNode* current);
int findMax(BstNode* current);
int findHeight(BstNode* current);
void levelOrder(BstNode* root);
void preOrderStack(BstNode* root);
void preOrder(BstNode* root);
void inOrder(BstNode* root);
void postOrder(BstNode* root);
bool isBinarySearchTree(BstNode* root);
bool utilForBstCheck(BstNode* root, int mini, int maxi);
BstNode* FindMin(BstNode* current);
BstNode* deleteNode(BstNode* root, int data);
bool isBalanced(BstNode* root);
BstNode* makeTreeFromSortedArray(int a[], int startA, int endA);
int findFirstAncestor(BstNode* root, int p, int q);
BstNode* mirrorTree(BstNode* root);
void inOrder2(BstNode* root);
#endif
