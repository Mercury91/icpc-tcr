#include <iostream>
#include <cmath>
using namespace std;
int phi(int);
int main(){
	int n;
	while((cin>>n)!=0) cout << phi(n) << endl;
	return 0;
}

int phi(int n){
	int coprime = 1;
	int primes[] = {2,3,5,7,11,13};//...
	int primessizes  = 6; //anpassen !
	//zusaetzlich Primfaktorzerlegung v. n
	for(int i =0; i<primessizes; i++){
		int anz = 0;
		while(n % primes[i] == 0){
			n = n / primes[i];
			anz ++;
			cout<<" p: "<<primes[i]<<endl;
		}
		if(anz>0)
			coprime *= ((int) pow((double) primes[i],
				(double)(anz-1))*(primes[i] - 
1));
		if(n==1) break;
	}
	if(n != 1){
		coprime *= (n - 1);
	}
	return coprime;
}
