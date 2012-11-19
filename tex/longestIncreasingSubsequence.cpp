#include <vector>
using namespace std;

/** finde LIS in O(n log k)
 *a: Sequenz (in)
 *b: LIS (out)
 */
void find_lis(vector<int> &a, vector<int> &b)
{
	vector<int> p(a.size());
	int u, v;
	if (a.empty()) return;
	b.push_back(0);

	for (size_t i = 1; i < a.size(); i++)
        {
        // ist naechstes Element a[i] groesser als letztes der aktuelle LIS
		// a[b.back()], fuege es (Index) an "b" an.
		if (a[b.back()] < a[i]) {
			p[i] = b.back();
			b.push_back(i);
			continue;
		}

        // finde kleinstes El. in LIS (index in b) welches gerade groesser als a[i] ist
        // binaere suche |b|<=k  =>  O(log k)
		for (u = 0, v = b.size()-1; u < v;)
        {
			int c = (u + v) / 2;
			if (a[b[c]] < a[i]) u=c+1; else v=c;
		}

        // aktualisiere b falls neuer Wert kleiner als vorheriger kleinerer Wert
		if (a[i] < a[b[u]])
                {
			if (u > 0) p[i] = b[u-1];
			b[u] = i;
		}
	}

	for (u = b.size(), v = b.back(); u--; v = p[v]) b[u] = v;
}

#include <cstdio>
int main()
{
	int a[] = { 1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7 };
	vector<int> seq(a, a+sizeof(a)/sizeof(a[0])); // seq : Eingabesequent
	vector<int> lis;                              // lis : Index Vektor fuer LIS
    find_lis(seq, lis);
     //Sequenz ausgeben:
	for (size_t i = 0; i < lis.size(); i++)
		printf("%d ", seq[lis[i]]);
        printf("\n");

	return 0;
}
