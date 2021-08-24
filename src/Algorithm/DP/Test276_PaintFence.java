package Algorithm.DP;

public class Test276_PaintFence {
	// TC: O(n)
	// SC: O(n)
	// SC acn be improved to O(1), since only 2 previous state is needed
	public int numWaysI(int n, int k) {
		if (n == 0) return 0;
		if (n == 1) return k;
		if (n == 2) return k*k;
		// number of ways to paint i fences with k colors
		int[] DP = new int[n + 1];
		DP[1] = k;
		DP[2] = k*k;
		for (int i = 3; i <= n; i++) {
			// to paint i-the fence
			// can paint different color with (i - 1)-th fence => DP[i - 1]*(k - 1)
			// if (i - 1)-th fence is different as (i - 2)-th fence
			//      can paint same color with (i - 1)-th fence
			//      (i - 1) different with (i - 2) => DP[i - 2]*(k - 1)
			DP[i] = (k - 1)*(DP[i - 1] + DP[i - 2]);
		}
		return DP[n];
	}

	// SC improved to O(1)
	// TC: O(n)
	// SC: O(1)
	public int numWaysII(int n, int k) {
		if (n == 0) return 0;
		if (n == 1) return k;
		if (n == 2) return k*k;
		// number of ways to paint i fences with k colors
		int[] DP = new int[3];
		DP[1] = k;
		DP[2] = k*k;
		for (int i = 3; i <= n; i++) {
			DP[i % 3] = (k - 1)*(DP[(i - 1) % 3] + DP[(i - 2) % 3]);
		}
		return DP[n % 3];
	}

}
