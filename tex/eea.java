static int[] eea(int a, int b) {
	int[] dst = new int[3];
	if (b == 0) {
		dst[0] = a;
		dst[1] = 1;
		return dst; // a, 1, 0
	}
	dst = eea(b, a % b);
	int tmp = dst[2];
	dst[2] = dst[1] - ((a / b) * dst[2]);
	dst[1] = tmp;
	return dst;
}