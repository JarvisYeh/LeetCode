package Algorithm.DP;

public class Test123_BestTimeToBuyAndSellStockIII {
	// finding the max profit in doing at most 2 transactions

	// Method 1: DFS
	// TLE
	// TC: O(2^n), one node have at most 2 children
	// SC: O(n) for recursion tree level
	public int maxProfitI(int[] prices) {
		int[] max = {0};
		helper(0, 0, prices, 0, 0, max, 4);
		return max[0];
	}

	// state = 0 for no hold, 1 for hold
	private void helper(int idx, int state, int[] prices, int hold, int profit, int[] max, int remain) {
		// base case 1
		if (remain == 0) {
			max[0] = Math.max(max[0], profit);
			return;
		}

		// base case 2
		if (idx == prices.length) {
			max[0] = Math.max(max[0], profit);
			return;
		}

		// skip idx-th day
		helper(idx + 1, state, prices, hold, profit, max, remain);

		// if no hold, can choose to buy
		if (state == 0) {
			helper(idx + 1, 1, prices, hold - prices[idx], profit, max, remain - 1);
		}

		// if hold, can choose to sell
		if (state == 1) {
			helper(idx + 1, 0, prices, 0, profit + hold + prices[idx], max, remain - 1);
		}
	}

	// Method 2: divide and conquer
	// TLE
	// divide array into left and right
	// find the max1 = max profit in doing 1 trade in left
	// 			max2 = max profit in doing 1 trade in right
	// 			max3 = max profit in doing 1 trade in whole array
	// res = max(max3, max1 + max2)
	// TC: O(n^3)
	// SC: O(1)
	public int maxProfitII(int[] prices) {
		int n = prices.length;
		// [0, n] => left = [0, i] right = [i + 1, n - 1]
		int max = 0;
		for (int i = 1; i < n - 1; i++) {
			int max1 = findMax(prices, 0, i);
			int max2 = findMax(prices, i + 1, n - 1);
			max = Math.max(max, max1 + max2);
		}
		return Math.max(max, findMax(prices, 0, n - 1));
	}

	// return the max profit with doing 1 transaction in prices[l, r]
	private int findMax(int[] prices, int l, int r) {
		int min = Integer.MAX_VALUE;
		int profit = 0;
		for (int i = l; i <= r; i++) {
			if (prices[i] < min) {
				min = prices[i];
			} else {
				profit = Math.max(profit, prices[i] - min);
			}
		}
		return profit;
	}

	// Method 3: improved divide and conquer with DP
	// profitL[i] represents the maximum profit in [0, i] with one transaction
	// profitR[i] represents the maximum profit in [i, n - 1] with one transaction
	// TC: O(n)
	// SC: O(n)
	public int maxProfitIII(int[] prices) {
		int n = prices.length;
		int[] profitL = new int[n], profitR = new int[n];

		// min is the min price in [0, i - 1]
		int min = prices[0];
		profitL[0] = 0;
		for (int i = 1; i < n; i++) {
			if (prices[i] < min) {
				min = prices[i];
				profitL[i] = profitL[i - 1];
			} else {
				profitL[i] = Math.max(profitL[i - 1], prices[i] - min);
			}
		}

		// max is the max price in [i + 1, n - 1]
		int max = prices[n - 1];
		profitR[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			if (prices[i] > max) {
				max = prices[i];
				profitR[i] = profitR[i + 1];
			} else {
				profitR[i] = Math.max(profitR[i + 1], max - prices[i]);
			}
		}

		int maxProfit = 0;
		// [0, n - 1] => left:[0, i] U right:[i + 1, n - 1]
		for (int i = 1; i < n - 1; i++) {
			maxProfit = Math.max(maxProfit, profitL[i] + profitR[i + 1]);
		}
		// return the max of making one transaction || two transactions
		return Math.max(maxProfit, profitL[n - 1]);
	}

	// Method 4: direct DP
	// TLE
	// DP[i][j] is the max profit when have at most i transactions in day [0:j]
	// TC: O(n^2*k)
	// SC: O(n^2)
	public int maxProfitIV(int[] prices) {
		int n = prices.length;
		// DP[i][j] is the max profit when have at most i transactions in day [0:j]
		int[][] DP = new int[3][n];
		// base case DP[0][i] = 0

		for (int i = 1; i < n; i++) {
			for (int k = 1; k <= 2; k++) {
				// not sell in day i, profit same as make at most k trade in day[0: i - 1]
				DP[k][i] = DP[k][i - 1];

				// the max money hold in hand before day[i]
				int max = Integer.MIN_VALUE;
				max = Math.max(max, -prices[0]);
				// divide [0 : i] into [0 : j - 1] U [j:i]
				for (int j = 0; j < i; j++) {
					// case 1: bought on day[0], sell on day[i]
					if (j == 0) {
						max = Math.max(max, -prices[0]);
						continue;
					}
					// case 2: bought in day[j], sell in day[i]
					// previous max profit - bought in day[j] money
					max = Math.max(max, DP[k - 1][j - 1] - prices[j]);
				}
				DP[k][i] = Math.max(DP[k][i], prices[i] + max);
			}
		}
		return DP[2][n - 1];
	}

	// Method 5: DP improved TC
	// DP[i][j] is the max profit when have at most i transactions in day [0,j]
	// TC: O(k*n)
	// SC: O(k*n)
	public int maxProfitV(int[] prices) {
		int n = prices.length;
		// DP[i][j] is the max profit when have at most i transactions in day [0,j]
		int[][] DP = new int[3][n];
		// base case DP[0][i] = 0

		// for each k, max is the max money in hand when have one stock in hand in day[0,i - 1]
		// max{DP[k - 1][j] - prices[j]} when j = 1...i - 1
		// so can update max round by round
		for (int k = 1; k <= 2; k++) {
			int max = DP[k][0] - prices[0]; // = - prices[0]
			for (int i = 1; i < n; i++) {
				// not sell in day i, profit same as make at most k trade in day[0,i - 1]
				// or sell in day i, profit = previous money hold in hand(can be negative) + prices[i]
				DP[k][i] = Math.max(DP[k][i - 1], max + prices[i]);
				// update max with bought in day i
				max = Math.max(max, DP[i - 1][k - 1] - prices[i]);
			}
		}
		return DP[2][n - 1];
	}


	public static void main(String[] args) {
		Test123_BestTimeToBuyAndSellStockIII t = new Test123_BestTimeToBuyAndSellStockIII();
		System.out.println(t.maxProfitIV(new int[]{1,2,3,4,5}));
	}
}
