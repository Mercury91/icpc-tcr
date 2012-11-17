static int[][] mem = new int[MAX_N][(MAX_N + 1) / 2];
static int binoCo(int n, int k) {
	if (k < 0 || k > n) return 0;
	if (2 * k > n) binoCo(n, n - k);
	if (mem[n][k] > 0) return mem[n][k];
	int ret = 1;
	for (int i = 1; i <= k; i++) {
		ret *= n - k + i;
		ret /= i;
		mem[n][i] = ret;
	}
	return ret;
}