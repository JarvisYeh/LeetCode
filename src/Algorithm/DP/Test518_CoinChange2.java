package Algorithm.DP;

public class Test518_CoinChange2 {
	// TC: O(m*n)
	// SC: O(m*n)
	public int changeI(int amount, int[] coins) {
		int n = coins.length;
		// DP[i][j]: using [0, j] coins, how many combinations can it have to fit i amount of money
		int[][] DP = new int[amount + 1][n];

		// base case: use [0, j] coins to fit 0
		for (int j = 0; j < n; j++) DP[0][j] = 1;

		// induction rules
		// DP[i][j] = sum of
		//  1. not use coins[j] => DP[i][j - 1] ways
		//  2. use one coins[j], amount decreases coins[j] => DP[i - coins[j]][j] ways
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0) {
					DP[i][j] = i % coins[j] == 0 ? 1 : 0;
				} else {
					DP[i][j] = DP[i][j - 1] + (i - coins[j] >= 0 ? DP[i - coins[j]][j] : 0);
				}
			}
		}
		return DP[amount][n - 1];
	}


	// TC: O(m*n)
	// SC: O(n)
	public int changeII(int amount, int[] coins) {
		int n = coins.length;
		// DP[i][j]: using [0, j] coins, how many combinations can it have to fit i amount of money
		int[][] DP = new int[amount + 1][2];

		// base case: use [0, j] coins to fit 0
		for (int j = 0; j < 2; j++) DP[0][j] = 1;

		// induction rules
		// DP[i][j] = sum of
		//  1. not use coins[j] => DP[i][j - 1] ways
		//  2. use one coins[j], amount decreases coins[j] => DP[i - coins[j]][j] ways
		// since only use two columns, need to compute the DP[i][j] column by column, loop j first
		for (int j = 0; j < n; j++) {
			for (int i = 1; i <= amount; i++) {
				if (j == 0) {
					DP[i][j % 2] = (i % coins[j % 2] == 0 ? 1 : 0);
				} else {
					DP[i][j % 2] = DP[i][(j - 1) % 2];
					if (i - coins[j] >= 0) {
						DP[i][j % 2] += DP[i - coins[j]][j % 2];
					}
				}
			}
		}
		return DP[amount][(n - 1) % 2];
	}

	public static void main(String[] args) {
		Test518_CoinChange2 t = new Test518_CoinChange2();
		System.out.println(t.changeII(5, new int[]{1,2,5}));
	}
}
