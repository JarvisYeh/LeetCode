package Algorithm.DP;

public class Test309_BestTimeToBuyAndSellStockWithCooldown {
	// Method 1: DFS
	// TLE
	// define 3 state, 0:hold, 1:sell, 2:rest(no hold)
	// hold/rest -> hold
	// sell/rest -> rest
	// hold -> sell
	// TC: O(2^n)
	// SC: O(n)
	public int maxProfitI(int[] prices) {
		int[] max = {0};
		helper(0, 2, prices, max, 0, 0);
		return max[0];
	}

	private void helper(int idx, int prevState, int[] prices, int[] max, int hold, int profit) {
		if (idx == prices.length) {
			max[0] = Math.max(profit, max[0]);
			return;
		}

		// hold
		if (prevState == 0) {
			// stay in hold
			helper(idx + 1, 0, prices, max, hold, profit);
			// sell on price[idx]
			helper(idx + 1, 1, prices, max, 0, profit + hold + prices[idx]);
		}

		// sell
		if (prevState == 1) {
			// rest on price[idx]
			helper(idx + 1, 2, prices, max, hold, profit);
		}

		// rest (no hold)
		if (prevState == 2) {
			// stay on rest
			helper(idx + 1, 2, prices, max, hold, profit);
			// bought prices[idx]
			helper(idx + 1, 0, prices, max, hold - prices[idx], profit);
		}
	}

	// Method 2: DP
	// define 3 state, 0:hold, 1:sell, 2:rest(no hold)
	// hold/rest -> hold
	// sell/rest -> rest
	// hold -> sell
	// TC: O(n)
	// SC: O(n), can be improved to O(1) since only i - 1's state are needed
	public int maxProfitII(int[] prices) {
		int n = prices.length;
		// DP[i][0] the max profit when hold some stock in day i
		// DP[i][1] the max profit when sell in day i
		// DP[i][2] the max profit when take rest at day i
		int[][] DP = new int[n][3];

		// base cases
		DP[0][0] = - prices[0];			// hold: bought in day 0
		DP[0][1] = Integer.MIN_VALUE;   // sell: invalid to sell on day 0
		DP[0][2] = 0;					// rest: no profit in day 0
		for (int i = 1; i < n; i++) {
			// hold[i] = max(hold[i - 1], rest[i - 1] - prices[i])
			DP[i][0] = Math.max(DP[i - 1][0], DP[i - 1][2] - prices[i]);
			// sell[i] = hold[i - 1] + prices[i])
			DP[i][1] = DP[i - 1][0] + prices[i];
			// rest[i] = Math.max(rest[i - 1], sell[i - 1])
			DP[i][2] = Math.max(DP[i - 1][2], DP[i - 1][1]);
		}
		return Math.max(DP[n - 1][1], DP[n - 1][2]);
	}

	public static void main(String[] args) {
		Test309_BestTimeToBuyAndSellStockWithCooldown t = new Test309_BestTimeToBuyAndSellStockWithCooldown();
		System.out.println(t.maxProfitII(new int[]{76,425,354,205,479,283,0,380,97,12,202,35,134,279,225,411,23,225,60,314,300,290,362,463,90,318,191,330,418,446,384,430,316,11,331,33,97,217,233,278,167,44,111,166,73,146,21,250,492,178,244,147,116,445,155,5,217,90,379,491,246,171,454,232,77,333,263,370,448,148,476,250,196,196,160,329,156,335,394,489,405,195,126,170,452,400,45,79,366,263,26,485,253,495,381,428,169,481}));
	}
}
