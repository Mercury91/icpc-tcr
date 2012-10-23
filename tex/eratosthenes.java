static boolean[] sieve(int until) {
	boolean[] a = new boolean[until + 1];
	Arrays.fill(a, true);
	for (int i = 2; i < Math.sqrt(a.length); i++) {
		if (a[i]) {
			for (int j = i * i; j < a.length; j += i) a[j] = false;
		}
	}
	return a; // a[i] == true, iff. i is prime. a[0] is ignored
}