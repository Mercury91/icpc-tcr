import java.util.Scanner;
public class PermsAndSequ {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while ((n = sc.nextInt()) != 0) {
			int k = sc.nextInt();
			Sequences(n, k);
			Permutations(n);
		}
	}

	public static void Sequences(int n, int k) {
		int[] x = new int[k];
		for (int i = 0; i < k; i++)
			x[i] = 1;
		Print(x);
		while (true) {
			boolean lastX = true;
			for (int i = 0; i < k; i++)
				if (x[i] != n) {
					lastX = false;
					break;
				}
			if (lastX)
				break;
			int p = k - 1;
			while (!(x[p] < n))
				p--;
			x[p] = x[p] + 1;
			for (int i = p + 1; i < k; i++)
				x[i] = 1;
			Print(x);
		}
	}
	public static void Permutations(int n) {
		int[] x = new int[n];
		for (int i = 0; i < n; i++)
			x[i] = i + 1;
		Print(x);
		while (true) {
			boolean lastX = true;
			for (int i = 0; i < n - 1; i++)
				if (x[i] < x[i + 1]) {
					lastX = false;
					break;
				}
			if (lastX)	break;
			int k = n - 1 - 1;
			while (x[k] > x[k + 1])	k--;
			int t = k + 1;
			while (t < (n - 1) && x[t + 1] > x[k])
				t++;
			int tmp = x[k];
			x[k] = x[t];
			x[t] = tmp;
			// reverse x[k+1] ... x[n-1]
			for (int i = 0; i <= ((n - 1) - (k + 1)) / 2; i++) {
				tmp = x[k + 1 + i];
				x[k + 1 + i] = x[n - 1 - i];
				x[n - 1 - i] = tmp;
			}
			Print(x);
		}
	}
	public static void Print(int[] x) {
		for (int i = 0; i < x.length; i++)
			System.out.print(x[i] + " ");
		System.out.println("");
	}

}
