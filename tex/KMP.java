int match(String text, String pattern, int[] jump) {
	int j = 0;
	if (text.length() == 0)
		return -1;
	for (int i = 0; i < text.length(); i++) {
		while (j > 0 && pattern.charAt(j) != text.charAt(i))
			j = jump[j - 1];
		if (pattern.charAt(j) == text.charAt(i))
			j++;
		if (j == pattern.length())
			return i - pattern.length() + 1;
	}
	return -1;}

// Computes the jump function
int[] computeJump(String pattern) {
	int[] jump = new int[pattern.length()];
	int j = 0;
	for (int i = 1; i < pattern.length(); i++) {
		while (j > 0 && pattern.charAt(j) != pattern.charAt(i))
			j = jump[j - 1];
		if (pattern.charAt(j) == pattern.charAt(i))
			j++;
		jump[i] = j;}
	return jump;}