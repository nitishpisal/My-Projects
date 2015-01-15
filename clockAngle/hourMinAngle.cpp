#include <stdio.h>
#include <iostream>
using namespace std;

float calc (int h, int m)
{
float diff = 0.0;

float hrDegree = (h*60 + m)*0.5;
float minDegree = m*6;

diff = minDegree - hrDegree;

float angle = min(diff, 360-diff);

return angle;
}

float min(float diff1, float diff2)
{
return (diff1>diff2)? diff2:diff1;
}

int main()
{
int h=0;
int m=0;

cout << "please enter hour value :";
cin >> h;
cout <<"Please enter minute value :";
cin >> m;

float degree = calc(h, m);
printf("Algle is %f ",degree);
return 0;
}
