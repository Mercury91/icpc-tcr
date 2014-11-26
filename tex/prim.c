#define WHITE 0
#define BLACK 1
#define INF INT_MAX

int baum( int **matrix, int N){
	int i, sum = 0;
	
	int color[N];
	int dist[N];

    // markiere alle Knoten ausser 0 als unbesucht
	color[0] = BLACK;
	for( i=1; i<N; i++){
		color[i] = WHITE;
		dist[i] = INF;
	}

    // berechne den Rand
	for( i=1; i<N; i++){
        if( dist[i] > matrix[i][nextIndex]){
            dist[i] = matrix[i][nextIndex];
        }  }
	while( 1){
		int nextDist = INF, nextIndex = -1;

		/* Den naechsten Knoten waehlen */
		for(i=0; i<N; i++){
			if( color[i] != WHITE) continue;

			if( dist[i] < nextDist){
				nextDist = dist[i];
				nextIndex = i;
			}
		}

		/* Abbruchbedingung*/
		if( nextIndex == -1) break;

		/* Knoten in MST aufnehmen */
		color[nextIndex] = RED;
		sum += nextDist;		

		/* naechste kuerzeste Distanzen berechnen */
		for( i=0; i<N; i++){
            if( i == nextIndex || color[i] == BLACK ) continue;
            
            if( dist[i] > matrix[i][nextIndex]){
                dist[i] = matrix[i][nextIndex];
            }
		}
	}
	return sum;
}

