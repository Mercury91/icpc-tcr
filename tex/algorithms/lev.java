public static int lev(String a, String b) { 
		int n = a.length();	int m = b.length();
        int[][] distance = new int[n + 1][m + 1];        
 
        for (int i = 0; i <= n; i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= m; j++)                                 
            distance[0][j] = j;                                                  
 
        for (int i = 1; i <= n; i++)                                 
            for (int j = 1; j <= m; j++)                             
                distance[i][j] = min(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1));
        return distance[n][m];                           
    }    
