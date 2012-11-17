static boolean isPrim(int p) {
	if (p < 2 || p > 2 && p % 2 == 0) return false;
	for (int i = 3; i <= Math.sqrt(p); i += 2)
		if (p % i == 0) return false;
	return true;
}