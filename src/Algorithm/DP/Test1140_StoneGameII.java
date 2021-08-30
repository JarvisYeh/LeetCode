package Algorithm.DP;

public class Test1140_StoneGameII {
	// alice and bob get piles from beginning by turns
	// each turn one could get at most X piles
	// 1 <= X <= 2*M
	// M is updated each round, M = Math.max(M, X)
	// return the max stones the one starts first would get

	// Method 1: DP with recursion
	public int stoneGameII(int[] piles) {
		int n = piles.length;
		int[] prefix = new int[n];
		for (int i = 0; i < n; i++) prefix[i] = (i == 0 ? 0 : prefix[i - 1]) + piles[i];

		return dfs(0, 1, prefix, piles, new int[n][n]);
	}

	// DP[i][j]: the max piles one could get with piles[i:] and M = j
	private int dfs(int idx, int M, int[] prefix, int[] piles, int[][] DP) {
		int n = piles.length;
		// base case: can get all remaining piles[idx:]
		if (idx + 2*M - 1 >= n - 1) {
			return prefix[n - 1] - prefix[idx] + piles[idx];
		}

		// if the result is calculated before, directly returns it
		if (DP[idx][M] > 0) return DP[idx][M];

		// take 1...2*M piles
		for (int i = 1; i <= 2*M; i++) {
			// take i piles
			int left = prefix[idx + i - 1] - prefix[idx] + piles[idx];
			// right max = sum(piles[idx + i:]) - DP[idx + i][new M]
			int right = prefix[n - 1] - prefix[idx + i] + piles[idx + i]
					- dfs(idx + i, Math.max(M, i), prefix, piles, DP);
			DP[idx][M] = Math.max(DP[idx][M], left + right);
		}
		return DP[idx][M];
	}

	// TC: O(n^3)
	// SC: O(n^2)
	public int stoneGameII_II(int[] piles) {
		int n = piles.length;
		int[] prefix = new int[n];
		for (int i = 0; i < n; i++) prefix[i] = (i == 0 ? 0 : prefix[i - 1]) + piles[i];

		// DP[i][j]: the max piles one could get with piles[i:] and M = j
		int[][] DP = new int[n][n + 1];


		for (int i = n - 1; i >= 0; i--) {
			for (int m = 1; m <= n; m++) {
				// base case: when one could pick all remaining piles
				if (i + m*2 - 1 >= n - 1) {
					DP[i][m] = prefix[n - 1] - prefix[i] + piles[i];
				} else {
					// when M = m, one can pick 1...2*m piles
					for (int z = 1; z <= m*2; z++) {
						int picked = prefix[i + z - 1] - prefix[i] + piles[i];
						// if one pick z piles, in the next round, M = max(M, z)
						int nextM = Math.max(m, z);
						if (nextM > n) nextM = n; // since m here is up bounded by the array size, m <= n

						// right = sum(piles[i + Z:n]) - the maximum stones next one could pick
						int right = prefix[n - 1] - prefix[i + z] + piles[i + z] - DP[i + z][nextM];

						// one could pick max in this round = max among all z
						DP[i][m] = Math.max(DP[i][m], picked + right);
					}
				}
			}
		}

		// initially, M = 1
		return DP[0][1];
	}

	public static void main(String[] args) {
		Test1140_StoneGameII t = new Test1140_StoneGameII();
		System.out.println(t.stoneGameII_II(new int[]{2,7,9,4,4}));
	}
}
