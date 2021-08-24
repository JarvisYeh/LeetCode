package Algorithm.DP;

public class Test322_CoinChange {
	public int coinChange(int[] coins, int amount) {
		int n = coins.length;
		// DP[i] represents the fewest coins needed for amount i
		int[] DP = new int[amount + 1];

		// base case
		DP[0] = 0;

		for (int i = 1; i <= amount; i++) {
			DP[i] = Integer.MAX_VALUE;
			// use coins[0:n] and record the minimal one
			for (int j = 0; j < n; j++) {
				if (i - coins[j] >= 0 && DP[i - coins[j]] != Integer.MAX_VALUE) {
					DP[i] = Math.min(DP[i], 1 + DP[i - coins[j]]);
				}
			}
		}

		return DP[amount] == Integer.MAX_VALUE ? -1 : DP[amount];
	}
}
