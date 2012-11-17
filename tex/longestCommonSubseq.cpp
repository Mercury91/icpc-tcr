#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
using namespace std;

#define MAX(a,b) (a > b) ? a : b

string X,Y;
vector< vector<int> > c(101, vector<int>(101,0));
int m,n,ctr;

int LCS()
{
     m = X.length(),n=Y.length();

    c.resize(m+1);
	for(int i = 0; i<n+1; i++) {
		c[i].resize(n+1);
		c[i][0] = 0;
	}

     int i,j;

     for (i=0;i<=m;i++)
         for (j=0;j<=n;j++)
             c[i][j]=0;

     for (i=1;i<=m;i++)
         for (j=1;j<=n;j++)
         {
             if (X[i-1]==Y[j-1])
                c[i][j]=c[i-1][j-1]+1;
             else
                 c[i][j]=max(c[i][j-1],c[i-1][j]);
         }
     return c[m][n];
}
/** Print a songle LCS */
void printLCS(int i,int j)
{
    if (i==0 || j==0)
       return;
    if (X[i-1]==Y[j-1])
    {
       printLCS(i-1,j-1);
       cout<<X[i-1];
    }
    else if (c[i][j]==c[i-1][j])
         printLCS(i-1,j);
    else
        printLCS(i,j-1);
}

int main()
{
    while(cin>>X>>Y)
    {
	cout << "Length: " << LCS() << endl;
        printLCS(m,n);
        cout<<endl ;
    }
}
