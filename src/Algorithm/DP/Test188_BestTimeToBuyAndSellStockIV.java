package Algorithm.DP;

import java.util.Arrays;

public class Test188_BestTimeToBuyAndSellStockIV {
	// Method 1: DP
	// DP[i][j] is the max profit in day [0,j] with at most i trade
	// TC: O(n^3)
	// SC: O(n*k)
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		// corner case
		if (n == 0) return 0;

		// DP[i][j] is the max profit in day [0,j] with at most i trade
		// one trade is buy + sell
		int[][] DP = new int[k + 1][n];

		// base case: DP[0][j] = 0
		// DP[i>0][0] is invalid since in day 0 can not make any trade

		for (int i = 1; i <= k; i++) {
			for (int j = 1; j < n; j++) {
				// case 1: not sell in day[j]
				DP[i][j] = Math.max(DP[i][j], DP[i][j - 1]);

				// case 2: sell in day[j]
				// max is the max money hold in hand when have one stock in hand in day[0,j]
				int max = Integer.MIN_VALUE;
				// bought in day[z], sell in day[j]
				// z in range[0, j - 1]
				for (int z = 0; z < j; z++) {
					// max = max profit in day[0,z-1] - buy in day z
					if (z == 0) max = -prices[0];
					else max = Math.max(max, DP[i - 1][z - 1] - prices[z]);
				}
				// max money hold so far + day[j]'s price
				DP[i][j] = Math.max(DP[i][j], max + prices[j]);
			}
		}
		return DP[k][n - 1];
	}


	// Method 2: DP Improve TC
	// DP[i][j] is the max profit in day [0,j] with at most i trade
	// the max in method one is repeatedly calculated for different j
	// can use real time update
	// TC: O(n^2)
	// SC: O(n*k)
	public int maxProfitII(int k, int[] prices) {
		int n = prices.length;
		// corner case
		if (n == 0) return 0;

		// DP[i][j] is the max profit in day [0,j] with at most i trade
		// one trade is buy + sell
		int[][] DP = new int[k + 1][n];

		// base case: DP[0][j] = 0
		// DP[i>0][0] is invalid since in day 0 can not make any trade

		for (int i = 1; i <= k; i++) {
			// max is the max money hold in hand when have one stock in hand in day[0,j]
			// when j = 1, can only buy stock in day 0, so max = -prices[0]
			int max = -prices[0];
			for (int j = 1; j < n; j++) {
				// case 1: not sell in day[j]
				// case 2: sell in day[j]
				DP[i][j] = Math.max(DP[i][j - 1], max + prices[j]);

				// update max with buy stock in day j
				max = Math.max(max, DP[i - 1][j - 1] - prices[j]);
			}
		}
		return DP[k][n - 1];
	}


	// Method 3: DP with state (need to deal with corner cases, invalid [i][j])
	// TC: O(n^2)
	// SC: O(2*n*k)
	public int maxProfitIII(int k, int[] prices) {
		int n = prices.length;
		if (n == 0) return 0;

		// hold/unHold[i][j] is the max profit one could make in day [0,i]
		// with j complete transactions (buy + sold)
		// and in day i, one hold/unHold some stock
		int[][] hold = new int[n][k + 1];
		int[][] unHold = new int[n][k + 1];
		// base case: buy/no buy the stock on day 1
		// unHold[0][0] = 0
		// other values invalid
		Arrays.fill(hold[0], Integer.MIN_VALUE / 2);
		Arrays.fill(unHold[0], Integer.MIN_VALUE / 2);
		hold[0][0] = -prices[0];
		unHold[0][0] = 0;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= k; j++) {
				// hold today = max(hold yesterday + skip today, unhold yesterday + buy today)
				hold[i][j] = Math.max(hold[i - 1][j], unHold[i - 1][j] - prices[i]);
				// unhold today = max(unhold yesterday + skip today, hold yesterday + sell today)
				if (j == 0) {
					unHold[i][j] = unHold[i - 1][j];
					continue;
				}
				unHold[i][j] = Math.max(unHold[i - 1][j], hold[i - 1][j - 1] + prices[i]);
			}
		}

		int max = 0;
		for (int i = 0; i <= k; i++) {
			max = Math.max(unHold[n - 1][i], max);
		}
		return max;
	}

	public static void main(String[] args) {
		Test188_BestTimeToBuyAndSellStockIV t = new Test188_BestTimeToBuyAndSellStockIV();
		System.out.println(t.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
	}
}
